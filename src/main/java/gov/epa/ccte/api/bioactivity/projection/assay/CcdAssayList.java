package gov.epa.ccte.api.bioactivity.projection.assay;


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
}
