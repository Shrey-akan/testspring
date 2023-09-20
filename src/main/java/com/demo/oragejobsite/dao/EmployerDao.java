package com.demo.oragejobsite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.Employer;

@Repository
public interface EmployerDao extends JpaRepository<Employer, Integer>{

}
