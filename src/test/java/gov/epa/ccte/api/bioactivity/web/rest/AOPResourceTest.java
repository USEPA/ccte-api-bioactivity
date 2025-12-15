package gov.epa.ccte.api.bioactivity.web.rest;


//This will test REST end-points in the AOPResource.java using WebMvcTest and MockitoBean

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

import gov.epa.ccte.api.bioactivity.domain.AOP;
import gov.epa.ccte.api.bioactivity.repository.AOPRepository;


@ActiveProfiles("test")
@WebMvcTest(AOPResource.class)
@RunWith(MockitoJUnitRunner.class)
public class AOPResourceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private AOPRepository aopRepository;


    private AOP aop;
    
    @BeforeEach
    void setUp(){
    	aop = AOP.builder()
                .id(1)
                .toxcastAeid(63)
                .entrezGeneId(196)
                .eventNumber(18)
                .eventLink("https://aopwiki.org/events/18")
                .aopNumber(21)
                .aopLink("https://aopwiki.org/aops/21")
                .build();
    }
    
    @Test
    void testGetAOPByToxcastAeid() throws Exception {
        final List<Object> data = Collections.singletonList(aop);

        when(aopRepository.findByToxcastAeid(63)).thenReturn(data);

        mockMvc.perform(get("/bioactivity/aop/search/by-toxcast-aeid/{toxcastAeid}", "63"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].toxcastAeid").value(aop.getToxcastAeid()));

    }
    
    @Test
    void testGetAOPByEntrezGeneId() throws Exception {
        final List<Object> data = Collections.singletonList(aop);

        when(aopRepository.findByEntrezGeneId(196)).thenReturn(data);

        mockMvc.perform(get("/bioactivity/aop/search/by-entrez-gene-id/{entrezGeneId}", "196"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].entrezGeneId").value(aop.getEntrezGeneId()));

    }
    
    @Test
    void testGetAOPByEventNumber() throws Exception {
        final List<Object> data = Collections.singletonList(aop);

        when(aopRepository.findByEventNumber(18)).thenReturn(data);

        mockMvc.perform(get("/bioactivity/aop/search/by-event-number/{eventNumber}", "18"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventNumber").value(aop.getEventNumber()));

    }
}