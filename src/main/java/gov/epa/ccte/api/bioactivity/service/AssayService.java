package gov.epa.ccte.api.bioactivity.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.epa.ccte.api.bioactivity.projection.assay.CcdAssayGene;

import gov.epa.ccte.api.bioactivity.projection.assay.CcdAssayList;
import gov.epa.ccte.api.bioactivity.repository.AssayAnnotationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssayService {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final AssayAnnotationRepository repo;

    public List<Map<String, Object>> fetchCcdAssayList() {
        return this.wrapCcdAssayList(repo.findAssayAnnotations(CcdAssayList.class));
    }

    // Convert CcdAssayList projection into the CCD assay-list response shape expected by clients.
    private List<Map<String, Object>> wrapCcdAssayList(List<CcdAssayList> assayList) {
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

                map.put("assayList", makeInnerAssayList(assay));

                map.put("geneArray", new ArrayList<Map<String, Object>>());

                map.put("singleConc", makeSingleConcList(assay));

                map.put("multiConc", makeMultiConcList(assay));

                return map;
            });

            var gene = makeGene(assay);
            if (gene != null) {
                ((List) formattedAssay.get("geneArray")).add(gene);
            }

        }

        return new ArrayList<>(assayMap.values());
    }

    private LinkedHashMap<String, Object> makeGene(CcdAssayList assay) {
        var geneMap = new LinkedHashMap<String, Object>();
        // todo: if we ever return here, we need to probably allow this to "fire" to reduce the payload size
//        if (assay.getGeneName() == null && assay.getEntrezGeneId() == null) {
//            return null;
//        }
        geneMap.put("entrezGeneId", assay.getEntrezGeneId());
        geneMap.put("geneName", assay.getGeneName());
        geneMap.put("officialSymbol", assay.getOfficialSymbol());
        return geneMap;
    }

    private List<Map<String, Object>> makeInnerAssayList(CcdAssayList assay) {
        String rawJson = assay.getAssayList();
        List<Map<String, Object>> parsed = new ArrayList<>();
        try {
            if (rawJson != null && !rawJson.isBlank()) {
                parsed = mapper.readValue(rawJson, new TypeReference<List<Map<String, Object>>>() {
                });
            }
        } catch (Exception e) {
            log.warn("Failed to parse assayList JSON for aeid={}", assay.getAeid(), e);
        }
        return parsed;
    }

    private List<Map<String, Object>> makeSingleConcList(CcdAssayList assay) {
        Map<String, Object> singleConc = new LinkedHashMap<>();
        singleConc.put("singleConcChemicalCountActive", assay.getSingleConcChemicalCountActive());
        singleConc.put("singleConcChemicalCountTotal", assay.getSingleConcChemicalCountTotal());
        final List<Map<String, Object>> singleConcList = List.of(singleConc);
        return singleConcList;
    }

    private List<Map<String, Object>> makeMultiConcList(CcdAssayList assay) {
        Map<String, Object> multiConc = new LinkedHashMap<>();
        multiConc.put("multiConcChemicalCountActive", assay.getMultiConcChemicalCountActive());
        multiConc.put("multiConcChemicalCountTotal", assay.getMultiConcChemicalCountTotal());
        List<Map<String, Object>> multiConcList = List.of(multiConc);
        return multiConcList;
    }

}
