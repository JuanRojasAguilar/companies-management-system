package com.backend.company.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.company.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
}
