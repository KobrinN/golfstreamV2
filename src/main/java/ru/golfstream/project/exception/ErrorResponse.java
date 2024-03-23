package ru.golfstream.project.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse{
    private int statusCode;
    private String message;
}
