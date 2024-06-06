package com.ra.module4_springboot_session2.controller;

import com.ra.module4_springboot_session2.model.Employee;
import com.ra.module4_springboot_session2.service.IEmployeeSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emp")
@RequiredArgsConstructor
public class EmployeeController
{
    private final IEmployeeSerivce employeeSerivce;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll()
    {
        return new ResponseEntity<>(employeeSerivce.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") Integer id)
    {
        return new ResponseEntity<>(employeeSerivce.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee employee)
    {
        return new ResponseEntity<>(employeeSerivce.save(employee), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Employee> update(@RequestBody Employee employee)
    {
        return new ResponseEntity<>(employeeSerivce.update(employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        employeeSerivce.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employees/{name}")
    public ResponseEntity<List<Employee>> findByName(@PathVariable("name") String name)
    {
        return new ResponseEntity<>(employeeSerivce.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/salaryRange")
    public ResponseEntity<List<Employee>> findInSalaryRange(
            @RequestParam(name = "empName", defaultValue = "") String empName,
            @RequestParam(name = "start", defaultValue = "0") Double start,
            @RequestParam(name = "end", defaultValue = "0") Double end,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "itemPerPage", defaultValue = "3") Integer itemPerPage,
            @RequestParam(name = "orderBy", defaultValue = "salary") String orderBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction)
    {
        return new ResponseEntity<>(employeeSerivce.
                findByEmpNameBetweenStartAndEndOrderBySalaryPaging
                        (empName, start, end, page - 1, itemPerPage, orderBy, direction).getContent(),
                HttpStatus.OK);
    }
}
