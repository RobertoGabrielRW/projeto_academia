package com.seuprojeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entitys.PersonalTrainer;
import repository.PersonalTrainerRepository;

@RestController
@RequestMapping("/personal")
public class ControllerPersonal {

    @Autowired
    private PersonalTrainerRepository repository;

    // Criar novo registro
    @PostMapping
    public PersonalTrainer create(@RequestBody PersonalTrainer personal) {
        return repository.save(personal);
    }

    // Listar todos
    @GetMapping
    public Iterable<PersonalTrainer> listAll() {
        return repository.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public PersonalTrainer findById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // Atualizar
    @PutMapping("/{id}")
    public PersonalTrainer update(@PathVariable Long id, @RequestBody PersonalTrainer personal) {
        personal.setId(id);
        return repository.save(personal);
    }

    // Deletar
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
