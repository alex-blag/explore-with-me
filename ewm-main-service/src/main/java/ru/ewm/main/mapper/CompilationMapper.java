package ru.ewm.main.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.ewm.main.dto.compilation.CompilationCreateRequestDto;
import ru.ewm.main.dto.compilation.CompilationListResponseDto;
import ru.ewm.main.dto.compilation.CompilationResponseDto;
import ru.ewm.main.dto.compilation.CompilationUpdateRequestDto;
import ru.ewm.main.model.Compilation;
import ru.ewm.main.model.event.Event;

import java.util.List;

@Mapper
public interface CompilationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", source = "events")
    Compilation toCompilation(CompilationCreateRequestDto compilationCreateRequestDto, List<Event> events);

    CompilationResponseDto toCompilationResponseDto(Compilation compilation);

    @BeanMapping(
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", source = "events")
    void updateCompilation(
            CompilationUpdateRequestDto compilationUpdateRequestDto,
            List<Event> events,
            @MappingTarget Compilation compilation
    );

    CompilationListResponseDto toCompilationListResponseDto(List<Compilation> compilations, long totalElements);

}
