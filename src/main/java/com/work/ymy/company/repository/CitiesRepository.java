package com.work.ymy.company.repository;

import com.work.ymy.company.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CitiesRepository extends JpaRepository<Cities,Long> {

    Optional<Cities> findByCityKey(Long cityKey);

}
