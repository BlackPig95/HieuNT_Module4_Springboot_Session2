package com.ra.module4_springboot_session2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRequestDto
{
    private String empName;
    private Double start;
    private Double end;
    private Integer page;
    private Integer itemPerPage;
    private String orderBy;
    private String direction;
}
