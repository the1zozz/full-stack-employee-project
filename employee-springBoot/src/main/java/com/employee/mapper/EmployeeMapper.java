package com.employee.mapper;

import com.employee.dto.EmployeeRqDto;
import com.employee.dto.EmployeeRsDto;
import com.employee.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeRqDto employeeRqDto);
    EmployeeRsDto toDto(Employee employee);
    void updateEntity(@MappingTarget Employee employee,EmployeeRqDto employeeRqDto);
}
