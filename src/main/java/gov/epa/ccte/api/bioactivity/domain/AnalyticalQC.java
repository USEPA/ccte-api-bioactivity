package gov.epa.ccte.api.bioactivity.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mv_chemical_analytical_qc", schema = "invitro")
public class AnalyticalQC {

    @Id
    @Column(name = "analytical_qc_id")
    private Long analyticalQcId;

    @Column(name = "dsstox_substance_id")
    private String dtxsid;

    @Column(name = "chnm")
    private String chnm;

    @Column(name = "spid")
    private String spid;

    @Column(name = "qc_level")
    private String qcLevel;

    @Column(name = "p_or_caution")
    private String pOrCaution;

    @Column(name = "t0")
    private String t0;

    @Column(name = "t4")
    private String t4;

    @Column(name = "call")
    private String call;

    @Column(name = "annotation")
    private String annotation;

    @Column(name = "flags")
    private String flags;

    @Column(name = "average_mass")
    private String averageMass;

    @Column(name = "log10_vapor_pressure_opera_pred")
    private String log10VaporPressureOperaPred;

    @Column(name = "logkow_octanol_water_opera_pred")
    private String logkowOctanolWaterOperaPred;

    @Column(name = "export_date")
    private LocalDate exportDate;

    @Column(name = "data_version")
    private String dataVersion;
}