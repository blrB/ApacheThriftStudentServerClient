package by.bsuir.aipos.service;

import by.bsuir.aipos.model.Student;

import java.util.List;

/**
 * Created by andrey on 10/02/17.
 */
public interface StudentService{

    public Student save(Student student);

    public Student get(long id);

    public void delete(long id);

    public List<Student> getAll();

}