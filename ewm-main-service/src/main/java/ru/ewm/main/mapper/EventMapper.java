package ru.ewm.main.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ValueMapping;
import ru.ewm.main.dto.event.EventListResponseDto;
import ru.ewm.main.dto.event.EventResponseDto;
import ru.ewm.main.dto.event.EventState;
import ru.ewm.main.dto.event.EventUpdateAdminRequestDto;
import ru.ewm.main.model.Category;
import ru.ewm.main.model.Event;
import ru.ewm.main.model.EventPublishedState;
import ru.ewm.main.model.Location;
import ru.ewm.main.model.State;

import java.util.List;

@Mapper
public interface EventMapper {

    EventResponseDto toEventResponseDto(Event event);

    EventListResponseDto toEventListResponseDto(List<Event> events, long totalElements);

    @ValueMapping(target = "PENDING", source = "PENDING")
    @ValueMapping(target = "PUBLISHED", source = "PUBLISHED")
    @ValueMapping(target = "CANCELED", source = "CANCELED")
    List<State> toStates(List<EventState> eventStates);

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

}
