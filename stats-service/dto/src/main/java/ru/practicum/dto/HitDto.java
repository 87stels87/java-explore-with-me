package ru.practicum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class HitDto {

    private Long id;

    @NotNull(message = "app cannot be empty.")
    @NotBlank(message = "app it cannot consist only of spaces.")
    String app;

    @NotNull(message = "uri cannot be empty.")
    @NotBlank(message = "uri it cannot consist only of spaces.")
    String uri;

    @NotNull(message = "ip cannot be empty.")
    @NotBlank(message = "ip it cannot consist only of spaces.")
    String ip;

    @NotNull(message = "timestamp cannot be empty.")
    @NotBlank(message = "timestamp it cannot consist only of spaces.")
    LocalDateTime timestamp;
}