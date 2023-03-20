package com.maersk.logapi.domain;

import java.util.List;

public interface LogToDestinationService {

    void logEntryToDestination(LogEntry entry);

    void logBulkOfEntriesToDestination(List<LogEntry> entry);

}
