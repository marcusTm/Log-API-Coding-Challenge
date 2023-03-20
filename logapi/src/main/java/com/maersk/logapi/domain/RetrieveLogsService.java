package com.maersk.logapi.domain;

import java.util.List;
import java.util.UUID;

public interface RetrieveLogsService {
    List<LogEntry> getLogsOrderedByTimestamp(UUID traceId);
}
