package gov.epa.ccte.api.bioactivity.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AssayList implements Serializable {
    private String name;
    private String description;
}
