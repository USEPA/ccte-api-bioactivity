package gov.epa.ccte.api.bioactivity.web.rest;

import gov.epa.ccte.api.bioactivity.domain.AnalyticalQC;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Analytical QC Resource", description = "Endpoint for Analytical QC data to inform the chemical's applicability domain for in vitro screening at the sample and substance level. These curated data sourced from US EPA's Toxicity Forecaster (ToxCast) invitrodb.")
@RequestMapping(value = "/bioactivity/analyticalqc", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AnalyticalQCApi {

    @Operation(summary = "Get analytical QC data by DTXSID", description = "return analytical QC data for requested DTXSID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {AnalyticalQC.class}))),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {ProblemDetail.class})))
    })
    @GetMapping("/search/by-dtxsid/{dtxsid}")
    List<AnalyticalQC> findByDsstoxSubstanceId(@PathVariable("dtxsid") String dtxsid);
}

