package SpaceTravel.DatabaseServices;

import SpaceTravel.Utils.PropertyUtil;
import org.flywaydb.core.Flyway;

public class DatabaseInitService {

    public void initDb() {

        String filename = "hibernate.properties";

        String dbUrl = PropertyUtil.load(filename).getProperty("hibernate.connection.url");
        String user = PropertyUtil.load(filename).getProperty("hibernate.connection.user");
        String pass = PropertyUtil.load(filename).getProperty("hibernate.connection.password");

        Flyway flyway = Flyway
                .configure()
                .dataSource(dbUrl, user, pass)
                .load();

        flyway.migrate();
    }
}

