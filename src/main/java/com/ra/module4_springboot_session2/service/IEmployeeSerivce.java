package com.ra.module4_springboot_session2.service;

import com.ra.module4_springboot_session2.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmployeeSerivce
{
    List<Employee> findAll();

    Employee findById(Integer id);

    Employee save(Employee employee);

    Employee update(Employee employee);

    void delete(Integer id);

    List<Employee> findByName(String empName);

    Page<Employee> findByEmpNameBetweenStartAndEndOrderBySalaryPaging(String empName,
                                                                      Double start,
                                                                      Double end,
                                                                      Integer page,
                                                                      Integer itemPerPage,
                                                                      String orderBy,
                                                                      String direction);
}
