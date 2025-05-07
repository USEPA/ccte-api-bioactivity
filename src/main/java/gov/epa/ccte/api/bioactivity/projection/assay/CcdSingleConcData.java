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
}