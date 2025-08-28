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
        description = "API endpoints for collecting bioactivity models.")
@SecurityRequirement(name = "api_key")
@RequestMapping("bioactivity/models")
public interface BioactivityModelApi {
    /**
     * {@code GET  /bioactivity/models/search/by-dtxsid/{dtxsid} : get toxcast models for the "dtxsid".
     *
     * @param dtxsid the matching dtxsid of the Models to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the toxcast models.
     */
    @Operation(summary = "Get toxcast model by dtxsid", description = "Return toxcast model for given dtxsid", tags = {"bioactivity", "model"})
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
    @Operation(summary = "Get toxcast model by dtxsid and model", description = "Return toxcast model for given dtxsid and model", tags = {"bioactivity", "model"})
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
