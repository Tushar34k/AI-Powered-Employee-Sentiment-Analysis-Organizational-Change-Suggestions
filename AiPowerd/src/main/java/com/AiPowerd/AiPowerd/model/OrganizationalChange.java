package com.AiPowerd.AiPowerd.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationalChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String changeTitle;
    private String changeDescription;

    @ManyToMany
    @JoinTable(name = "change_employee", joinColumns = @JoinColumn(name = "change_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> impactedEmployees;

}
