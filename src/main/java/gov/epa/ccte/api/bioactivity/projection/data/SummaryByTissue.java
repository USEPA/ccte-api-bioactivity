package gov.epa.ccte.api.bioactivity.projection.data;

public interface SummaryByTissue {

    String getChemicalName();
    String getDtxsid();
    String getHitCall();
    Float getContinuousHitCall();
    Float getAC50();
    Float getLogAC50();
    Float getCutOff();
    Float getACC();
    Float getMaxMedConc();
    String getIntendedTargetFamily();
    String getTissue();
       
}
