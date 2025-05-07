package gov.epa.ccte.api.bioactivity.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AedData {

	private String dtxsid;
	private String preferredName;
	private Integer aeid;

	private List<Double> aedVal;
	private List<String> aedType;
	private List<String> httkModel;
	private List<String> aedValUnit;
	private List<String> httkVersion;
	private List<String> potencyValType;
	private List<String> invitrodbVersion;
	private List<Double> interindividualVarPerc;

}
