package gov.epa.ccte.api.bioactivity.repository;

import gov.epa.ccte.api.bioactivity.domain.BioactivitySc;
import gov.epa.ccte.api.bioactivity.projection.assay.CcdSingleConcData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface BioactivityScRepository extends JpaRepository<BioactivitySc, Long> {

    @Query(value = """
            select cd.preferred_name                as preferredName,
                   sc.dsstox_substance_id           as dtxsid,
                   sc.aeid,
                   an.assay_component_endpoint_name as endpointName,
                   casn,
                   bmad,
                   coff,
                   hitc,
                   max_med                          as maxMedVal
            from invitro.mv_bioactivity_sc sc
                     join invitro.mv_assay_annotation an on sc.aeid = an.aeid
                     left join ch.v_chemical_details cd on cd.dtxsid = sc.dsstox_substance_id
            where sc.aeid = :aeid and sc.chid_rep = 1
            """, nativeQuery = true)
    List<CcdSingleConcData> getSigleConcDataByAeid(Integer aeid);

    List<BioactivitySc> findByAeidAndChidRep(Integer aeid, Short chidRep);
}