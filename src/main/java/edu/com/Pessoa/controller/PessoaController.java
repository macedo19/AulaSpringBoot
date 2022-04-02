package edu.com.Pessoa.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.com.Pessoa.domain.Pessoa;
import edu.com.Pessoa.repository.PessoaRepository;

@RestController
public class PessoaController {
    
    private final PessoaRepository repository;

    PessoaController(PessoaRepository repository){
        this.repository = repository;
    }

    @GetMapping("/pessoas")
    Iterable<Pessoa> listar(){
        return repository.findAll();
    }

    @PostMapping("/pessoas")
    Pessoa incluir(@RequestBody Pessoa novaPessoa){
        return repository.save(novaPessoa);
    }

    @GetMapping("/pessoas/{id}")
    Pessoa buscarPorId(@PathVariable Long id){
        return (Pessoa) repository.findById(id).get();
    }

    @PutMapping("/pessoas/{id}")
    Pessoa atualizar(@RequestBody Pessoa pessoaAlterada, @PathVariable Long id){
        return repository.findById(id).map(pessoa -> {
            pessoa.setNome(pessoaAlterada.getNome());
            return repository.save(pessoaAlterada);
        }).orElseGet(() -> {
            pessoaAlterada.setId(id);
            return repository.save(pessoaAlterada);
        });
    }

    @DeleteMapping("/pessoas/{id}")
    void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }

}
