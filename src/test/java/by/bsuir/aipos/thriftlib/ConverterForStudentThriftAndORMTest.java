package by.bsuir.aipos.thriftlib;

import static org.mockito.Mockito.*;

import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;
import by.bsuir.aipos.service.StudentGroupService;
import by.bsuir.aipos.thriftserver.StudentServer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest(StudentServer.class)
public class ConverterForStudentThriftAndORMTest {

    private static Date date;
    private static String dateString;
    private static Student student;
    private static StudentThrift studentThrift;
    private static StudentGroup studentGroup;
    private static StudentGroupThrift studentGroupThrift;

    @BeforeClass
    public static void init() throws ParseException {
        dateString = "1996-12-05";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse(dateString);
        studentGroup = new StudentGroup("421702");
        studentGroup.setId(1);
        student = new Student(
                "Andrey",
                "Bobkov",
                "Valerievich",
                date,
                "Bobrujsk",
                studentGroup);
        student.setId(1);
        studentGroupThrift = new StudentGroupThrift(1,"421702");
        studentThrift = new StudentThrift(
                1,
                "Andrey",
                "Bobkov",
                "Valerievich",
                dateString,
                "Bobrujsk",
                studentGroupThrift);
    }

    @Test
    public void convert() throws Exception {
        StudentGroupThrift g = ConverterForStudentThriftAndORM.convert(studentGroup);
        assert (studentGroupThrift.equals(g));
    }

    @Test
    public void convert1() throws Exception {
        StudentGroup g = ConverterForStudentThriftAndORM.convert(studentGroupThrift);
        assert (studentGroup.getId() == g.getId() && studentGroup.getName().equals(g.getName()));
    }

    @Test
    public void convert2() throws Exception {
        StudentThrift s = ConverterForStudentThriftAndORM.convert(student);
        assert (studentThrift.equals(s));
    }

    @Test
    public void convert3() throws Exception {
        PowerMockito.mockStatic(StudentServer.class);
        StudentGroupService studentGroupService = mock(StudentGroupService.class);
        Mockito.when(StudentServer.getStudentGroupService()).thenReturn(studentGroupService);
        when(studentGroupService.get(anyString())).thenReturn(studentGroup);
        Student student = ConverterForStudentThriftAndORM.convert(studentThrift);
        assert (studentThrift.getLastName().equals(student.getLastName()));
    }

}