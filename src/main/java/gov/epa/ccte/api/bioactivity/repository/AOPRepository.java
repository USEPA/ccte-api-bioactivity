package gov.epa.ccte.api.bioactivity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import gov.epa.ccte.api.bioactivity.domain.AOP;

@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface AOPRepository extends JpaRepository<AOP, Integer> {

    @Transactional(readOnly = true)
    <T>List<T> findByToxcastAeid(Integer toxcastAeid);
    
    @Transactional(readOnly = true)
    <T>List<T> findByEntrezGeneId(Integer entrezGeneId);
    
    @Transactional(readOnly = true)
    <T>List<T> findByEventNumber(Integer eventNumber);
}
