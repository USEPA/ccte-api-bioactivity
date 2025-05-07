package gov.epa.ccte.api.bioactivity.projection.assay;

public interface ToxcastSummaryPlot {

    String getAeid();
    Float getHitc();
    Float getAC50();
    Float getTopOverCutoff();

}
