package com.aisentiment.openai.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.aisentiment.openai.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Long>  {

}

