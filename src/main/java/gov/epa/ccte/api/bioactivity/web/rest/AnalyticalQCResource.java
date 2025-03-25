package gov.epa.ccte.api.bioactivity.web.rest;

import gov.epa.ccte.api.bioactivity.domain.AnalyticalQC;
import gov.epa.ccte.api.bioactivity.repository.AnalyticalQCRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class AnalyticalQCResource implements AnalyticalQCApi {

    private final AnalyticalQCRepository repository;

    public AnalyticalQCResource(AnalyticalQCRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AnalyticalQC> findByDsstoxSubstanceId(String dtxsid) {
        log.debug("Fetching Analytical QC data for dtxsid = {}", dtxsid);
        return repository.findByDtxsid(dtxsid, AnalyticalQC.class);
    }
}