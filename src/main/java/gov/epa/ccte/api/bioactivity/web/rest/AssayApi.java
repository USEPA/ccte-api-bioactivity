package gov.epa.ccte.api.bioactivity.web.rest;

import gov.epa.ccte.api.bioactivity.domain.AssayAnnotation;
import gov.epa.ccte.api.bioactivity.projection.assay.AssayAll;
import gov.epa.ccte.api.bioactivity.projection.assay.AssayBase;
import gov.epa.ccte.api.bioactivity.projection.data.BioactivityDataAll;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * REST controller for getting the {@link AssayAnnotation}s.
 */
@Tag(name = "Bioactivity Assay Resource",
        description = "API endpoints for collecting Assay annotations.")
@SecurityRequirement(name = "api_key")
@RequestMapping( value = "bioactivity/assay", produces = MediaType. APPLICATION_JSON_VALUE)
public interface AssayApi {
    /**
     * {@code GET  /bioactivity/assay/by-aeid/:aeid} : get assay annotation for the "aeid".
     *
     * @param aeid the matching aeid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the asssay annotation.
     */
	@Operation(summary = "Get assay data by aeid with projections", 
	           description = "Fetches assay data based on the specified projection. Available projections: " +
	                         "ccd-assay-annotation, ccd-assay-gene, ccd-assay-citations, ccd-assay-tcpl, ccd-assay-reagents, assay-all. " +
	                         "If no projection is specified, the default full assay data will be returned.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
	        @ApiResponse(responseCode = "400", description = "Invalid projection type", content = @Content(mediaType = "application/json")),
	        @ApiResponse(responseCode = "404", description = "No data found for the given aeid", content = @Content(mediaType = "application/json"))
	})
	@RequestMapping(value = "/search/by-aeid/{aeid}", method = RequestMethod.GET)
	@ResponseBody
	List<?> assayByAeid(
	        @Parameter(required = true, description = "Numeric assay endpoint identifier", example = "3032")
	        @PathVariable("aeid") Integer aeid,
	        @Parameter(description = "Specifies which projection to use. Options: ccd-assay-annotation, ccd-assay-gene, ccd-assay-citations, " +
	                                "ccd-assay-tcpl, ccd-assay-reagents, assay-all. If omitted, the full assay data is returned.")
	        @RequestParam(value = "projection", required = false) String projection
	);
    
    
    /**
     * {@code GET  /bioactivity/assay/chemicals/search/by-aeid/:aeid} : get array of DTXSIDs for the "aeid".
     *
     * @param aeid the matching aeid of the chemicals to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and array of DTXSIDs for the "aeid".
     */
    @Operation(summary = "Get list of DTXSIDs by aeid")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json"))
    })
    @RequestMapping(value = "/chemicals/search/by-aeid/{aeid}", method = RequestMethod.GET)
    @ResponseBody
    List<String> chemicalsByAeid(@Parameter(required = true, description = "Numeric assay endpoint identifier", example = "3032") @PathVariable("aeid") Integer aeid);

    /**
     * {@code POST  /bioactivity/assay/by-aeid/} : get assay annotation for the batch of "aeids".
     *
     * @param aeids the matching aeids of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assay annotation.
     */
    @Operation(summary = "Find assay annotations by batch of aeids", description = "return assay annotations for requested aeids.")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {AssayAll.class}))),
    })
    @PostMapping(value = "/search/by-aeid/")
    @ResponseBody
    List<AssayAll> batchSearchAssayByAeid(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifier",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"111\\\",\\\"3032\\\"]\"")})})
                                                    @RequestBody String[] aeids);
    
    /**
     * {@code GET  /bioactivity/assay/} : get all assays annotation .
     * *
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the array of  asssay annotation.
     */
    @Operation(summary = "Get all assays")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {AssayAnnotation.class}))),
    })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    List<?> allAssays(@RequestParam(value = "projection", required = false, defaultValue = "assay-all") String projection);
}
