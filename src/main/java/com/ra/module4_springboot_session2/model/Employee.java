package com.ra.module4_springboot_session2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;
    @Column(length = 100)
    private String empName;
    private Boolean gender;
    private Date birthDay;
    private String address;
    @Column(length = 50)
    private String company;
    @Column(length = 50)
    private String department;
    @Min(value = 0)
    private Double salary;
}
