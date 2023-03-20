package com.maersk.logapi.infrastructure.repository;

import com.maersk.logapi.domain.LogEntry;
import org.springframework.data.repository.CrudRepository;

public interface LogEntryRepository extends CrudRepository<LogEntry, Long> {
}
