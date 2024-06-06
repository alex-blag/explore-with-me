package ru.ewm.stats.server.dto;

import lombok.Data;

@Data
public class ViewStatsResponseDto {

    private String app;

    private String uri;

    private Long hits;

}
