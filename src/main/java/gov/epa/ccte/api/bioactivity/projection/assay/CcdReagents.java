package gov.epa.ccte.api.bioactivity.projection.assay;

public interface CcdReagents {

    Integer getOrderId();
    String getReagentType();
    String getReagentValue();
    String getCultureOrAssay();
    
    Void setOrderId(Integer orderId);
    Void setReagentType(String reagentType);
    Void setReagentValue(String reagentValue);
    Void setCultureOrAssay(String cultureOrAssay);

}
