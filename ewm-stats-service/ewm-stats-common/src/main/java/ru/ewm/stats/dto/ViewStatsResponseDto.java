package ru.ewm.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewStatsResponseDto {

    private String app;

    private String uri;

    private Long hits;

}
