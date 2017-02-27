package by.bsuir.aipos.service;

import by.bsuir.aipos.model.Student;

import java.util.List;

public interface StudentService{
    /**
     * Save student
     * @param student student to save
     * @return saved student
     */
    Student save(Student student);

    /**
     * Get student by id
     *
     * @param id id of student
     * @return found student
     */
    Student get(long id);

    /**
     * Delete student by id
     *
     * @param id id of student to delete
     */
    void delete(long id);

    /**
     * Get all student
     *
     * @return list of students
     */
    List<Student> getAll();

}