package ru.practicum.ewm.events.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.events.dto.EventFullDto;
import ru.practicum.ewm.events.dto.EventShortDto;
import ru.practicum.ewm.events.dto.NewEventDto;
import ru.practicum.ewm.events.dto.UpdateEventUserRequest;
import ru.practicum.ewm.events.service.EventService;
import ru.practicum.ewm.requests.dto.ParticipationRequestDto;
import ru.practicum.ewm.requests.model.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.requests.model.EventRequestStatusUpdateResult;
import ru.practicum.ewm.requests.service.RequestService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventControllerPrivate {

    EventService eventService;

    RequestService requestService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public EventFullDto addEvent(@PathVariable Long userId,
                                 @RequestBody @Valid NewEventDto newEventDto) {
        log.info("Received a request to add an event: userId={}, newEventDto={}", userId, newEventDto);
        EventFullDto eventFullDto = eventService.addEvent(userId, newEventDto);
        log.info("Returning the created event: eventFullDto={}", eventFullDto);
        return eventFullDto;
    }

    @GetMapping
    List<EventShortDto> getEventShortByOwner(@PathVariable Long userId,
                                             @RequestParam(value = "from", defaultValue = "0") @PositiveOrZero Integer from,
                                             @RequestParam(value = "size", defaultValue = "10") @Positive Integer size) {
        log.info("Received a request for a list of events from the user: userId={}, from={}, size={}", userId, from, size);
        List<EventShortDto> eventShortDtoList = eventService.getEventsShortByOwner(userId, from, size);
        log.info("Returning {} item(s).", eventShortDtoList.size());
        return eventShortDtoList;
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventFullByOwner(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("Received a request for an event from the user: userId={}, eventId={}", userId, eventId);
        EventFullDto eventFullDto = eventService.getEventFullByOwner(userId, eventId);
        log.info("Returning eventFullDto={}", eventFullDto);
        return eventFullDto;
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventByOwner(@PathVariable Long userId, @PathVariable Long eventId, @RequestBody @Valid UpdateEventUserRequest eventUserRequest) {
        log.info("Received a request to update: userId={}, eventId={}, updateEventUserRequest={}", userId, eventId, eventUserRequest);
        EventFullDto eventFullDto = eventService.updateEventByOwner(userId, eventId, eventUserRequest);
        log.info("Returning the updated event: eventFullDto={}", eventFullDto);
        return eventFullDto;
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getRequestsByOwnerEvent(@PathVariable Long userId,
                                                                 @PathVariable Long eventId) {
        log.info("Received a request for a list of requests: userId={}, eventId={}", userId, eventId);
        List<ParticipationRequestDto> requestDtoList = requestService.getRequestsByOwnerEvent(userId, eventId);
        log.info("Returning {} item(s).", requestDtoList.size());
        return requestDtoList;
    }

    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResult updateStatusRequests(@PathVariable Long userId,
                                                               @PathVariable Long eventId,
                                                               @RequestBody EventRequestStatusUpdateRequest request) {
        log.info("Received a request to change the status of an event: userId={}, eventId={}, request={}", userId, eventId, request);
        EventRequestStatusUpdateResult updateResult = requestService.updateStatusRequests(userId, eventId, request);
        log.info("Returning the updated request: {}", updateResult);
        return updateResult;
    }
}
