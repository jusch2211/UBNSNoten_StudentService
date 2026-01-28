package service.student.StudentService.persistence;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import service.student.StudentService.model.*;


@Repository
public class StudentRepository {

    private final Map<Long, Student> store = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();


        // Statische Demo-Daten
    public StudentRepository() {
        save(new Student(123L, "Max", "Mustermann", new ArrayList<>(Arrays.asList("Informatik", "Physik")), "BGT111"));
        save(new Student(124L, "Erika", "Musterfrau", new ArrayList<>(Arrays.asList("Mathematik")), "BGT111"));
    }

    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }

    public Student save(Student student) {
        if(student.getId() != -4242 && store.containsKey(student.getId())) {  //-4242 is used to indicate new student without ID
            throw new IllegalArgumentException("Student with ID " + student.getId() + " already exists.");
        }
        long id = idGenerator.incrementAndGet();
        student.setId(id);
        store.put(id, student);
        return student;
    }

    public Optional<Student> update(Long id, Student student) {
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
