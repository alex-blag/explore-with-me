package ru.ewm.main.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ValueMapping;
import ru.ewm.main.dto.event.EventBriefListResponseDto;
import ru.ewm.main.dto.event.EventCreateUserRequestDto;
import ru.ewm.main.dto.event.EventListResponseDto;
import ru.ewm.main.dto.event.EventPublicSorting;
import ru.ewm.main.dto.event.EventResponseDto;
import ru.ewm.main.dto.event.EventStateDto;
import ru.ewm.main.dto.event.EventStateUserAction;
import ru.ewm.main.dto.event.EventUpdateAdminRequestDto;
import ru.ewm.main.dto.event.EventUpdateUserRequestDto;
import ru.ewm.main.model.Category;
import ru.ewm.main.model.Location;
import ru.ewm.main.model.User;
import ru.ewm.main.model.event.Event;
import ru.ewm.main.model.event.EventPublishedState;
import ru.ewm.main.model.event.EventSorting;
import ru.ewm.main.model.event.EventState;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EventMapper {

    EventResponseDto toEventResponseDto(Event event);

    EventListResponseDto toEventListResponseDto(List<Event> events, long totalElements);

    @ValueMapping(target = "PENDING", source = "PENDING")
    @ValueMapping(target = "PUBLISHED", source = "PUBLISHED")
    @ValueMapping(target = "CANCELED", source = "CANCELED")
    List<EventState> toStates(List<EventStateDto> eventStates);

    @BeanMapping(
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "initiator", ignore = true)
    @Mapping(target = "publishedOn", source = "eventPublishedState.publishedOn")
    @Mapping(target = "state", source = "eventPublishedState.state")
    @Mapping(target = "description", source = "eventUpdateAdminRequestDto.description")
    void updateEvent(
            EventUpdateAdminRequestDto eventUpdateAdminRequestDto,
            Category category,
            Location location,
            EventPublishedState eventPublishedState,
            @MappingTarget Event event
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", source = "eventCreateUserRequestDto.description")
    @Mapping(target = "publishedOn", ignore = true)
    Event toEvent(
            EventCreateUserRequestDto eventCreateUserRequestDto,
            LocalDateTime createdOn,
            User initiator,
            Category category,
            Location location,
            EventState state
    );

    @ValueMapping(target = "PENDING", source = "SEND_TO_REVIEW")
    @ValueMapping(target = "CANCELED", source = "CANCEL_REVIEW")
    EventState toState(EventStateUserAction eventStateUserAction);

    @BeanMapping(
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "initiator", ignore = true)
    @Mapping(target = "publishedOn", ignore = true)
    @Mapping(target = "state", source = "eventUpdateUserRequestDto.stateAction")
    @Mapping(target = "description", source = "eventUpdateUserRequestDto.description")
    void updateEvent(
            EventUpdateUserRequestDto eventUpdateUserRequestDto,
            Category category,
            Location location,
            @MappingTarget Event event
    );

    EventBriefListResponseDto toEventBriefListResponseDto(List<Event> events, long totalElements);

    EventSorting toSorting(EventPublicSorting sorting);

}
