package cc.sfclub.mars.database.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface IDatabaseProvider {
    String getDriver();

    IDatabaseProvider getProvider();

    Connection getConnection();

    Statement prepare(String sql);

    Statement insertArgs(Statement statement, Object... args);

    int execute(Statement statement);

    ResultSet executeWithQuery(Statement statement);
}
