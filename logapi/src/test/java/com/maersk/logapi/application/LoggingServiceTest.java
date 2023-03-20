package com.maersk.logapi.application;

import com.maersk.logapi.domain.LogEntry;
import com.maersk.logapi.domain.LogToDestinationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoggingServiceTest {

    @Mock
    private LogToDestinationService logToRepositoryService;
    @InjectMocks
    private LoggingService service;

    @Test
    void logEntry() {
        //Given
        LogEntry entry = new LogEntry();

        //When
        service.logEntry(entry);

        //Then
        verify(logToRepositoryService).logEntryToDestination(entry);
    }

    @Test
    void logBulkOfEntries() {
        //Given
        List<LogEntry> entries = List.of(new LogEntry());

        //When
        service.logBulkOfEntries(entries);

        //Then
        verify(logToRepositoryService).logBulkOfEntriesToDestination(entries);
    }
}