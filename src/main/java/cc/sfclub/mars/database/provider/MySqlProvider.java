package cc.sfclub.mars.database.provider;

import cc.sfclub.mars.utility.Tuple3;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySqlProvider implements IDatabaseProvider {
    private final DataSource db;

    public MySqlProvider(DataSource ds) {
        db = ds;
    }

    @Override
    public String getType(Field field) {
        String type = "varchar";
        Class<?> type1 = field.getType();
        if (type1.isAssignableFrom(Integer.class)) {
            type = "int";
        } else if (type1.isAssignableFrom(Short.class)) {
            type = "smallint";
        } else if (type1.isAssignableFrom(Long.class)) {
            type = "bigint";
        } else if (type1.isAssignableFrom(Float.class)) {
            type = "float";
        } else if (type1.isAssignableFrom(Double.class)) {
            type = "double";
        } else if (type1.isAssignableFrom(Byte.class)) {
            type = "tinyint";
        } else if (type1.isAssignableFrom(Boolean.class)) {
            type = "tinyint(1)";
        } else if (type1.isAssignableFrom(Character.class)) {
            type = "char";
        } else if (type1.isAssignableFrom(String.class)) {
            type = "text";
        }

        return type;
    }

    @Override
    public void createTable(List<Tuple3<String, String, Boolean>> columns, String table, boolean dropIfExists) {
        try {
            Connection connection = db.getConnection();
            connection.setAutoCommit(false);
            try {
                if (dropIfExists) {
                    String sql = "drop table `" + table + "` if exists;";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    if (!ps.execute()) {
                        throw new SQLException("Bad execute result! SQL: " + sql);
                    }
                }

                String fields = "";
                for (Tuple3<String, String, Boolean> c :
                        columns) {
                    if (c.getRight()) {
                        fields += "`" + c.getLeft() + "` " + c.getMiddle() + " auto_increment, ";
                    } else {
                        fields += "`" + c.getLeft() + "` " + c.getMiddle() + ", ";
                    }
                }
                String sql = "create table `" + table + "` (" + fields + ") engine=InnoDB default charset=utf8mb4;";
                PreparedStatement ps = connection.prepareStatement(sql);
                if (!ps.execute()) {
                    throw new SQLException("Bad execute result! SQL: " + sql);
                }

                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
                connection.rollback();
                connection.setAutoCommit(true);
                throw ex;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
