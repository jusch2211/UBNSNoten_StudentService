package service.student.StudentService.persistence;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import service.student.StudentService.model.*;


@Repository
public class StudentRepository {

    private final Map<Integer, Student> store = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger();

        // Statische Demo-Daten
    public StudentRepository() {
        save(new Student(123, "Max", "Mustermann", new ArrayList<>(Arrays.asList("Informatik", "Physik")), "BGT111"));
        save(new Student(124, "Erika", "Musterfrau", new ArrayList<>(Arrays.asList("Mathematik")), "BGT111"));
    }

    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }

    public Student save(Student student) {
        int id = idGenerator.incrementAndGet();
        student.setId(id);
        store.put(id, student);
        return student;
    }

    public Optional<Student> update(Integer id, Student student) {
        if (!store.containsKey(id)) {
            return Optional.empty();
        }
        student.setId(id);
        store.put(id, student);
        return Optional.of(student);
    }

    public boolean deleteById(Integer id) {
        return store.remove(id) != null;
    }
}
