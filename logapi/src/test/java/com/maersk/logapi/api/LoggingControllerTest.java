package com.maersk.logapi.api;

import com.maersk.logapi.application.LoggingService;
import com.maersk.logapi.domain.LogEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoggingControllerTest {

    @Mock
    private LoggingService loggingService;
    @InjectMocks
    private LoggingController controller;

    @Test
    void addLogEntry() {
        //Given
        LogEntry logEntry = new LogEntry();

        //When
        controller.addLogEntry(logEntry);

        //Then
        verify(loggingService).logEntry(logEntry);
    }

    @Test
    void addBulkOfLogEntries() {
        //Given
        List<LogEntry> entries = List.of(new LogEntry());

        //When
        controller.addBulkOfLogEntries(entries);

        //Then
        verify(loggingService).logBulkOfEntries(entries);
    }
}