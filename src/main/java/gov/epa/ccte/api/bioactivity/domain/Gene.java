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
public class Gene implements Serializable {

    @JsonAlias("gene_id")
    private Integer geneId;
    @JsonAlias("gene_name")
    private String geneName;
    private String description;
    @JsonAlias("gene_symbol")
    private String geneSymbol;
    @JsonAlias("organism_id")
    private Integer organismId;
    @JsonAlias("track_status")
    private String trackStatus;
    @JsonAlias("entrez_gene_id")
    private Integer entrezGeneId;
    @JsonAlias("official_symbol")
    private String officialSymbol;
    @JsonAlias("official_full_name")
    private String officialFullName;
    @JsonAlias("uniprot_accession_number")
    private String uniprotAccessionNumber;
}
