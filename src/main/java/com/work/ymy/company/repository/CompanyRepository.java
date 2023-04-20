package com.work.ymy.company.repository;

import com.work.ymy.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    List<Company> findByStatusEqualsAndUserIdEqualsOrderByIdAsc(boolean status, int userId);

}
