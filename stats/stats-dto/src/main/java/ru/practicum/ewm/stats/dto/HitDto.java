package ru.practicum.ewm.stats.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HitDto {
    Long id;

    @NotBlank(message = "App can not be Blank.")
    @Size(min = 1, max = 255, message = "App length must be between 1 and 255 characters.")
    String app;

    @NotBlank(message = "Uri can not be Blank.")
    String uri;

    @NotBlank(message = "Ip can not be Blank.")
    String ip;

    @NotNull(message = "Time can not be Null.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime timestamp;
}
