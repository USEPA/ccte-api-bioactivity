package gov.epa.ccte.api.bioactivity.web.rest;

import gov.epa.ccte.api.bioactivity.domain.AedData;
import gov.epa.ccte.api.bioactivity.domain.AssayAgg;
import gov.epa.ccte.api.bioactivity.domain.ChemicalAgg;
import gov.epa.ccte.api.bioactivity.projection.data.SummaryByTissue;
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
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.bioactivity.domain.BioactivityData}s.
 */
@Tag(name = "Bioactivity Data Resource",
        description = "Collection of endpoints with multiple-concentration targeted bioactivity screening data. These curated data sourced from US EPA's Toxicity Forecaster (ToxCast) invitrodb.")
@SecurityRequirement(name = "api_key")
@RequestMapping("bioactivity/data")
public interface DataApi {
    /**
     * {@code GET  /bioactivity/data/by-dtxsid/:dtxsid} : get bioactivity data for the "dtxsid".
     *
     * @param dtxsid the matching dtxsid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get data by DTXSID", description = "Return data for a given DTXSID. There is an available projection aligned with what's available on the CCD ToxCast Summary plot: toxcast-summary-plot. If no projection is specified, a default BioactivityDataAll projection will be returned.", tags = {"bioactivity", "data"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content( mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(oneOf = {BioactivityDataAll.class}))))
    })
    @RequestMapping(value = "/search/by-dtxsid/{dtxsid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<?> dataByDtxsid(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID7020182") 
                                         @PathVariable("dtxsid") String dtxsid,
                             	        @Parameter(description = "Specifies if projection is used. Option: toxcast-summary-plot. " +
            	                                "If omitted, the default BioactivityDataAll data is returned.")
            	                        @RequestParam(value = "projection", required = false) String projection);

    /**
     * {@code POST  /bioactivity/data/by-dtxsid/} : get bioactivity data for the batch of "dtxsids".
     *
     * @param dtxsids the matching dtxsids of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get bioactivity data for a batch of DTXSIDs", description = "return data for requested DTXSIDs.")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {BioactivityDataAll.class}))),
    })
    @PostMapping(value = "/search/by-dtxsid/")
    @ResponseBody
    List<BioactivityDataAll> batchSearchDataByDtxsid(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifiers",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"DTXSID9026974\\\",\\\"DTXSID9020112\\\"]\"")})})
                                                    @RequestBody String[] dtxsids);
    
    /**
     * {@code GET  /bioactivity/data/by-aeid/:aeid} : get bioactivity data for the "aeid".
     *
     * @param aeid the matching aeid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get data by AEID", description = "Return data by ToxCast assay component endpoint ID (AEID)", tags = {"bioactivity", "data"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content( mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BioactivityDataAll.class))))
    })
    @RequestMapping(value = "/search/by-aeid/{aeid}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<BioactivityDataAll> dataByAeid(@Parameter(required = true, description = "ToxCast assay component endpoint ID (AEID)", example = "3032")
                    @PathVariable("aeid") Integer aeid);
    
    /**
     * {@code POST  /bioactivity/data/by-aeid/} : get bioactivity data for the batch of "aeids".
     *
     * @param aeids the matching aeids of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get data for a batch of AEIDs", description = "return data for requested ToxCast assay component endpoint IDs (AEIDs)")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {BioactivityDataAll.class}))),
    })
    @PostMapping(value = "/search/by-aeid/")
    @ResponseBody
    List<BioactivityDataAll> batchSearchDataByAeid(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of ToxCast assay component endpoint IDs (AEIDs)",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"3032\\\",\\\"755\\\"]\"")})})
                                                    @RequestBody String[] aeids);

    /**
     * {@code GET  /bioactivity/data/by-spid/:spid} : get bioactivity data for the "spid".
     *
     * @param spid the matching spid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get data by SPID", description = "return data for given sample ID (SPID)", tags = {"bioactivity", "data"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content( mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BioactivityDataAll.class))))
    })
    @RequestMapping(value = "/search/by-spid/{spid}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<BioactivityDataAll> dataBySpid(@Parameter(required = true, description = "sample ID (SPID)", example = "EPAPLT0232A03")
                    @PathVariable("spid") String spid);
    
    /**
     * {@code POST  /bioactivity/data/by-spid/} : get bioactivity data for the batch of "spids".
     *
     * @param spids the matching spids of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get data for a batch of SPIDs", description = "return data for requested sample IDs (SPIDs)")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {BioactivityDataAll.class}))),
    })
    @PostMapping(value = "/search/by-spid/")
    @ResponseBody
    List<BioactivityDataAll> batchSearchDataBySpid(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of SPIDs",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"EPAPLT0232A03\\\",\\\"TP0000311A04\\\"]\"")})})
                                                    @RequestBody String[] spids);

    /**
     * {@code GET  /bioactivity/data/by-m4id/:m4id} : get bioactivity data for the "m4id".
     *
     * @param m4id the matching m4id of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get data by M4ID", description = "return data for given Level 4 ID (M4ID), corresponding to the individual modeled endpoint-sample concentration-response", tags = {"bioactivity", "data"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {BioactivityDataAll.class}))),
    })
    @RequestMapping(value = "/search/by-m4id/{m4id}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<BioactivityDataAll> dataByM4Id(@Parameter(required = true, description = "M4ID", example = "7826737")
                                   @PathVariable("m4id") Integer m4id);

    /**
     * {@code POST  /bioactivity/data/by-m4id/} : get bioactivity data for the batch of "m4ids".
     *
     * @param spids the matching m4ids of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get data for a batch of M4IDs", description = "return data for requested Level 4 IDs (M4IDs), corresponding to the individual modeled endpoint-sample concentration-responses")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {BioactivityDataAll.class}))),
    })
    @PostMapping(value = "/search/by-m4id/")
    @ResponseBody
    List<BioactivityDataAll> batchSearchDataByM4id(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of M4IDs",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"7826737\\\",\\\"7834113\\\"]\"")})})
                                                    @RequestBody String[] spids);
    
    /**
     * {@code GET  /bioactivity/data/summary/search/by-aeid/:aeid} : get bioactivity summary for the "aeid".
     *
     * @param aeid the matching aeid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Summary data.
     */
    @Operation(summary = "Get summary data by AEID", description = "return summary of active hits and total multi- and single-concentration chemicals tested for given ToxCast assay component endpoint ID (AEID)", tags = {"bioactivity", "data"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {AssayAgg.class}))),
    })
    @RequestMapping(value = "/summary/search/by-aeid/{aeid}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<AssayAgg> summaryByAeid(@Parameter(required = true, description = "ToxCast assay component endpoint ID (AEID)", example = "3032")
                                 @PathVariable("aeid") Integer aeid);
    
    /**
     * {@code GET  /bioactivity/data/summary/search/by-dtxsid/:dtxsid} : get bioactivity summary for the "dtxsid".
     *
     * @param dtxsid the matching aeid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Summary data.
     */
    @Operation(summary = "Get summary data by DTXSID", description = "return summary of active hits and total multi- and single-concentration assays tested for given DTXSID", tags = {"bioactivity", "data"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {ChemicalAgg.class}))),
    })
    @RequestMapping(value = "/summary/search/by-dtxsid/{dtxsid}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List summaryByDtxsid(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID9026974")
                                 @PathVariable("dtxsid")String dtxsid);
    

    @GetMapping(value = "/aed/search/by-dtxsid/{dtxsid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get AED data by DTXSID", description = "return administered equivalent dose (AED) data derived from ToxCast in vitro bioactivity data for given DTXSID", tags = {"bioactivity", "data"})
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AedData.class)))
    @ResponseBody
    List<AedData> getAedDataByDtxsid(@Parameter(required = true, example = "DTXSID5021209") @PathVariable String dtxsid);
    
    
    @Operation(summary = "Get AED data for a batch of DTXSIDs", description = "return administered equivalent dose (AED) data derived from ToxCast in vitro bioactivity data for given DTXSIDs", tags = {"bioactivity", "data"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = AedData.class))))
    })
    @PostMapping(value = "/aed/search/by-dtxsid/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<AedData> getAedDataForBatchDtxsids(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifiers",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
            examples = {@ExampleObject("\"[\\\"DTXSID7020182\\\",\\\"DTXSID9020112\\\"]\"")})})
                                               @RequestBody String[] dtxsids);
    

    /**
     * {@code GET  /bioactivity/data/summary/search/by-dtxsid/:dtxsid} : get bioactivity summary for the "dtxsid".
     *
     * @param dtxsid the matching aeid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Summary data.
     */
    @Operation(summary = "Get summary data by DTXSID and assay tissue origin", description = "return summary data for given DTXSID and the assay format's tissue origin", tags = {"bioactivity", "data"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {SummaryByTissue.class}))),
    })
    @RequestMapping(value = "/summary/search/by-tissue/",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<SummaryByTissue> summaryByDtxsidAndTissue(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID7024241")String dtxsid,
                                 @Parameter(required = true, description = "assay format's tissue of origin", example = "liver")String tissue);

}
