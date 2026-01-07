package gov.epa.ccte.api.bioactivity.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import gov.epa.ccte.api.bioactivity.domain.AnalyticalQC;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.*;

@Testcontainers
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AnalyticalQCRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> pgsqldb = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager entityManager;
    @Autowired private AnalyticalQCRepository repository;

    @Test
    void connectionEstablished(){
        assertThat(pgsqldb.isCreated()).isTrue();
        assertThat(pgsqldb.isRunning()).isTrue();
    }

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
        assertThat(repository.findAll().size()).isEqualTo(10);
    }
    
    @Test
    void testAnalyticalQCByDtxsid() { 
    	assertThat(repository.findByDtxsid("DTXSID7020182", AnalyticalQC.class)).size().isEqualTo(5);
        
    	assertThat(repository.findByDtxsid("DTXSID9020112", AnalyticalQC.class)).size().isEqualTo(5);
    }
}
