package gov.epa.ccte.api.bioactivity.domain;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mv_toxcast_aop_map", schema = "aop")
public class AOP {
	
    @Id
    private Integer  id;
    
    @Column(name = "toxcast_aeid")
    private Integer toxcastAeid;
    
    @Column(name = "entrez_gene_id")
    private Integer entrezGeneId;
    
    @Column(name = "event_number")
    private Integer eventNumber;
    
    @Column(name = "event_link")
    private String eventLink;
    
    @Column(name = "aop_number")
    private Integer aopNumber;
    
    @Column(name = "aop_link")
    private String aopLink;
}
