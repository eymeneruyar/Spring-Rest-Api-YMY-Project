package com.ymy.company.repository;

import com.ymy.company.entity.Towns;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TownsRepository extends JpaRepository<Towns,Long> {

    List<Towns> findByTownCityKeyOrderByIdAsc(Long townCityKey);

}
