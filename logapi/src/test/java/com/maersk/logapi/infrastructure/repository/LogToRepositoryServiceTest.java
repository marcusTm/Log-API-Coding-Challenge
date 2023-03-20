package com.maersk.logapi.infrastructure.repository;

import com.maersk.logapi.domain.LogEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
}