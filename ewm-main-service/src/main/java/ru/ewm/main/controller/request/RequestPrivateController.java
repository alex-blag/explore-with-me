package ru.ewm.main.controller.request;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ewm.main.dto.request.RequestForParticipationListResponseDto;
import ru.ewm.main.dto.request.RequestForParticipationResponseDto;
import ru.ewm.main.mapper.RequestMapper;
import ru.ewm.main.model.request.Request;
import ru.ewm.main.service.request.RequestPrivateService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping(path = "/users/{userId}/requests")
@RequiredArgsConstructor
public class RequestPrivateController {

    private final RequestPrivateService requestPrivateService;
    private final RequestMapper requestMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequestForParticipationResponseDto createRequestForParticipationByRequesterIdAndEventId(
            @PathVariable long userId,
            @RequestParam long eventId
    ) {
        Request request = requestPrivateService.saveByEventIdAndRequesterId(eventId, userId);
        return requestMapper.toRequestForParticipationResponseDto(request);
    }

    @PatchMapping("/{requestId}/cancel")
    public RequestForParticipationResponseDto cancelRequestForParticipationByRequesterIdAndRequestId(
            @PathVariable long userId,
            @PathVariable long requestId
    ) {
        Request request = requestPrivateService.cancelByIdAndRequesterId(requestId, userId);
        return requestMapper.toRequestForParticipationResponseDto(request);
    }

    @GetMapping
    public RequestForParticipationListResponseDto getAllRequestsForParticipationByRequesterId(
            @PathVariable long userId,
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Request> requests = requestPrivateService.findAllByRequesterId(userId, pageable);
        return requestMapper.toRequestForParticipationListResponseDto(requests.getContent(), requests.getTotalElements());
    }

}
