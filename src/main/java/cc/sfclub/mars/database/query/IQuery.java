package cc.sfclub.mars.database.query;

import cc.sfclub.mars.database.provider.IDatabaseProvider;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface IQuery<T> {
    IQuery<T> select(String... columns);

    IQuery<T> selectAs(Map<String, String> columns);

    IQuery<T> selectAll();

    IQuery<T> from(String table);

    IQuery<T> where(String condition, String... args);

    //IQuery<T> orderBy(String column, boolean asc);

    IQuery<T> limit(int count);

    String[] args();

    String getSql();

    ResultSet query(IDatabaseProvider provider);
}
