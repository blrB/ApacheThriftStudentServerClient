package by.bsuir.aipos.service;

import by.bsuir.aipos.model.StudentGroup;

import java.util.List;

public interface StudentGroupService {
    /**
     * Save student group
     * @param studentGroup student group to save
     * @return saved student group
     */
    StudentGroup save(StudentGroup studentGroup);

    /**
     * Get student group by group id
     * @return student group
     */
    StudentGroup get(long id);

    /**
     * Get student group by name
     * @param name name of group
     * @return found group
     */
    StudentGroup get(String name);

    /**
     * Delete student group with given id
     * @param id id of group to delete
     */
    void delete(long id);

    /**
     * Get list of all students groups
     * @return list of all student groups
     */
    List<StudentGroup> getAll();
}
