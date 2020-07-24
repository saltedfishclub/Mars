package cc.sfclub.mars.database.provider;

import cc.sfclub.mars.utility.Tuple3;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

public interface IDatabaseProvider {
    String getType(Field field);

    void createTable(List<Tuple3<String, String, Boolean>> columns, String table, boolean dropIfExists) throws SQLException;
}
