package gov.epa.ccte.api.bioactivity.domain;

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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mv_bioactivity_sc", schema = "invitro")
public class BioactivitySc {
    @Id
    @Column(name = "s2id")
    private Long s2id;

    @Column(name = "aeid")
    private Integer aeid;

    @Size(max = 50)
    @Column(name = "spid", length = 50)
    private String spid;

    @Column(name = "chid")
    private Integer chid;

    @Size(max = 45)
    @Column(name = "casn", length = 45)
    private String casn;

    @Size(max = 255)
    @Column(name = "chnm")
    private String chnm;

    @Size(max = 255)
    @Column(name = "dsstox_substance_id")
    private String dsstoxSubstanceId;

    @Column(name = "bmad")
    private Double bmad;

    @Column(name = "max_med")
    private Double maxMed;

    @Column(name = "coff")
    private Double coff;

    @Column(name = "hitc")
    private Double hitc;

    @Column(name = "chid_rep")
    private Short chidRep;

    @Column(name = "stkc")
    private Double stkc;

    @Size(max = 50)
    @Column(name = "stkc_unit", length = 50)
    private String stkcUnit;

    @Size(max = 50)
    @Column(name = "tested_conc_unit", length = 50)
    private String testedConcUnit;

    @Column(name = "sc1_param")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> sc1Param;

    @Column(name = "export_date")
    private LocalDate exportDate;

    @Size(max = 28)
    @Column(name = "data_version", length = 28)
    private String dataVersion;

}