package gov.epa.ccte.api.bioactivity.projection.assay;

import gov.epa.ccte.api.bioactivity.domain.AssayList;

public interface CcdAssayList extends AssayBase {
	
    String getVendorKey();    
    String getVendorName();
    String getAssayName();    
    Long getAeid();    
    String getAssayComponentName();    
    String getAssayComponentEndpointName();    
    String getAssayComponentEndpointDesc();    
    String getCcdAssayDetail();    
    String getEntrezGeneId();    
    String getGeneName();    
    String getGeneSymbol();    
    String getCommonName();    
    String getTaxonName();    
    String getAssayList();    
    Integer getSingleConcChemicalCountActive();    
    Integer getSingleConcChemicalCountTotal();    
    Integer getMultiConcChemicalCountActive();    
    Integer getMultiConcChemicalCountTotal();
    
    Void setVendorKey(String vendorKey);
    Void setVendorName(String vendorName);
    Void setAssayName(String assayName);
    Void setAeid(Long aeid);
    Void setAssayComponentName(String assayComponentName);
    Void setAssayComponentEndpointName(String assayComponentEndpontName);
    Void setAssayComponentEndpontDesc(String assayComponentEndpointDesc);
    Void setCcdAssayDetail(String ccdAssayDetail);
    Void setcommonName(String commonName);
    Void setTaxonName(String taxonName);
    Void setSingleConcChemicalCountActive(Integer singleConcChemicalCountActive);
    Void setSingleConcChemicalCountTotal(Integer singleConcChemicalCountTotal);    
    Void setMultiConcChemicalCountActive(Integer multiConcChemicalCountActive);    
    Void setMultiConcChemicalCountTotal(Integer multiConcChemicalCountTotal);
    
	Void setGene(CcdAssayGene gene);
	Void setParsedAssayList(AssayList assayList);
    Void setSingleConc(CcdAssayList singleConc);
    Void setMulticonc(CcdAssayList multiconc);
    
}
