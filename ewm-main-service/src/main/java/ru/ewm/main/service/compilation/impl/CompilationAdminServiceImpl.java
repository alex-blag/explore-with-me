package ru.ewm.main.service.compilation.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.dto.compilation.CompilationCreateRequestDto;
import ru.ewm.main.dto.compilation.CompilationUpdateRequestDto;
import ru.ewm.main.mapper.CompilationMapper;
import ru.ewm.main.model.Compilation;
import ru.ewm.main.model.event.Event;
import ru.ewm.main.service.compilation.CompilationAdminService;
import ru.ewm.main.service.compilation.CompilationService;
import ru.ewm.main.service.event.EventAdminService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompilationAdminServiceImpl implements CompilationAdminService {

    private final CompilationService compilationService;
    private final CompilationMapper compilationMapper;
    private final EventAdminService eventAdminService;

    @Override
    @Transactional
    public Compilation save(CompilationCreateRequestDto compilationCreateRequestDto) {
        List<Event> events = eventAdminService.findAllByIds(compilationCreateRequestDto.getEventIds());
        Compilation compilation = compilationMapper.toCompilation(compilationCreateRequestDto, events);
        return compilationService.save(compilation);
    }

    @Override
    @Transactional
    public Compilation updateById(long id, CompilationUpdateRequestDto compilationUpdateRequestDto) {
        List<Event> events = eventAdminService.findAllByIds(compilationUpdateRequestDto.getEventIds());
        Compilation compilation = compilationService.getByIdOrThrow(id);
        compilationMapper.updateCompilation(compilationUpdateRequestDto, events, compilation);
        return compilation;
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        compilationService.deleteById(id);
    }

}
