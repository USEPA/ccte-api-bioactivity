package gov.epa.ccte.api.bioactivity.projection.assay;

public interface CcdAssayGene {
	
	Integer getEntrezGeneId();
	String getGeneName();
	String getGeneSymbol();
	
	Void setEntrezGeneId(Integer entrezGeneId);
	Void setGeneName(String geneName);
	Void setGeneSymbol(String geneSymbol);
	
	Void setServiceEntrezGeneId(String entrezGeneId);

}
