package gov.epa.ccte.api.bioactivity.web.rest;

import gov.epa.ccte.api.bioactivity.domain.AssayAnnotation;
import gov.epa.ccte.api.bioactivity.projection.assay.AssayAll;
import gov.epa.ccte.api.bioactivity.projection.assay.AssayEndpointsList;
import gov.epa.ccte.api.bioactivity.projection.assay.CcDAssayAnnotation;
import gov.epa.ccte.api.bioactivity.projection.assay.CcdAssayCitation;
import gov.epa.ccte.api.bioactivity.projection.assay.CcdAssayGene;
import gov.epa.ccte.api.bioactivity.projection.assay.CcdReagents;
import gov.epa.ccte.api.bioactivity.projection.assay.CcdSingleConcData;
import gov.epa.ccte.api.bioactivity.projection.assay.CcdTcplData;
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
import org.springframework.http.ProblemDetail;
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
        description = "Collection of endpoints for assay annotations. Annotations describe who conducted the assay, what platform was used, what was being measured (raw readout), and how the measurement was interpreted (normalized component data). Assay annotations facilitate data interpretation by allowing users to flexibly aggregate and differentiate processed data. These curated data sourced from US EPA's Toxicity Forecaster (ToxCast) invitrodb.")
@SecurityRequirement(name = "api_key")
@RequestMapping( value = "bioactivity/assay", produces = MediaType. APPLICATION_JSON_VALUE)
public interface AssayApi {
    /**
     * {@code GET  /bioactivity/assay/by-aeid/:aeid} : get assay annotation for the "aeid".
     *
     * @param aeid the matching aeid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the asssay annotation.
     */
	@Operation(summary = "Get assay annotations by AEID", 
	           description = "return assay annotations by ToxCast assay component endpoint ID (AEID) based on the specified projection. Available projections: " +
	                         "ccd-assay-annotation, ccd-assay-gene, ccd-assay-citations, ccd-assay-tcpl, ccd-assay-reagents, assay-all. " +
	                         "If no projection is specified, the full view of assay annotations will be returned by default.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {AssayAnnotation.class, CcDAssayAnnotation.class, CcdAssayGene.class, CcdAssayCitation.class, CcdTcplData.class, CcdReagents.class}))),
	        @ApiResponse(responseCode = "400", description = "Invalid projection type", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {ProblemDetail.class}))),
	        @ApiResponse(responseCode = "404", description = "No data found for the given AEID", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {ProblemDetail.class})))
	})
	@RequestMapping(value = "/search/by-aeid/{aeid}", method = RequestMethod.GET)
	@ResponseBody
	List<?> assayByAeid(
	        @Parameter(required = true, description = "ToxCast assay component endpoint ID", example = "3032")
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
    @Operation(summary = "Get DTXSID list by AEID", description = "return list of chemicals (DTXSIDs) tested by ToxCast assay component endpoint ID (AEID).")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json", schema = @Schema(oneOf = {String.class})))
    })
    @RequestMapping(value = "/chemicals/search/by-aeid/{aeid}", method = RequestMethod.GET)
    @ResponseBody
    List<?> chemicalsByAeid(@Parameter(required = true, description = "ToxCast assay component endpoint ID", example = "3032") @PathVariable("aeid") Integer aeid,
    		                     @RequestParam(value = "projection", required = false, defaultValue = "dtxsidsonly") String projection);

    /**
     * {@code POST  /bioactivity/assay/by-aeid/} : get assay annotation for the batch of "aeids".
     *
     * @param aeids the matching aeids of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assay annotation.
     */
    @Operation(summary = "Get assay annotations for a batch of AEIDs", description = "return assay annotations for requested ToxCast assay component endpoint IDs (AEIDs)")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {AssayAll.class}))),
    })
    @PostMapping(value = "/search/by-aeid/")
    @ResponseBody
    List<AssayAll> batchSearchAssayByAeid(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifiers",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"111\\\",\\\"3032\\\"]\"")})})
                                                    @RequestBody String[] aeids);

	/**
     * {@code GET  /bioactivity/assay/search/by-endpoint/{assayEndpointName}} : returns associated aeid for the assay endpoint name
     * *
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and aeid for the assay endpoint name
     */
    @Operation(summary = "Get AEID by assay component endpoint name")
    @RequestMapping(value = "/search/by-endpoint/", method = RequestMethod.GET)
    @ResponseBody
    Long aeidByAssayEndpointName(@RequestParam(required = true, value = "endpoint") String assayComponentEndpointName);

    /**
     * {@code GET  /bioactivity/assay/single-conc/search/by-aeid/:aeid} : single conc data for the "aeid".
     *
     * @param aeid the matching aeid of the single conc data to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and array of single conc data for the "aeid".
     */
    @Operation(summary = "Get single conc data by AEID", description = "return single concentration screening data for requested ToxCast assay component endpoint ID (AEID)")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json", schema = @Schema(oneOf = {CcdSingleConcData.class})))
    })
    @RequestMapping(value = "/single-conc/search/by-aeid/{aeid}", method = RequestMethod.GET)
    @ResponseBody
    List<?> singleConcDataByAeid(@Parameter(required = true, description = "ToxCast assay component endpoint ID", example = "3032") @PathVariable("aeid") Integer aeid
            , @RequestParam(value = "projection", required = false, defaultValue = "single-conc") String projection
    );


    /**
     * {@code GET  /bioactivity/assay/} : get all assays annotation .
     * *
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the array of  asssay annotation.
     */
    @Operation(summary = "Get all assay annotations")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {AssayAnnotation.class}))),
    })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    List<?> allAssays(@RequestParam(value = "projection", required = false, defaultValue = "assay-all") String projection);
    
    /**
     * {@code GET  bioactivity/assay/search/by-gene/{geneSymbol}} : get assay endpoints list by gene symbol .
     * *
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the array of asssay endpoints list.
     */
    @Operation(summary = "Get assay summary by gene symbol", description = "Return assay summary data, including percentage of single and multiple-concentration screening actives, for applicable assays for requested offical gene symbol.")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {AssayEndpointsList.class}))),
    })
    @RequestMapping(value = "/search/by-gene/{geneSymbol}", method = RequestMethod.GET)
    @ResponseBody
    List<AssayEndpointsList> assayEndpointsListByGene(@Parameter(required = true, description = "Gene Symbol", example = "TUBA1A") @PathVariable("geneSymbol") String geneSymbol);

    /**
     * {@code GET  bioactivity/assay/count}} : returns total count of all assay
     * *
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and total count of all assay
     */
    @Operation(summary = "Get total assay count")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    Long assayCount();
}
