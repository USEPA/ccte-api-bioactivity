package gov.epa.ccte.api.bioactivity.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "US EPA's Toxicity Forecaster (ToxCast) program makes invitro medium- and high-throughput screening assay data publicly available for prioritization and hazard characterization.The summary endpoint returns the number of active hits and total multi- and single-concentration chemicals tested for specific ‘aeids’. For multi-concentration data, a continuous hit call value greater than or equal to 0.9 is considered active, whereas values less than 0.9 are considered inactive. For single concentration data, the hit call value is binary where 1 is active and 0 is inactive. Multiple samples of the same chemical may be tested for a given assay endpoint, and all samples per endpoint are reflected in these counts.")
@Entity
@Table(name = "v_assay_list_counts", schema = "bio4_1")
public class AssayListCount {
    @Id
    @Column(name = "aeid")
    @Schema(description = "Assay component endpoint ID")
    private Integer aeid;

    @Column(name = "active_mc")
    @Schema(description = "Number of active representative samples tested at multiple concentrations per endpoint (aeid)")
    private Long activeMc;

    @Column(name = "total_mc")
    @Schema(description = "Total count of samples tested at multiple concentrations per endpoint (aeid)")
    private Long totalMc;

    @Column(name = "active_sc")
    @Schema(description = "Number of active representative samples tested at single concentration per endpoint (aeid)")
    private Long activeSc;

    @Column(name = "total_sc")
    @Schema(description = "Total count of samples tested at single concentration per endpoint (aeid)")
    private Long totalSc;

}