package gov.epa.ccte.api.bioactivity.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

import gov.epa.ccte.api.bioactivity.domain.SearchAssay;
import gov.epa.ccte.api.bioactivity.repository.SearchAssayRepository;
import gov.epa.ccte.api.bioactivity.service.SearchAssayService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SearchAssayResource implements SearchAssayApi{

    private final SearchAssayRepository searchRepository;
    private final SearchAssayService assayService;

    public SearchAssayResource(SearchAssayRepository searchRepository, SearchAssayService assayService) {
        this.searchRepository = searchRepository;
        this.assayService = assayService;
    }

    @Override
    public List<SearchAssay> assayStartWith(String value, Integer top) {
    	
        List<SearchAssay> searchResult = assayService.getStartWith(value, top);
        if (!searchResult.isEmpty()) {
            if (top != null && top > 0) {
                log.debug("picking up top {} records", top);
                return searchResult.stream().limit(top).collect(Collectors.toList());
            } else {
                return searchResult;
            }
        }
		return searchResult; 
    }

    @Override
    public List assayEqual(String value) {
    	
        String searchValue = assayService.preprocessingSearchValue(value);
        log.debug("input search word = {} and process search word = {}. ", value, searchValue);
        
        List<SearchAssay> searchResult = searchRepository.findBySearchValueOrderByIdAsc(searchValue, SearchAssay.class);
        log.debug("{} records match for {}", searchResult.size(), value);

        return searchResult;
    }

    @Override
    public List<SearchAssay> assayContain(String value, Integer top) {
    	
        String searchValue = assayService.preprocessingSearchValue(value);
        log.debug("input search word = {} and process search word = {}", value, searchValue);
        
        List<SearchAssay> searchResult = assayService.getContain(searchValue, top, SearchAssay.class);
        log.debug("{} records match for {}", searchResult.size(), value);

        return searchResult;
    }
}
