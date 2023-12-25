package ru.practicum.ewm.requests.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.requests.dto.ParticipationRequestDto;
import ru.practicum.ewm.requests.service.RequestService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users/{userId}/requests")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestController {

    RequestService requestService;

    @GetMapping
    public List<ParticipationRequestDto> getAllRequests(@PathVariable Long userId) {
        log.info("Received a request for the list of registrations from User: userId={}", userId);
        List<ParticipationRequestDto> requestDtoList = requestService.getAllRequests(userId);
        log.info("Returning {} item(s).", requestDtoList.size());
        return requestDtoList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto addRequest(@PathVariable Long userId, @RequestParam Long eventId) {
        log.info("Received a request for registration from User: userId={}, eventId={}", userId, eventId);
        ParticipationRequestDto requestDto = requestService.addRequest(userId, eventId);
        log.info("Returning request={}", requestDto);
        return requestDto;
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(@PathVariable Long userId, @PathVariable Long requestId) {
        log.info("Received a request to cancel registration from User: userId={}, requestId={}", userId, requestId);
        ParticipationRequestDto requestDto = requestService.cancelRequest(userId, requestId);
        log.info("Returning requestDto={}", requestDto);
        return requestDto;
    }
}
