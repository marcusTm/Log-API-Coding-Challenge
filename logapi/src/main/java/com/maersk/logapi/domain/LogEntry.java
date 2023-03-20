package com.maersk.logapi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Entity
public class LogEntry {

    @Id
    @GeneratedValue
    private Long logEntryId;

    @NotNull
    @Getter
    // Source of log entry
    private UUID applicationId;

    @NotNull
    @Getter
    private UUID traceId;

    @NotNull
    @Getter
    @Enumerated(EnumType.STRING)
    private LogSeverityLevel severity;

    @Getter
    @NotNull
    private OffsetDateTime timestamp;

    @Getter
    @NotBlank
    private String message;

    // Optional metadata:
    @Getter
    private String componentName;
    @Getter
    private UUID requestId;
}
