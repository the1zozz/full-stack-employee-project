package com.employee.controller;

import com.employee.dto.EmployeeRqDto;
import com.employee.dto.EmployeeRsDto;
import com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("find/all")
    public ResponseEntity<List<EmployeeRsDto>> getAllEmployees(){
        log.info("Fetching all employees");
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @GetMapping("find/{id}")
    public ResponseEntity<EmployeeRsDto> getEmployeeById(@PathVariable Long id){
        log.info("Fetching employee by id: {}",id);
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping("add")
    public ResponseEntity<EmployeeRsDto> addEmployee(@RequestBody @Valid EmployeeRqDto employeeRqDto){
        log.info("Adding employee: {}",employeeRqDto);
        return ResponseEntity.ok(employeeService.addEmployee(employeeRqDto));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<EmployeeRsDto> updateEmployee(@PathVariable Long id,@RequestBody @Valid EmployeeRqDto employeeRqDto){
        log.info("Updating employee: {} by id : {} ",employeeRqDto,id);
        return ResponseEntity.ok(employeeService.updateEmployee(id,employeeRqDto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        log.info("Deleting employee by id: {}",id);
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
