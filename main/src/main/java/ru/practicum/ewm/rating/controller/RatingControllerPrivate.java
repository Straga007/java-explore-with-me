package ru.practicum.ewm.rating.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.rating.dto.RatingDto;
import ru.practicum.ewm.rating.service.RatingService;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/rating/{eventId}")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingControllerPrivate {

    RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingDto> manageRating(@PathVariable Long userId,
                                                  @PathVariable Long eventId,
                                                  @RequestParam(name = "likes", required = false) Boolean likes) {
        if (likes != null) {
            log.info("Received a request to add a like from {} to {}.", userId, eventId);
            RatingDto addRating = ratingService.addRating(userId, eventId, likes);
            log.info("Returning the created rating: {}.", addRating);
            return ResponseEntity.status(HttpStatus.CREATED).body(addRating);
        } else {
            log.info("Received a request to remove a like from Event: {} by User: {}.", eventId, userId);
            ratingService.addRating(userId, eventId, null);
            log.info("Like from Event: {} by User: {} removed.", eventId, userId);
            return ResponseEntity.noContent().build();
        }
    }
}
