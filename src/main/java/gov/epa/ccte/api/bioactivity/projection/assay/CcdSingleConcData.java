package gov.epa.ccte.api.bioactivity.projection.assay;

/**
 * Projection for {@link gov.epa.ccte.api.bioactivity.domain.BioactivitySc}
 */
public interface CcdSingleConcData {
	
    Long getS2id();
    String getPreferredName();
    String getDtxsid();
    Integer getAeid();
    String getEndpointName();
    String getCasn();
    Double getBmad();
    Double getMaxMedVal();
    Double getCoff();
    Double getHitc();
    
    Void setS2id(Long s2id);
    Void setPreferredName(String preferredName);
    Void setDtxsid(String dtxsid);
    Void setAeid(Integer aeid);
    Void setEndpointName(String endpointName);
    Void setCasn(String casn);
    Void setBmad(Double bmad);
    Void setMaxMedVal(Double maxMedVal);
    Void setCoff(Double coff);
    Void setHitc(Double hitc);
    
}