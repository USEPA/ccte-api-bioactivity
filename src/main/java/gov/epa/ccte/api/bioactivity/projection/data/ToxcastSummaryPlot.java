package gov.epa.ccte.api.bioactivity.projection.data;

public interface ToxcastSummaryPlot {

    String getAeid();
    Float getHitc();
    Float getAC50();
    Float getTopOverCutoff();

    Void setAeid(String aeid);
    Void setHitc(Float hitc);
    Void setAC50(Float ac50);
    Void setTopOverCutoff(Float topOverCutoff);
}
