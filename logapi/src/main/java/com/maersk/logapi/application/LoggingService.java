package com.maersk.logapi.application;

import com.maersk.logapi.domain.LogEntry;
import com.maersk.logapi.domain.LogToDestinationService;
import com.maersk.logapi.domain.RetrieveLogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoggingService {

    private final RetrieveLogsService retrieveLogsService;

    // Note: Spring uses variable names of the following two fields to determine the classes to call
    private final LogToDestinationService logToRepositoryService;
    private final LogToDestinationService logToMQService;

    public List<LogEntry> retrieveLogEntries(UUID traceId) {
        return retrieveLogsService.getLogsOrderedByTimestamp(traceId);
    }

    public void logEntry(LogEntry logEntry){
        logToRepositoryService.logEntryToDestination(logEntry);
        logToMQService.logEntryToDestination(logEntry);

    }

    public void logBulkOfEntries(List<LogEntry> entries) {
        logToRepositoryService.logBulkOfEntriesToDestination(entries);
        logToMQService.logBulkOfEntriesToDestination(entries);
    }

}
