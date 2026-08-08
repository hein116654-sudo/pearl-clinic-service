package com.pearl.warehouse.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoomInput(
        @NotBlank(message = "Room number is required")
        @Size(max = 50, message = "Room number must not exceed 50 characters")
        String roomNo,

        @NotBlank(message = "Room type is required")
        String roomType,

        Boolean isAvailable
) {
}
