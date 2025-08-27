package gov.epa.ccte.api.bioactivity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import gov.epa.ccte.api.bioactivity.domain.BioactivityModel;

@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface BioactivityModelRepository extends JpaRepository<BioactivityModel, Long>{
	
    @Transactional(readOnly = true)
    List<BioactivityModel> findByDtxsid(String dtxsid);
    
}
