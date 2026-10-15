package gov.epa.ccte.api.bioactivity.projection.assay;

public interface CcdAssayGene {
	
	Integer getEntrezGeneId();
	String getGeneName();
        @Deprecated
	String getGeneSymbol();
        String getOfficialSymbol();
	
	Void setEntrezGeneId(Integer entrezGeneId);
	Void setGeneName(String geneName);
        @Deprecated
	Void setGeneSymbol(String geneSymbol);
	Void setOfficialSymbol(String officialSymbol);
	
	Void setServiceEntrezGeneId(String entrezGeneId);

}
