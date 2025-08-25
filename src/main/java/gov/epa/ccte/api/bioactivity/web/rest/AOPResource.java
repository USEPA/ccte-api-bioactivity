package gov.epa.ccte.api.bioactivity.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.ccte.api.bioactivity.domain.AOP;
import gov.epa.ccte.api.bioactivity.repository.AOPRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AOPResource implements AOPApi{
	
	private final AOPRepository repository;
	
	public AOPResource(AOPRepository repository) {
		this.repository = repository;
	}
	
    @Override
    public @ResponseBody
    List<AOP> dataByToxcastAeid(Integer toxcastAeid) {

        log.debug("Find Toxcast AOP for Toxcast aeid = {}", toxcastAeid);

        List<AOP> data = repository.findByToxcastAeid(toxcastAeid);

        return data;
    }

    @Override
    public @ResponseBody
    List<AOP> dataByEntrezGeneId(Integer entrezGeneId) {

        log.debug("Find Toxcast AOP for Entrez Gene Id = {}", entrezGeneId);

        List<AOP> data = repository.findByEntrezGeneId(entrezGeneId);

        return data;
    }
    
    @Override
    public @ResponseBody
    List<AOP> dataByEventNumber(Integer eventNumber) {

        log.debug("Find Toxcast AOP for Event Number = {}", eventNumber);

        List<AOP> data = repository.findByEventNumber(eventNumber);

        return data;
    }
}
