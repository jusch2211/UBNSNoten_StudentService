package service.student.StudentService.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log =
            LoggerFactory.getLogger(StudentController.class);

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    // Flutter: getStudents()
    @GetMapping
    public List<Student> getStudents() {
        log.info("GET /students called");
        return repository.findAll();
    }

    // Flutter: addStudent(Map student)
    @PostMapping
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {
        log.info("POST addStudent() /students - firstName={}, lastName={}, id={}, className={}",
                student.getFirstName(), student.getLastName(), student.getId(), student.getClassName());
        repository.save(student);
        return ResponseEntity.ok().build();
    }

    // Flutter: updateStudent(int id, Map student)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student
    ) {
        log.info("PUT updateStudent() /students - firstName={}, lastName={}, id={}, className={}",
                student.getFirstName(), student.getLastName(), student.getId(), student.getClassName());
        return repository.update(id, student)
                .map(s -> ResponseEntity.ok().<Void>build())
                .orElse(ResponseEntity.notFound().build());
    }

    // Flutter: deleteStudent(int id)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        log.info("DELETE /students - id={}", id);
        boolean removed = repository.deleteById(id);
        return removed
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
