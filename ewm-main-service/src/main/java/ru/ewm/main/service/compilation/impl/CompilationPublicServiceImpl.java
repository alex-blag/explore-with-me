package ru.ewm.main.service.compilation.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.model.Compilation;
import ru.ewm.main.service.compilation.CompilationPublicService;
import ru.ewm.main.service.compilation.CompilationService;
import ru.ewm.main.service.event.EventPublicService;

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
        throw new UnsupportedOperationException();
    }

//    @Override
//    public List<Compilation> findAllByPinned(Boolean pinned, int page, int size) {
//        Predicate p = buildQCompilationPredicateByPinned(pinned);
//        Pageable pageable = PageRequest.of(page, size);
//        List<Compilation> compilations = compilationService.findAll(p, pageable);
//
//        List<Event> events = compilations
//                .stream()
//                .map(Compilation::getEvents)
//                .flatMap(Collection::stream)
//                .collect(toList());
//
//        eventPublicService.updateConfirmedRequestsAndViews(events);
//
//        return compilations;
//    }
//
//    private Predicate buildQCompilationPredicateByPinned(Boolean pinned) {
//        BooleanBuilder builder = new BooleanBuilder();
//
//        if (pinned != null) {
//            builder.and(Q_COMPILATION.pinned.eq(pinned));
//        }
//
//        return builder;
//    }

}
