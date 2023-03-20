package com.maersk.logapi.api;

import com.maersk.logapi.application.LoggingService;
import com.maersk.logapi.domain.LogEntry;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoggingControllerTest {

    @Mock
    private LoggingService loggingService;
    @InjectMocks
    private LoggingController controller;

    @Test
    void getLogEntries() {
        //Given
        UUID traceId = UUID.randomUUID();
        List<LogEntry> relevantLogEntries = Arrays.asList(new LogEntry(), new LogEntry());
        given(loggingService.retrieveLogEntries(traceId)).willReturn(relevantLogEntries);

        //When
        List<LogEntry> result = controller.getLogEntries(traceId);

        //Then
        assertEquals(relevantLogEntries, result);
    }

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