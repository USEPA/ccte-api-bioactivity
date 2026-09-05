package gov.epa.ccte.api.bioactivity.repository;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.EMBEDDED;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = "spring.jpa.properties.hibernate.connection.provider_disables_autocommit=false")
@ActiveProfiles("test")
@AutoConfigureEmbeddedDatabase(type = POSTGRES, provider = EMBEDDED)
public class BioactivityScRepositoryTest {

    private static final String POSTGRES_ONLY_MESSAGE =
            "Skipping PostgreSQL JSON/entity mapping test on non-PostgreSQL database";

    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager entityManager;
    @Autowired private BioactivityScRepository repository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(repository).isNotNull();
    }


    // Now test data loaded or not
    @Test
    void testDataLoaded() {
        TestDatabaseAssumptions.assumePostgreSql(dataSource, POSTGRES_ONLY_MESSAGE);
        assertThat(repository.findAll().size()).isEqualTo(10);
    }

    @Test
    void testCCDSingleConcByAeid() {
        TestDatabaseAssumptions.assumePostgreSql(dataSource, POSTGRES_ONLY_MESSAGE);
    	assertThat(repository.getSingleConcDataByAeid(111)).size().isEqualTo(1);

    	assertThat(repository.getSingleConcDataByAeid(3032)).size().isEqualTo(2);
    }

    @Test
    void testSingleConcByAeidAndChid() {
        TestDatabaseAssumptions.assumePostgreSql(dataSource, POSTGRES_ONLY_MESSAGE);
    	assertThat(repository.findByAeidAndChidRep(111, (short) 1)).size().isEqualTo(1);

    	assertThat(repository.findByAeidAndChidRep(3032, (short) 0)).size().isEqualTo(7);
    }

}