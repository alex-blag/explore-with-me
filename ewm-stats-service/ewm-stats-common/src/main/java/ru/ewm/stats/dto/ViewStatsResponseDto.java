package ru.ewm.stats.dto;

import lombok.Data;

@Data
public class ViewStatsResponseDto {

    private String app;

    private String uri;

    private Long hits;

}
