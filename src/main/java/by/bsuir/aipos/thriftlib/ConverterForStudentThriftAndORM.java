package by.bsuir.aipos.thriftlib;

import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;
import by.bsuir.aipos.thriftserver.StudentServer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterForStudentThriftAndORM {
    /**
     * Convert student model to student thrift model
     * @param student student model to convert
     * @return result of conversion - student thrift model
     */
    public static StudentThrift convert(Student student) {
        StudentThrift studentThrift = new StudentThrift();
        if(student.getId() != 0) {
            studentThrift.setId(student.getId());
        }
        studentThrift.setFirstName(student.getFirstName());
        studentThrift.setLastName(student.getLastName());
        if(student.getMiddleName() != null) {
            studentThrift.setMiddleName(student.getMiddleName());
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String birthDate = format.format(student.getDateOfBirth());
        studentThrift.setDateOfBirth(birthDate);
        studentThrift.setHomeAddress(student.getHomeAddress());
        StudentGroupThrift studentGroupThrift = new StudentGroupThrift();
        studentGroupThrift.setId(student.getStudentGroup().getId());
        studentGroupThrift.setName(student.getStudentGroup().getName());
        studentThrift.setStudentGroup(studentGroupThrift);
        return studentThrift;
    }

    /**
     * Convert student group to thrift student group
     *
     * @param studentGroup student group to convert
     * @return result of conversion - thrift student group
     */
    public static StudentGroupThrift convert(StudentGroup studentGroup){
        StudentGroupThrift studentGroupThrift = new StudentGroupThrift();
        if(studentGroup.getId() != 0) {
            studentGroupThrift.setId(studentGroup.getId());
        }
        studentGroupThrift.setName(studentGroup.getName());
        return studentGroupThrift;
    }

    /**
     * Convert thrift student to student
     * @param studentThrift thrift student to convert
     * @return result of conversion - student
     */
    public static Student convert(StudentThrift studentThrift) {
        Student student = new Student();
        if(studentThrift.getId() != 0) {
            student.setId(studentThrift.getId());
        }
        student.setFirstName(studentThrift.getFirstName());
        student.setLastName(studentThrift.getLastName());
        if(studentThrift.getMiddleName() != null) {
            student.setMiddleName(studentThrift.getMiddleName());
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = format.parse(studentThrift.getDateOfBirth());
            student.setDateOfBirth(birthDate);
        } catch (ParseException e) {
            StudentServer.getLogger().error("ParseException");
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            StudentServer.getLogger().debug(stack.toString());
        }
        student.setHomeAddress(studentThrift.getHomeAddress());
        StudentGroup studentGroup = StudentServer
                .getStudentGroupService()
                .get(studentThrift.getStudentGroup().getName());
        student.setStudentGroup(studentGroup);
        return student;
    }

    /**
     * Convert thrift student group to student group
     * @param studentGroupThrift - thrift student group to convert
     * @return result of conversion student group
     */
    public static StudentGroup convert(StudentGroupThrift studentGroupThrift){
        StudentGroup studentGroup = new StudentGroup();
        if(studentGroupThrift.getId() != 0) {
            studentGroup.setId(studentGroupThrift.getId());
        }
        studentGroup.setName(studentGroupThrift.getName());
        return studentGroup;
    }

}
