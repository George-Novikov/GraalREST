package com.fatemorgan.graalrest.rest;

import com.fatemorgan.graalrest.services.ScriptRunningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/api/scripts")
public class ScriptRunningController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScriptRunningController.class);
    ScriptRunningService scriptRunningService;

    public ScriptRunningController(ScriptRunningService scriptRunningService) {
        this.scriptRunningService = scriptRunningService;
    }

    @PostMapping(
            path = "/run",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> runScript(@RequestBody MultiValueMap<String, Object> formInput){
        try {
            return ResponseEntity.ok(scriptRunningService.run(formInput));
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
