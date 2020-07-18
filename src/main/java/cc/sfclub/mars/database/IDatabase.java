package cc.sfclub.mars.database;

import cc.sfclub.mars.database.query.IQuery;
import cc.sfclub.mars.database.record.IRecord;
import cc.sfclub.mars.database.result.IResult;

public interface IDatabase {
    boolean create(Class<?> table, boolean overrideOnDifferent);

    boolean drop(Class<?> table);

    IRecord<?> fetch(IQuery<?> query);

    IResult insert(Object record, boolean overrideOnExists);

    IResult update(Object record);

    IResult delete(Object record);

    void saveChanges();
}
