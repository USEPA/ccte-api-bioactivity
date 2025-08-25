package gov.epa.ccte.api.bioactivity.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sc1Method implements Serializable{
	
    private String desc;
    private String sc1_mthd;
    private Integer sc1_mthd_id;
}
