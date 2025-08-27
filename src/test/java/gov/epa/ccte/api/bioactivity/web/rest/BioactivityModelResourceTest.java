package gov.epa.ccte.api.bioactivity.web.rest;


//This will test REST end-points in the BioactivityModelResource.java using WebMvcTest and MockitoBean

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import gov.epa.ccte.api.bioactivity.domain.BioactivityModel;
import gov.epa.ccte.api.bioactivity.repository.BioactivityModelRepository;


@ActiveProfiles("test")
@WebMvcTest(BioactivityModelResource.class)
@RunWith(MockitoJUnitRunner.class)
public class BioactivityModelResourceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private BioactivityModelRepository repository;


    private BioactivityModel model;
    
    @BeforeEach
    void setUp(){
    	model = BioactivityModel.builder()
                .id(1197521)
                .dtxsid("DTXSID7020182")
                .model("COMPARA (Consensus)")
                .receptor("Androgen")
                .agonist("0")
                .antagonist("1")
                .binding("1")
                .modelDesc("CoMPARA is a larger scale collaboration between 35 international groups,\n using QSAR models to predict androgen receptor activity using a common\n training set of 1746 compounds provided by U.S. EPA. A key result is a\n consensus model of AR agonist and antagonist activity that is run against\n the DSSTox chemical library. These results are intended to be used in\n prioritization for compounds for follow-up testing. More details about\n the project are available on <a href='https://www.researchgate.net/publication/316606155_\n CoMPARA_Collaborative_Modeling_Project_\n for_Androgen_Receptor_Activity'target='_blank'>ResearchGate</a>.")
                .build();
    }
    
    @Test
    void testGetBioactivityModelByDtxsidGet() throws Exception {
        final List<BioactivityModel> data = Collections.singletonList(model);

        when(repository.findByDtxsid("DTXSID7020182")).thenReturn(data);

        mockMvc.perform(get("/bioactivity/models/search/by-dtxsid/{dtxsid}", "DTXSID7020182"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(model.getDtxsid()));

    }
}