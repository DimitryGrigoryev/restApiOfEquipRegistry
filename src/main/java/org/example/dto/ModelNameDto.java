package org.example.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelNameDto{
    @NotEmpty(message = "{validation.registry.model.not-empty}")
    @Size(min = 3, max = 255)
    private String name;
}
