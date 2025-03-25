package gov.epa.ccte.api.bioactivity.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mc4Method implements Serializable {

    private String desc;
    private String mc4_mthd;
    private Integer mc4_mthd_id;
}
