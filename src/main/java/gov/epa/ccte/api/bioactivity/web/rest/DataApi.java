package gov.epa.ccte.api.bioactivity.web.rest;

import gov.epa.ccte.api.bioactivity.domain.AedData;
import gov.epa.ccte.api.bioactivity.domain.AssayAgg;
import gov.epa.ccte.api.bioactivity.domain.ChemicalAgg;
import gov.epa.ccte.api.bioactivity.projection.data.BioactivityDataBase;
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
        description = "API endpoints for collecting bioactivity data.")
@SecurityRequirement(name = "api_key")
@RequestMapping("bioactivity/data")
public interface DataApi {
    /**
     * {@code GET  /bioactivity/data/by-dtxsid/:dtxsid} : get bioactivity data for the "dtxsid".
     *
     * @param dtxsid the matching dtxsid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get data by dtxsid with or without projection", description = "Return data for given DTXSID", tags = {"bioactivity", "data"})
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
    @Operation(summary = "Find bioactivity data by batch of dtxsids", description = "return bioactivity data for requested dtxsids.")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {BioactivityDataAll.class}))),
    })
    @PostMapping(value = "/search/by-dtxsid/")
    @ResponseBody
    List<BioactivityDataAll> batchSearchDataByDtxsid(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifier",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"DTXSID9026974\\\",\\\"DTXSID9020112\\\"]\"")})})
                                                    @RequestBody String[] dtxsids);
    
    /**
     * {@code GET  /bioactivity/data/by-aeid/:aeid} : get bioactivity data for the "aeid".
     *
     * @param aeid the matching aeid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get data by aeid", description = "Return data for given aeid", tags = {"bioactivity", "data"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content( mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BioactivityDataAll.class))))
    })
    @RequestMapping(value = "/search/by-aeid/{aeid}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<BioactivityDataAll> dataByAeid(@Parameter(required = true, description = "Numeric assay endpoint identifier", example = "3032")
                    @PathVariable("aeid") Integer aeid);
    
    /**
     * {@code POST  /bioactivity/data/by-aeid/} : get bioactivity data for the batch of "aeids".
     *
     * @param aeids the matching aeids of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Find bioactivity data by batch of aeids", description = "return bioactivity data for requested aeids.")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {BioactivityDataAll.class}))),
    })
    @PostMapping(value = "/search/by-aeid/")
    @ResponseBody
    List<BioactivityDataAll> batchSearchDataByAeid(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifier",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"3032\\\",\\\"755\\\"]\"")})})
                                                    @RequestBody String[] aeids);

    /**
     * {@code GET  /bioactivity/data/by-spid/:spid} : get bioactivity data for the "spid".
     *
     * @param spid the matching spid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get data by spid", description = "Return data for given spid", tags = {"bioactivity", "data"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content( mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BioactivityDataAll.class))))
    })
    @RequestMapping(value = "/search/by-spid/{spid}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<BioactivityDataAll> dataBySpid(@Parameter(required = true, description = "identifier", example = "EPAPLT0232A03")
                    @PathVariable("spid") String spid);
    
    /**
     * {@code POST  /bioactivity/data/by-spid/} : get bioactivity data for the batch of "spids".
     *
     * @param spids the matching spids of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Find bioactivity data by batch of spids", description = "return bioactivity data for requested spids.")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {BioactivityDataAll.class}))),
    })
    @PostMapping(value = "/search/by-spid/")
    @ResponseBody
    List<BioactivityDataAll> batchSearchDataBySpid(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifier",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"EPAPLT0232A03\\\",\\\"TP0000311A04\\\"]\"")})})
                                                    @RequestBody String[] spids);

    /**
     * {@code GET  /bioactivity/data/by-m4id/:m4id} : get bioactivity data for the "m4id".
     *
     * @param m4id the matching m4id of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get data by m4id", description = "Return single data row for given m4id", tags = {"bioactivity", "data"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {BioactivityDataAll.class}))),
    })
    @RequestMapping(value = "/search/by-m4id/{m4id}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    BioactivityDataBase dataByM4Id(@Parameter(required = true, description = "Numeric data identifier", example = "1135145")
                                   @PathVariable("m4id") Integer m4id);

    /**
     * {@code POST  /bioactivity/data/by-m4id/} : get bioactivity data for the batch of "m4ids".
     *
     * @param spids the matching m4ids of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Find bioactivity data by batch of m4ids", description = "return bioactivity data for requested m4ids.")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {BioactivityDataAll.class}))),
    })
    @PostMapping(value = "/search/by-m4id/")
    @ResponseBody
    List<BioactivityDataAll> batchSearchDataByM4id(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifier",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"1135145\\\",\\\"394876\\\"]\"")})})
                                                    @RequestBody String[] spids);
    
    /**
     * {@code GET  /bioactivity/data/summary/search/by-aeid/:aeid} : get bioactivity summary for the "aeid".
     *
     * @param aeid the matching aeid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Summary data.
     */
    @Operation(summary = "Get summary by aeid", description = "Return summary data for given aeid", tags = {"bioactivity", "data"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {AssayAgg.class}))),
    })
    @RequestMapping(value = "/summary/search/by-aeid/{aeid}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    AssayAgg summaryByAeid(@Parameter(required = true, description = "Numeric assay endpoint identifier", example = "3032")
                                 @PathVariable("aeid") Integer aeid);
    
    /**
     * {@code GET  /bioactivity/data/summary/search/by-dtxsid/:dtxsid} : get bioactivity summary for the "dtxsid".
     *
     * @param dtxsid the matching aeid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Summary data.
     */
    @Operation(summary = "Get summary by dtxsid", description = "Return summary data for given dtxsid", tags = {"bioactivity", "data"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {ChemicalAgg.class}))),
    })
    @RequestMapping(value = "/summary/search/by-dtxsid/{dtxsid}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List summaryByDtxsid(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID9026974")
                                 @PathVariable("dtxsid")String dtxsid);
    

    @GetMapping(value = "/aed/search/by-dtxsid/{dtxsid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get AED data by dtxsid", description = "Returns AED data for given DTXSID", tags = {"bioactivity", "data"})
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AedData.class)))
    @ResponseBody
    List<AedData> getAedDataByDtxsid(@Parameter(required = true, example = "DTXSID5021209") @PathVariable String dtxsid);
    
    
    @Operation(summary = "Batch fetch AED data by DTXSIDs", description = "Returns AED bioactivity data for a list of DTXSIDs", tags = {"bioactivity", "data"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = AedData.class))))
    })
    @PostMapping(value = "/aed/search/by-dtxsid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<AedData> getAedDataForBatchDtxsids(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "List of DTXSIDs",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
            examples = {@ExampleObject("\"[\\\"DTXSID7020182\\\",\\\"DTXSID9020112\\\"]\"")})})
                                               @RequestBody List<String> dtxsids);
    

    /**
     * {@code GET  /bioactivity/data/summary/search/by-dtxsid/:dtxsid} : get bioactivity summary for the "dtxsid".
     *
     * @param dtxsid the matching aeid of the assays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Summary data.
     */
    @Operation(summary = "Get summary by dtxsid", description = "Return summary data for given dtxsid", tags = {"bioactivity", "data"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",  content = @Content( mediaType = "application/json",
                    schema=@Schema(oneOf = {SummaryByTissue.class}))),
    })
    @RequestMapping(value = "/summary/search/by-tissue/",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<SummaryByTissue> summaryByDtxsidAndTissue(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID7024241")String dtxsid,
                                 @Parameter(required = true, description = "Tissue of origin", example = "liver")String tissue);

}
