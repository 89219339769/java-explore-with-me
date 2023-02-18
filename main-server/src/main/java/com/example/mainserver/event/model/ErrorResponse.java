package com.example.mainserver.event.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ErrorResponse {
    private final String message;
    private final String status;

    private final String reason;

    private LocalDateTime timestamp;

    public ErrorResponse(String error, String status, String reason, LocalDateTime timestamp) {
        this.message = error;
        this.status = status;
        this.reason = reason;
        this.timestamp=timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
