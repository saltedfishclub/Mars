package cc.sfclub.mars.database.query;

import cc.sfclub.mars.database.provider.IDatabaseProvider;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.util.Map;

public class Query<T> implements IQuery<T> {
    private String selectString = "";
    private String fromTable = "";
    private String whereString = "";
    private String[] whereArgs = null;
    private int limit = -1;

    @Override
    public IQuery<T> select(String... columns) {
        selectString = "(";
        for (int i = 0; i < columns.length; i++) {
            selectString += columns[i];
            if (i < columns.length - 1) {
                selectString += ", ";
            } else {
                selectString += ")";
            }
        }
        return this;
    }

    @Override
    public IQuery<T> selectAs(Map<String, String> columns) {
        throw new NotImplementedException();
    }

    @Override
    public IQuery<T> selectAll() {
        selectString = "*";
        return this;
    }

    @Override
    public IQuery<T> from(String table) {
        fromTable = "`" + table + "`";
        return this;
    }

    @Override
    public IQuery<T> where(String condition, String... args) {
        whereString += condition;
        whereArgs = args;
        return this;
    }

    @Override
    public IQuery<T> limit(int count) {
        limit = count;
        return this;
    }

    @Override
    public String[] args() {
        return whereArgs;
    }

    @Override
    public String getSql() {
        return "select " + selectString + " from " + fromTable +
                " where " + whereString +
                (limit == -1 ? "" : "limit " + limit) + ";";
    }

    @Override
    public ResultSet query(IDatabaseProvider provider) {
        return provider.query(getSql(), args());
    }
}
