package gov.epa.ccte.api.bioactivity.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Mc6Param implements Serializable {
    private String[] flag;
    @JsonAlias("mc6_mthd_id")
    private Integer[] mc6MthdId;
}
