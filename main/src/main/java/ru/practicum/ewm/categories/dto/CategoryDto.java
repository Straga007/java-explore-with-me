package ru.practicum.ewm.categories.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDto {
    Long id;

    @Size(min = 1, max = 50)
    @NotBlank(message = "Name can't be Blank")
    String name;
}