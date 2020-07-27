package cc.sfclub.mars.database;

import cc.sfclub.mars.database.api.annotation.Entity;
import cc.sfclub.mars.database.api.annotation.Id;
import cc.sfclub.mars.database.api.annotation.Name;
import cc.sfclub.mars.database.provider.DatabaseProviderFactory;
import cc.sfclub.mars.database.provider.IDatabaseProvider;
import cc.sfclub.mars.database.query.IQuery;
import cc.sfclub.mars.database.record.IRecord;
import cc.sfclub.mars.database.record.Record;
import cc.sfclub.mars.database.result.IResult;
import cc.sfclub.mars.database.result.Result;
import cc.sfclub.mars.utility.Tuple3;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mars implements IDatabase {
    private final DataSource db;
    private IDatabaseProvider provider;

    public Mars(DataSource ds) {
        db = ds;
        provider = DatabaseProviderFactory.getProvider(db);
    }

    @Override
    public boolean create(Class<?> clazz, String name) {
        List<Tuple3<String, String, Boolean>> columns = new ArrayList<>();

        try {
            if (clazz.getAnnotation(Entity.class) == null) {
                throw new SQLException("Class is not an entity.");
            }

            for (Field f :
                    clazz.getFields()) {
                String name1 = f.getName();
                Name name2 = f.getAnnotation(Name.class);
                if (name2 != null) {
                    name1 = name2.value();
                }

                boolean autoIncrease = false;
                if (f.getAnnotation(Id.class) != null) {
                    autoIncrease = true;
                }

                String type = provider.getType(f);

                columns.add(new Tuple3<>(name1, type, autoIncrease));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            provider.createTable(columns, name, false);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean drop(String table) {
        return false;
    }

    @Override
    public ResultSet fetch(IQuery<?> query) {
        return query.query(provider);
    }

    @Override
    public IResult insert(Object record, String table, boolean overrideOnExists) {
        return null;
    }

    @Override
    public IResult update(Object record, String table) {
        return null;
    }

    @Override
    public IResult delete(Object record, String table) {
        return null;
    }

    @Override
    public void saveChanges() {
        throw new NotImplementedException();
    }
}
