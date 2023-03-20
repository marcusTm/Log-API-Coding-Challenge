package com.maersk.logapi.infrastructure.repository;

import com.maersk.logapi.domain.LogEntry;
import com.maersk.logapi.domain.LogToDestinationService;
import com.maersk.logapi.domain.RetrieveLogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LogToRepositoryService implements LogToDestinationService, RetrieveLogsService {

    private final LogEntryRepository logEntryRepository;

    @Override
    public List<LogEntry> getLogsOrderedByTimestamp(UUID traceId) {
        return logEntryRepository.findByTraceIdOrderByTimestampDesc(traceId);
    }
    @Override
    public void logEntryToDestination(LogEntry entry) {
        logEntryRepository.save(entry);
    }

    @Override
    public void logBulkOfEntriesToDestination(List<LogEntry> entries) {
        logEntryRepository.saveAll(entries);
    }

}
