package vn.edu.iuh.fit.week02.services.impl;

import vn.edu.iuh.fit.week02.models.Employee;
import vn.edu.iuh.fit.week02.repositories.EmployeeRepository;
import vn.edu.iuh.fit.week02.services.EmployeeService;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepository();
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long empId) {
        return employeeRepository.findById(empId);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.updateEmployee(employee);
    }

    @Override
    public void delete(Long empId) {
        employeeRepository.deleteEmployee(empId);
    }
}
