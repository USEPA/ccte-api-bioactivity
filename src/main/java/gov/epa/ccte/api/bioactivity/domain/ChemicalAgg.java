package gov.epa.ccte.api.bioactivity.domain;

import org.hibernate.annotations.Immutable;

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
@Immutable
@Table(name = "mv_chemical_agg", schema = "invitro")
public class ChemicalAgg {

    @Id
    @Size(max = 255)
    @Column(name = "dsstox_substance_id")
    private String dtxsid;
    
    @Column(name = "active_mc")
    private Integer activeMc;
    
    @Column(name = "total_mc")
    private Integer totalMc;
    
    @Column(name = "active_sc")
    private Integer activeSc;
    
    @Column(name = "total_sc")
    private Integer totalSc;
    
    @Column(name = "cytotox_median_raw")
    private Float cytotoxMedianRaw;
    
    @Column(name = "cytotox_mad")
    private Float cytotoxMad;
    
    @Column(name = "global_mad")
    private Float globalMad;
    
    @Column(name = "cytotox_median_log")
    private Float cytotoxMedianLog;
    
    @Column(name = "cytotox_median_um")
    private Float cytotoxMedianUm;
    
    @Column(name = "cytotox_lower_bound_um")
    private Float cytotoxLowerUm;
    
    @Column(name = "cytotox_lower_bound_log")
    private Float cytotoxLowerLog;
    
    @Column(name = "ntested")
    private Float ntested;

    @Column(name = "nhit")
    private Float nhit;

}
