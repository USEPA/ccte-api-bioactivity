package gov.epa.ccte.api.bioactivity.repository;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import gov.epa.ccte.api.bioactivity.projection.assay.*;

import javax.sql.DataSource;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.EMBEDDED;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest(properties = "spring.jpa.properties.hibernate.connection.provider_disables_autocommit=false")
@ActiveProfiles("test")
@AutoConfigureEmbeddedDatabase(type = POSTGRES, provider = EMBEDDED)
public class AssayAnnotationRepositoryTest {

    private static final String POSTGRES_ONLY_MESSAGE =
            "Skipping PostgreSQL JSON/entity mapping test on non-PostgreSQL database";

    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager entityManager;
    @Autowired private AssayAnnotationRepository repository;

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
        assertThat(repository.findAll().size()).isEqualTo(3);
    }
    

    @Test
    void testAssayAnnotationByAeid(){
        TestDatabaseAssumptions.assumePostgreSql(dataSource, POSTGRES_ONLY_MESSAGE);
        assertThat(repository.findByAeid(111, AssayAll.class)).isNotNull();

        assertThat(repository.findByAeid(3032, AssayAll.class)).isNotNull();
    }

    @Test
    void testAeidByEndpoint(){
        TestDatabaseAssumptions.assumePostgreSql(dataSource, POSTGRES_ONLY_MESSAGE);
        assertThat(repository.findAeidByAssayComponentEndpointName("ATG_TCF_b_cat_CIS")).isEqualTo(111L);

        assertThat(repository.findAeidByAssayComponentEndpointName("CCTE_GLTED_hIYD")).isEqualTo(3032L);
    }

    @Test
    void testAssayAnnotationByBatchAeid() {
        TestDatabaseAssumptions.assumePostgreSql(dataSource, POSTGRES_ONLY_MESSAGE);
    	String[] aeids = {"111","3032"};
    	assertThat(repository.findByAeidInOrderByAeidAsc(aeids, AssayAll.class)).size().isEqualTo(2);

    }

    @Test
    void testGetAllAssayAnnotations(){
        TestDatabaseAssumptions.assumePostgreSql(dataSource, POSTGRES_ONLY_MESSAGE);
        assertThat(repository.findBy(AssayAll.class)).size().isEqualTo(3);
    }

    @Test
    void testGetAllAssayAnnotationsCCD(){
        TestDatabaseAssumptions.assumePostgreSql(dataSource, POSTGRES_ONLY_MESSAGE);
        assertThat(repository.findAssayAnnotations(CcdAssayList.class)).size().isEqualTo(6);

    }

    @Test
    void testAssayEndpointsListByGene(){
        TestDatabaseAssumptions.assumePostgreSql(dataSource, POSTGRES_ONLY_MESSAGE);
        assertThat(repository.findAssayEndpointsListByGene("TCF7")).size().isEqualTo(1);
    }
}