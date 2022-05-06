package com.nfdeveloper.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nfdeveloper.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
