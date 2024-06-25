package ru.ewm.main.service.request.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.exception.ExceptionUtil;
import ru.ewm.main.mapper.RequestMapper;
import ru.ewm.main.model.User;
import ru.ewm.main.model.event.Event;
import ru.ewm.main.model.event.EventState;
import ru.ewm.main.model.request.Request;
import ru.ewm.main.model.request.RequestStatus;
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

        // TODO -- update views ?

        User requester = userPrivateService.getByIdOrThrow(requesterId);
        Request request = requestMapper.toRequest(LocalDateTime.now(), event, requester, getRequestStatus(event));
        return requestService.save(request);
    }

    @Override
    @Transactional
    public Request cancelByIdAndRequesterId(long id, long requesterId) {
        checkRequesterExistsOrThrow(requesterId);

        Request request = requestService.getByIdOrThrow(id);
        request.setStatus(RequestStatus.CANCELED);
        return request;
    }

    @Override
    public Page<Request> findAllByRequesterId(long requesterId, Pageable pageable) {
        checkRequesterExistsOrThrow(requesterId);
        return requestService.findAllByRequesterId(requesterId, pageable);
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

    private void checkRequesterExistsOrThrow(long requesterId) {
        if (!userPrivateService.existsById(requesterId)) {
            throw ExceptionUtil.getUserNotFoundException(requesterId);
        }
    }

}
