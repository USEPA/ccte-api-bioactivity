package gov.epa.ccte.api.bioactivity.web.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gov.epa.ccte.api.bioactivity.domain.BioactivityModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.bioactivity.domain.BioactivityModel}s.
 */
@Tag(name = "Bioactivity Model Resource",
        description = "Collection of endpoints for prediction results from ToxCast bioactivity models. The publications associated with the ToxCast Pathway Models are: 1) ESTROGEN: As described in Browne, et al (2015) DOI: 10.1021/acs.est.5b02641 and presented to December 2014 FIFRA SAP under EPA-HQ-OPP-2014-0614. 2) ANDROGEN: As described in Kleinstreuer et al (2017) 10.1021/acs.chemrestox.6b00347 and presented to November 2017 FIFRA SAP under EPA-HQ-OPP-2017-0214.")
@SecurityRequirement(name = "api_key")
@RequestMapping("bioactivity/models")
public interface BioactivityModelApi {
    /**
     * {@code GET  /bioactivity/models/search/by-dtxsid/{dtxsid} : get toxcast models for the "dtxsid".
     *
     * @param dtxsid the matching dtxsid of the Models to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the toxcast models.
     */
    @Operation(summary = "Get predictions by DTXSID", description = "return ToxCast model predictions for a given DTXSID", tags = {"bioactivity", "model"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content( mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = BioactivityModel.class))))
    })
    @RequestMapping(value = "/search/by-dtxsid/{dtxsid}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<BioactivityModel> getBioactivityModelByDtxsid(@Parameter(required = true, description = "dtxsid", example = "DTXSID7020182")@PathVariable("dtxsid") String dtxsid);
    
    /**
     * {@code GET  /bioactivity/models/search/ : get toxcast models for the "dtxsid" and "model".
     *
     * @param dtxsid the matching dtxsid and model of the Models to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the toxcast models.
     */
    @Operation(summary = "Get predictions by DTXSID and model", 
                description = "return ToxCast model predictions for given a DTXSID and model type. Model type options include: 'CERAPP Potency Level (Consensus)', 'CERAPP Potency Level (From Literature)', 'COMPARA (Consensus)', and 'ToxCast Pathway Model (AUC)'", 
                tags = {"bioactivity", "model"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content( mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = BioactivityModel.class))))
    })
    @RequestMapping(value = "/search/",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<BioactivityModel> getBioactivityModelByDtxsidAndModel(@Parameter(required = true, description = "dtxsid", example = "DTXSID7020182")String dtxsid,
    		@Parameter(required = true, description = "model", example = "CERAPP ")String model);
    
}
