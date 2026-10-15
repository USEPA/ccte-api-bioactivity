package gov.epa.ccte.api.bioactivity.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AssayAggRepositoryTest {

    private static final String POSTGRES_ONLY_MESSAGE =
            "Skipping PostgreSQL JSON query test on non-PostgreSQL database";

    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager entityManager;
    @Autowired private AssayAggRepository repository;

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
        assertThat(repository.findAll().size()).isEqualTo(2);
    }
    
    @Test
    void testAssayAggByAeid() { 
    	assertThat(repository.findByAeid(111)).isNotNull();
        
    	assertThat(repository.findByAeid(3032)).isNotNull();
    }
    
    // *********************** AssayAgg - End *************************************
    // *********************** CCD Assay Annotation Projections - Start *************************************
    
    @Test
    void testCCDAssayAnnotationByAeid() { 
    	assertThat(repository.findByAeid(111)).isNotNull();
        
    	assertThat(repository.findByAeid(3032)).isNotNull();
    }
    
    @Test
    void testCCDCitationByAeid() {
        TestDatabaseAssumptions.assumePostgreSql(dataSource, POSTGRES_ONLY_MESSAGE);
        assertThat(repository.findCitationsByAeid(111)).isNotNull();

        assertThat(repository.findCitationsByAeid(3032)).isNotNull();
    }

    @Test
    void testCCDGeneByAeid() {
        TestDatabaseAssumptions.assumePostgreSql(dataSource, POSTGRES_ONLY_MESSAGE);
        assertThat(repository.findGeneByAeid(111)).isNotNull();

        assertThat(repository.findGeneByAeid(3032)).isNotNull();
    }

    @Test
    void testCCDTcplByAeid() {
        TestDatabaseAssumptions.assumePostgreSql(dataSource, POSTGRES_ONLY_MESSAGE);

        assertThat(repository.findTcplByAeid(111)).isNotNull();

        assertThat(repository.findTcplByAeid(3032)).isNotNull();
    }

    @Test
    void testCCDReagentByAeid() {
        TestDatabaseAssumptions.assumePostgreSql(dataSource, POSTGRES_ONLY_MESSAGE);
        assertThat(repository.findReagentByAeid(111)).isNotNull();

        assertThat(repository.findReagentByAeid(3032)).isNotNull();
    }

}