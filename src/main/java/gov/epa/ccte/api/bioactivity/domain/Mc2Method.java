package gov.epa.ccte.api.bioactivity.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mc2Method implements Serializable {

    private String desc;
    private String mc2_mthd;
    private Integer exec_ordr;
    private Integer mc2_mthd_id;
}
