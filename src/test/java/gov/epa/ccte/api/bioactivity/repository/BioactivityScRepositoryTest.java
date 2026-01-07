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

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BioactivityScRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> pgsqldb = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager entityManager;
    @Autowired private BioactivityScRepository repository;

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
    void testCCDSingleConcByAeid() {
    	assertThat(repository.getSingleConcDataByAeid(111)).size().isEqualTo(1);
        
    	assertThat(repository.getSingleConcDataByAeid(3032)).size().isEqualTo(2);
    }
    
    @Test
    void testSingleConcByAeidAndChid() {
    	assertThat(repository.findByAeidAndChidRep(111, (short) 1)).size().isEqualTo(1);
        
    	assertThat(repository.findByAeidAndChidRep(3032, (short) 0)).size().isEqualTo(7);
    }
}
