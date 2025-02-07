package com.aisentiment.openai.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.aisentiment.openai.entity.Employee;
import com.aisentiment.openai.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id)
    {
           return employeeService.getEmployeeId(id);
    }

    @GetMapping
    public List<Employee> getAllEmplooyee()
    {
        return employeeService.getAllEmployee();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id)
    {
         employeeService.deleteEmployee(id);
         return "employee delete success fully";
    }

    @PutMapping("/{id}/update")
    public Employee updateEmplyee(@PathVariable Long id, @RequestBody Employee employee)
    {
            return  employeeService.updateEmployee(id, employee);
    }



}

