package gov.epa.ccte.api.bioactivity.projection.data;

import gov.epa.ccte.api.bioactivity.domain.Mc3Param;
import gov.epa.ccte.api.bioactivity.domain.Mc6Param;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Projection for {@link gov.epa.ccte.api.bioactivity.domain.BioactivityData}
 */
public interface BioactivityDataAll extends BioactivityDataBase {
    Long getM4id();

    Long getAeid();

    String getSpid();

    Integer getChid();

    String getCasn();

    String getChnm();

    String getDtxsid();

    Double getBmad();

    Double getRespMax();

    Double getRespMin();

    Double getMaxMean();

    Double getMaxMeanConc();

    Double getMaxMed();

    Double getMaxMedConc();

    Double getConcMax();

    Double getConcMin();

    Integer getNconc();

    Integer getNpts();

    Double getNrep();

    Integer getNmedGtblPos();
    
    Integer getNmedGtblNeg();

    Long getM5id();

    String getModl();

    Double getHitc();

    Short getFitc();

    Double getCoff();

    Double getActp();

    Short getModelType();

    Short getChidRep();

    Double getStkc();

    String getStkcUnit();

    String getTestedConcUnit();

    Mc3Param getMc3Param();

    Map<String, BigDecimal> getMc4Param();

    Map<String, BigDecimal> getMc5Param();

    Mc6Param getMc6Param();
}