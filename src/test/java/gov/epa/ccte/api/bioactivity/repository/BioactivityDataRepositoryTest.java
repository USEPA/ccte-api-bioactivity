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

import gov.epa.ccte.api.bioactivity.projection.data.BioactivityDataAll;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BioactivityDataRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> pgsqldb = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager entityManager;
    @Autowired private BioactivityDataRepository repository;

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
    void testBioacitivtyDatabyDtxsid() {
    	assertThat(repository.findByDtxsid("DTXSID7020182", BioactivityDataAll.class)).size().isEqualTo(5);
        
    	assertThat(repository.findByDtxsid("DTXSID9020112", BioactivityDataAll.class)).size().isEqualTo(5);
    }
    
    @Test
    void testBioacitivtyDatabyBatchDtxsid() {
    	String[] dtxsids = {"DTXSID7020182","DTXSID9020112"};
    	assertThat(repository.findByDtxsidInOrderByDtxsidAsc(dtxsids, BioactivityDataAll.class)).size().isEqualTo(10);
        
    }

    @Test
    void testBioacitivtyDataByM4id() {
    	assertThat(repository.findByM4id(7827405, BioactivityDataAll.class)).size().isEqualTo(1);
    	
    	assertThat(repository.findByM4id(7826967, BioactivityDataAll.class)).size().isEqualTo(1);
    }
    
    @Test
    void testBioacitivtyDataByBatchM4id() {
    	String[] m4ids = {"7827405","7826967"};
    	assertThat(repository.findByM4idInOrderByM4idAsc(m4ids, BioactivityDataAll.class)).size().isEqualTo(2);
        
    }

    @Test
    void testBioacitivtyDataByAeid() {
    	assertThat(repository.findByAeid(3032, BioactivityDataAll.class)).size().isEqualTo(1);
    	
    	assertThat(repository.findByAeid(2532, BioactivityDataAll.class)).size().isEqualTo(1);
    }
    
    @Test
    void testBioacitivtyDataByBatchAeid() {
    	String[] aeids = {"3032","2532"};
    	assertThat(repository.findByAeidInOrderByAeidAsc(aeids, BioactivityDataAll.class)).size().isEqualTo(2);
        
    }

    @Test
    void testBioacitivtyDataBySpid() {
    	assertThat(repository.findBySpid("EPAPLT0023I15", BioactivityDataAll.class)).size().isEqualTo(3);
    	
    	assertThat(repository.findBySpid("TP0001667B07", BioactivityDataAll.class)).size().isEqualTo(2);
    }
    
    @Test
    void testBioacitivtyDataByBatchSpid() {
    	String[] spids = {"EPAPLT0023I15","TP0001667B07"};
    	assertThat(repository.findBySpidInOrderBySpidAsc(spids, BioactivityDataAll.class)).size().isEqualTo(5);
        
    }
    
    @Test
    void testAedDatabyDtxsid() {
    	assertThat(repository.findAedDataByDtxsid("DTXSID7020182")).size().isEqualTo(2);
        
    	assertThat(repository.findAedDataByDtxsid("DTXSID9020112")).size().isEqualTo(1);
    }
    
    @Test
    void testAedDatabyBatchDtxsid() {
    	String[] dtxsids = {"DTXSID7020182","DTXSID9020112"};
    	assertThat(repository.findAedDataByDtxsidIn(dtxsids)).size().isEqualTo(1);
        
    }
    
    @Test
    void testBioacitivtyDatabyDtxsidAndTissue() {
    	assertThat(repository.findByDtxsidAndTissue("DTXSID7020182", "bone")).size().isEqualTo(1);
        
    }
    
    @Test
    void testToxcastSummaryPlotbyDtxsid() {
    	assertThat(repository.findToxcastSummaryPlotByDtxsid("DTXSID7020182")).size().isEqualTo(5);
        
    	assertThat(repository.findToxcastSummaryPlotByDtxsid("DTXSID9020112")).size().isEqualTo(5);
    }
    
    @Test
    void testChemicalsByAeid() {
    	assertThat(repository.getChemicalsByAeid(3032)).size().isEqualTo(1);
    	
    	assertThat(repository.getChemicalsByAeid(2532)).size().isEqualTo(1);
    }
    
    @Test
    void testCCDAssayDetailsByAeid() {
    	assertThat(repository.getFullCcdAssayDetailsByAeid(3032)).size().isEqualTo(1);
    	
    	assertThat(repository.getFullCcdAssayDetailsByAeid(2532)).size().isEqualTo(1);
    }
}