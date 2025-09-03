package gov.epa.ccte.api.bioactivity.projection.assay;

public interface CcdAssayCitation {
	
	Long getAeid();
	String getAssayComponentEndpointName();
	String getPmid();
	String getAuthor();
	String getCitation();
	String getDoi();
	String getOtherId();
	String getOtherSource();
	String getTitle();
	String getUrl();
	
	Void setAeid(Long aeid);
	Void setAssayComponentEndpointName(String assayComponentEndpointName);
	Void setPmid(String pmid);
	Void setAuthor(String author);
	Void setCitation(String citation);
	Void setDoi(String doi);
	Void setOtherId(String otherId);
	Void setOtherSource(String otherSource);
	Void setTitle(String title);
	Void setUrl(String url);
}
