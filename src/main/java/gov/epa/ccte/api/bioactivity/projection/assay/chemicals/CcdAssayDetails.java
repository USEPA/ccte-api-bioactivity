package gov.epa.ccte.api.bioactivity.projection.assay.chemicals;

public interface CcdAssayDetails {

	String getCasrn();

	Integer getCompoundId();

	Integer getGenericSubstanceId();

	String getPreferredName();

	Integer getActiveAssays();

	Long getCpdataCount();

	String getMolFormula();

	Double getMonoisotopicMass();

	Double getAverageMass();

	Double getPercentAssays();

	Integer getPubchemCount();

	Double getPubmedCount();

	String getStereo();

	Long getSourcesCount();

	Integer getQcLevel();

	String getQcLevelDesc();

	Integer getIsotope();

	Integer getMulticomponent();

	Integer getTotalAssays();

	Integer getPubchemCid();

	Long getRelatedSubstanceCount();

	Long getRelatedStructureCount();

	Integer getHasStructureImage();

	String getIupacName();

	String getSmiles();

	String getInchiString();

	String getInchikey();

	String getQcNotes();

	String getQsarReadySmiles();

	String getMsReadySmiles();

	String getIrisLink();

	String getPprtvLink();

	String getWikipediaArticle();

	Boolean getIsMarkush();

	String getDtxsid();

	String getDtxcid();

	String getToxcastSelect();

	Double getTop();

	Double getScaledTop();

	Double getAc50();

	Double getLogAc50();

	Double getHitc();

}