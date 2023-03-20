package com.maersk.logapi.infrastructure.repository;

import com.maersk.logapi.domain.LogEntry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface LogEntryRepository extends CrudRepository<LogEntry, Long> {
    List<LogEntry> findByTraceIdOrderByTimestampDesc(UUID traceId);
}
