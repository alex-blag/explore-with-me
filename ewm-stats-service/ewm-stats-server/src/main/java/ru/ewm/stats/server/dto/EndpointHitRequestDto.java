package ru.ewm.stats.server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class EndpointHitRequestDto {

    @NotBlank
    private String app;

    @NotBlank
    private String uri;

    @NotBlank
    private String ip4;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

}
