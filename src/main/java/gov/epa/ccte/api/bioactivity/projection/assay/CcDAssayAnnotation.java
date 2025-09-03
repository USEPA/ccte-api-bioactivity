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
    
    
	Void setAeid(Long aeid);
	Void setAssayComponentEndpointName(String assayComponentEndpointName);
	Void setAssayComponentEndpointDesc(String assayComponentEndpointDesc);
	Void setAssayFunctionType(String assayFunctionType);
	Void setNormalizedDataType(String normalizedDataType);
	Void setBurstAssay(Short burstAssay);
	Void setKeyPositiveControl(String keyPositiveControl);
	Void setSignalDirection(String signalDirection);
	Void setIntendedTargetType(String intendedTargetType);
	Void setIntendedTargetTypeSub(String intendedTargetTypeSub);
	Void setIntendedTargetFamily(String intendedTargetFamily);
	Void setIntendedTargetFamilySub(String intendedTargetFamilySub);
	Void setAssayComponentName(String assayComponentName);
	Void setAssayComponentDesc(String assayComponentDesc);
	Void setAssayComponentTargetDesc(String assayComponentTargetDesc);
	Void setParameterReadoutType(String parameterReadoutType);
	Void setAssayDesignType(String assayDesignType);
	Void setAssayDesignTypeSub(String assayDesignTypeSub);
	Void setBiologicalProcessTarget(String biologicalProcessTarget);
	Void setDetectionTechnologyType(String detectionTechnologyType);
    Void setDetectionTechnologyTypeSub(String detectionTechnologyTypeSub);
    Void setDetectionTechnology(String detectionTechnology);
	Void setKeyAssayReagentType(String keyAssayReagentType);
	Void setKeyAssayReagent(String keyAssayReagent);
	Void setTechnologicalTargetTypes(String technologicalTargetType);
	Void setTechnologicalTargetTypeSub(String technologicalTargetTypeSub);
	Void setAssayname(String assayName);
	Void setAssayDescs(String assayDesc);
	Void setTimepointHr(Double timepointHr);
	Void setOrganism(String organism);
	Void setTissue(String tissue);
	Void setCellFormat(String cellFormat);
	Void setCellFreeComponentSource(String cellFreeComponentSource);
	Void setCellShortName(String cellShortName);
	Void setCellGrowthMode(String cellGrowthMode);
	Void setAssayFootprint(String assayFootprint);
	Void setAssayFormatType(String assayFormatType);
	Void setAssayFormatTypeSub(String assayFormatTypeSub);
	Void setContentReadoutType(String contnentReadoutType);
	Void setDilutionSolvent(String dilutionSolvent);
	Void setDilutionSolventPercentMax(Double dilutionSolventPercentMax);
	Void setAssaySourceName(String assaySourceName);
	Void setAssaySourceLongName(String assaySourceLongName);
	Void setAssaySourceDesc(String assaySourceDesc);
	Void setToxCastAssayDescription(String toxCastAssayDescription);

}