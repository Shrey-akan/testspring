package com.demo.oragejobsite.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.Employer;

@Repository
public interface EmployerDao extends JpaRepository<Employer, Integer>{

	 Optional<Employer> findByEmppass(String emppass);

}
