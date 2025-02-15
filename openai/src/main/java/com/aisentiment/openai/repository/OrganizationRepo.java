package com.aisentiment.openai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aisentiment.openai.entity.OrganizationalChange;

public interface OrganizationRepo  extends JpaRepository<OrganizationalChange,Long> {

}
