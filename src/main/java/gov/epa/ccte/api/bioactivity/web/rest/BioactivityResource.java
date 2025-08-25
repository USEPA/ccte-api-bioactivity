package gov.epa.ccte.api.bioactivity.web.rest;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for getting the {@link BioactivityResource}s.
 */
@Slf4j
@RestController
@CrossOrigin
@Hidden // OpenAPI annotation for hidding endpoints from documentation generator
public class BioactivityResource {
    private final JdbcTemplate jdbcTemplate;

    public BioactivityResource(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @GetMapping("/bioactivity/health")
    public ResponseEntity health(){

        log.info("checking the health");

        if(jdbcTemplate != null){
            try {
                jdbcTemplate.execute("SELECT 1 ");
                log.debug("DB connection established");

                return ResponseEntity.ok().build();

            } catch (Exception ep){
                return ResponseEntity.notFound().build();
            }
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
