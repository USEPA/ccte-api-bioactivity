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

import gov.epa.ccte.api.bioactivity.domain.SearchAssay;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SearchAssayRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> pgsqldb = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager entityManager;
    @Autowired private SearchAssayRepository repository;

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
        assertThat(repository.findAll().size()).isEqualTo(7);
    }
    
    @Test
    void testSearchAssayEqual() {
    	assertThat(repository.findBySearchValueOrderByIdAsc("CCTE_GLTED_hIYD", SearchAssay.class).size()).isEqualTo(1);
    	
    	assertThat(repository.findBySearchValueOrderByIdAsc("IYD", SearchAssay.class).size()).isEqualTo(1);
    }
    
    // *********************** SearchAssayService - Contains and StartWith *************************************
    
    @Test
    void testSearchValuesToModifiedValues() {
    	String searchValue = " CCTE_GLTED_hIYD ";
    	String modifiedValue = searchValue.toUpperCase().trim();
    	String removeSpaces = modifiedValue.replaceAll(" ", "");
		assertThat(repository.findAllByModifiedValueIn(java.util.List.of(searchValue,removeSpaces), SearchAssay.class).size()).isEqualTo(1);

    }
    
    @Test
    void testSearchAssayContain() {
		assertThat(repository.findByModifiedValueContainsOrderByIdAscSearchValue("IYD", org.springframework.data.domain.Limit.of(10), SearchAssay.class).size()).isEqualTo(2);
		
		assertThat(repository.findByModifiedValueContainsOrderByIdAscSearchValue("GLTED", org.springframework.data.domain.Limit.of(10), SearchAssay.class).size()).isEqualTo(1);
	}
    
    @Test
	void testSearchAssayStartWith() {
		assertThat(repository.findByModifiedValueStartingWithOrderByIdAsc("CCTE_GLTED", org.springframework.data.domain.Limit.of(10), SearchAssay.class).size()).isEqualTo(1);
		
		assertThat(repository.findByModifiedValueStartingWithOrderByIdAsc("IYD", org.springframework.data.domain.Limit.of(10), SearchAssay.class).size()).isEqualTo(1);
	}
}
