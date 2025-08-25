package gov.epa.ccte.api.bioactivity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import gov.epa.ccte.api.bioactivity.domain.SearchAssay;
import gov.epa.ccte.api.bioactivity.repository.SearchAssayRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SearchAssayService {

    private final SearchAssayRepository searchRepository;
    
    public SearchAssayService(SearchAssayRepository searchRepository) {
    	this.searchRepository = searchRepository;
    }
    
    public String preprocessingSearchValue(String searchValue){

        // From https://confluence.epa.gov/display/CCTEA/Search+Requirements
        // Make all character upper case
        searchValue = searchValue.toUpperCase();
        // Search word should be trim
        searchValue = searchValue.trim();
        
        return searchValue;
    }
    
    public List<SearchAssay> getStartWith(String value, Integer top) {
        String searchValue = preprocessingSearchValue(value);


        log.debug("input search value = {} and process search value = {}. ", value, searchValue);

        List<SearchAssay> searchResult; // exact search and final results
        List<SearchAssay> searchResult2; // start-with results

        // for adding exact search on top of return result
        String removeSpaces = searchValue.replaceAll(" ", "");

        searchResult = searchRepository.findAllByModifiedValueIn(List.of(searchValue, removeSpaces), SearchAssay.class);

        log.debug("exact search count {}",searchResult.size());

        if(shouldSearchMore(searchValue, searchResult)) {
        	
            searchResult2 = getStartWithFromDB(searchValue, top);
            searchResult.addAll(searchResult2); // append start-with results
        }
        log.debug("{} records match for {}", searchResult.size(), value);

        return searchResult;
    }
    
    private List<SearchAssay> getStartWithFromDB(String searchValue, Integer top) {

        if(top != null && top > 0 ){
            log.debug("picking up top {} records", top);
            return searchRepository.findByModifiedValueStartingWithOrderByIdAsc(searchValue,
                    Limit.of(top),
                    SearchAssay.class);
        }else{
            return searchRepository.findByModifiedValueStartingWithOrderByIdAsc(searchValue,
                    Limit.unlimited(),
                    SearchAssay.class);
        }
    }


    // identify the condition if there is not more searching needed
    private boolean shouldSearchMore(String searchValue, List<SearchAssay> searchResult) {
    	if (!searchResult.isEmpty())
            return false;
        else
            return true;
    }
    

    public List getContain(String searchValue, Integer top, Class<SearchAssay> assayClass) {

        // Determine the limit based on the 'top' parameter
        var limit = (top != null && top > 0) ? Limit.of(top) : Limit.unlimited();

        // Call the repository method with the appropriate parameters
        return searchRepository.findByModifiedValueContainsOrderByIdAscSearchValue(
                searchValue,
                limit,
                assayClass
        );
    }
    
}
