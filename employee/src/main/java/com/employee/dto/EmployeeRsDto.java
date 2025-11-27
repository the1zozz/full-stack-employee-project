package com.employee.dto;

public record EmployeeRsDto(
        Long id,
        String name,
        String email,
        String jobTitle,
        String phone,
        String imageUrl,
        String employeeCode
) {
}
