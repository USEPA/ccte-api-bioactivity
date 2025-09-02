package gov.epa.ccte.api.bioactivity.web.rest;

import gov.epa.ccte.api.bioactivity.domain.AedData;
import gov.epa.ccte.api.bioactivity.domain.AssayAgg;
import gov.epa.ccte.api.bioactivity.projection.data.AedRawDataProjection;
import gov.epa.ccte.api.bioactivity.projection.data.BioactivityDataAll;
import gov.epa.ccte.api.bioactivity.projection.data.BioactivityDataBase;
import gov.epa.ccte.api.bioactivity.projection.data.SummaryByTissue;
import gov.epa.ccte.api.bioactivity.repository.AssayAggRepository;
import gov.epa.ccte.api.bioactivity.repository.BioactivityDataRepository;
import gov.epa.ccte.api.bioactivity.repository.ChemicalAggRepository;
import gov.epa.ccte.api.bioactivity.web.rest.error.HigherNumberOfRequestsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;


@Slf4j
@RestController
public class DataResource implements DataApi {

    private final BioactivityDataRepository dataRepository;
    private final AssayAggRepository assayAggRepository;
    private final ChemicalAggRepository chemAggRepository;
    
    @Value("200")
    private Integer batchSize;
    
    public DataResource(BioactivityDataRepository dataRepository, AssayAggRepository assayAggRepository, ChemicalAggRepository chemAggRepository) {
        this.dataRepository = dataRepository;
        this.assayAggRepository = assayAggRepository;
        this.chemAggRepository = chemAggRepository;
    }

