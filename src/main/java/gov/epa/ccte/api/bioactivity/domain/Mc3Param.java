package gov.epa.ccte.api.bioactivity.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class Mc3Param implements Serializable {

    private BigDecimal[] conc;
    private BigDecimal[] resp;

    
}
