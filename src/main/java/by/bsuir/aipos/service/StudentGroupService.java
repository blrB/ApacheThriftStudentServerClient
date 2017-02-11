package by.bsuir.aipos.service;

import by.bsuir.aipos.model.StudentGroup;

import java.util.List;

public interface StudentGroupService {

    public StudentGroup save(StudentGroup studentGroup);

    public StudentGroup get(long id);

    public StudentGroup get(String name);

    public void delete(long id);

    public List<StudentGroup> getAll();
}
