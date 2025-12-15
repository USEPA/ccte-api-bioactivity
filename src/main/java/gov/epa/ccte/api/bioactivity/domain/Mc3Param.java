package gov.epa.ccte.api.bioactivity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Mc3Param implements Serializable {

    private BigDecimal[] conc;
    private BigDecimal[] resp;

    
}
