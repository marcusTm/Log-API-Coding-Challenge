package com.maersk.logapi.application;

import com.maersk.logapi.domain.LogEntry;
import com.maersk.logapi.domain.LogToDestinationService;
import com.maersk.logapi.domain.RetrieveLogsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoggingServiceTest {

    @Mock
    RetrieveLogsService retrieveLogsService;

    @Mock
    LogToDestinationService logToDestinationService;

    @InjectMocks
    private LoggingService service;

    @Test
    void retrieveLogEntries() {
        //Given
        UUID traceId = UUID.randomUUID();
        List<LogEntry> relevantLogEntries = Arrays.asList(new LogEntry(), new LogEntry());
        given(retrieveLogsService.getLogsOrderedByTimestamp(traceId)).willReturn(relevantLogEntries);

        //When
        List<LogEntry> result = service.retrieveLogEntries(traceId);

        //Then
        assertEquals(relevantLogEntries, result);
    }

    @Test
    void logEntry() {
        //Given
        LogEntry entry = new LogEntry();

        //When
        service.logEntry(entry);

        //Then
        verify(logToDestinationService, times(2)).logEntryToDestination(entry);
    }

    @Test
    void logBulkOfEntries() {
        //Given
        List<LogEntry> entries = List.of(new LogEntry());

        //When
        service.logBulkOfEntries(entries);

        //Then
        verify(logToDestinationService, times(2)).logBulkOfEntriesToDestination(entries);
    }
}