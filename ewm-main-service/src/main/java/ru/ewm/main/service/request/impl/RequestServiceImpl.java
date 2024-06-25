package ru.ewm.main.service.request.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.model.Request;
import ru.ewm.main.model.RequestStatus;
import ru.ewm.main.repository.RequestRepository;
import ru.ewm.main.service.request.RequestService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Override
    public Request save(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public boolean existsByEventIdAndRequesterId(long eventId, long requesterId) {
        return requestRepository.existsByEventIdAndRequesterId(eventId, requesterId);
    }

    @Override
    public long countConfirmedRequestsByEventId(long eventId) {
        return requestRepository.countAllByEventIdAndStatus(eventId, RequestStatus.CONFIRMED);
    }

    /*@Override
    public Request findOne(Predicate predicate) {
        return requestRepository
                .findOne(predicate)
                .orElseThrow(() -> getRequestNotFoundException(predicate.toString()));
    }

    @Override
    public List<Request> findAll(Predicate predicate) {
        return toList(requestRepository.findAll(predicate));
    }

    @Override
    public Map<Long, Long> mapEventIdsToConfirmedRequests(List<Long> eventIds) {
        Predicate p = buildQRequestPredicateConfirmedByEventIds(eventIds);
        return this
                .findAll(p)
                .stream()
                .collect(groupingBy(r -> r.getEvent().getId(), counting()));
    }

    @Override
    public boolean exists(Predicate predicate) {
        return requestRepository.exists(predicate);
    }

    private Predicate buildQRequestPredicateConfirmedByEventIds(List<Long> eventIds) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(Q_REQUEST.status.eq(RequestStatus.CONFIRMED));

        if (eventIds != null) {
            builder.and(Q_REQUEST.event.id.in(eventIds));
        }

        return builder;
    }*/

}
