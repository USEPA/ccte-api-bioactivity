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
	Void setCellViabilityAssay(Short cellViabilityAssay);
	Void setAcid(Integer acid);
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
	Void setAid(Integer aid);
	Void setAssayname(String assayName);
	Void setAssayDescs(String assayDesc);
	Void setTimepointHr(Double timepointHr);
	Void setOrganismId(Integer organismId);
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
	Void setAsid(Integer asid);
	Void setAssaySourceName(String assaySourceName);
	Void setAssaySourceLongName(String assaySourceLongName);
	Void setAssaySourceDesc(String assaySourceDesc);
	Void setGene(Gene gene);
	Void setAssayList(AssayList assayList);
	Void setCitations(Citation citation);

}