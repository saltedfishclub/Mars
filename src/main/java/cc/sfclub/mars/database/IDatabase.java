package cc.sfclub.mars.database;

import cc.sfclub.mars.database.query.IQuery;
import cc.sfclub.mars.database.record.IRecord;
import cc.sfclub.mars.database.result.IResult;

public interface IDatabase {
    boolean create(Class<?> table, boolean overrideOnDifferent);

    boolean drop(Class<?> table);

    IRecord<?> fetch(IQuery query);

    IResult insert(Class<?> record, boolean overrideOnExists);

    IResult update(Class<?> record);

    IResult delete(Class<?> record);

    void saveChanges();
}
