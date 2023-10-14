package com.fatemorgan.graalrest.rest;

import com.fatemorgan.graalrest.objects.ScriptRunRequest;
import com.fatemorgan.graalrest.services.ScriptRunningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("rest/api/scripts")
public class ScriptRunningController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScriptRunningController.class);
    ScriptRunningService scriptRunningService;

    public ScriptRunningController(ScriptRunningService scriptRunningService) {
        this.scriptRunningService = scriptRunningService;
    }

    @PostMapping("/run")
    public ResponseEntity<Object> runScript(@RequestBody ScriptRunRequest script){
        try {
            return ResponseEntity.ok(scriptRunningService.run(script));
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}