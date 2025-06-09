package gov.epa.ccte.api.bioactivity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import gov.epa.ccte.api.bioactivity.domain.BioactivityAgg;

@RepositoryRestResource(exported = false)
public interface BioactivityAggRepository extends JpaRepository<BioactivityAgg, Integer>{

    @Query(value = "select distinct dsstox_substance_id from invitro41.mv_bioactivity where aeid = :aeid ", nativeQuery = true)
    List<String> getChemicalsByAeid(Integer aeid);

}
