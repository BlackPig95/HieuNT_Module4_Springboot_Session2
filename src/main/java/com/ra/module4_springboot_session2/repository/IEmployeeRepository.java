package com.ra.module4_springboot_session2.repository;

import com.ra.module4_springboot_session2.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer>, PagingAndSortingRepository<Employee, Integer>
{
    @Query("select e from Employee e where e.empName like concat('%',:empName,'%')")
    List<Employee> findByEmpName(String empName);

    @Query("select e from Employee e where e.empName like concat('%',:empName,'%') and e.salary between :start and :end")
    Page<Employee> findByEmpNameBetweenStartAndEndOrderBySalaryPaging(String empName,
                                                                      Double start,
                                                                      Double end,
                                                                      Pageable pageable);
}
