package ru.practicum.ewm.compilations.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.dto.CompilationDto;
import ru.practicum.ewm.compilations.dto.NewCompilationDto;
import ru.practicum.ewm.compilations.model.UpdateCompilationRequest;
import ru.practicum.ewm.compilations.service.CompilationService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompilationControllerAdmin {
    CompilationService compilationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompilationDto addCompilation(@RequestBody @Valid NewCompilationDto newCompilationDto) {
        log.info("Received a request to add a compilation: newCompilationDto={}", newCompilationDto);
        CompilationDto compilationDto = compilationService.addCompilation(newCompilationDto);
        log.info("Returning compilationDto={}", compilationDto);
        return compilationDto;
    }

    @PatchMapping("/{compilationId}")
    public CompilationDto updateCompilation(@PathVariable Long compilationId,
                                            @RequestBody @Valid UpdateCompilationRequest compilationRequest) {
        log.info("Received a request to update a compilation: updateCompilationRequest={}", compilationRequest);
        CompilationDto compilationDto = compilationService.updateCompilation(compilationId, compilationRequest);
        log.info("Returning the updated compilation: compilationDto={}", compilationDto);
        return compilationDto;
    }

    @DeleteMapping("/{compilationId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable long compilationId) {
        log.info("Received a request to delete a compilation: compilationId={}", compilationId);
        compilationService.deleteCompilation(compilationId);
        log.info("Compilation compilationId={} deleted.", compilationId);
    }
}
