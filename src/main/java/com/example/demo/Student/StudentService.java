package com.example.demo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final Repo repo;
@Autowired
    public StudentService(Repo repo) {
        this.repo = repo;
    }

    public List<Student> getStudent(){
    return repo.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> xd = repo.findStudentByEmail(student.getEmail());
        if (xd.isPresent()){
            throw new IllegalStateException("email already have");
        }
        repo.save(student);
    }

    public void deleteStudent(Long studentId) {
    boolean exists= repo.existsById(studentId);
    if(!exists){
        throw new IllegalStateException("Student id not found");
    }
    repo.deleteById(studentId);


    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

    Student student= repo.findById(studentId).orElseThrow(()-> new IllegalStateException(
            "Student not found"
    ));
    if (name!= null && name.length()>0&& !Objects.equals(student.getName(), name)){
        student.setName(name);
    }
    if (email !=null&& email.length()>0&& !Objects.equals(student.getEmail(), email)){
        Optional<Student>studentOptional= repo.findStudentByEmail(email);
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        student.setEmail(email);
    }

    }
}
