package gov.epa.ccte.api.bioactivity.projection.assay;

public interface AssayEndpointsList {
	
    Long getAeid();
    String getAssayComponentEndpointName();
    String getMultiConcActives();
    String getSingleConcActive();
	String getAssayComponentEndpointDesc();
    String getGeneSymbol();
    
    Void setAeid(Long aeid);
    Void setAssayComponentEndpointName(String assayComponentEndpointName);
    Void setMultiConcActives(String multiConcActives);
    Void setSingleConcActive(String singleConcActive);
    Void setAssayComponentEndpointDesc(String assayComponentEndpointDesc);
    Void setGeneSymbol(String geneSymbol);
}
