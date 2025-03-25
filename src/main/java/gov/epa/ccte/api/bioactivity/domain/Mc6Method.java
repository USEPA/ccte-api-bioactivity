package gov.epa.ccte.api.bioactivity.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mc6Method implements Serializable{
	
    private String desc;
    private String mc6_mthd;
    private Integer mc6_mthd_id;
}
