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
public class AOPRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager entityManager;
    @Autowired private AOPRepository repository;

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
    void testAnalyticalQCByAeid() { 
    	assertThat(repository.findByToxcastAeid(63)).size().isEqualTo(1);
        
    	assertThat(repository.findByToxcastAeid(2167)).size().isEqualTo(1);
    }
    
    @Test
    void testAnalyticalQCByEntrezGeneId() { 
    	assertThat(repository.findByEntrezGeneId(196)).size().isEqualTo(2);
        
    	assertThat(repository.findByEntrezGeneId(367)).size().isEqualTo(3);
    }
    
    @Test
    void testAnalyticalQCByEventNumber() { 
    	assertThat(repository.findByEventNumber(25)).size().isEqualTo(3);
        
    	assertThat(repository.findByEventNumber(52)).size().isEqualTo(2);
    }
}
