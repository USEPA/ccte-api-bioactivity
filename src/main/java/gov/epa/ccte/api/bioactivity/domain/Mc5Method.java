package gov.epa.ccte.api.bioactivity.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mc5Method implements Serializable {

    private String desc;
    private String mc5_mthd;
    private Integer mc5_mthd_id;
}
