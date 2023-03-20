package com.maersk.logapi.infrastructure.rabbitmq;

import com.maersk.logapi.domain.LogEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LogToMQServiceTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Captor
    ArgumentCaptor<LogEntry> logEntryCaptor;
    @InjectMocks
    private LogToMQService service;

    @Test
    void logEntryToDestination() {
        //Given
        LogEntry entry = new LogEntry();

        //When
        service.logEntryToDestination(entry);

        //Then
        verify(rabbitTemplate).convertAndSend(RabbitMQConfig.exchangeName, "", entry);

    }

    @Test
    void send2MessagesWhenLoggingBulkOf2Entries() {
        //Given
        LogEntry entry1 = new LogEntry();
        LogEntry entry2 = new LogEntry();

        //When
        service.logBulkOfEntriesToDestination(Arrays.asList(entry1, entry2));

        //Then
        verify(rabbitTemplate, times(2)).convertAndSend(eq(RabbitMQConfig.exchangeName),
                eq(""), logEntryCaptor.capture());
        assertAll(
                () -> assertEquals(entry1, logEntryCaptor.getAllValues().get(0)),
                () -> assertEquals(entry2, logEntryCaptor.getAllValues().get(1))
        );
    }
}