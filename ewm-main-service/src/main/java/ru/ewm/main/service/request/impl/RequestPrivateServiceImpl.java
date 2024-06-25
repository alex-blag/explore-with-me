package ru.ewm.main.service.request.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.exception.ExceptionUtil;
import ru.ewm.main.mapper.RequestMapper;
import ru.ewm.main.model.Request;
import ru.ewm.main.model.RequestStatus;
import ru.ewm.main.model.User;
import ru.ewm.main.model.event.Event;
import ru.ewm.main.model.event.EventState;
import ru.ewm.main.service.event.EventPrivateService;
import ru.ewm.main.service.request.RequestPrivateService;
import ru.ewm.main.service.request.RequestService;
import ru.ewm.main.service.user.UserPrivateService;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RequestPrivateServiceImpl implements RequestPrivateService {

    private final RequestService requestService;
    private final RequestMapper requestMapper;
    private final EventPrivateService eventPrivateService;
    private final UserPrivateService userPrivateService;

    @Override
    @Transactional
    public Request saveByEventIdAndRequesterId(long eventId, long requesterId) {
        checkRequestNotCreatedYetOrThrow(eventId, requesterId);
        Event event = eventPrivateService.getByIdOrThrow(eventId);

        checkRequesterNotOwnEventOrThrow(requesterId, event);
        checkEventAlreadyPublishedOrThrow(event);

        long confirmedRequests = requestService.countConfirmedRequestsByEventId(eventId);
        event.setConfirmedRequests(confirmedRequests);
        checkEventParticipantLimitNotReachedOrThrow(event);

        // TODO -- update views

        User requester = userPrivateService.getByIdOrThrow(requesterId);
        Request request = requestMapper.toRequest(LocalDateTime.now(), event, requester, getRequestStatus(event));
        return requestService.save(request);
    }

    @Override
    @Transactional
    public Request cancelByIdAndRequesterId(long id, long requesterId) {

        throw new UnsupportedOperationException();

        /*checkUserExistsOrThrow(requesterId, userPrivateService.existsById(requesterId));

        Predicate p = buildQRequestPredicateByIdAndRequesterId(id, requesterId);
        Request request = requestService.findOne(p);
        request.setStatus(RequestStatus.CANCELED);

        return request;*/
    }

    @Override
    public Page<Request> findAllByRequesterId(long requesterId, Pageable pageable) {

        throw new UnsupportedOperationException();

        /*checkUserExistsOrThrow(requesterId, userPrivateService.existsById(requesterId));

        Predicate p = buildQRequestPredicateByRequesterId(requesterId);
        return requestService.findAll(p);*/
    }

    private void checkRequestNotCreatedYetOrThrow(long eventId, long requesterId) {
        if (requestService.existsByEventIdAndRequesterId(eventId, requesterId)) {
            throw ExceptionUtil.getRequestAlreadyCreatedException(eventId, requesterId);
        }
    }

    private void checkRequesterNotOwnEventOrThrow(long requesterId, Event event) {
        if (requesterId == event.getInitiator().getId()) {
            throw ExceptionUtil.getRequesterOwnsEventException(requesterId, event.getId());
        }
    }

    private void checkEventAlreadyPublishedOrThrow(Event event) {
        if (event.getState() != EventState.PUBLISHED) {
            throw ExceptionUtil.getEventNotPublishedException(event.getId(), event.getState());
        }
    }

    private void checkEventParticipantLimitNotReachedOrThrow(Event event) {
        int participantLimit = event.getParticipantLimit();
        if (participantLimit > 0 && event.getConfirmedRequests() >= participantLimit) {
            throw ExceptionUtil.getEventParticipantLimitReachedException(event.getId(), participantLimit);
        }
    }

    private RequestStatus getRequestStatus(Event event) {
        return event.getRequestModeration()
                ? RequestStatus.PENDING
                : RequestStatus.CONFIRMED;
    }

    /*@Override
    public List<Request> findAllByEventIdAndInitiatorId(long eventId, long initiatorId) {
        checkUserExistsOrThrow(initiatorId, userPrivateService.existsById(initiatorId));
        checkEventExistsOrThrow(eventId, eventPrivateService.existsByIdAndInitiatorId(eventId, initiatorId));

        Predicate p = buildQRequestPredicateByEventId(eventId);
        return requestService.findAll(p);
    }*/

    /*private Predicate buildQRequestPredicateByIdAndRequesterId(long id, long requesterId) {
        return buildQRequestPredicateByIdAndEventIdAndRequesterId(id, null, requesterId);
    }

    private Predicate buildQRequestPredicateByEventId(long eventId) {
        return buildQRequestPredicateByIdAndEventIdAndRequesterId(null, eventId, null);
    }

    private Predicate buildQRequestPredicateByRequesterId(long requesterId) {
        return buildQRequestPredicateByIdAndEventIdAndRequesterId(null, null, requesterId);
    }

    private Predicate buildQRequestPredicateByEventIdAndRequesterId(long eventId, long requesterId) {
        return buildQRequestPredicateByIdAndEventIdAndRequesterId(null, eventId, requesterId);
    }

    private Predicate buildQRequestPredicateByIdAndEventIdAndRequesterId(Long id, Long eventId, Long requesterId) {
        BooleanBuilder builder = new BooleanBuilder();

        if (id != null) {
            builder.and(Q_REQUEST.id.eq(id));
        }

        if (eventId != null) {
            builder.and(Q_REQUEST.event.id.eq(eventId));
        }

        if (requesterId != null) {
            builder.and(Q_REQUEST.requester.id.eq(requesterId));
        }

        return builder;
    }

    private Predicate buildQRequestPredicateByIds(List<Long> ids) {
        return buildQRequestPredicateByIdsAndStatus(ids, null);
    }

    private Predicate buildQRequestPredicateByIdsAndStatus(List<Long> ids, RequestStatus status) {
        BooleanBuilder builder = new BooleanBuilder();

        if (ids != null) {
            builder.and(Q_REQUEST.id.in(ids));
        }

        if (status != null) {
            builder.and(Q_REQUEST.status.eq(status));
        }

        return builder;
    }*/

}
