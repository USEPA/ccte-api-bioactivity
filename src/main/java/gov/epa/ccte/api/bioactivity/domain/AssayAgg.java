package gov.epa.ccte.api.bioactivity.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mv_assay_agg", schema = "invitro")
public class AssayAgg {
    
    @Id
    @Column(name = "aeid")
    @Schema(description = "Assay component endpoint ID")
    private Long aeid;

    @Size(max = 23)
    @Column(name = "active_mc")
    private Integer activeMc;

    @Column(name = "total_mc")
    private Long totalMc;
    
    @Size(max = 23)
    @Column(name = "active_sc")
    private Integer activeSc;
    
    @Column(name = "total_sc")
    private Long totalSc;

}