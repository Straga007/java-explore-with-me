package ru.practicum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsDto {

    @NotBlank(message = "App can not be Blank.")
    String app;

    @NotBlank(message = "Uri can not be Blank.")
    String uri;

    Long hits;
}