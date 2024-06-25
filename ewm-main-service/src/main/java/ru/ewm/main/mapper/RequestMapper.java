package ru.ewm.main.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ewm.main.dto.request.RequestForParticipationListResponseDto;
import ru.ewm.main.dto.request.RequestForParticipationResponseDto;
import ru.ewm.main.model.Request;
import ru.ewm.main.model.RequestStatus;
import ru.ewm.main.model.User;
import ru.ewm.main.model.event.Event;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface RequestMapper {

    @Mapping(target = "eventId", source = "event.id")
    @Mapping(target = "requesterId", source = "request.id")
    RequestForParticipationResponseDto toRequestForParticipationResponseDto(Request request);

    RequestForParticipationListResponseDto toRequestForParticipationListResponseDto(List<Request> requests, long totalElements);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", source = "created")
    @Mapping(target = "event", source = "event")
    @Mapping(target = "requester", source = "requester")
    @Mapping(target = "status", source = "status")
    Request toRequest(LocalDateTime created, Event event, User requester, RequestStatus status);

}
