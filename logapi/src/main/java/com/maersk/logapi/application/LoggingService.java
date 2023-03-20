package com.maersk.logapi.application;

import com.maersk.logapi.domain.LogEntry;
import com.maersk.logapi.domain.LogToDestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoggingService {

    private final LogToDestinationService logToRepositoryService;

    public void logEntry(LogEntry logEntry){
        logToRepositoryService.logEntryToDestination(logEntry);
    }

    public void logBulkOfEntries(List<LogEntry> entries) {
        logToRepositoryService.logBulkOfEntriesToDestination(entries);
    }

}
