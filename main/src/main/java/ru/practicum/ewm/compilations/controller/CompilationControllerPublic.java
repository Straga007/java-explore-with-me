package ru.practicum.ewm.compilations.controller;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.dto.CompilationDto;
import ru.practicum.ewm.compilations.service.CompilationService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompilationControllerPublic {

    CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> getCompilations(@RequestParam(required = false) Boolean pinned,
                                                @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Received a request for the list of compilations: pinned={}, from={}, size={}", pinned, from, size);
        List<CompilationDto> compilationDtoList = compilationService.getCompilations(pinned, from, size);
        log.info("Returning {} item(s)", compilationDtoList.size());
        return compilationDtoList;
    }

    @GetMapping("/{compilationId}")
    public CompilationDto getCompilationById(@PathVariable Long compilationId) {
        log.info("Received a request for a specific compilation: compilationId={}", compilationId);
        CompilationDto compilationDto = compilationService.getCompilationById(compilationId);
        log.info("Returning compilationDto={}", compilationDto);
        return compilationDto;
    }
}