    public List<?> dataByDtxsid(String dtxsid, String projection) {
        log.debug("dtxsid = {}", dtxsid);
        
        if (projection == null || projection.isEmpty()) {
            List<BioactivityDataAll> data = dataRepository.findByDtxsid(dtxsid, BioactivityDataAll.class);
            return data; 
        }
        
        Object result = switch (projection) {
        	case "toxcast-summary-plot" -> dataRepository.findToxcastSummaryPlotByDtxsid(dtxsid);
        	default -> dataRepository.findByDtxsid(dtxsid, BioactivityDataAll.class);
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
    List<BioactivityDataAll>batchSearchDataByDtxsid(String[] dtxsids) {
        log.debug("bioactiivty data for dtxsid size = {}", dtxsids.length);

        if(dtxsids.length > batchSize)
            throw new HigherNumberOfRequestsException(dtxsids.length, batchSize);

        List<BioactivityDataAll> data = dataRepository.findByDtxsidInOrderByDtxsidAsc(dtxsids, BioactivityDataAll.class);

        return data;
    }

    @Override
    public @ResponseBody
    List dataByAeid(Integer aeid) {

        log.debug("aeid = {}", aeid);

        List<BioactivityDataAll> data = dataRepository.findByAeid(aeid, BioactivityDataAll.class);

        log.debug("result.size = {}", data.size());

        return data;
    }
    
    @Override
    public @ResponseBody
    List<BioactivityDataAll>batchSearchDataByAeid(String[] aeids) {
        log.debug("bioactiivty data for aeid size = {}", aeids.length);

        if(aeids.length > batchSize)
            throw new HigherNumberOfRequestsException(aeids.length, batchSize);

        List<BioactivityDataAll> data = dataRepository.findByAeidInOrderByAeidAsc(aeids, BioactivityDataAll.class);

        return data;
    }

    @Override
    public @ResponseBody
    List dataBySpid(String spid) {

        log.debug("spid = {}", spid);

        List<BioactivityDataAll> data = dataRepository.findBySpid(spid, BioactivityDataAll.class);

        log.debug("result.size = {}", data.size());

        return data;
    }
    
    @Override
    public @ResponseBody
    List<BioactivityDataAll>batchSearchDataBySpid(String[] spids) {
        log.debug("bioactiivty data for spids size = {}", spids.length);

        if(spids.length > batchSize)
            throw new HigherNumberOfRequestsException(spids.length, batchSize);

        List<BioactivityDataAll> data = dataRepository.findBySpidInOrderBySpidAsc(spids, BioactivityDataAll.class);

        return data;
    }

    @Override
    public @ResponseBody
    BioactivityDataBase dataByM4Id(Integer m4id) {

        log.debug("m4id = {}", m4id);

        BioactivityDataAll data = dataRepository.findByM4id(m4id, BioactivityDataAll.class);

        return data;
    }
    
    @Override
    public @ResponseBody
    List<BioactivityDataAll>batchSearchDataByM4id(String[] m4ids) {
        log.debug("bioactiivty data for m4ids size = {}", m4ids.length);

        if(m4ids.length > batchSize)
            throw new HigherNumberOfRequestsException(m4ids.length, batchSize);

        List<BioactivityDataAll> data = dataRepository.findByM4idInOrderByM4idAsc(m4ids, BioactivityDataAll.class);

        return data;
    }

    @Override
    public @ResponseBody
    AssayAgg summaryByAeid(Integer aeid) {

        log.debug("aeid = {}", aeid);

         return assayAggRepository.findByAeid(aeid);
    }
    
    @Override
    public @ResponseBody
    List summaryByDtxsid(String dtxsid) {
        log.debug("bioactivity summary data for dtxsid = {}", dtxsid);

         return chemAggRepository.findByDtxsid(dtxsid);
    }
    
	@Override
	public List<AedData> getAedDataByDtxsid(String dtxsid) {
		List<AedRawDataProjection> projections = dataRepository.findAedDataByDtxsid(dtxsid);
		ObjectMapper mapper = new ObjectMapper();

		return projections.stream().map(p -> {
			AedData dto = new AedData();
			dto.setDtxsid(p.getDsstoxSubstanceId());
			dto.setPreferredName(p.getPreferredName());
			dto.setAeid(p.getAeid());

			try {
				JsonNode node = mapper.readTree(p.getMc7Param());
				dto.setAedVal(mapper.convertValue(node.get("aed_val"), new TypeReference<>() {
				}));
				dto.setAedType(mapper.convertValue(node.get("aed_type"), new TypeReference<>() {
				}));
				dto.setHttkModel(mapper.convertValue(node.get("httk_model"), new TypeReference<>() {
				}));
				dto.setAedValUnit(mapper.convertValue(node.get("aed_val_unit"), new TypeReference<>() {
				}));
				dto.setHttkVersion(mapper.convertValue(node.get("httk_version"), new TypeReference<>() {
				}));
				dto.setPotencyValType(mapper.convertValue(node.get("potency_val_type"), new TypeReference<>() {
				}));
				dto.setInvitrodbVersion(mapper.convertValue(node.get("invitrodb_version"), new TypeReference<>() {
				}));
				dto.setInterindividualVarPerc(
						mapper.convertValue(node.get("interindividual_var_perc"), new TypeReference<>() {
						}));
			} catch (Exception e) {
				log.error("Failed to parse mc7_param", e);
			}

			return dto;
		}).toList();
	}

	@Override
	public @ResponseBody List<AedData> getAedDataForBatchDtxsids(List<String> dtxsids) {
		log.debug("Fetching AED data for dtxsids size = {}", dtxsids.size());

		if (dtxsids.size() > batchSize) {
			throw new HigherNumberOfRequestsException(dtxsids.size(), batchSize);
		}

		List<AedRawDataProjection> results = dataRepository.findAedDataByDtxsidIn(dtxsids);
		ObjectMapper mapper = new ObjectMapper();

		return results.stream().map(p -> {
			AedData dto = new AedData();
			dto.setDtxsid(p.getDsstoxSubstanceId());
			dto.setPreferredName(p.getPreferredName());
			dto.setAeid(p.getAeid());
			dto.setAenm(p.getAenm());

			try {
				JsonNode node = mapper.readTree(p.getMc7Param());
				dto.setAedVal(mapper.convertValue(node.get("aed_val"), new TypeReference<>() {
				}));
				dto.setAedType(mapper.convertValue(node.get("aed_type"), new TypeReference<>() {
				}));
				dto.setHttkModel(mapper.convertValue(node.get("httk_model"), new TypeReference<>() {
				}));
				dto.setAedValUnit(mapper.convertValue(node.get("aed_val_unit"), new TypeReference<>() {
				}));
				dto.setHttkVersion(mapper.convertValue(node.get("httk_version"), new TypeReference<>() {
				}));
				dto.setPotencyValType(mapper.convertValue(node.get("potency_val_type"), new TypeReference<>() {
				}));
				dto.setInvitrodbVersion(mapper.convertValue(node.get("invitrodb_version"), new TypeReference<>() {
				}));
				dto.setInterindividualVarPerc(
						mapper.convertValue(node.get("interindividual_var_perc"), new TypeReference<>() {
						}));
			} catch (Exception e) {
				log.error("Failed to parse mc7_param for dtxsid={}", p.getDsstoxSubstanceId(), e);
			}

			return dto;
		}).toList();
	}

    @Override
    public @ResponseBody
    List<SummaryByTissue> summaryByDtxsidAndTissue(String dtxsid, String tissue) {
        log.debug("bioactivity summary data for dtxsid = {} and tissue = {}", dtxsid, tissue);

         return dataRepository.findByDtxsidAndTissue(dtxsid, tissue);
    }
}
