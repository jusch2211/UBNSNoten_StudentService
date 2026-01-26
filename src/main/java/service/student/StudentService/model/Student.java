package service.student.StudentService.model;

import java.util.List;

public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private List<String> courses;
    private String className;

    public Student() {
    }

    public Student(Long id, String firstName, String lastName, List<String> courses, String className) {  
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
        this.className = className  ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
