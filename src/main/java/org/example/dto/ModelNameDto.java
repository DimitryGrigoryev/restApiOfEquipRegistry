package org.example.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelNameDto{
    @NotEmpty(message = "{validation.registry.model.not-empty}")
    @Size(min = 3, max = 255)
    String name;
}
