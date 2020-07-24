package cc.sfclub.mars.database.query;

import java.util.List;
import java.util.Map;

public interface IQuery<T> {
    IQuery<T> select(String... columns);

    IQuery<T> selectAs(Map<String, String> columns);

    IQuery<T> selectAll();

    IQuery<T> from(Class<T> clazz, String table);

    IQuery<T> where(String condition, String... args);

    IQuery<T> orderBy(String column, boolean asc);

    IQuery<T> limit(int count);

    List<T> toList();

    T firstOrDefault();

    String getSql();
}
