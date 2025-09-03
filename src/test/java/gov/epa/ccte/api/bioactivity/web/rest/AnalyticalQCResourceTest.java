package gov.epa.ccte.api.bioactivity.web.rest;


//This will test REST end-points in the AnalyticalQCResource.java using WebMvcTest and MockitoBean

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import gov.epa.ccte.api.bioactivity.domain.AnalyticalQC;
import gov.epa.ccte.api.bioactivity.repository.AnalyticalQCRepository;


@ActiveProfiles("test")
@WebMvcTest(AnalyticalQCResource.class)
@RunWith(MockitoJUnitRunner.class)
public class AnalyticalQCResourceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private AnalyticalQCRepository analyticalQCRepository;


    private AnalyticalQC analyticalQC;
    
    @BeforeEach
    void setUp(){
    	analyticalQC = AnalyticalQC.builder()
                .analyticalQcId(28321L)
                .dtxsid("DTXSID7020182")
                .chnm("Bisphenol A")
                .spid(null)
                .qcLevel("substance")
                .t0("A")
                .t4("A")
                .call("S")
                .annotation("Pure and Stable over time")
                .flags(null)
                .averageMass("228.1150298")
                .log10VaporPressureOperaPred("-7.168823475")
                .logkowOctanolWaterOperaPred("3.32044")
                .exportDate(LocalDate.of(2025,03,05))
                .dataVersion("prod_internal_invitrodb_v4_2")
                .pOrCaution("pass")
                .build();
    }
    
    @Test
    void testGetAnalyticalQCByDtxsid() throws Exception {
        final List<AnalyticalQC> data = Collections.singletonList(analyticalQC);

        when(analyticalQCRepository.findByDtxsid("DTXSID7020182", AnalyticalQC.class)).thenReturn(data);

        mockMvc.perform(get("/bioactivity/analyticalqc/search/by-dtxsid/{dtxsid}", "DTXSID7020182"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(analyticalQC.getDtxsid()));

    }
}