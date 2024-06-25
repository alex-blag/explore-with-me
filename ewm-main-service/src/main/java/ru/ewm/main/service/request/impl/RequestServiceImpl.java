package ru.ewm.main.service.request.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.exception.ExceptionUtil;
import ru.ewm.main.model.request.Request;
import ru.ewm.main.model.request.RequestStatus;
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

    @Override
    public Request getByIdOrThrow(long id) {
        return requestRepository
                .findById(id)
                .orElseThrow(() -> ExceptionUtil.getRequestNotFoundException(id));
    }

    @Override
    public Page<Request> findAllByRequesterId(long requesterId, Pageable pageable) {
        return requestRepository.findAllByRequesterId(requesterId, pageable);
    }

}
