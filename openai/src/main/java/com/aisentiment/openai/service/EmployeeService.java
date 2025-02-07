package com.aisentiment.openai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aisentiment.openai.entity.Employee;
import com.aisentiment.openai.exception.NotFoundException;
import com.aisentiment.openai.repository.EmployeeRepo;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee createEmployee(Employee employee) {
        Employee e = employeeRepo.save(employee);
        return e;
    }

    public Employee getEmployeeId(Long id) {
        return employeeRepo.findById(id).orElseThrow(() -> new NotFoundException("employee not found!!" + id));

    }

    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    public String deleteEmployee(Long id) {
        employeeRepo.deleteById(id);

        return "emlpyee Deleted====" + id;

    }

    // api for update

    public Employee updateEmployee(Long id, Employee employee) {
        Employee e = employeeRepo.findById(id).orElseThrow(() -> new NotFoundException("not found" + id));

        e.setName(employee.getName());
        e.setDepartment(employee.getDepartment());
        e.setEmail(employee.getEmail());

        employeeRepo.save(e);

        return e;
    }

}
