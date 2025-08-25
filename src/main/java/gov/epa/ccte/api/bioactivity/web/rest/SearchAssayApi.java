package gov.epa.ccte.api.bioactivity.web.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import gov.epa.ccte.api.bioactivity.domain.SearchAssay;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Assay Search Resource", description = "API endpoints for searching assays (starts-with, exact, contains.")
@SecurityRequirement(name = "api_key")
public interface SearchAssayApi {

    @Operation(summary = "Search by starting value", description = "NOTE: Search value needs to be URL encoded for synonyms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {SearchAssay.class}))),
            @ApiResponse(responseCode = "400", description = "Data not found.",
                    content = @Content(mediaType = "application/problem+json", schema = @Schema(oneOf = {ProblemDetail.class})))
    })
    @GetMapping(value = "bioactivity/search/start-with/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<SearchAssay> assayStartWith(
            @Parameter(required = true, description = "Starting characters for search value",
                    examples = {@ExampleObject(name="Search Value", value = "ATG_S")})
            @PathVariable("value") String value,
            @RequestParam(value = "top", required = false, defaultValue = "500") Integer top);

    @Operation(summary = "Search by exact value", description = "NOTE: Search value needs to be URL encoded for synonyms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {SearchAssay.class}))),
            @ApiResponse(responseCode = "400", description = "Data not found.",
                    content = @Content(mediaType = "application/problem+json", schema = @Schema(oneOf = {ProblemDetail.class})))
    })
    @GetMapping(value = "bioactivity/search/equal/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<SearchAssay> assayEqual(
            @Parameter(required = true, description = "Exact match of search value",
                    examples = {@ExampleObject(name="Search Value", value = "ATG_STAT3_CIS")})
            @PathVariable("value") String value);

    @Operation(summary = "Search by substring value", description = "NOTE: Search value needs to be URL encoded for synonyms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {SearchAssay.class}))),
            @ApiResponse(responseCode = "400", description = "Data not found.",
                    content = @Content(mediaType = "application/problem+json", schema = @Schema(oneOf = {ProblemDetail.class})))
    })
    @GetMapping(value = "bioactivity/search/contain/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<SearchAssay> assayContain(
            @Parameter(required = true, description = "Substring of search word",
                    examples = {@ExampleObject(name="Search Value", value = "AT3_CIS"),
                            @ExampleObject(name="Synonym", value = "razine")})
            @PathVariable("value") String value,
            @RequestParam(value = "top", required = false, defaultValue = "0") Integer top);
}
