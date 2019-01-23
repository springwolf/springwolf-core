package com.stavshamir.swagger4kafka.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationMessage {

    private boolean isValid;
    private String message;

}
