package gov.epa.ccte.api.bioactivity.domain;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Immutable
@Table(name = "search_assay", schema = "invitro")
public class SearchAssay {
	
	@Id
	@NotNull
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Column(name = "aeid")
	private Integer aeid;

	@Size(max = 50)
	@Column(name = "search_name")
	private String searchName;
	
	@Size(max =200)
	@Column(name = "search_value")
	private String searchValue;
	
	@Column(name = "search_value_desc")
	private String searchValueDesc;
	
	@Column(name = "modified_value")
	private String modifiedValue;

}
