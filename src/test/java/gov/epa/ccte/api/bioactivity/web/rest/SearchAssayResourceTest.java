package gov.epa.ccte.api.bioactivity.web.rest;

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

import gov.epa.ccte.api.bioactivity.domain.SearchAssay;
import gov.epa.ccte.api.bioactivity.repository.SearchAssayRepository;
import gov.epa.ccte.api.bioactivity.service.SearchAssayService;

@ActiveProfiles("test")
@WebMvcTest(SearchAssayResource.class)
@RunWith(MockitoJUnitRunner.class)

public class SearchAssayResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SearchAssayRepository searchAssayRepository;
    @MockitoBean
    private SearchAssayService searchAssayService;
    
    private SearchAssay searchAssay, searchAssay2, searchGene;
    
    @BeforeEach
    void setUp(){

    	searchAssay = SearchAssay.builder()
    			.id(74)
    			.aeid(105)
    			.searchName("assay")
    			.searchValue("ATG_Sox_CIS")
    			.searchValueDesc("Data from the assay component ATG_Sox_CIS was analyzed into 1 assay endpoint. \r\r\n\r\r\nThis assay endpoint, ATG_Sox_CIS, was analyzed with bidirectional fitting relative to DMSO as the negative control and baseline of activity. \r\r\n\r\r\nUsing a type of inducible reporter, measures of mRNA for gain or loss-of-signal activity can be used to understand the reporter gene at the transcription factor-level as they relate to the gene SOX1. \r\r\n\r\r\nFurthermore, this assay endpoint can be referred to as a primary readout, because this assay has produced multiple assay endpoints where this one serves a reporter gene function. \r\r\n\r\r\nTo generalize the intended target to other relatable targets, this assay endpoint is annotated to the dna binding intended target family, where the subfamily is HMG box protein.")
    			.modifiedValue("ATG_SOX_CIS")
    			.build();
    	
    	searchAssay2 = SearchAssay.builder()
    			.id(77)
    			.aeid(108)
    			.searchName("assay")
    			.searchValue("ATG_STAT3_CIS")
    			.searchValueDesc("Data from the assay component ATG_STAT3_CIS was analyzed into 1 assay endpoint. \r\r\n\r\r\nThis assay endpoint, ATG_STAT3_CIS, was analyzed with bidirectional fitting relative to DMSO as the negative control and baseline of activity. \r\r\n\r\r\nUsing a type of inducible reporter, measures of mRNA for gain or loss-of-signal activity can be used to understand the reporter gene at the transcription factor-level as they relate to the gene STAT3. \r\r\n\r\r\nFurthermore, this assay endpoint can be referred to as a primary readout, because this assay has produced multiple assay endpoints where this one serves a reporter gene function. \r\r\n\r\r\nTo generalize the intended target to other relatable targets, this assay endpoint is annotated to the dna binding intended target family, where the subfamily is stat protein.")
    			.modifiedValue("ATG_STAT3_CIS")
    			.build();
    	
    	searchGene = SearchAssay.builder()
    			.id(2236)
    			.aeid(8)
    			.searchName("gene")
    			.searchValue("TUBA1A")
    			.searchValueDesc("tubulin, alpha 1a")
    			.modifiedValue("TUBA1A TUBULIN, ALPHA 1A")
    			.build();
    }
    
    @Test
    void testStartWithByAssay() throws Exception {
        final List<SearchAssay> search = Collections.singletonList(searchAssay);

        when(searchAssayService.getStartWith("ATG_S", 500)).thenReturn(search);

        mockMvc.perform(get("/bioactivity/search/start-with/{value}", "ATG_S"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(searchAssay.getAeid()));

    }
    
    @Test
    void testStartWithByGene() throws Exception {
        final List<SearchAssay> search = Collections.singletonList(searchGene);

        when(searchAssayService.getStartWith("TUBA1", 500)).thenReturn(search);

        mockMvc.perform(get("/bioactivity/search/start-with/{value}", "TUBA1"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(searchGene.getAeid()));

    }
    
    @Test
    void testEqualByAssay() throws Exception {
        final List<SearchAssay> search = Collections.singletonList(searchAssay2);
        final String searchValue = searchAssayService.preprocessingSearchValue("ATG_STAT3_CIS");
        when(searchAssayRepository.findBySearchValueOrderByIdAsc(searchValue, SearchAssay.class)).thenReturn(search);

        mockMvc.perform(get("/bioactivity/search/equal/{value}", "ATG_STAT3_CIS"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(searchAssay2.getAeid()));

    }
    
    @Test
    void testEqualByGene() throws Exception {
        final List<SearchAssay> search = Collections.singletonList(searchGene);
        final String searchValue = searchAssayService.preprocessingSearchValue("TUBA1A");
        when(searchAssayRepository.findBySearchValueOrderByIdAsc(searchValue, SearchAssay.class)).thenReturn(search);

        mockMvc.perform(get("/bioactivity/search/equal/{value}", "TUBA1A"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(searchGene.getAeid()));

    }
    
    @Test
    void testContainByAssay() throws Exception {
        final List<SearchAssay> search = Collections.singletonList(searchAssay2);
        final String searchValue = searchAssayService.preprocessingSearchValue("AT3_CIS");
        when(searchAssayService.getContain(searchValue, 0, SearchAssay.class)).thenReturn(search);

        mockMvc.perform(get("/bioactivity/search/contain/{value}", "AT3_CIS"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(searchAssay2.getAeid()));

    }
    
    @Test
    void testContainByGene() throws Exception {
        final List<SearchAssay> search = Collections.singletonList(searchGene);
        final String searchValue = searchAssayService.preprocessingSearchValue("UBA1A");
        when(searchAssayService.getContain(searchValue, 0, SearchAssay.class)).thenReturn(search);

        mockMvc.perform(get("/bioactivity/search/contain/{value}", "UBA1A"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(searchGene.getAeid()));

    }
    
}