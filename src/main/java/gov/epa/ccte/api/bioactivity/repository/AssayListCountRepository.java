package gov.epa.ccte.api.bioactivity.repository;

import gov.epa.ccte.api.bioactivity.domain.AssayListCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface AssayListCountRepository extends JpaRepository<AssayListCount, Integer> {

    @Transactional(readOnly = true)
    AssayListCount findByAeid(Integer aeid);
}