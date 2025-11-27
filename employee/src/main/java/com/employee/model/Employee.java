package com.employee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.io.Serializable;
@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,updatable = false)
    private Long id ;
    @Column(name = "name")
    private String name ;
    @Column(name = "email",unique = true)
    private String email ;
    private String jobTitle ;
    private String phone ;
    private String imageUrl ;

    @Column(name = "employee_code",updatable = false,nullable = false)
    private String employeeCode ;
}
