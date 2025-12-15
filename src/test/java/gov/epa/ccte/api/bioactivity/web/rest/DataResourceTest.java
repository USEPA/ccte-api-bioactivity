package gov.epa.ccte.api.bioactivity.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the DataResource.java using WebMvcTest and MockitoBean

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

import gov.epa.ccte.api.bioactivity.repository.BioactivityDataRepository;
import gov.epa.ccte.api.bioactivity.repository.AssayAggRepository;
import gov.epa.ccte.api.bioactivity.repository.ChemicalAggRepository;
import gov.epa.ccte.api.bioactivity.projection.data.*;
import gov.epa.ccte.api.bioactivity.domain.AssayAgg;
import gov.epa.ccte.api.bioactivity.domain.ChemicalAgg;
import gov.epa.ccte.api.bioactivity.domain.Mc3Param;
import gov.epa.ccte.api.bioactivity.domain.Mc6Param;

import java.util.*;


@ActiveProfiles("test")
@WebMvcTest(DataResource.class)
@RunWith(MockitoJUnitRunner.class)

public class DataResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BioactivityDataRepository bioactivityDataRepository;
    @MockitoBean
    private AssayAggRepository assayAggRepository;
    @MockitoBean
    private ChemicalAggRepository chemicalAggRepository;
    
    private AssayAgg assayAgg;
    private ChemicalAgg chemicalAgg;
    private Mc3Param mc3Param;
    private Mc6Param mc6Param;
    private AedRawDataProjection aedRawDataProjection;
    private BioactivityDataAll bioactivityDataAll;
    private SummaryByTissue summaryByTissue;
    private ToxcastSummaryPlot toxcastSummaryPlot;
    private ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @BeforeEach
    void setUp(){

    	aedRawDataProjection = factory.createProjection(AedRawDataProjection.class);
    	aedRawDataProjection.setAeid(2386);
    	aedRawDataProjection.setAenm(null);
    	aedRawDataProjection.setDsstoxSubstanceId("DTXSID7020182");
    	aedRawDataProjection.setMc7Param("{aed_val: [0.4274, 0.3691, 1.107, 1.183, 2.459, 7.307, 2.35, 7.675, 6.961, 1.398, 1.364, 7.247], aed_type: [aed.bmd.pbtk95, aed.bmd.3css95, aed.acc.pbtk95, aed.ac50.3css95, aed.bmd.3css50, aed.acc.3css50, aed.bmd.pbtk50, aed.acc.pbtk50, aed.ac50.pbtk50, aed.ac50.pbtk95, aed.acc.3css95, aed.ac50.3css50], httk_model: [PBTK, 3compartmentss, PBTK, 3compartmentss, 3compartmentss, 3compartmentss, PBTK, PBTK, PBTK, PBTK, 3compartmentss, 3compartmentss], aed_val_unit: [mg/kg/day, mg/kg/day, mg/kg/day, mg/kg/day, mg/kg/day, mg/kg/day, mg/kg/day, mg/kg/day, mg/kg/day, mg/kg/day, mg/kg/day, mg/kg/day], httk_version: [httk_v_2_3_1, httk_v_2_3_1, httk_v_2_3_1, httk_v_2_3_1, httk_v_2_3_1, httk_v_2_3_1, httk_v_2_3_1, httk_v_2_3_1, httk_v_2_3_1, httk_v_2_3_1, httk_v_2_3_1, httk_v_2_3_1], potency_val_type: [bmd, bmd, acc, ac50, bmd, acc, bmd, acc, ac50, ac50, acc, ac50], invitrodb_version: [invitrodb_v_4_2, invitrodb_v_4_2, invitrodb_v_4_2, invitrodb_v_4_2, invitrodb_v_4_2, invitrodb_v_4_2, invitrodb_v_4_2, invitrodb_v_4_2, invitrodb_v_4_2, invitrodb_v_4_2, invitrodb_v_4_2, invitrodb_v_4_2], interindividual_var_perc: [95.0, 95.0, 95.0, 95.0, 50.0, 50.0, 50.0, 50.0, 50.0, 95.0, 95.0, 50.0]}");
    	aedRawDataProjection.setPreferredName("Bisphenol A");
    	
    	bioactivityDataAll = factory.createProjection(BioactivityDataAll.class);
    	bioactivityDataAll.setActp(null);
    	bioactivityDataAll.setAeid(3032L);
    	bioactivityDataAll.setBmad(6.805901797768499);
    	bioactivityDataAll.setCasn("26264-06-2");
    	bioactivityDataAll.setChid(27891);
    	bioactivityDataAll.setChidRep((short) 1);
    	bioactivityDataAll.setChnm("Calcium dodecylbenzene sulfonate");
    	bioactivityDataAll.setCoff((double) 20);
    	bioactivityDataAll.setConcMax(199.994);
    	bioactivityDataAll.setConcMin(0.03199904);
    	bioactivityDataAll.setDtxsid("DTXSID1027891");
    	bioactivityDataAll.setFitc((short) 41);
    	bioactivityDataAll.setHitc(0.999999998935129);
    	bioactivityDataAll.setM4id(7827467L);
    	bioactivityDataAll.setM5id(16406365L);
    	bioactivityDataAll.setMaxMean(101.14376395333333);
    	bioactivityDataAll.setMaxMeanConc(99.997);
    	bioactivityDataAll.setMaxMed(104.8938098);
    	bioactivityDataAll.setMaxMedConc(199.994);
    	bioactivityDataAll.setMc3Param(mc3Param);
    	bioactivityDataAll.setMc4Param(null);
    	bioactivityDataAll.setMc5Param(null);
    	bioactivityDataAll.setMc6Param(mc6Param);
    	bioactivityDataAll.setModelType((short) 3);
    	bioactivityDataAll.setModl("poly2");
    	bioactivityDataAll.setNconc(7);
    	bioactivityDataAll.setNmedGtblNeg(0);
    	bioactivityDataAll.setNmedGtblPos(3);
    	bioactivityDataAll.setNpts(21);
    	bioactivityDataAll.setNrep((double) 3);
    	bioactivityDataAll.setRespMax(107.2572038);
    	bioactivityDataAll.setRespMin(-21.45144077);
    	bioactivityDataAll.setSpid("EPAPLT0137A10");
    	bioactivityDataAll.setStkc(19.999399185180664);
    	bioactivityDataAll.setStkcUnit("mM");
    	bioactivityDataAll.setTestedConcUnit("uM");
    	
    	mc3Param = Mc3Param.builder()
    			.conc(null)
    			.resp(null)
    			.build();
    	
    	mc6Param = Mc6Param.builder()
    			.flag(null)
    			.mc6MthdId(null)
    			.build();
    	
    	summaryByTissue = factory.createProjection(SummaryByTissue.class);
    	summaryByTissue.setAC50((float) 9.839215);
    	summaryByTissue.setACC((float) 5.2068067);
    	summaryByTissue.setChemicalName("Oxyfluorfen");
    	summaryByTissue.setContinuousHitCall((float) 1);
    	summaryByTissue.setCutOff((float) 0.9005807);
    	summaryByTissue.setDtxsid("DTXSID7024241");
    	summaryByTissue.setHitCall("Active");
    	summaryByTissue.setIntendedTargetFamily("cyp");
    	summaryByTissue.setLogAC50((float) 0.99296045);
    	summaryByTissue.setMaxMedConc((float) 100);
    	summaryByTissue.setTissue("liver");
    	
    	toxcastSummaryPlot = factory.createProjection(ToxcastSummaryPlot.class);
    	toxcastSummaryPlot.setAC50((float) 46.05973);
    	toxcastSummaryPlot.setAeid("2309");
    	toxcastSummaryPlot.setHitc((float) 1);
    	toxcastSummaryPlot.setTopOverCutoff((float) 5.1251335);
    	
    	assayAgg = AssayAgg.builder()
    			.aeid(3032L)
    			.activeMc(114)
    			.totalMc(166L)
    			.activeSc(218)
    			.totalSc(1969L)
    			.build();
    	
    	chemicalAgg = ChemicalAgg.builder()
    			.dtxsid("DTXSID9026974")
    			.activeMc(292)
    			.totalMc(630)
    			.activeSc(13)
    			.totalSc(254)
    			.cytotoxMedianRaw((float) 1.4645178)
    			.cytotoxMad((float) 0.2908745)
    			.globalMad((float) 0.17031652)
    			.cytotoxMedianLog((float) 1.4645178)
    			.cytotoxMedianUm((float) 29.141897)
    			.cytotoxLowerUm((float) 8.986038)
    			.cytotoxLowerLog((float) 0.9535683)
    			.ntested((float) 87)
    			.nhit((float) 80)
    			.build();
    }
    
    @Test
    void testGetDataByDtxsid() throws Exception {
        final List<BioactivityDataAll> data = Collections.singletonList(bioactivityDataAll);

        when(bioactivityDataRepository.findByDtxsid("DTXSID1027891", BioactivityDataAll.class)).thenReturn(data);

        mockMvc.perform(get("/bioactivity/data/search/by-dtxsid/{dtxsid}", "DTXSID1027891"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(bioactivityDataAll.getDtxsid()));

    }

    @Test
    void testGetDataByDtxsidToxCast() throws Exception {
        final List<ToxcastSummaryPlot> data = Collections.singletonList(toxcastSummaryPlot);

        when(bioactivityDataRepository.findToxcastSummaryPlotByDtxsid("DTXSID9026974")).thenReturn(data);

        mockMvc.perform(get("/bioactivity/data/search/by-dtxsid/{dtxsid}", "DTXSID9026974")
        		.param("projection", "toxcast-summary-plot"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(toxcastSummaryPlot.getAeid()));

    }
    
    @Test
    void testGetDataByBatchDtxsid() throws Exception {
        final List<BioactivityDataAll> data = Collections.singletonList(bioactivityDataAll);
        String[] jsonArray = {"DTXSID1027891"};
        String jsonBody = new ObjectMapper().writeValueAsString(jsonArray);
        
        when(bioactivityDataRepository.findByDtxsidInOrderByDtxsidAsc(jsonArray, BioactivityDataAll.class)).thenReturn(data);

        mockMvc.perform(post("/bioactivity/data/search/by-dtxsid/")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(jsonBody))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(bioactivityDataAll.getDtxsid()));

    }
    
    @Test
    void testGetDataByAeid() throws Exception {
        final List<BioactivityDataAll> data = Collections.singletonList(bioactivityDataAll);

        when(bioactivityDataRepository.findByAeid(3032, BioactivityDataAll.class)).thenReturn(data);

        mockMvc.perform(get("/bioactivity/data/search/by-aeid/{aeid}", 3032))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(bioactivityDataAll.getAeid()));

    }
    
    @Test
    void testGetDataByBatchAeid() throws Exception {
        final List<BioactivityDataAll> data = Collections.singletonList(bioactivityDataAll);
        String[] jsonArray = {"3032"};
        String jsonBody = new ObjectMapper().writeValueAsString(jsonArray);
        
        when(bioactivityDataRepository.findByAeidInOrderByAeidAsc(jsonArray, BioactivityDataAll.class)).thenReturn(data);

        mockMvc.perform(post("/bioactivity/data/search/by-aeid/")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(jsonBody))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(bioactivityDataAll.getAeid()));

    }
    
    @Test
    void testGetDataBySpid() throws Exception {
        final List<BioactivityDataAll> data = Collections.singletonList(bioactivityDataAll);

        when(bioactivityDataRepository.findBySpid("EPAPLT0137A10", BioactivityDataAll.class)).thenReturn(data);

        mockMvc.perform(get("/bioactivity/data/search/by-spid/{spid}", "EPAPLT0137A10"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].spid").value(bioactivityDataAll.getSpid()));

    }
    
    @Test
    void testGetDataByBatchSpid() throws Exception {
        final List<BioactivityDataAll> data = Collections.singletonList(bioactivityDataAll);
        String[] jsonArray = {"EPAPLT0137A10"};
        String jsonBody = new ObjectMapper().writeValueAsString(jsonArray);
        
        when(bioactivityDataRepository.findBySpidInOrderBySpidAsc(jsonArray, BioactivityDataAll.class)).thenReturn(data);

        mockMvc.perform(post("/bioactivity/data/search/by-spid/")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(jsonBody))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].spid").value(bioactivityDataAll.getSpid()));

    }
    
    @Test
    void testGetDataByM4id() throws Exception {
        final List<BioactivityDataAll> data = Collections.singletonList(bioactivityDataAll);

        when(bioactivityDataRepository.findByM4id(7827467, BioactivityDataAll.class)).thenReturn(data);

        mockMvc.perform(get("/bioactivity/data/search/by-m4id/{m4id}", 7827467))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].m4id").value(bioactivityDataAll.getM4id()));

    }
    
    @Test
    void testGetDataByBatchM4id() throws Exception {
        final List<BioactivityDataAll> data = Collections.singletonList(bioactivityDataAll);
        String[] jsonArray = {"7827467"};
        String jsonBody = new ObjectMapper().writeValueAsString(jsonArray);
        
        when(bioactivityDataRepository.findByM4idInOrderByM4idAsc(jsonArray, BioactivityDataAll.class)).thenReturn(data);

        mockMvc.perform(post("/bioactivity/data/search/by-m4id/")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(jsonBody))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].m4id").value(bioactivityDataAll.getM4id()));

    }
    
    @Test
    void testGetSummaryByAeid() throws Exception {
        final List<Object> data = Collections.singletonList(assayAgg);

        when(assayAggRepository.findByAeid(3032)).thenReturn(data);

        mockMvc.perform(get("/bioactivity/data/summary/search/by-aeid/{aeid}", 3032))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(assayAgg.getAeid()));

    }
    
    @Test
    void testGetSummaryByDtxsid() throws Exception {
        final List<Object> data = Collections.singletonList(chemicalAgg);

        when(chemicalAggRepository.findByDtxsid("DTXSID9026974")).thenReturn(data);

        mockMvc.perform(get("/bioactivity/data/summary/search/by-dtxsid/{dtxsid}", "DTXSID9026974"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(chemicalAgg.getDtxsid()));

    }
    
    @Test
    void testGetAedDataByDtxsid() throws Exception {
        final List<AedRawDataProjection> data = Collections.singletonList(aedRawDataProjection);

        when(bioactivityDataRepository.findAedDataByDtxsid("DTXSID9026974")).thenReturn(data);

        mockMvc.perform(get("/bioactivity/data/aed/search/by-dtxsid/{dtxsid}", "DTXSID9026974"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(aedRawDataProjection.getDsstoxSubstanceId()));

    }
    
    @Test
    void testGetAedDataByBatchDtxsid() throws Exception {
        final List<AedRawDataProjection> data = Collections.singletonList(aedRawDataProjection);
        String[] jsonArray = {"DTXSID7020182"};
        String jsonBody = new ObjectMapper().writeValueAsString(jsonArray);
        
        when(bioactivityDataRepository.findAedDataByDtxsidIn(jsonArray)).thenReturn(data);

        mockMvc.perform(post("/bioactivity/data/aed/search/by-dtxsid/")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(jsonBody))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(aedRawDataProjection.getDsstoxSubstanceId()));

    }
    
    @Test
    void testGetSummaryByDtxsidAndTissue() throws Exception {
        final List<SummaryByTissue> assays = Collections.singletonList(summaryByTissue);

        when(bioactivityDataRepository.findByDtxsidAndTissue("DTXSID7024241","liver")).thenReturn(assays);

        mockMvc.perform(get("/bioactivity/data/summary/search/by-tissue/")
        		.param("dtxsid", "DTXSID7024241")
        		.param("tissue", "liver"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(summaryByTissue.getDtxsid()));

    }

}
