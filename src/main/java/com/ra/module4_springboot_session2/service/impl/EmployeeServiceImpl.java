package com.ra.module4_springboot_session2.service.impl;

import com.ra.module4_springboot_session2.model.Employee;
import com.ra.module4_springboot_session2.repository.IEmployeeRepository;
import com.ra.module4_springboot_session2.service.IEmployeeSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeSerivce
{
    private final IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll()
    {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id)
    {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee save(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee)
    {
        if (findById(employee.getEmpId()) == null)
        {
            throw new NoSuchElementException("Emp not exist");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Integer id)
    {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByName(String empName)
    {
        return employeeRepository.findByEmpName(empName);
    }

    @Override
    public Page<Employee> findByEmpNameBetweenStartAndEndOrderBySalaryPaging(String empName,
                                                                             Double start,
                                                                             Double end,
                                                                             Integer page,
                                                                             Integer itemPerPage,
                                                                             String orderBy,
                                                                             String direction)
    {
        Pageable pageable = null;
        if (!orderBy.isEmpty())
        {
            Sort sort = null;
            switch (direction.toUpperCase())
            {
                case "ASC":
                    sort = Sort.by(orderBy).ascending();
                    break;
                case "DESC":
                    sort = Sort.by(orderBy).descending();
                    break;
                default://Tránh trường hợp có nhập orderBy nhưng lại không nhập direction sẽ khiến sort bị null
                    sort = Sort.by(orderBy).ascending();
                    break;
            }
            pageable = PageRequest.of(page, itemPerPage, sort);
        } else
        {
            pageable = PageRequest.of(page, itemPerPage);
        }
        if (start != null && end != null)
        {
            return employeeRepository.findByEmpNameBetweenStartAndEndOrderBySalaryPaging(empName, start, end, pageable);
        } else
        {
            return employeeRepository.findAll(pageable);
        }
    }
}
