package ru.ewm.main.service.compilation.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.model.Compilation;
import ru.ewm.main.model.event.Event;
import ru.ewm.main.service.compilation.CompilationPublicService;
import ru.ewm.main.service.compilation.CompilationService;
import ru.ewm.main.service.event.EventPublicService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompilationPublicServiceImpl implements CompilationPublicService {

    private final CompilationService compilationService;
    private final EventPublicService eventPublicService;

    @Override
    public Compilation getByIdOrThrow(long id) {
        Compilation compilation = compilationService.getByIdOrThrow(id);
        eventPublicService.updateNumberOfConfirmedRequests(compilation.getEvents());
        return compilation; // TODO -- update views ?
    }

    @Override
    public Page<Compilation> findAllByPinned(Boolean pinned, Pageable pageable) {
        Page<Compilation> compilations = compilationService.findAllByPinned(pinned, pageable);
        List<Event> events = compilations.getContent()
                .stream()
                .map(Compilation::getEvents)
                .flatMap(Collection::stream)
                .collect(Collectors
                        .toList());
        eventPublicService.updateNumberOfConfirmedRequests(events);
        return compilations; // TODO -- update views ?
    }

}
