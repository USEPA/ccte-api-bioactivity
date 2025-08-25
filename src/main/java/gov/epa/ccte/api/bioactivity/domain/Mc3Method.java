package gov.epa.ccte.api.bioactivity.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mc3Method implements Serializable {

    private String desc;
    private String mc3_mthd;
    private Integer mc3_mthd_id;
}
