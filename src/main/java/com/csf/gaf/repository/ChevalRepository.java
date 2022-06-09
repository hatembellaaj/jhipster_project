package com.csf.gaf.repository;

import com.csf.gaf.domain.Cheval;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Cheval entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChevalRepository extends JpaRepository<Cheval, Long> {}
