package gov.epa.ccte.api.bioactivity.projection.assay;

public interface CcdTcplData {

    Integer getOrderId();
    String getAssayRunType();
    Integer getLevelApplied();
    String getMethodName();
    String getDescription();
    
    Void setOrderId(Integer orderId);
    Void setAssayRunType(String assayRunType);
    Void setLevelApplied(Integer levelApplied);
    Void setMethodName(String methodName);
    Void setDescription(String description);
}
