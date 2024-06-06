package ru.ewm.stats.common.dto;

import lombok.Data;

@Data
public class ViewStatsResponseDto {

    private String app;

    private String uri;

    private Long hits;

}
