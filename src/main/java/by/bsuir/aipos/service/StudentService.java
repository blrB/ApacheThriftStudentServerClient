package by.bsuir.aipos.service;

import by.bsuir.aipos.model.Student;

import java.util.List;

public interface StudentService{

    public Student save(Student student);

    public Student get(long id);

    public void delete(long id);

    public List<Student> getAll();

}