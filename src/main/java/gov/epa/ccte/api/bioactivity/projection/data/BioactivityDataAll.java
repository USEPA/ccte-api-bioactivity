package gov.epa.ccte.api.bioactivity.projection.data;

import gov.epa.ccte.api.bioactivity.domain.Mc3Param;
import gov.epa.ccte.api.bioactivity.domain.Mc6Param;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Projection for {@link gov.epa.ccte.api.bioactivity.domain.BioactivityData}
 */
public interface BioactivityDataAll extends BioactivityDataBase {
	
    Long getM4id();
    Long getAeid();
    String getSpid();
    Integer getChid();
    String getCasn();
    String getChnm();
    String getDtxsid();
    Double getBmad();
    Double getRespMax();
    Double getRespMin();
    Double getMaxMean();
    Double getMaxMeanConc();
    Double getMaxMed();
    Double getMaxMedConc();
    Double getConcMax();
    Double getConcMin();
    Integer getNconc();
    Integer getNpts();
    Double getNrep();
    Integer getNmedGtblPos();
    Integer getNmedGtblNeg();
    Long getM5id();
    String getModl();
    Double getHitc();
    Short getFitc();
    Double getCoff();
    Double getActp();
    Short getModelType();
    Short getChidRep();
    Double getStkc();
    String getStkcUnit();
    String getTestedConcUnit();
    Mc3Param getMc3Param();
    Map<String, BigDecimal> getMc4Param();
    Map<String, BigDecimal> getMc5Param();
    Mc6Param getMc6Param();
    
    Void setM4id(Long m4id);
    Void setAeid(Long aeid);
    Void setSpid(String spid);
    Void setChid(Integer chid);
    Void setCasn(String casn);
    Void setChnm(String chnm);
    Void setDtxsid(String dtxsid);
    Void setBmad(Double bmad);
    Void setRespMax(Double respMax);
    Void setRespMin(Double respMin);
    Void setMaxMean(Double maxMean);
    Void setMaxMeanConc(Double maxMeanConc);
    Void setMaxMed(Double maxMed);
    Void setMaxMedConc(Double maxMedConc);
    Void setConcMax(Double concMax);
    Void setConcMin(Double concMin);
    Void setNconc(Integer nconc);
    Void setNpts(Integer npts);
    Void setNrep(Double nrep);
    Void setNmedGtblPos(Integer nmedGtblPos);
    Void setNmedGtblNeg(Integer nmedGtblNeg);
    Void setM5id(Long m5id);
    Void setModl(String modl);
    Void setHitc(Double hitc);
    Void setFitc(Short fitc);
    Void setCoff(Double coff);
    Void setActp(Double actp);
    Void setModelType(Short modelType);
    Void setChidRep(Short chidRep);
    Void setStkc(Double stkc);
    Void setStkcUnit(String stkcUnit);
    Void setTestedConcUnit(String testedConcUnit);
    Void setMc3Param(Mc3Param mc3Param);
    Void setMc4Param(Map<String, BigDecimal> mc4Param);
    Void setMc5Param(Map<String, BigDecimal> mc5Param);
    Void setMc6Param(Mc6Param mc6Param);
}