package com.employee.dto;

import jakarta.validation.constraints.NotBlank;

public record EmployeeRqDto(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "email is required")
        String email,
        @NotBlank(message = "job title is required")
        String jobTitle,
        @NotBlank(message = "phone is required")
        String phone,
        @NotBlank(message = "image url is required")
        String imageUrl
) {
}
