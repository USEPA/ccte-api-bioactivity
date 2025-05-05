package gov.epa.ccte.api.bioactivity.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import gov.epa.ccte.api.bioactivity.projection.assay.CcdAssayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AssayService {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public List<Map<String, Object>> wrapCcdAssayList(List<CcdAssayList> assayList) {
	    Map<Long, Map<String, Object>> assayMap = new LinkedHashMap<>();

	    for (CcdAssayList assay : assayList) {
	        Long aeid = assay.getAeid();
	        Map<String, Object> formattedAssay = assayMap.computeIfAbsent(aeid, id -> {
	            Map<String, Object> map = new LinkedHashMap<>();
	            map.put("vendorKey", assay.getVendorKey());
	            map.put("vendorName", assay.getVendorName());
	            map.put("assayName", assay.getAssayName());
	            map.put("aeid", aeid);
	            map.put("assayComponentName", assay.getAssayComponentName());
	            map.put("assayComponentEndpointName", assay.getAssayComponentEndpointName());
	            map.put("assayComponentEndpointDesc", assay.getAssayComponentEndpointDesc());
	            map.put("ccdAssayDetail", assay.getCcdAssayDetail());
	            map.put("commonName", assay.getCommonName());
	            map.put("taxonName", assay.getTaxonName());
	            
	            String rawJson = assay.getAssayList();
	            List<Map<String, Object>> parsed = new ArrayList<>();
	            try {
	                if (rawJson != null && !rawJson.isBlank()) {
	                    parsed = mapper.readValue(rawJson, new TypeReference<List<Map<String, Object>>>() {});
	                }
	            } catch (Exception e) {
	            }
	            map.put("assayList", parsed);
	            
	            map.put("geneArray", new ArrayList<Map<String, Object>>());

	            Map<String, Object> singleConc = new LinkedHashMap<>();
	            singleConc.put("singleConcChemicalCountActive", assay.getSingleConcChemicalCountActive());
	            singleConc.put("singleConcChemicalCountTotal", assay.getSingleConcChemicalCountTotal());
	            map.put("singleConc", List.of(singleConc));

	            Map<String, Object> multiConc = new LinkedHashMap<>();
	            multiConc.put("multiConcChemicalCountActive", assay.getMultiConcChemicalCountActive());
	            multiConc.put("multiConcChemicalCountTotal", assay.getMultiConcChemicalCountTotal());
	            map.put("multiConc", List.of(multiConc));

	            return map;
	        });

	        List<Map<String, Object>> geneArray = (List<Map<String, Object>>) formattedAssay.get("geneArray");
	        Map<String, Object> geneMap = new LinkedHashMap<>();
	        geneMap.put("entrezGeneId", assay.getEntrezGeneId());
	        geneMap.put("geneName", assay.getGeneName());
	        geneMap.put("geneSymbol", assay.getGeneSymbol());
	        geneArray.add(geneMap);
	    }

	    return new ArrayList<>(assayMap.values());
	}

}
