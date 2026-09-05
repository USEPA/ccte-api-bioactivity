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
import static org.assertj.core.api.Assertions.*;

@DataJpaTest(properties = "spring.jpa.properties.hibernate.connection.provider_disables_autocommit=false")
@ActiveProfiles("test")
@AutoConfigureEmbeddedDatabase(type = POSTGRES, provider = EMBEDDED)
public class AnalyticalQCRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager entityManager;
    @Autowired private AnalyticalQCRepository repository;

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
        assertThat(repository.count()).isEqualTo(10);
    }
    
    @Test
    void testAnalyticalQCByDtxsid() { 
    	assertThat(repository.findByDtxsid("DTXSID7020182", AnalyticalQCTestProjection.class)).size().isEqualTo(5);
        
    	assertThat(repository.findByDtxsid("DTXSID9020112", AnalyticalQCTestProjection.class)).size().isEqualTo(5);
    }

    interface AnalyticalQCTestProjection {
        Long getAnalyticalQcId();
        String getDtxsid();
    }
}
