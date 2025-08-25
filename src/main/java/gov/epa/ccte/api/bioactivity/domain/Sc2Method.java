package gov.epa.ccte.api.bioactivity.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sc2Method implements Serializable{

    private String desc;
    private String sc2_mthd;
    private Integer sc2_mthd_id;
}
