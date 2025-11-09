package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/persons")
@CrossOrigin
public class PersonController {

    private final Map<Long, Person> store = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<List<Person>> listAll() {
        return ResponseEntity.ok(new ArrayList<>(store.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id) {
        return Optional.ofNullable(store.get(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody @jakarta.validation.Valid Person person) {
        long id = idCounter.getAndIncrement();
        person.setId(id);
        store.put(id, person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id,
                                         @RequestBody @jakarta.validation.Valid Person person) {
        if (!store.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        person.setId(id);
        store.put(id, person);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return (store.remove(id) != null)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // DTO usando Lombok e Jakarta Validation
    @lombok.Data
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    @lombok.Builder
    public static class Person {
        private Long id;

        @jakarta.validation.constraints.NotBlank(message = "name must not be blank")
        private String name;

        @jakarta.validation.constraints.Min(value = 0, message = "age must be >= 0")
        private Integer age;
    }
}