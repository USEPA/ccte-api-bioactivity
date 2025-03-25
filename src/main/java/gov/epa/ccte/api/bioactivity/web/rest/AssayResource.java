package gov.epa.ccte.api.bioactivity.web.rest;

import gov.epa.ccte.api.bioactivity.projection.assay.AssayAll;
import gov.epa.ccte.api.bioactivity.projection.assay.AssayBase;
import gov.epa.ccte.api.bioactivity.projection.assay.CcdAssayList;
import gov.epa.ccte.api.bioactivity.projection.data.BioactivityDataAll;
import gov.epa.ccte.api.bioactivity.repository.AssayAnnotationAggRepository;
import gov.epa.ccte.api.bioactivity.repository.AssayAnnotationRepository;
import gov.epa.ccte.api.bioactivity.repository.BioactivityAggRepository;
import gov.epa.ccte.api.bioactivity.web.rest.error.HigherNumberOfRequestsException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AssayResource implements AssayApi {
    final private AssayAnnotationRepository annotationRepository;
    
    final private BioactivityAggRepository bioactivityAggRepository;
    final private AssayAnnotationAggRepository assayAnnotationAggRepository;

    
    @Value("200")
    private Integer batchSize;
    
    public AssayResource(AssayAnnotationRepository annotationRepository, BioactivityAggRepository bioactivityAggRepository, AssayAnnotationAggRepository assayAnnotationAggRepository) {
        this.annotationRepository = annotationRepository;
		this.bioactivityAggRepository = bioactivityAggRepository;
		this.assayAnnotationAggRepository = assayAnnotationAggRepository;
    }

    @Override
    public List<?> assayByAeid(Integer aeid, String projection) {
        log.debug("Fetching assay data for aeid = {} with projection = {}", aeid, projection);

        if (projection == null || projection.isEmpty()) {
            AssayAll result = annotationRepository.findByAeid(aeid, AssayAll.class);
            return result != null ? List.of(result) : List.of(); 
        }

        Object result = switch (projection) {
            case "ccd-assay-annotation" -> assayAnnotationAggRepository.findAnnotationByAeid(aeid);
            case "ccd-assay-gene" -> assayAnnotationAggRepository.findGeneByAeid(aeid);
            case "ccd-assay-citations" -> assayAnnotationAggRepository.findCitationsByAeid(aeid);
            case "ccd-tcpl-processing" -> assayAnnotationAggRepository.findTcplByAeid(aeid);
            case "ccd-assay-reagents" -> assayAnnotationAggRepository.findReagentByAeid(aeid);
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
    List<AssayAll>batchSearchAssayByAeid(String[] aeids) {
        log.debug("bioactiivty data for aeid size = {}", aeids.length);

        if(aeids.length > batchSize)
            throw new HigherNumberOfRequestsException(aeids.length, batchSize);

        List<AssayAll> data = annotationRepository.findByAeidInOrderByAeidAsc(aeids, AssayAll.class);

        return data;
    }

    @Override
    public List<?> allAssays( String projection) {

    	return switch (projection) {
        case "ccd-assay-list" -> annotationRepository.findAssayAnnotations(CcdAssayList.class);
        case "assay-all" -> annotationRepository.findBy(AssayAll.class);
		default -> annotationRepository.findBy(AssayAll.class);
    };  
    
    }

	@Override
	public List<String> chemicalsByAeid(Integer aeid) {
		
        log.debug("aeid = {}", aeid);
        return bioactivityAggRepository.getChemicalsByAeid(aeid);
	}
	
}
