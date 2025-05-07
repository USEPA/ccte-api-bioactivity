package gov.epa.ccte.api.bioactivity.web.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gov.epa.ccte.api.bioactivity.domain.AOP;
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
 * REST controller for getting the {@link gov.epa.ccte.api.bioactivity.domain.BioactivityData}s.
 */
@Tag(name = "Bioactivity AOP Data Resource",
        description = "API endpoints for collecting bioactivity AOP data.")
@SecurityRequirement(name = "api_key")
@RequestMapping("bioactivity/aop")
public interface AOPApi {
    /**
     * {@code GET  /bioactivity/aop/search/by-toxcast-aeid/{toxcastAeid} : get bioactivity aop data for the "toxcastAeid".
     *
     * @param toxcastAeid the matching toxcastAeid of the AOPs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get aop data by toxcast aeid", description = "Return aop data for given toxcast aeid", tags = {"bioactivity", "aop"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content( mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AOP.class))))
    })
    @RequestMapping(value = "/search/by-toxcast-aeid/{toxcastAeid}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<AOP> dataByToxcastAeid(@Parameter(required = true, description = "Toxcast Aeid", example = "63")@PathVariable("toxcastAeid") Integer toxcastAeid);
    
    /**
     * {@code GET  /bioactivity/aop/search/by-entrez-gene-id/{entrezGeneId} : get bioactivity aop data for the "entrezGeneId".
     *
     * @param entrezGeneId the matching entrezGeneId of the AOPs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get aop data by entrez gene id", description = "Return aop data for given entrez gene id", tags = {"bioactivity", "aop"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content( mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AOP.class))))
    })
    @RequestMapping(value = "/search/by-entrez-gene-id/{entrezGeneId}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<AOP> dataByEntrezGeneId(@Parameter(required = true,  description = "Entrez Gene Id",example = "196")@PathVariable("entrezGeneId") Integer entrezGeneId);
    
    /**
     * {@code GET  /bioactivity/aop/search/by-event-number/{eventNumber} : get bioactivity aop data for the "eventNumber".
     *
     * @param eventNumber the matching eventNumber of the AOPs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bioactivity data.
     */
    @Operation(summary = "Get aop data by event number", description = "Return aop data for given event number", tags = {"bioactivity", "aop"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content( mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AOP.class))))
    })
    @RequestMapping(value = "/search/by-event-number/{eventNumber}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<AOP> dataByEventNumber(@Parameter(required = true,  description = "Event Number", example = "18")@PathVariable("eventNumber") Integer eventNumber);
}
