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
}
