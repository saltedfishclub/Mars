package cc.sfclub.mars.database;

import cc.sfclub.mars.database.query.IQuery;
import cc.sfclub.mars.database.record.IRecord;
import cc.sfclub.mars.database.result.IResult;

import java.sql.ResultSet;

public interface IDatabase {
    boolean create(Class<?> table, String name);

    boolean drop(String table);

    ResultSet fetch(IQuery<?> query);

    IResult insert(Object record, String table, boolean overrideOnExists);

    IResult update(Object record, String table);

    IResult delete(Object record, String table);

    void saveChanges();
}
