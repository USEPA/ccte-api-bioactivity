package gov.epa.ccte.api.bioactivity.projection.assay;

import gov.epa.ccte.api.bioactivity.domain.AssayAnnotation;
import gov.epa.ccte.api.bioactivity.domain.AssayList;
import gov.epa.ccte.api.bioactivity.domain.Citation;
import gov.epa.ccte.api.bioactivity.domain.Gene;

import java.util.List;

/**
 * Projection for {@link AssayAnnotation}
 */
public interface AssayAll extends AssayBase{
    Long getAeid();

    String getAssayComponentEndpointName();

    String getAssayComponentEndpointDesc();

    String getAssayFunctionType();

    String getNormalizedDataType();

    Short getBurstAssay();

    String getKeyPositiveControl();

    String getSignalDirection();

    String getIntendedTargetType();

    String getIntendedTargetTypeSub();

    String getIntendedTargetFamily();

    String getIntendedTargetFamilySub();

    Short getCellViabilityAssay();

    Integer getAcid();

    String getAssayComponentName();

    String getAssayComponentDesc();

    String getAssayComponentTargetDesc();

    String getParameterReadoutType();

    String getAssayDesignType();

    String getAssayDesignTypeSub();

    String getBiologicalProcessTarget();

    String getDetectionTechnologyType();

    String getDetectionTechnologyTypeSub();

    String getDetectionTechnology();

    String getKeyAssayReagentType();

    String getKeyAssayReagent();

    String getTechnologicalTargetType();

    String getTechnologicalTargetTypeSub();

    Integer getAid();

    String getAssayName();

    String getAssayDesc();

    Double getTimepointHr();

    Integer getOrganismId();

    String getOrganism();

    String getTissue();

    String getCellFormat();

    String getCellFreeComponentSource();

    String getCellShortName();

    String getCellGrowthMode();

    String getAssayFootprint();

    String getAssayFormatType();

    String getAssayFormatTypeSub();

    String getContentReadoutType();

    String getDilutionSolvent();

    Double getDilutionSolventPercentMax();

    Integer getAsid();

    String getAssaySourceName();

    String getAssaySourceLongName();

    String getAssaySourceDesc();

    List<Gene> getGene();

    List<AssayList> getAssayList();

    List<Citation> getCitations();
}