package gov.epa.ccte.api.bioactivity.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the AssayResource.java using WebMvcTest and MockitoBean

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
import static org.hamcrest.CoreMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

import gov.epa.ccte.api.bioactivity.repository.AssayAnnotationRepository;
import gov.epa.ccte.api.bioactivity.repository.AssayAnnotationAggRepository;
import gov.epa.ccte.api.bioactivity.repository.AOPRepository;
import gov.epa.ccte.api.bioactivity.repository.BioactivityDataRepository;
import gov.epa.ccte.api.bioactivity.repository.BioactivityScRepository;
import gov.epa.ccte.api.bioactivity.service.AssayService;
import gov.epa.ccte.api.bioactivity.projection.assay.chemicals.CcdAssayDetails;
import gov.epa.ccte.api.bioactivity.projection.assay.*;
import gov.epa.ccte.api.bioactivity.domain.AssayList;
import gov.epa.ccte.api.bioactivity.domain.Citation;
import gov.epa.ccte.api.bioactivity.domain.Gene;
import gov.epa.ccte.api.bioactivity.domain.AOP;
import gov.epa.ccte.api.bioactivity.domain.BioactivitySc;

import java.time.LocalDate;
import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(AssayResource.class)
@RunWith(MockitoJUnitRunner.class)

public class AssayResourceTest {
	
    @Autowired
    private MockMvc mockMvc;
	
    @MockitoBean
    private AssayAnnotationRepository assayAnnotationRepository;
    @MockitoBean
    private AssayAnnotationAggRepository assayAggRepository;
    @MockitoBean
    private AOPRepository aopRepository;
    @MockitoBean
    private BioactivityDataRepository bioactivityDataRepository;
    @MockitoBean
    private BioactivityScRepository bioactivityScRepository;
    @MockitoBean 
    private AssayService assayService;
    
