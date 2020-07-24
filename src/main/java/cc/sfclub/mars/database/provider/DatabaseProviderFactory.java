package cc.sfclub.mars.database.provider;

import javax.sql.DataSource;

public class DatabaseProviderFactory {
    public static IDatabaseProvider getProvider(DataSource ds) {
        return new MySqlProvider(ds);
    }
}
