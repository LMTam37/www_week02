package vn.edu.iuh.fit.week02.services;

import vn.edu.iuh.fit.week02.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();

    Optional<Employee> findById(Long empId);

    void save(Employee employee);

    void update(Employee employee);

    void delete(Long empId);
}
