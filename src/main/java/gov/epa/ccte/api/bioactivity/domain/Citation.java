package gov.epa.ccte.api.bioactivity.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Citation implements Serializable {
    private String doi;
    private String url;
    private Integer pmid;
    private String title;
    private String author;
    private String citation;
    @JsonAlias("other_id")
    private String otherId;
    @JsonAlias("citation_id")
    private Integer citationId;
    @JsonAlias("other_source")
    private String otherSource;
}
