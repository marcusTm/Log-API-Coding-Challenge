package com.maersk.logapi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class LogEntry {

    @NotNull
    // Source of log entry
    private UUID applicationId;

    @NotNull
    private UUID traceId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LogSeverityLevel severity;

    @NotNull
    private OffsetDateTime timestamp;

    @NotBlank
    private String message;

    // Optional metadata:
    private String componentName;

    private UUID requestId;
}
