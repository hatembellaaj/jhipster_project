package com.csf.gaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.csf.gaf.domain.entity.BL;

@Repository
public interface BLRepository extends JpaRepository<BL, String>{


}