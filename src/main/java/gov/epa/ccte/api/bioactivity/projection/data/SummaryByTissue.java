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
       
    Void setChemicalName(String chemicalName);
    Void setDtxsid(String dtxsid);
    Void setHitCall(String hitCall);
    Void setContinuousHitCall(Float continuousHitCall);
    Void setAC50(Float ac50);
    Void setLogAC50(Float logAC50);
    Void setCutOff(Float cutOff);
    Void setACC(Float acc);
    Void setMaxMedConc(Float maxMedConc);
    Void setIntendedTargetFamily(String intendedTargetFamily);
    Void setTissue(String tissue);
}
