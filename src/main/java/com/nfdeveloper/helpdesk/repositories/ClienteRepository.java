package com.nfdeveloper.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nfdeveloper.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
