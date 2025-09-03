package gov.epa.ccte.api.bioactivity.repository;

import gov.epa.ccte.api.bioactivity.domain.AssayAgg;
import gov.epa.ccte.api.bioactivity.projection.assay.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface AssayAggRepository extends JpaRepository<AssayAgg, Long> {

	<T>List<T> findByAeid(Integer aeid);
	
@Query(value = """   
   			SELECT aeid,
             assay_component_endpoint_name as AssayComponentEndpointName,
             assay_component_endpoint_desc as AssayComponentEndpointDesc,
             assay_function_type           as AssayFunctionType,
             normalized_data_type          as NormalizedDataType,
             burst_assay                   as BurstAssay,
             key_positive_control          as KeyPositiveControl,
             signal_direction              as SignalDirection,
             intended_target_type          as IntendedTargetType,
             intended_target_type_sub      as IntendedTargetTypeSub,
             intended_target_family        as IntendedTargetFamily,
             intended_target_family_sub    as IntendedTargetFamilySub,
             assay_component_name          as AssayComponentName,
             assay_component_desc          as AssayComponentDesc,
             assay_component_target_desc   as AssayComponentTargetDesc,
             parameter_readout_type        as ParameterReadoutType,
             assay_design_type             as AssayDesignType,
             assay_design_type_sub         as AssayDesignTypeSub,
             biological_process_target     as BiologicalProcessTarget,
             detection_technology          as DetectionTechnology,
             detection_technology_type     as DetectionTechnologyType,
             detection_technology_type_sub as DetectionTechnologyTypeSub,
             key_assay_reagent_type        as KeyAssayReagentType,
             key_assay_reagent             as KeyAssayReagent,
             technological_target_type     as TechnologicalTargetType,
             technological_target_type_sub as TechnologicalTargetTypeSub,
             assay_name                    as AssayName,
             assay_desc                    as AssayDesc,
             timepoint_hr                  as TimepointHr,
             organism,
             tissue,
             cell_format                   as CellFormat,
             cell_free_component_source    as CellFreeComponentSource,
             cell_short_name               as CellShortName,
             cell_growth_mode              as CellGrowthMode,
             assay_footprint               as AssayFootprint,
             assay_format_type             as AssayFormatType,
             assay_format_type_sub         as AssayFormatTypeSub,
             content_readout_type          as ContentReadoutType,
             dilution_solvent              as DilutionSolvent,
             dilution_solvent_percent_max  as DilutionSolventPercentMax,
             assay_source_name             as AssaySourceName,
             assay_source_long_name        as AssaySourceLongName,
             assay_source_desc             as AssaySourceDesc,
             CONCAT('https://clowder.edap-cluster.com/files/', clowder_uid) AS ToxCastAssayDescription

      FROM invitro.mv_assay_annotation
			WHERE aeid = :aeid
			""", nativeQuery = true)
	List<CcDAssayAnnotation> findAnnotationByAeid(@Param("aeid") Integer aeid);

	@Query(value = """
			    SELECT aeid,
			           assay_component_endpoint_name,
			           element ->> 'pmid'      AS pmid,
			           element ->> 'author'    AS author,
			           element ->> 'citation'  AS citation,
			           element ->> 'doi'       AS doi,
			           element ->> 'other_id'  AS otherId,
			           element ->> 'oth_sourc' AS otherSource,
			           element ->> 'title'     AS title,
			           element ->> 'url'       AS url
			    FROM invitro.mv_assay_annotation maa,
			         jsonb_array_elements(maa.citations\\:\\:jsonb) AS element
			    WHERE maa.citations IS NOT NULL AND aeid = :aeid
			""", nativeQuery = true)
	List<CcdAssayCitation> findCitationsByAeid(@Param("aeid") Integer aeid);

	@Query(value = """
			    SELECT aeid,
			           element ->> 'entrez_gene_id' AS entrezGeneId,
			           element ->> 'gene_name'      AS geneName,
			           element ->> 'gene_symbol'    AS geneSymbol
			    FROM invitro.mv_assay_annotation maa,
			         jsonb_array_elements(maa.gene\\:\\:jsonb) AS element
			    WHERE gene IS NOT NULL AND aeid = :aeid
			""", nativeQuery = true)
	List<CcdAssayGene> findGeneByAeid(@Param("aeid") Integer aeid);

	@Query(value = """
		    select row_number() over (
		               order by assayRunType, levelApplied, methodName
		           ) as orderId,
		           assayRunType,
		           assayRunType,
		           methodName,
		           description,
		           levelApplied
		    from (
		        select aeid,
		               'multi'                    as assayRunType,
		               2                          as levelApplied,
		               element_mc2 ->> 'mc2_mthd' as methodName,
		               element_mc2 ->> 'desc'     as description
		        from invitro.mv_assay_annotation maa,
		             jsonb_array_elements(mc2_methods::jsonb) as element_mc2
		        where mc2_methods is not null
		        union
		        select aeid,
		               'multi',
		               3,
		               element_mc3 ->> 'mc3_mthd',
		               element_mc3 ->> 'desc'
		        from invitro.mv_assay_annotation maa,
		             jsonb_array_elements(mc3_methods::jsonb) as element_mc3
		        where mc3_methods is not null
		        union
		        select aeid,
		               'multi',
		               4,
		               element_mc4 ->> 'mc4_mthd',
		               element_mc4 ->> 'desc'
		        from invitro.mv_assay_annotation maa,
		             jsonb_array_elements(mc4_methods::jsonb) as element_mc4
		        where mc4_methods is not null
		        union
		        select aeid,
		               'multi',
		               5,
		               element_mc5 ->> 'mc5_mthd',
		               element_mc5 ->> 'desc'
		        from invitro.mv_assay_annotation maa,
		             jsonb_array_elements(mc5_methods::jsonb) as element_mc5
		        where mc5_methods is not null
		        union
		        select aeid,
		               'multi',
		               6,
		               element_mc6 ->> 'mc6_mthd',
		               element_mc6 ->> 'desc'
		        from invitro.mv_assay_annotation maa,
		             jsonb_array_elements(mc6_methods::jsonb) as element_mc6
		        where mc6_methods is not null
		        union
		        select aeid,
		               'single',
		               1,
		               element_sc1 ->> 'sc1_mthd',
		               element_sc1 ->> 'desc'
		        from invitro.mv_assay_annotation maa,
		             jsonb_array_elements(sc1_methods::jsonb) as element_sc1
		        where element_sc1 ->> 'sc1_mthd' is not null
		           or element_sc1 ->> 'desc' is not null
		        union
		        select aeid,
		               'single',
		               2,
		               element_sc2 ->> 'sc2_mthd',
		               element_sc2 ->> 'desc'
		        from invitro.mv_assay_annotation maa,
		             jsonb_array_elements(sc2_methods::jsonb) as element_sc2
		        where element_sc2 ->> 'sc2_mthd' is not null
		           or element_sc2 ->> 'desc' is not null
		    ) tcpl
		    where aeid = :aeid
		    order by assayRunType, levelApplied, methodName
		    """, nativeQuery = true)
		List<CcdTcplData> findTcplByAeid(@Param("aeid") Integer aeid);

	@Query(value = """
		    SELECT 
		        row_number() OVER (
		            ORDER BY 
		                reagent ->> 'reagent_name_value_type', 
		                reagent ->> 'reagent_name_value'
		        ) AS orderId,
		        reagent ->> 'reagent_name_value_type' AS reagentType,
		        reagent ->> 'reagent_name_value' AS reagentValue,
		        reagent ->> 'culture_or_assay' AS cultureOrAssay
		    FROM invitro.mv_assay_annotation maa,
		         jsonb_array_elements(assay_reagent\\:\\:jsonb) AS reagent
		    WHERE maa.aeid = :aeid
		""", nativeQuery = true)
		List<CcdReagents> findReagentByAeid(@Param("aeid") Integer aeid);

}