    private AssayList assayList, serviceAssayList;
    private Citation citation;
    private Gene gene;
    private AOP aop;
    private BioactivitySc bioactivitySc;
    private CcdAssayDetails ccdAssayDetails;
    private AssayAll assayAll;
    private AssayEndpointsList assayEndpointsList;
    private CcDAssayAnnotation ccDAssayAnnotation;
    private CcdAssayCitation ccdAssayCitation;
    private CcdAssayGene ccdAssayGene, serviceGene;
    private CcdAssayList ccdAssayList, singleConc, multiConc;
    private CcdReagents ccdReagents;
    private CcdSingleConcData ccdSingleConcData;
    private CcdTcplData ccdTcplData;
    private ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    
    @BeforeEach
    void setUp(){
    	ccdAssayDetails = factory.createProjection(CcdAssayDetails.class);
    	ccdAssayDetails.setCasrn("26264-06-2");
    	ccdAssayDetails.setCompoundId(null);
    	ccdAssayDetails.setGenericSubstanceId(27891);
    	ccdAssayDetails.setPreferredName("Calcium dodecylbenzene sulfonate");
    	ccdAssayDetails.setActiveAssays(156);
    	ccdAssayDetails.setCpdataCount(9L);
    	ccdAssayDetails.setMolFormula(null);
    	ccdAssayDetails.setMonoisotopicMass(null);
    	ccdAssayDetails.setAverageMass(null);
    	ccdAssayDetails.setPercentAssays(null);
    	ccdAssayDetails.setPubchemCount(null);
    	ccdAssayDetails.setPubmedCount(null);
    	ccdAssayDetails.setStereo("0");
    	ccdAssayDetails.setSourcesCount(65L);
    	ccdAssayDetails.setQcLevel(1);
    	ccdAssayDetails.setQcLevelDesc("Level 1: Expert curated, highest confidence in accuracy and consistency of unique chemical identifiers");
    	ccdAssayDetails.setIsotope(null);
    	ccdAssayDetails.setMulticomponent(0);
    	ccdAssayDetails.setTotalAssays(499);
    	ccdAssayDetails.setPubchemCid(null);
    	ccdAssayDetails.setRelatedSubstanceCount(1L);
    	ccdAssayDetails.setRelatedStructureCount(1L);
    	ccdAssayDetails.setHasStructureImage(0);
    	ccdAssayDetails.setIupacName(null);
    	ccdAssayDetails.setSmiles(null);
    	ccdAssayDetails.setInchiString(null);
    	ccdAssayDetails.setInchikey(null);
    	ccdAssayDetails.setQcNotes("mixture of benzenesulfonates; benzene sulfonated at C 2,3,4 and dodecyl chain attached at any position except terminal carbons; parent [27176-87-0 ]");
    	ccdAssayDetails.setQsarReadySmiles(null);
    	ccdAssayDetails.setMsReadySmiles(null);
    	ccdAssayDetails.setIrisLink(null);
    	ccdAssayDetails.setPprtvLink(null);
    	ccdAssayDetails.setWikipediaArticle(null);
    	ccdAssayDetails.setIsMarkush(true);
    	ccdAssayDetails.setDtxsid("DTXSID1027891");
    	ccdAssayDetails.setDtxcid(null);
    	ccdAssayDetails.setToxcastSelect(null);
    	ccdAssayDetails.setTop(102.47809070275451);
    	ccdAssayDetails.setScaledTop(5.123904535137726);
    	ccdAssayDetails.setAc50(38.42445226501973);
    	ccdAssayDetails.setLogAc50(1.584607685411212);
    	ccdAssayDetails.setHitc(0.999999998935129);
    	ccdAssayDetails.setHitCall("Active");
    	
    	
        /// Created to insert into assayAll
    	assayList = AssayList.builder()
    			.name("Thyroid Bioactivity")
    			.description("Assays related to the thyroid adverse outcome pathway network")
    			.build();
    	
    	citation = Citation.builder()
    			.doi("10.1016/j.tiv.2020.105073")
    			.url("https://pubmed.ncbi.nlm.nih.gov/33352258/")
    			.pmid(33352258)
    			.title("In vitro screening for chemical inhibition of the iodide recycling enzyme, iodotyrosine deiodinase")
    			.author("Olker JH, Korte JJ, Denny JS, Haselman JT, Hartig PC, Cardon MC, Hornung MW, Degitz SJ")
    			.citation("Olker JH, Korte JJ, Denny JS, Haselman JT, Hartig PC, Cardon MC, Hornung MW, Degitz SJ. In vitro screening for chemical inhibition of the iodide recycling enzyme, iodotyrosine deiodinase. Toxicol In Vitro. 2021 Mar;71:105073. doi: 10.1016/j.tiv.2020.105073. Epub 2020 Dec 29. PMID: 33352258; PMCID: PMC8130633.")
    			.otherId("0")
    			.citationId(239)
    			.otherSource("")
    			.build();
    	
    	gene = Gene.builder()
    			.geneId(24338)
    			.geneName("iodotyrosine deiodinase")
    			.description(null)
    			.geneSymbol("IYD")
    			.organismId(1)
    			.trackStatus(null)
    			.entrezGeneId(389434)
    			.officialSymbol("IYD")
    			.officialFullName("iodotyrosine deiodinase")
    			.uniprotAccessionNumber(null)
    			.build();
    	///
    	assayAll = factory.createProjection(AssayAll.class);
    	assayAll.setAeid(3032L);
    	assayAll.setAssayComponentEndpointName("CCTE_GLTED_hIYD");
    	assayAll.setAssayComponentEndpointDesc("Data from the assay component CCTE_GLTED_hIYD was analyzed at the assay endpoint CCTE_GLTED_hIYD in the positive analysis fitting direction relative to DMSO as the negative control and baseline of activity. Using a type of enzyme reporter, loss-of-signal activity can be used to understand iodotyrosine deiodinase activity. To generalize the intended target to other relatable targets, this assay endpoint is annotated to the 'dehalogenase' intended target family, where the subfamily is 'iodotyrosine deiodinase'.");
    	assayAll.setAssayFunctionType("enzymatic activity");
    	assayAll.setNormalizedDataType("percent_activity");
    	assayAll.setBurstAssay((short) 0);
    	assayAll.setKeyPositiveControl("3-Nitro-L-tyrosine (0.05 M NaOH for model inhibitor (MNT))");
    	assayAll.setSignalDirection("loss");
    	assayAll.setIntendedTargetType("protein");
    	assayAll.setIntendedTargetTypeSub("enzyme");
    	assayAll.setIntendedTargetFamily("dehalogenase");
    	assayAll.setIntendedTargetFamilySub("iodotyrosine deiodinase");
    	assayAll.setCellViabilityAssay((short) 0);
    	assayAll.setAcid(2818);
    	assayAll.setAssayComponentName("CCTE_GLTED_hIYD");
    	assayAll.setAssayComponentDesc("CCTE_GLTED_hIYD is the assay component measured from the CCTE_GLTED_hIYD assay. It measures substrate involved in regulation of catalytic activity using spectrophotometry. The assay measures the deiodination activity of human IYD on the substrate monoiodotyrosine (MIT).  After 3h incubation of substrate, enzyme, and test chemical, the components are applied to a 96-well Dowex column. Free iodide is not retained by the column and is separated from interfering assay components. The free iodide is measured by the Sandell-Kolthoff reaction in which the rate of reduction of cerium (Ce+4 to Ce+3) by arsenic (As+3 to As+5) is increased by the presence of iodide. This is observed as the loss of yellow color in the reaction and the quantified by the change in absorbance at 420 nm. The change in reaction rate is proportional to the amount of iodide present.");
    	assayAll.setAssayComponentTargetDesc("The iodide recycling enzyme, iodotyrosine deiodinase (IYD), is one conserved putative molecular target that plays an essential role in maintaining adequate levels of free iodide in the thyroid gland for hormone synthesis.");
    	assayAll.setParameterReadoutType("single");
    	assayAll.setAssayDesignType("enzyme reporter");
    	assayAll.setAssayDesignTypeSub("enzyme activity");
    	assayAll.setBiologicalProcessTarget("regulation of catalytic activity");
    	assayAll.setDetectionTechnologyType("Absorbance ");
    	assayAll.setDetectionTechnologyTypeSub("Colorimetric");
    	assayAll.setDetectionTechnology("Spectrophotometry");
    	assayAll.setKeyAssayReagentType("substrate");
    	assayAll.setKeyAssayReagent("Monoiodotyrosine (MIT)");
    	assayAll.setTechnologicalTargetTypes("protein");
    	assayAll.setTechnologicalTargetTypeSub("enzyme");
    	assayAll.setAid(866);
    	assayAll.setAssayname("CCTE_GLTED_hIYD");
    	assayAll.setAssayDescs("CCTE_GLTED_hIYD is a cell-free, single-readout assay that uses human iodotyrosine deiodinase (hIYD) enzyme produced with baculovirus-insect cell system. The assay is based on iodide release measured by the Sandell-Kolthoff method and is run in 96-well plate format with assay plates run in triplicate (n=3) with measurements taken at 3 hours after chemical dosing. Platewise-normalization was used based on positive control (3-Nitro-L-tyrosine, MNT) and negative/solvent controls (DMSO, NaOH).");
    	assayAll.setTimepointHr((double) 3);
    	assayAll.setOrganismId(9606);
    	assayAll.setOrganism("human");
    	assayAll.setTissue("NA");
    	assayAll.setCellFormat("cell-free");
    	assayAll.setCellFreeComponentSource("baculovirus-insect cells");
    	assayAll.setCellShortName("SF-21");
    	assayAll.setCellGrowthMode("NA");
    	assayAll.setAssayFootprint("microplate: 96-well plate");
    	assayAll.setAssayFormatType("biochemical");
    	assayAll.setAssayFormatTypeSub("protein single format");
    	assayAll.setContentReadoutType("single");
    	assayAll.setDilutionSolvent("DMSO");
    	assayAll.setDilutionSolventPercentMax(1.65);
    	assayAll.setAsid(24);
    	assayAll.setAssaySourceName("CCTE_GLTED");
    	assayAll.setAssaySourceLongName("CCTE Great Lakes Toxicology and Ecology Division");
    	assayAll.setAssaySourceDesc("The EPA CCTE Great Lakes Toxicology and Ecology Division focuses on ecotoxicology and stressors of water resources, including devleopment and implementation of in vitro and in vivo assays.");
    	assayAll.setGene(gene);
    	assayAll.setAssayList(assayList);
    	assayAll.setCitations(citation);
    	
    	assayEndpointsList = factory.createProjection(AssayEndpointsList.class);
    	assayEndpointsList.setAeid(8L);
    	assayEndpointsList.setGeneSymbol("TUBA1A");
    	assayEndpointsList.setAssayComponentEndpointName("APR_HepG2_MicrotubuleCSK_1hr");
    	assayEndpointsList.setAssayComponentEndpointDesc("Data from the assay component APR_HepG2_MicrotubuleCSK_1hr was analyzed into 1 assay endpoint. \nThis assay endpoint, APR_HepG2_MicrotubuleCSK_1hr, was analyzed with bidirectional fitting relative to DMSO as the negative control and baseline of activity. \nUsing a type of conformation reporter, measures of protein for gain or loss-of-signal activity can be used to understand the signaling at the cellular-level. \nFurthermore, this assay endpoint can be referred to as a primary readout, because this assay has produced multiple assay endpoints where this one serves a signaling function. \nTo generalize the intended target to other relatable targets, this assay endpoint is annotated to the cell morphology intended target family, where the subfamily is cell conformation.");
    	assayEndpointsList.setMultiConcActives("1/310(0.32%)");
    	assayEndpointsList.setSingleConcActive(null);
    	
    	ccDAssayAnnotation = factory.createProjection(CcDAssayAnnotation.class);
    	ccDAssayAnnotation.setAeid(3032L);
    	ccDAssayAnnotation.setAssayComponentEndpointName("CCTE_GLTED_hIYD");
    	ccDAssayAnnotation.setAssayComponentEndpointDesc("Data from the assay component CCTE_GLTED_hIYD was analyzed at the assay endpoint CCTE_GLTED_hIYD in the positive analysis fitting direction relative to DMSO as the negative control and baseline of activity. Using a type of enzyme reporter, loss-of-signal activity can be used to understand iodotyrosine deiodinase activity. To generalize the intended target to other relatable targets, this assay endpoint is annotated to the 'dehalogenase' intended target family, where the subfamily is 'iodotyrosine deiodinase'.");
    	ccDAssayAnnotation.setAssayFunctionType("enzymatic activity");
    	ccDAssayAnnotation.setNormalizedDataType("percent_activity");
    	ccDAssayAnnotation.setBurstAssay((short) 0);
    	ccDAssayAnnotation.setKeyPositiveControl("3-Nitro-L-tyrosine (0.05 M NaOH for model inhibitor (MNT))");
    	ccDAssayAnnotation.setSignalDirection("loss");
    	ccDAssayAnnotation.setIntendedTargetType("protein");
    	ccDAssayAnnotation.setIntendedTargetTypeSub("enzyme");
    	ccDAssayAnnotation.setIntendedTargetFamily("dehalogenase");
    	ccDAssayAnnotation.setIntendedTargetFamilySub("iodotyrosine deiodinase");
    	ccDAssayAnnotation.setAssayComponentName("CCTE_GLTED_hIYD");
    	ccDAssayAnnotation.setAssayComponentDesc("CCTE_GLTED_hIYD is the assay component measured from the CCTE_GLTED_hIYD assay. It measures substrate involved in regulation of catalytic activity using spectrophotometry. The assay measures the deiodination activity of human IYD on the substrate monoiodotyrosine (MIT).  After 3h incubation of substrate, enzyme, and test chemical, the components are applied to a 96-well Dowex column. Free iodide is not retained by the column and is separated from interfering assay components. The free iodide is measured by the Sandell-Kolthoff reaction in which the rate of reduction of cerium (Ce+4 to Ce+3) by arsenic (As+3 to As+5) is increased by the presence of iodide. This is observed as the loss of yellow color in the reaction and the quantified by the change in absorbance at 420 nm. The change in reaction rate is proportional to the amount of iodide present.");
    	ccDAssayAnnotation.setAssayComponentTargetDesc("The iodide recycling enzyme, iodotyrosine deiodinase (IYD), is one conserved putative molecular target that plays an essential role in maintaining adequate levels of free iodide in the thyroid gland for hormone synthesis.");
    	ccDAssayAnnotation.setParameterReadoutType("single");
    	ccDAssayAnnotation.setAssayDesignType("enzyme reporter");
    	ccDAssayAnnotation.setAssayDesignTypeSub("enzyme activity");
    	ccDAssayAnnotation.setBiologicalProcessTarget("regulation of catalytic activity");
    	ccDAssayAnnotation.setDetectionTechnologyType("Absorbance ");
    	ccDAssayAnnotation.setDetectionTechnologyTypeSub("Colorimetric");
    	ccDAssayAnnotation.setDetectionTechnology("Spectrophotometry");
    	ccDAssayAnnotation.setKeyAssayReagentType("substrate");
    	ccDAssayAnnotation.setKeyAssayReagent("Monoiodotyrosine (MIT)");
    	ccDAssayAnnotation.setTechnologicalTargetTypes("protein");
    	ccDAssayAnnotation.setTechnologicalTargetTypeSub("enzyme");
    	ccDAssayAnnotation.setAssayname("CCTE_GLTED_hIYD");
    	ccDAssayAnnotation.setAssayDescs("CCTE_GLTED_hIYD is a cell-free, single-readout assay that uses human iodotyrosine deiodinase (hIYD) enzyme produced with baculovirus-insect cell system. The assay is based on iodide release measured by the Sandell-Kolthoff method and is run in 96-well plate format with assay plates run in triplicate (n=3) with measurements taken at 3 hours after chemical dosing. Platewise-normalization was used based on positive control (3-Nitro-L-tyrosine, MNT) and negative/solvent controls (DMSO, NaOH).");
    	ccDAssayAnnotation.setTimepointHr((double) 3);
    	ccDAssayAnnotation.setOrganism("human");
    	ccDAssayAnnotation.setTissue("NA");
    	ccDAssayAnnotation.setCellFormat("cell-free");
    	ccDAssayAnnotation.setCellFreeComponentSource("baculovirus-insect cells");
    	ccDAssayAnnotation.setCellShortName("SF-21");
    	ccDAssayAnnotation.setCellGrowthMode("NA");
    	ccDAssayAnnotation.setAssayFootprint("microplate: 96-well plate");
    	ccDAssayAnnotation.setAssayFormatType("biochemical");
    	ccDAssayAnnotation.setAssayFormatTypeSub("protein single format");
    	ccDAssayAnnotation.setContentReadoutType("single");
    	ccDAssayAnnotation.setDilutionSolvent("DMSO");
    	ccDAssayAnnotation.setDilutionSolventPercentMax(1.65);
    	ccDAssayAnnotation.setAssaySourceName("CCTE_GLTED");
    	ccDAssayAnnotation.setAssaySourceLongName("CCTE Great Lakes Toxicology and Ecology Division");
    	ccDAssayAnnotation.setAssaySourceDesc("The EPA CCTE Great Lakes Toxicology and Ecology Division focuses on ecotoxicology and stressors of water resources, including devleopment and implementation of in vitro and in vivo assays.");
    	ccDAssayAnnotation.setToxCastAssayDescription("https://clowder.edap-cluster.com/files/66df2da7e4b0a7c65d2ed85e ");
    	
    	ccdAssayCitation = factory.createProjection(CcdAssayCitation.class);
    	ccdAssayCitation.setTitle("In vitro screening for chemical inhibition of the iodide recycling enzyme, iodotyrosine deiodinase");
    	ccdAssayCitation.setUrl("https://pubmed.ncbi.nlm.nih.gov/33352258/");
    	ccdAssayCitation.setAeid(3032L);
    	ccdAssayCitation.setAssayComponentEndpointName("CCTE_GLTED_hIYD");
    	ccdAssayCitation.setDoi("10.1016/j.tiv.2020.105073");
    	ccdAssayCitation.setPmid("33352258");
    	ccdAssayCitation.setAuthor("Olker JH, Korte JJ, Denny JS, Haselman JT, Hartig PC, Cardon MC, Hornung MW, Degitz SJ");
    	ccdAssayCitation.setCitation("Olker JH, Korte JJ, Denny JS, Haselman JT, Hartig PC, Cardon MC, Hornung MW, Degitz SJ. In vitro screening for chemical inhibition of the iodide recycling enzyme, iodotyrosine deiodinase. Toxicol In Vitro. 2021 Mar;71:105073. doi: 10.1016/j.tiv.2020.105073. Epub 2020 Dec 29. PMID: 33352258; PMCID: PMC8130633.");
    	ccdAssayCitation.setOtherId("0");
    	ccdAssayCitation.setOtherSource(null);
    	
    	ccdAssayGene = factory.createProjection(CcdAssayGene.class);
    	ccdAssayGene.setEntrezGeneId(389434);
    	ccdAssayGene.setGeneName("iodotyrosine deiodinase");
    	ccdAssayGene.setGeneSymbol("IYD");
    	
        /// Created to insert into ccdAssayList for result
    	serviceAssayList = AssayList.builder()
    			.name("ToxCast ER Pathway Model")
    			.description("Estrogen receptor assays used in ToxCast ER Pathway model")
    				.build();
    	
    	serviceGene = factory.createProjection(CcdAssayGene.class);
    	serviceGene.setServiceEntrezGeneId("2099");
    	serviceGene.setGeneName("estrogen receptor 1");
    	serviceGene.setGeneSymbol("ESR1");
    	
    	singleConc = factory.createProjection(CcdAssayList.class);
    	singleConc.setSingleConcChemicalCountActive(null);
    	singleConc.setSingleConcChemicalCountTotal(null);
    	
    	multiConc = factory.createProjection(CcdAssayList.class);
    	multiConc.setMultiConcChemicalCountActive(291);
    	multiConc.setMultiConcChemicalCountTotal(3183);
    	///
    	ccdAssayList = factory.createProjection(CcdAssayList.class);
    	ccdAssayList.setVendorKey("ACEA");
    	ccdAssayList.setVendorName("ACEA Biosciences, Inc. (ACEA) is a privately owned biotechnology company that developed a realtime, label free, cell growth assay system called xCELLigence based on a microelectronic impedance readout.");
    	ccdAssayList.setAssayName("ACEA_ER");
    	ccdAssayList.setAeid(2L);
    	ccdAssayList.setAssayComponentName("ACEA_ER_80hr");
    	ccdAssayList.setAssayComponentEndpointName("ACEA_ER_80hr");
    	ccdAssayList.setAssayComponentEndpontDesc("Data from the assay component ACEA_ER_80hr was analyzed into 1 assay endpoint. \r\nThis assay endpoint, ACEA_ER_80hr_Positive, was analyzed in the positive analysis fitting direction relative to DMSO as the negative control and baseline of activity. \r\nUsing a type of growth reporter, measures of the cells for gain-of-signal activity can be used to understand the signaling at the pathway-level as they relate to the gene ESR1. \r\nFurthermore, this assay endpoint can be referred to as a primary readout, because this assay has produced multiple assay endpoints where this one serves a signaling function. \r\nTo generalize the intended target to other relatable targets, this assay endpoint is annotated to the nuclear receptor intended target family, where the subfamily is steroidal.");
    	ccdAssayList.setCcdAssayDetail("ACEA_ER is a cell-based, single-readout assay that uses T47D, a human breast cell line, with measurements taken at 80 hours after chemical dosing in a 96-well plate, although TO2 (mc0.srcf) used a 384-well plate. Differences in plate size can be ignored given data normalization. <br> ACEA_ER_80hr is one of two assay component(s) measured or calculated from the ACEA_ER assay. It is designed to make measurements of real-time cell-growth kinetics, a form of growth reporter, as detected with electrical impedance signals by Real-Time Cell Electrode Sensor (RT-CES) technology.<br>Data from the assay component ACEA_ER_80hr was analyzed into 1 assay endpoint. \r\nThis assay endpoint, ACEA_ER_80hr_Positive, was analyzed in the positive analysis fitting direction relative to DMSO as the negative control and baseline of activity. \r\nUsing a type of growth reporter, measures of the cells for gain-of-signal activity can be used to understand the signaling at the pathway-level as they relate to the gene ESR1. \r\nFurthermore, this assay endpoint can be referred to as a primary readout, because this assay has produced multiple assay endpoints where this one serves a signaling function. \r\nTo generalize the intended target to other relatable targets, this assay endpoint is annotated to the nuclear receptor intended target family, where the subfamily is steroidal.");
    	ccdAssayList.setcommonName("human");
    	ccdAssayList.setTaxonName("Homo sapiens");
    	ccdAssayList.setGene(serviceGene);
    	ccdAssayList.setParsedAssayList(serviceAssayList);
    	ccdAssayList.setSingleConc(singleConc);
    	ccdAssayList.setMulticonc(multiConc);
    	
    	ccdReagents = factory.createProjection(CcdReagents.class);
    	ccdReagents.setReagentType("media_base");
    	ccdReagents.setReagentValue("Alpha-MEM");
    	ccdReagents.setCultureOrAssay("culture");
    	ccdReagents.setOrderId(1);
    	
    	ccdSingleConcData = factory.createProjection(CcdSingleConcData.class);
    	ccdSingleConcData.setAeid(3032);
    	ccdSingleConcData.setPreferredName("3-Nitro-L-tyrosine");
    	ccdSingleConcData.setDtxsid("DTXSID80863216");
    	ccdSingleConcData.setCasn("621-44-3");
    	ccdSingleConcData.setBmad(5.8178195132652);
    	ccdSingleConcData.setHitc((double) 1);
    	ccdSingleConcData.setCoff((double) 20);
    	ccdSingleConcData.setS2id(null);
    	ccdSingleConcData.setEndpointName("CCTE_GLTED_hIYD");
    	ccdSingleConcData.setMaxMedVal(100.0121212);
    	
    	ccdTcplData = factory.createProjection(CcdTcplData.class);
    	ccdTcplData.setMethodName("none");
    	ccdTcplData.setDescription("Use corrected response value (cval) as is; cval = cval. No additional mc2 methods needed for component-specific corrections.");
    	ccdTcplData.setOrderId(1);
    	ccdTcplData.setAssayRunType("multi");
    	ccdTcplData.setLevelApplied(2);
    	
    	aop = AOP.builder()
                .id(2)
                .toxcastAeid(806)
                .entrezGeneId(196)
                .eventNumber(18)
                .eventLink("https://aopwiki.org/events/18")
                .aopNumber(21)
                .aopLink("https://aopwiki.org/aops/21")
                .build();
    	
    	bioactivitySc = BioactivitySc.builder()
                .s2id(6448215L)
                .aeid(3032)
                .spid("EPAPLT0228B02")
                .chid(880025)
                .casn("335-95-5")
                .chnm("Sodium perfluorooctanoate")
                .dsstoxSubstanceId("DTXSID40880025")
                .bmad(5.8178195132652)
                .maxMed(15.6546489563567)
                .coff((double) 20)
                .hitc((double) 0)
                .chidRep((short) 1)
                .stkc((double) 20)
                .stkcUnit("mM")
                .testedConcUnit("uM")
                .sc1Param(null)
                .exportDate(LocalDate.of(2025,3,12))
                .dataVersion("prod_internal_invitrodb_v4_2")
                .build();
    }
    
