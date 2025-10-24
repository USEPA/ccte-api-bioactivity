package gov.epa.ccte.api.bioactivity.web.rest;

import gov.epa.ccte.api.bioactivity.projection.assay.AssayAll;
import gov.epa.ccte.api.bioactivity.projection.assay.AssayEndpointsList;
import gov.epa.ccte.api.bioactivity.projection.assay.CcdAssayList;
import gov.epa.ccte.api.bioactivity.repository.*;
import gov.epa.ccte.api.bioactivity.service.AssayService;
import gov.epa.ccte.api.bioactivity.web.rest.error.HigherNumberOfRequestsException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AssayResource implements AssayApi {
    final private AssayAnnotationRepository annotationRepository;
    final private AssayAggRepository assayAggRepository;
    final private AOPRepository aopRepository;
    private final AssayService assayService;
    private final BioactivityDataRepository dataRepository;
    private final BioactivityScRepository bioactivityScRepository;


    @Value("200")
    private Integer batchSize;

    public AssayResource(AssayAnnotationRepository annotationRepository,
                         AssayAggRepository assayAggRepository,
                         AOPRepository aopRepository, AssayService assayService,
                         BioactivityDataRepository dataRepository,
                         BioactivityScRepository bioactivityScRepository) {

        this.annotationRepository = annotationRepository;
        this.assayAggRepository = assayAggRepository;
        this.aopRepository = aopRepository;
        this.assayService = assayService;
        this.dataRepository = dataRepository;
        this.bioactivityScRepository = bioactivityScRepository;
    }

    @Override
    public List<?> assayByAeid(Integer aeid, String projection) {
        log.debug("Fetching assay data for aeid = {} with projection = {}", aeid, projection);

        if (projection == null || projection.isEmpty()) {
            AssayAll result = annotationRepository.findByAeid(aeid, AssayAll.class);
            return result != null ? List.of(result) : List.of();
        }

        Object result = switch (projection) {
            case "ccd-assay-annotation" -> assayAggRepository.findAnnotationByAeid(aeid);
            case "ccd-assay-gene" -> assayAggRepository.findGeneByAeid(aeid);
            case "ccd-assay-citations" -> assayAggRepository.findCitationsByAeid(aeid);
            case "ccd-tcpl-processing" -> assayAggRepository.findTcplByAeid(aeid);
            case "ccd-assay-reagents" -> assayAggRepository.findReagentByAeid(aeid);
            case "ccd-assay-aop" -> aopRepository.findByToxcastAeid(aeid);
            default -> annotationRepository.findByAeid(aeid, AssayAll.class);
        };

        if (result instanceof List<?>) {
            return (List<?>) result;
        } else if (result != null) {
            return List.of(result);
        } else {
            return List.of();
        }
    }

    @Override
    public @ResponseBody
    List<AssayAll> batchSearchAssayByAeid(String[] aeids) {
        log.debug("bioactiivty data for aeid size = {}", aeids.length);

        if (aeids.length > batchSize)
            throw new HigherNumberOfRequestsException(aeids.length, batchSize);

        List<AssayAll> data = annotationRepository.findByAeidInOrderByAeidAsc(aeids, AssayAll.class);

        return data;
    }

    @Override
    @GetMapping("/search/by-endpoint/{assayEndpointName}")
    public Long aeidByAssayEndpointName(@PathVariable("assayEndpointName") String assayEndpointName){
        log.debug("Fetching aeid for assayEndpointName = {}", assayEndpointName);
        Long data = annotationRepository.findAeidByAssayComponentEndpointName(assayEndpointName);
        return data;
    }
    
    @Override
    public List<?> singleConcDataByAeid(Integer aeid, String projection) {
        return switch (projection) {
            case "single-conc" -> bioactivityScRepository.findByAeidAndChidRep(aeid, (short) 1);
            case "ccd-single-conc" -> bioactivityScRepository.getSingleConcDataByAeid(aeid);
            default -> bioactivityScRepository.findByAeidAndChidRep(aeid, (short) 1);
        };
    }

    @Override
    public List<?> allAssays(String projection) {

        return switch (projection) {
            case "ccd-assay-list" ->
                    assayService.wrapCcdAssayList(annotationRepository.findAssayAnnotations(CcdAssayList.class));
            case "assay-all" -> annotationRepository.findBy(AssayAll.class);
            default -> annotationRepository.findBy(AssayAll.class);
        };

    }

    @Override
    public List<?> chemicalsByAeid(Integer aeid, String projection) {
        log.debug("aeid = {}, projection = {}", aeid, projection);

        return switch (projection.toLowerCase()) {
            case "ccdassaydetails" -> dataRepository.getFullCcdAssayDetailsByAeid(aeid);
            case "dtxsidsonly" -> dataRepository.getChemicalsByAeid(aeid);
            default -> dataRepository.getChemicalsByAeid(aeid);
        };
    }

    @Override
    public List<AssayEndpointsList> assayEndpointsListByGene(String geneSymbol) {

        log.debug("aeid = {}", geneSymbol);
        return annotationRepository.findAssayEndpointsListByGene(geneSymbol);
    }

    @Override
    public Long assayCount() {

        return annotationRepository.count();
    }
}