package ru.practicum.ewm.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsDto {

    @NotBlank(message = "App can not be Blank.")
    @Size(min = 1, max = 255, message = "App length must be between 1 and 255 characters.")
    String app;

    @NotBlank(message = "Uri can not be Blank.")
    String uri;

    Long hits;
}