package gov.epa.ccte.api.bioactivity.web.rest;

import gov.epa.ccte.api.bioactivity.domain.AnalyticalQC;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Analytical QC Resource", description = "API endpoints for Analytical QC data.")
@RequestMapping(value = "/bioactivity/analyticalqc", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AnalyticalQCApi {

    @Operation(summary = "Get analytical QC data by DTXSID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/search/by-dtxsid/{dtxsid}")
    List<AnalyticalQC> findByDsstoxSubstanceId(@PathVariable("dtxsid") String dtxsid);
}