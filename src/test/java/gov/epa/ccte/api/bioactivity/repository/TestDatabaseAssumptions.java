package gov.epa.ccte.api.bioactivity.repository;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

final class TestDatabaseAssumptions {

    private TestDatabaseAssumptions() {
        // Utility class
    }

    static void assumePostgreSql(DataSource dataSource, String message) {
        assumeTrue(isPostgreSql(dataSource), message);
    }

    static boolean isPostgreSql(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            String productName = connection.getMetaData().getDatabaseProductName();
            return productName != null && productName.toLowerCase().contains("postgresql");
        } catch (SQLException ex) {
            return false;
        }
    }
}
