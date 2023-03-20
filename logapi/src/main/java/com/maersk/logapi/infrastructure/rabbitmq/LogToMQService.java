package com.maersk.logapi.infrastructure.rabbitmq;

import com.maersk.logapi.domain.LogEntry;
import com.maersk.logapi.domain.LogToDestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogToMQService implements LogToDestinationService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void logEntryToDestination(LogEntry entry) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeName, "", entry);
    }

    @Override
    public void logBulkOfEntriesToDestination(List<LogEntry> entries) {
        entries.forEach(entry ->
                rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeName, "", entry)
        );
    }
}
