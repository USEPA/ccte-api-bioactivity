package gov.epa.ccte.api.bioactivity.repository;

import gov.epa.ccte.api.bioactivity.domain.BioactivityData;
import gov.epa.ccte.api.bioactivity.projection.assay.chemicals.CcdAssayDetails;
import gov.epa.ccte.api.bioactivity.projection.data.AedRawDataProjection;
import gov.epa.ccte.api.bioactivity.projection.data.SummaryByTissue;
import gov.epa.ccte.api.bioactivity.projection.data.ToxcastSummaryPlot;

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

	@Query(value = "select distinct dsstox_substance_id from invitro.mv_bioactivity where aeid = :aeid and dsstox_substance_id is not null", nativeQuery = true)
	List<String> getChemicalsByAeid(Integer aeid);
	
	@Query(value = """
		    SELECT
		        cd.dtxsid AS dtxsid,
		        cd.dtxcid AS dtxcid,
		        cd.casrn AS casrn,
		        cd.compound_id AS compoundId,
		        cd.generic_substance_id AS genericSubstanceId,
		        cd.preferred_name AS preferredName,
		        cd.mol_formula AS molFormula,
		        cd.monoisotopic_mass AS monoisotopicMass,
		        cd.pubchem_cid AS pubchemCid,
		        cd.smiles AS smiles,
		        cd.inchi_string AS inchiString,
		        cd.inchikey AS inchikey,
		        cd.average_mass AS averageMass,
		        cd.qsar_ready_smiles AS qsarReadySmiles,
		        cd.ms_ready_smiles AS msReadySmiles,
		        cd.qc_level AS qcLevel,
		        cd.qc_level_desc AS qcLevelDesc,
		        cd.water_solubility_test AS waterSolubilityTest,
		        cd.density AS density,
		        cd.boiling_point_degc_test_pred AS boilingPointDegcTestPred,
		        cd.melting_point_degc_test_pred AS meltingPointDegcTestPred,
		        cd.octanol_water_partition AS octanolWaterPartition,
		        cd.tetrahymena_pyriformis AS tetrahymenaPyriformis,
		        cd.toxval_data AS toxvalData,
		        cd.related_substance_count AS relatedSubstanceCount,
		        cd.related_structure_count AS relatedStructureCount,
		        cd.total_assays AS totalAssays,
		        cd.active_assays AS activeAssays,
		        cd.cpdata_count AS cpdataCount,
		        cd.pubchem_count AS pubchemCount,
		        cd.pubmed_count AS pubmedCount,
		        cd.sources_count AS sourcesCount,
		        cd.stereo AS stereo,
		        cd.isotope AS isotope,
		        cd.multicomponent AS multicomponent,
		        cd.has_structure_image AS hasStructureImage,
		        cd.iupac_name AS iupacName,
		        cd.iris_link AS irisLink,
		        cd.pprtv_link AS pprtvLink,
		        cd.wikipedia_article AS wikipediaArticle,
		        cd.expocat AS expocat,
		        cd.expocat_median_prediction AS expocatMedianPrediction,
		        cd.nhanes AS nhanes,
		        cd.qc_notes AS qcNotes,
		        cd.mol_file AS molFile,
		        cd.mrv_file AS mrvFile,
		        cd.descriptor_string_tsv AS descriptorStringTsv,
		        cd.flash_point_degc_test_pred AS flashPointDegcTestPred,
		        cd.devtox_test_pred AS devtoxTestPred,
		        cd.viscosity_cp_cp_test_pred AS viscosityCpCpTestPred,
		        cd.vapor_pressure_mmhg_test_pred AS vaporPressureMmhgTestPred,
		        cd.vapor_pressure_mmhg_opera_pred AS vaporPressureMmhgOperaPred,
		        cd.soil_adsorption_coefficient AS soilAdsorptionCoefficient,
		        cd.biodegradation_half_life_days AS biodegradationHalfLifeDays,
		        cd.bioconcentration_factor_test_pred AS bioconcentrationFactorTestPred,
		        cd.bioconcentration_factor_opera_pred AS bioconcentrationFactorOperaPred,
		        cd.atmospheric_hydroxylation_rate AS atmosphericHydroxylationRate,
		        cd.ames_mutagenicity_test_pred AS amesMutagenicityTestPred,
		        cd.pkaa_opera_pred AS pkaaOperaPred,
		        cd.pkab_opera_pred AS pkabOperaPred,
		        cd.logd5_5 AS logd55,
		        cd.logd7_4 AS logd74,
		        cd.ready_bio_deg AS readyBioDeg,
		        cd.is_markush AS isMarkush,

		        -- Assay fields from mc5_param JSON
		        CASE
                    WHEN b.hitc >= 0.9 THEN 'Active'
                    WHEN b.hitc >= 0 AND b.hitc < 0.9 THEN 'Inactive'
                    WHEN b.hitc < 0 THEN 'NA'
                    ELSE 'NA'
                END AS hitc,
		        b.mc5_param->>'top' AS top,
		        b.mc5_param->>'top_over_cutoff' AS scaledTop,
		        b.mc5_param->>'ac50' AS ac50,
		        CASE
		            WHEN b.mc5_param->>'ac50' IS NOT NULL
		            THEN LOG(CAST(b.mc5_param->>'ac50' AS float))
		        END AS logAc50

		    FROM ch.v_chemical_details cd
		    JOIN invitro.mv_bioactivity b
		        ON b.dsstox_substance_id = cd.dtxsid
		    WHERE b.aeid = :aeid
		""", nativeQuery = true)
		List<CcdAssayDetails> getFullCcdAssayDetailsByAeid(
		    @Param("aeid") Integer aeid
		);
}
