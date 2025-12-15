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
@Table(name = "mv_bioactivity_model", schema = "invitro")
public class BioactivityModel {
	    @Id
	    @Column(name = "id")
	    private Integer id;


	    @Column(name = "dtxsid", nullable = true, length = 50)
	    private String dtxsid;

	    @Column(name = "model", nullable = true, length = 255)
	    private String model;

	    @Column(name = "receptor", nullable = true, length = 255)
	    private String receptor;

	    @Column(name = "agonist", nullable = true, length = 255)
	    private String agonist;

	    @Column(name = "antagonist", nullable = true, length = 255)
	    private String antagonist;

	    @Column(name = "binding", nullable = true, length = 255)
	    private String binding;

	    @Column(name = "help", nullable = true, length = 1024)
	    private String modelDesc;
	}
