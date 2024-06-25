package ru.ewm.main.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class RequestForParticipationListResponseDto {

    private List<RequestForParticipationResponseDto> requests;

    private Long totalElements;

}
