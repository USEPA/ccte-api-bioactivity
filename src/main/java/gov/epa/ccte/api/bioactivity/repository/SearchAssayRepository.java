package gov.epa.ccte.api.bioactivity.repository;

import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import gov.epa.ccte.api.bioactivity.domain.SearchAssay;

@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface SearchAssayRepository extends JpaRepository<SearchAssay, Integer>{
    // For SearchAssayResource
	
	<T>List<T> findBySearchValueOrderByIdAsc(String searchValue, Class<T> type);
	
	
	// For SearchAssayService
	
	<T>List<T> findAllByModifiedValueIn(List<String> of, Class<T> type);

	<T>List<T> findByModifiedValueStartingWithOrderByIdAsc(String searchValue,Limit of, Class<T> type);

	<T>List<T> findByModifiedValueContainsOrderByIdAscSearchValue(String searchValue, Limit limit, Class<T> type);

}
