package ru.practicum.ewm.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.locations.dto.LocationDto;
import ru.practicum.ewm.users.dto.UserShortDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventRatedDto {
    Long id;

    @Size(min = 1, max = 2000)
    String annotation;

    @NotNull
    CategoryDto category;

    @Size(min = 1, max = 7000)
    String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    @NotNull
    UserShortDto initiator;

    @NotNull
    LocationDto location;

    @Size(min = 1, max = 120)
    String title;

    Integer likes;

    Integer dislikes;

    BigDecimal rate;

    Long views;
}
