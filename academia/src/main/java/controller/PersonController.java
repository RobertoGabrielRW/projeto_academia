package controller;

import abstracts.Person;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final List<Person> people = new ArrayList<>();

    // GET - lista todas as pessoas
    @GetMapping
    public ResponseEntity<List<Person>> listAll() {
        return ResponseEntity.ok(people);
    }

    // GET - busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable long id) {
        return people.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // POST - cria nova pessoa
    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        people.add(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    // PUT - atualiza pessoa existente
    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable long id, @RequestBody Person updatedPerson) {
        for (Person person : people) {
            if (person.getId() == id) {
                person.setName(updatedPerson.getName());
                person.setAge(updatedPerson.getAge());
                return ResponseEntity.ok(person);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // DELETE - remove pessoa por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean removed = people.removeIf(p -> p.getId() == id);
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
}