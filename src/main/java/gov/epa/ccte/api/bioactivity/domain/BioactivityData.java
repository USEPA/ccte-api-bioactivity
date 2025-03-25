package gov.epa.ccte.api.bioactivity.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Schema(description = "US EPA'sToxicity Forecaster (ToxCast) program makes invitro medium- and high-throughput screening assay data publicly available for prioritization and hazard characterization.The data endpoint returns chemical information, normalized concentration-response values, all models fit parameters, winning model fit parameters, and cautionary flags for a given ‘AEIDs’, ‘m4ids’, ‘SPIDs’, or ‘DTXSIDs’. Regular ToxCast/invitrodb users may find it easier to use the tcpl R package, which has integrated to make API data retrievable in a familiar format. See the tcpl vignette regarding data retrieval via API for more information.")
@Entity
@Immutable
@Table(name = "mv_bioactivity", schema = "invitro")
public class BioactivityData {
    @Id
    @Column(name = "m4id")
    @Schema(description = "Level 4 ID, autoincremented primary key")
    private Long m4id;

    @Column(name = "aeid")
    @Schema(description = "Assay endpoint ID")
    private Long aeid;

    @Size(max = 50)
    @Column(name = "spid", length = 50)
    @Schema(description = "Sample ID")
    private String spid;

    @Column(name = "chid")
    @Schema(description = "Chemical ID; this is an index specific to invitrodb (DSSTox GSID 1:1 with CASRN)")
    private Integer chid;

    @Size(max = 45)
    @Column(name = "casn", length = 45)
    @Schema(description = "CAS Registry number, also known as CASRN")
    private String casn;

    @Size(max = 255)
    @Column(name = "chnm")
    @Schema(description = "Chemical name")
    private String chnm;

    @Size(max = 255)
    @Column(name = "dsstox_substance_id")
    @Schema(description = "Distributed Structure-Searchable Toxicity (DSSTox) id, also known as DTXSID")
    private String dtxsid;

    @Column(name = "bmad")
    @Schema(description = "Baseline median absolute deviation")
    private Double bmad;

    @Column(name = "resp_max")
    @Schema(description = "Maximum response value")
    private Double respMax;

    @Column(name = "resp_min")
    @Schema(description = "Minimum response value")
    private Double respMin;

    @Column(name = "max_mean")
    @Schema(description = "Maximum mean response value")
    private Double maxMean;

    @Column(name = "max_mean_conc")
    @Schema(description = "Concentration at max_mean")
    private Double maxMeanConc;

    @Column(name = "max_med")
    @Schema(description = "Minimum median response value")
    private Double maxMed;

    @Column(name = "max_med_conc")
    @Schema(description = "Concentration at max_med")
    private Double maxMedConc;

    @Column(name = "conc_max")
    @Schema(description = "Maximum tested log concentration, log uM ")
    private Double concMax;

    @Column(name = "conc_min")
    @Schema(description = "Minimum tested log concentration, log uM")
    private Double concMin;

    @Column(name = "nconc")
    @Schema(description = "Number of concentrations tested")
    private Integer nconc;

    @Column(name = "npts")
    @Schema(description = "Number of points in the concentration series")
    private Integer npts;

    @Column(name = "nrep")
    @Schema(description = "Number of replicates in the concentration series")
    private Double nrep;

    @Column(name = "nmed_gtbl_pos")
    @Schema(description = "Number of median values greater than 3bmad")
    private Integer nmedGtblPos;

    @Column(name = "nmed_gtbl_neg")
    @Schema(description = "Number of median values greater than 3bmad")
    private Integer nmedGtblNeg;

    @Column(name = "m5id")
    @Schema(description = "Level 5 ID, autoincremented primary key")
    private Long m5id;

    @Size(max = 5)
    @Column(name = "modl", length = 5)
    @Schema(description = "Winning model")
    private String modl;

    @Column(name = "hitc")
    @Schema(description = "Hit-/activity-call, generally a continuous value from 0 to 1 if using tcplFit2 fitting. For research applications, hitc >= 0.9 is considered active whereas hitc < 0.9 is inactive.")
    private Double hitc;

    @Column(name = "fitc")
    @Schema(description = "Fit category")
    private Short fitc;

    @Column(name = "coff")
    @Schema(description = "Efficacy cutoff value")
    private Double coff;

    @Column(name = "actp")
    @Schema(description = "Activity probability (1 - const_prob not used with tcplFit2)")
    private Double actp;

    @Column(name = "model_type")
    @Schema(description = "Model type. Options include: 2 for bidirectional, 3 for gain (fit bidirectionally, but gain is intended direction), or 4 for loss (fit bidirectionally, but loss is intended direction)")
    private Short modelType;

    @Column(name = "chid_rep")
    @Schema(description = "Representative sample designation for a tested chemical: 1 if representative sample, else 0")
    private Short chidRep;

    @Column(name = "stkc")
    @Schema(description = "Stock concentration of chemical screened. Used in pre-processing concentration correction.")
    private Double stkc;

    @Size(max = 50)
    @Column(name = "stkc_unit", length = 50)
    @Schema(description = "Stock concentration unit. (mM)")
    private String stkcUnit;

    @Size(max = 50)
    @Column(name = "tested_conc_unit", length = 50)
    @Schema(description = "Tested concentration unit. (uM)")
    private String testedConcUnit;

    @Column(name = "mc3_param")
    @JdbcTypeCode(SqlTypes.JSON)
    @Schema(description = "JSON object containing Level 3 Multi-concentration (MC3) normalized concentration-response data")
    private Mc3Param mc3Param;

    @Column(name = "mc4_param")
    @JdbcTypeCode(SqlTypes.JSON)
    @Schema(description = "JSON object containing Level 4 Multi-concentration (MC4) All Models Fitting Parameters")
    private Map<String, BigDecimal> mc4Param;

    @Column(name = "mc5_param")
    @JdbcTypeCode(SqlTypes.JSON)
    @Schema(description = "JSON object containing Level 5 Multi-concentration (MC5) Winning Model Fitting Parameters")
    private Map<String, BigDecimal> mc5Param;

    @Column(name = "mc6_param")
    @JdbcTypeCode(SqlTypes.JSON)
    @Schema(description = "JSON object containing Level 6 Multi-concentration (MC6) Flags")
    private Mc6Param mc6Param;

}