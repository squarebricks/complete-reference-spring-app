package com.orgofarmsgroup.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DummyDTO {
    @Pattern(regexp = "[A-Za-z]{1,50}", message = "invalid name")
    private String name;
    private String email;
}
