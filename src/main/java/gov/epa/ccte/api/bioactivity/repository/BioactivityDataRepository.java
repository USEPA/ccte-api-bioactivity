package gov.epa.ccte.api.bioactivity.repository;

import gov.epa.ccte.api.bioactivity.domain.BioactivityData;
import gov.epa.ccte.api.bioactivity.projection.data.AedRawDataProjection;
import gov.epa.ccte.api.bioactivity.projection.data.SummaryByTissue;
import gov.epa.ccte.api.bioactivity.projection.assay.ToxcastSummaryPlot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface BioactivityDataRepository extends JpaRepository<BioactivityData, Long> {
    @Transactional(readOnly = true)
    <T>List<T> findByDtxsid(String dtxsid, Class<T> type);
    
    @Transactional(readOnly = true)
    <T>List<T> findByDtxsidInOrderByDtxsidAsc(String[] dtxsids, Class<T> type);

    @Transactional(readOnly = true)
    <T> T findByM4id(Integer m4id, Class<T> type);

    @Transactional(readOnly = true)
    <T>List<T> findByM4idInOrderByM4idAsc(String[] spids, Class<T> type);

    @Transactional(readOnly = true)
    <T>List<T> findByAeid(Integer aeid, Class<T> type);
    
    @Transactional(readOnly = true)
    <T>List<T> findByAeidInOrderByAeidAsc(String[] aeids, Class<T> type);

    @Transactional(readOnly = true)
    <T>List<T> findBySpid(String spid, Class<T> type);
    
    @Transactional(readOnly = true)
    <T>List<T> findBySpidInOrderBySpidAsc(String[] spids, Class<T> type);
    
	@Query(value = """
			    SELECT b.dsstox_substance_id AS dsstoxSubstanceId,
			           d.preferred_name AS preferredName,
			           b.aeid AS aeid,
			           b.mc7_param AS mc7Param
			    FROM invitro.mv_bioactivity b
			    JOIN ch.v_chemical_details d ON b.dsstox_substance_id = d.dtxsid
			    WHERE b.dsstox_substance_id = :dtxsid AND b.mc7_param IS NOT NULL
			""", nativeQuery = true)
	List<AedRawDataProjection> findAedDataByDtxsid(@Param("dtxsid") String dtxsid);

	@Query(value = """
			SELECT
			    b.dsstox_substance_id   AS dsstoxSubstanceId,
			    d.preferred_name        AS preferredName,
			    b.aeid                  AS aeid,
			    b.mc7_param             AS mc7Param,
			    a.assay_component_endpoint_name                 AS aenm
			FROM invitro.mv_bioactivity b
			  JOIN ch.v_chemical_details d
			    ON b.dsstox_substance_id = d.dtxsid
			  JOIN invitro.mv_assay_annotation a
			    ON b.aeid = a.aeid
			WHERE b.dsstox_substance_id IN (:dtxsids)
			  AND b.mc7_param IS NOT NULL
			ORDER BY b.dsstox_substance_id
			""", nativeQuery = true)
	List<AedRawDataProjection> findAedDataByDtxsidIn(@Param("dtxsids") List<String> dtxsids);

    @Query(value = """
    		SELECT
                bio.chnm AS chemicalName,
                bio.dsstox_substance_id AS dtxsid,
                bio_elem->>'top' AS top,
                bio_elem->>'ac50' AS AC50,
                bio_elem->>'acc' AS ACC,
                bio.max_med_conc AS maxMedConc,
                bio.coff AS cutOff,
                maa.intended_target_family AS intendedTargetFamily,
                maa.tissue AS tissue,
                bio.hitc AS continuousHitCall,
                        CASE
            WHEN bio_elem->>'ac50' IS NOT NULL THEN LOG(CAST(bio_elem->>'ac50' AS float))
            END AS logAC50,
            CASE
                WHEN bio.hitc >= 0.9 THEN 'Active'
                Else 'Inactive'
            END AS hitCall
            FROM 
                invitro.mv_bioactivity bio
            JOIN 
                invitro.mv_assay_annotation maa 
            ON 
                bio.aeid = maa.aeid, 
                json_array_elements(json_build_array(bio.mc5_param)) AS bio_elem
            WHERE
                bio.dsstox_substance_id = :dtxsid AND maa.tissue = :tissue
            ORDER BY bio.hitc DESC;
    		""", nativeQuery = true)
    List<SummaryByTissue> findByDtxsidAndTissue(@Param("dtxsid")String dtxsid, @Param("tissue")String tissue);
    
    @Query(value = """
    		SELECT
                bio.aeid AS aeid,
                json_array_elements(json_build_array(bio.mc5_param))->>'top_over_cutoff' AS topOverCutoff,
                json_array_elements(json_build_array(bio.mc5_param))->>'ac50' AS AC50,
                bio.hitc AS hitc
            FROM 
                invitro.mv_bioactivity bio
            WHERE
                bio.dsstox_substance_id = :dtxsid
    		""", nativeQuery = true)
    List<ToxcastSummaryPlot> findToxcastSummaryPlotByDtxsid(@Param("dtxsid")String dtxsid);

	@Query(value = "select distinct dsstox_substance_id from invitro.mv_bioactivity where aeid = :aeid ", nativeQuery = true)
	List<String> getChemicalsByAeid(Integer aeid);
}
