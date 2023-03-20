package com.maersk.logapi.application;

import com.maersk.logapi.domain.LogEntry;
import com.maersk.logapi.domain.LogToDestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoggingService {

    // Note: Spring uses variable names of the following two fields to determine the classes to call
    private final LogToDestinationService logToRepositoryService;
    private final LogToDestinationService logToMQService;

    public void logEntry(LogEntry logEntry){
        logToRepositoryService.logEntryToDestination(logEntry);
        logToMQService.logEntryToDestination(logEntry);

    }

    public void logBulkOfEntries(List<LogEntry> entries) {
        logToRepositoryService.logBulkOfEntriesToDestination(entries);
        logToMQService.logBulkOfEntriesToDestination(entries);
    }

}
