package ru.ewm.stats.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EndpointHitResponseDto {

    private Long id;

    private String app;

    private String uri;

    private String ip4;

    private LocalDateTime timestamp;

}
