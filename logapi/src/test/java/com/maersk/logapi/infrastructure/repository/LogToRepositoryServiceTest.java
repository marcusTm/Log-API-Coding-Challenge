package com.maersk.logapi.infrastructure.repository;

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
class LogToRepositoryServiceTest {

    @Mock
    private LogEntryRepository logEntryRepository;
    @InjectMocks
    private LogToRepositoryService service;
    @Test
    void logEntryToDestination() {
        //Given
        LogEntry entry = new LogEntry();

        //When
        service.logEntryToDestination(entry);

        //Then
        verify(logEntryRepository).save(entry);
    }

    @Test
    void logBulkOfEntriesToDestination() {
        //Given
        List<LogEntry> entries = List.of(new LogEntry());

        //When
        service.logBulkOfEntriesToDestination(entries);

        //Then
        verify(logEntryRepository).saveAll(entries);
    }

    @Test
    void getLogsOrderedByTimestamp() {
        //Given
        UUID traceId = UUID.randomUUID();
        List<LogEntry> relevantLogEntries = Arrays.asList(new LogEntry(), new LogEntry());
        given(logEntryRepository.findByTraceIdOrderByTimestampDesc(traceId)).willReturn(relevantLogEntries);

        //When
        List<LogEntry> result = service.getLogsOrderedByTimestamp(traceId);

        //Then
        assertEquals(relevantLogEntries, result);
    }
}