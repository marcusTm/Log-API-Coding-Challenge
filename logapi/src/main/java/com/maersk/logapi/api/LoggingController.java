package com.maersk.logapi.api;

import com.maersk.logapi.application.LoggingService;
import com.maersk.logapi.domain.LogEntry;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/log-entries")
@RequiredArgsConstructor
public class LoggingController {

    private final LoggingService service;

    @Operation(summary = "Add entry to log")
    @PostMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void addLogEntry(@Valid @RequestBody LogEntry logEntry){
        service.logEntry(logEntry);
    }

    @Operation(summary = "Add batch of log entries")
    @PostMapping("batch")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void addBulkOfLogEntries(@RequestBody List<LogEntry> entries){
        service.logBulkOfEntries(entries);
    }
}
