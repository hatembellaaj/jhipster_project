package com.csf.gaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.csf.gaf.domain.entity.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, String>{


}