package edu.com.Pessoa.repository;

import org.springframework.data.repository.CrudRepository;

import edu.com.Pessoa.domain.Pessoa;



public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
    
}
