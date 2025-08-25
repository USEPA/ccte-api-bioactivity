package gov.epa.ccte.api.bioactivity.projection.assay;

/**
 * Projection for {@link gov.epa.ccte.api.bioactivity.domain.AssayAnnotation}
 */
public interface CcDAssayAnnotation {
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

    String getAssayName();

    String getAssayDesc();

    Double getTimepointHr();

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

    String getAssaySourceName();

    String getAssaySourceLongName();

    String getAssaySourceDesc();
    
    String getToxCastAssayDescription();

}