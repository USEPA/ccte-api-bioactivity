package gov.epa.ccte.api.bioactivity.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.ccte.api.bioactivity.domain.BioactivityModel;
import gov.epa.ccte.api.bioactivity.repository.BioactivityModelRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BioactivityModelResource implements BioactivityModelApi{

	private final BioactivityModelRepository repository;
	
	public BioactivityModelResource(BioactivityModelRepository repository) {
		this.repository = repository;
	}
	
    @Override
    public @ResponseBody
    List<BioactivityModel> getBioactivityModelByDtxsid(String dtxsid) {

        log.debug("Find Bioactivity Model for dtxsid = {}", dtxsid);

        List<BioactivityModel> data = repository.findByDtxsid(dtxsid);

        return data;
    }

    @Override
    public @ResponseBody
    List<BioactivityModel> getBioactivityModelByDtxsidAndModel(String dtxsid, String model) {

        log.debug("Find Bioactivity Model for dtxsid = {} and model = {}", dtxsid, model);

        List<BioactivityModel> data = repository.findByDtxsidAndModelContaining(dtxsid, model);

        return data;
    }
}