    @Test
    void testGetAssayByAeid() throws Exception {
        final AssayAll assays = assayAll;

        when(assayAnnotationRepository.findByAeid(3032, AssayAll.class)).thenReturn(assays);

        mockMvc.perform(get("/bioactivity/assay/search/by-aeid/{aeid}", 3032))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(assayAll.getAeid()));
    
    }
    
    @Test
    void testGetAssayByAeidAssayAll() throws Exception {
        final AssayAll assays = assayAll;

        when(assayAnnotationRepository.findByAeid(3032, AssayAll.class)).thenReturn(assays);

        mockMvc.perform(get("/bioactivity/assay/search/by-aeid/{aeid}", 3032)
        		.param("projection", "assay-all"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(assayAll.getAeid()));
    
    }
    
    @Test
    void testGetAssayByAeidCcdAnnotation() throws Exception {
        final List<CcDAssayAnnotation> assays = Collections.singletonList(ccDAssayAnnotation);

        when(assayAggRepository.findAnnotationByAeid(3032)).thenReturn(assays);

        mockMvc.perform(get("/bioactivity/assay/search/by-aeid/{aeid}", 3032)
        		.param("projection", "ccd-assay-annotation"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(ccDAssayAnnotation.getAeid()));
    
    }
    
    @Test
    void testGetAssayByAeidCcdGene() throws Exception {
        final List<CcdAssayGene> assays = Collections.singletonList(ccdAssayGene);

        when(assayAggRepository.findGeneByAeid(3032)).thenReturn(assays);

        mockMvc.perform(get("/bioactivity/assay/search/by-aeid/{aeid}", 3032)
        		.param("projection", "ccd-assay-gene"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].geneSymbol").value(ccdAssayGene.getGeneSymbol()));
    
    }
    
    @Test
    void testGetAssayByAeidCcdCitation() throws Exception {
        final List<CcdAssayCitation> assays = Collections.singletonList(ccdAssayCitation);

        when(assayAggRepository.findCitationsByAeid(3032)).thenReturn(assays);

        mockMvc.perform(get("/bioactivity/assay/search/by-aeid/{aeid}", 3032)
        		.param("projection", "ccd-assay-citations"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(ccdAssayCitation.getAeid()));
    
    }
    
    @Test
    void testGetAssayByAeidCcdTcpl() throws Exception {
        final List<CcdTcplData> assays = Collections.singletonList(ccdTcplData);

        when(assayAggRepository.findTcplByAeid(3032)).thenReturn(assays);

        mockMvc.perform(get("/bioactivity/assay/search/by-aeid/{aeid}", 3032)
        		.param("projection", "ccd-tcpl-processing"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(ccdTcplData.getOrderId()));
    
    }
    
    @Test
    void testGetAssayByAeidCcdReagents() throws Exception {
        final List<CcdReagents> assays = Collections.singletonList(ccdReagents);

        when(assayAggRepository.findReagentByAeid(3032)).thenReturn(assays);

        mockMvc.perform(get("/bioactivity/assay/search/by-aeid/{aeid}", 3032)
        		.param("projection", "ccd-assay-reagents"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(ccdReagents.getOrderId()));
    
    }
    
    @Test
    void testGetAssayByAeidCcdAop() throws Exception {
        final List<Object> assays = Collections.singletonList(aop);

        when(aopRepository.findByToxcastAeid(806)).thenReturn(assays);

        mockMvc.perform(get("/bioactivity/assay/search/by-aeid/{aeid}", 806)
        		.param("projection", "ccd-assay-aop"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].toxcastAeid").value(aop.getToxcastAeid()));
    
    }
    
    @Test
    void testGetAssayByBatchAeid() throws Exception {
        final List<AssayAll> assays = Collections.singletonList(assayAll);
        String[] jsonArray = {"3032"};
        String jsonBody = new ObjectMapper().writeValueAsString(jsonArray);

        when(assayAnnotationRepository.findByAeidInOrderByAeidAsc(jsonArray, AssayAll.class)).thenReturn(assays);

        mockMvc.perform(post("/bioactivity/assay/search/by-aeid/")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonBody))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].aeid").value(assayAll.getAeid()))
			.andReturn();
    
    }
    
    @Test
    void testGetSingleConcByAeid() throws Exception {
        final List<BioactivitySc> singleconc = Collections.singletonList(bioactivitySc);

        when(bioactivityScRepository.findByAeidAndChidRep(3032, (short) 1)).thenReturn(singleconc);

        mockMvc.perform(get("/bioactivity/assay/single-conc/search/by-aeid/{aeid}", 3032))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(bioactivitySc.getAeid()));
    
    }
    
    @Test
    void testGetSingleConByAeidSingleConc() throws Exception {
        final List<BioactivitySc> singleconc = Collections.singletonList(bioactivitySc);

        when(bioactivityScRepository.findByAeidAndChidRep(3032, (short) 1)).thenReturn(singleconc);

        mockMvc.perform(get("/bioactivity/assay/single-conc/search/by-aeid/{aeid}", 3032)
        		.param("projection", "single-conc"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(bioactivitySc.getAeid()));
    
    }
    
    @Test
    void testGetSingleConcByAeidCcd() throws Exception {
        final List<CcdSingleConcData> singleconc = Collections.singletonList(ccdSingleConcData);

        when(bioactivityScRepository.getSigleConcDataByAeid(3032)).thenReturn(singleconc);

        mockMvc.perform(get("/bioactivity/assay/single-conc/search/by-aeid/{aeid}", 3032)
        		.param("projection", "ccd-single-conc"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(ccdSingleConcData.getAeid()));
    
    }
    
    @Test
    void testGetAllAssays() throws Exception {
        final List<AssayAll> assays = Collections.singletonList(assayAll);

        when(assayAnnotationRepository.findBy(AssayAll.class)).thenReturn(assays);

        mockMvc.perform(get("/bioactivity/assay/"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(assayAll.getAeid()));
    
    }
    
    @Test
    void testGetAllAssaysAllAssay() throws Exception {
        final List<AssayAll> assays = Collections.singletonList(assayAll);

        when(assayAnnotationRepository.findBy(AssayAll.class)).thenReturn(assays);

        mockMvc.perform(get("/bioactivity/assay/")
        		.param("projection", "assay-all"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(assayAll.getAeid()));
    
    }
    
    @Test
    void testGetAllAssaysCcd() throws Exception {
        final Long aeid = ccdAssayList.getAeid();
	    final Map<Long, Map<String, Object>> assayMap = new LinkedHashMap<>();
        	assayMap.computeIfAbsent(aeid, id -> {
        		Map<String, Object> map = new LinkedHashMap<>();
        		map.put("vendorKey", ccdAssayList.getVendorKey());
        		map.put("vendorName", ccdAssayList.getVendorName());
        		map.put("assayName", ccdAssayList.getAssayName());
        		map.put("aeid", aeid);
        		map.put("assayComponentName", ccdAssayList.getAssayComponentName());
        		map.put("assayComponentEndpointName", ccdAssayList.getAssayComponentEndpointName());
        		map.put("assayComponentEndpointDesc", ccdAssayList.getAssayComponentEndpointDesc());
        		map.put("ccdAssayDetail", ccdAssayList.getCcdAssayDetail());
        		map.put("commonName", ccdAssayList.getCommonName());
        		map.put("taxonName", ccdAssayList.getTaxonName());
        		map.put("assayList", ccdAssayList.setParsedAssayList(assayList));
        		map.put("geneArray", ccdAssayList.setGene(ccdAssayGene));
        		map.put("singleConc", ccdAssayList.setSingleConc(singleConc));
        		map.put("multiConc", ccdAssayList.setMulticonc(multiConc));
        		return map;});
        final List<Map<String,Object>> result = new ArrayList<>(assayMap.values());

        when(assayService.wrapCcdAssayList(assayAnnotationRepository.findAssayAnnotations(CcdAssayList.class))).thenReturn(result);

        mockMvc.perform(get("/bioactivity/assay/")
        		.param("projection", "ccd-assay-list"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(ccdAssayList.getAeid()));
    
    }
    
    @Test
    void testGetChemicalsByAeid() throws Exception {
        final List<String> chemicals = Collections.singletonList(ccdAssayDetails.getDtxsid());

        when(bioactivityDataRepository.getChemicalsByAeid(3032)).thenReturn(chemicals);

        mockMvc.perform(get("/bioactivity/assay/chemicals/search/by-aeid/{aeid}", 3032))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(ccdAssayDetails.getDtxsid())));
        
    
    }
    
    @Test
    void testGetChemicalsByAeidCcd() throws Exception {
        final List<CcdAssayDetails> chemicals = Collections.singletonList(ccdAssayDetails);

        when(bioactivityDataRepository.getFullCcdAssayDetailsByAeid(3032)).thenReturn(chemicals);

        mockMvc.perform(get("/bioactivity/assay/chemicals/search/by-aeid/{aeid}", 3032)
        		.param("projection", "ccdassaydetails"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(ccdAssayDetails.getDtxsid()));
    
    }
    
    @Test
    void testGetChemicalsByAeidDtxsidOnly() throws Exception {
        final List<String> chemicals = Collections.singletonList(ccdAssayDetails.getDtxsid());

        when(bioactivityDataRepository.getChemicalsByAeid(3032)).thenReturn(chemicals);

        mockMvc.perform(get("/bioactivity/assay/chemicals/search/by-aeid/{aeid}", 3032)
        		.param("projection", "dtxsidsonly"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(ccdAssayDetails.getDtxsid())));
    
    }
    
    @Test
    void testGetAssayEndpointsListByGene() throws Exception {
        final List<AssayEndpointsList> endpoints = Collections.singletonList(assayEndpointsList);

        when(assayAnnotationRepository.findAssayEndpointsListByGene("TUBA1A")).thenReturn(endpoints);

        mockMvc.perform(get("/bioactivity/assay/search/by-gene/{geneSymbol}", "TUBA1A"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aeid").value(assayEndpointsList.getAeid()));
    
    }
    
    @Test
    void testGetAssayCount() throws Exception {
        final Long count = assayAnnotationRepository.count();

        when(assayAnnotationRepository.count()).thenReturn(count);

        mockMvc.perform(get("/bioactivity/assay/count"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    
    }
}
