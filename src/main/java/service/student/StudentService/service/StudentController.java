package service.student.StudentService.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.student.StudentService.model.Student;
import service.student.StudentService.persistence.StudentRepository;



@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    // Flutter: getStudents()
    @GetMapping
    public List<Student> getStudents() {
        return repository.findAll();
    }

    // Flutter: addStudent(Map student)
    @PostMapping
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {
        repository.save(student);
        return ResponseEntity.ok().build();
    }

    // Flutter: updateStudent(int id, Map student)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(
            @PathVariable Integer id,
            @RequestBody Student student
    ) {
        return repository.update(id, student)
                .map(s -> ResponseEntity.ok().<Void>build())
                .orElse(ResponseEntity.notFound().build());
    }

    // Flutter: deleteStudent(int id)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        boolean removed = repository.deleteById(id);
        return removed
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
