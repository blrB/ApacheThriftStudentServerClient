package by.bsuir.aipos.thriftlib;

import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;
import by.bsuir.aipos.thriftserver.StudentServer;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

public class StudentThriftServiceImpl implements StudentThriftService.Iface{

    @Override
    public StudentThrift saveStudent(StudentThrift studentThrift) throws TException {
        StudentServer.getLogger().info("Save student : " + studentThrift.getLastName());
        Student student = ConverterForStudentThriftAndORM.convert(studentThrift);
        StudentServer.getStudentService().save(student);
        studentThrift.setId(student.getId());
        return studentThrift;
    }

    @Override
    public StudentThrift getStudent(long id) throws TException {
        StudentServer.getLogger().info("Get student id : " + id);
        Student student = StudentServer.getStudentService().get(id);
        return ConverterForStudentThriftAndORM.convert(student);
    }

    @Override
    public void deleteStudent(long id) throws TException {
        StudentServer.getLogger().info("Delete student id : " + id);
        StudentServer.getStudentService().delete(id);
    }

    @Override
    public List<StudentThrift> getAllStudent() throws TException {
        StudentServer.getLogger().info("Get all student");
        List<Student> students = StudentServer.getStudentService().getAll();
        List<StudentThrift> studentsThrift  = new ArrayList<>();
        students.forEach(student -> {
            studentsThrift.add(ConverterForStudentThriftAndORM.convert(student));
        });
        return studentsThrift;
    }

    @Override
    public StudentGroupThrift saveStudentGroup(StudentGroupThrift studentGroupThrift) throws TException {
        StudentServer.getLogger().info("Save student group : " + studentGroupThrift.getName());
        StudentGroup studentGroup = ConverterForStudentThriftAndORM.convert(studentGroupThrift);
        StudentServer.getStudentGroupService().save(studentGroup);
        studentGroupThrift.setId(studentGroup.getId());
        return studentGroupThrift;
    }

    @Override
    public StudentGroupThrift getStudentGroup(long id) throws TException {
        StudentServer.getLogger().info("Get student group id : " + id);
        StudentGroup studentGroup = StudentServer.getStudentGroupService().get(id);
        return ConverterForStudentThriftAndORM.convert(studentGroup);
    }

    @Override
    public StudentGroupThrift getStudentGroupByName(String name) throws TException {
        StudentServer.getLogger().info("Get student group name : " + name);
        StudentGroup studentGroup = StudentServer.getStudentGroupService().get(name);
        return ConverterForStudentThriftAndORM.convert(studentGroup);
    }

    @Override
    public void deleteStudentGroup(long id) throws TException {
        StudentServer.getLogger().info("Delete student group id : " + id);
        StudentServer.getStudentGroupService().delete(id);
    }

    @Override
    public List<StudentGroupThrift> getAllStudentGroup() throws TException {
        StudentServer.getLogger().info("Get all student group");
        List<StudentGroup> studentsGroup = StudentServer.getStudentGroupService().getAll();
        List<StudentGroupThrift> studentsGroupThrift  = new ArrayList<>();
        studentsGroup.forEach(studentGroup -> {
            studentsGroupThrift.add(ConverterForStudentThriftAndORM.convert(studentGroup));
        });
        return studentsGroupThrift;
    }
}
