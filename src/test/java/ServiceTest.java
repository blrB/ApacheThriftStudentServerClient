import by.bsuir.aipos.model.StudentGroup;
import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.service.StudentGroupService;
import by.bsuir.aipos.service.StudentGroupServiceImpl;
import by.bsuir.aipos.service.StudentService;
import by.bsuir.aipos.service.StudentServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceTest {

    private StudentGroupService studentGroupService = new StudentGroupServiceImpl();
    private StudentService studentService = new StudentServiceImpl();

    @Test
    public void testCreateGroup(){
        StudentGroup studentGroup = new StudentGroup("421700");
        studentGroupService.save(studentGroup);
        assert(studentGroupService.get("421700") != null);
    }

    @Test
    public void testUpdateGroup(){
        StudentGroup studentGroup = new StudentGroup("421700");
        studentGroupService.save(studentGroup);
        studentGroup = studentGroupService.get("421700");
        studentGroup.setName("521701");
        studentGroupService.save(studentGroup);
        assert(studentGroupService.get("521701") != null);
    }

    @Test
    public void testCreateStudent() throws ParseException {
        StudentGroup studentGroup = new StudentGroup("421702");
        studentGroupService.save(studentGroup);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1996-12-05");
        Student student = new Student(
                "Andrey",
                "Bobkov",
                birthDate,
                "Bobrujsk",
                studentGroupService.get("421702"));
        studentService.save(student);
        assert(studentService.getAll().size() == 1);
    }
}
