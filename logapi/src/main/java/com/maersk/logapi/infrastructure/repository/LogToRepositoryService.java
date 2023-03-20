package com.maersk.logapi.infrastructure.repository;

import com.maersk.logapi.domain.LogEntry;
import com.maersk.logapi.domain.LogToDestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LogToRepositoryService implements LogToDestinationService {

    private final LogEntryRepository logEntryRepository;
    @Override
    public void logEntryToDestination(LogEntry entry) {
        logEntryRepository.save(entry);
    }

    @Override
    public void logBulkOfEntriesToDestination(List<LogEntry> entries) {
        logEntryRepository.saveAll(entries);
    }

}
