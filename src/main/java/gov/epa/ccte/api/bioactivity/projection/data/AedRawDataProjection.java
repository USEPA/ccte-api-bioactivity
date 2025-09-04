package gov.epa.ccte.api.bioactivity.projection.data;

public interface AedRawDataProjection {

	String getDsstoxSubstanceId();
	String getPreferredName();
	Integer getAeid();
    String getAenm();
	String getMc7Param();
	
	Void setDsstoxSubstanceId(String dsstoxSubstanceId);
	Void setPreferredName(String preferredName);
	Void setAeid(Integer aeid);
	Void setAenm(String aenm);
	Void setMc7Param(String mc7Param);
}
