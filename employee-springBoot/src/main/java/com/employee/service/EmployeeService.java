package com.employee.service;

import com.employee.dto.EmployeeRqDto;
import com.employee.dto.EmployeeRsDto;
import com.employee.exception.UserNotFoundException;
import com.employee.mapper.EmployeeMapper;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeRsDto addEmployee(EmployeeRqDto employeeRqDto){
        if(employeeRepository.existsByEmail(employeeRqDto.email())){
            throw new RuntimeException("Employee already exists");
        }
        Employee employee = employeeMapper.toEntity(employeeRqDto);
        employee.setEmployeeCode(UUID.randomUUID().toString());
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    public EmployeeRsDto getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Employee not found"));
        return employeeMapper.toDto(employee);
    }

    public List<EmployeeRsDto> findAllEmployees(){
        return employeeRepository.findAll().stream().map(employeeMapper::toDto).toList();
    }

    public EmployeeRsDto updateEmployee(Long id,EmployeeRqDto employeeRqDto){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeMapper.updateEntity(employee,employeeRqDto);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    public void deleteEmployee(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeRepository.delete(employee);
    }






}
