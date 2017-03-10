package by.bsuir.aipos.thriftlib;

import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;
import by.bsuir.aipos.service.StudentGroupService;
import by.bsuir.aipos.service.StudentGroupServiceImpl;
import by.bsuir.aipos.service.StudentService;
import by.bsuir.aipos.service.StudentServiceImpl;
import by.bsuir.aipos.thriftserver.StudentServer;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest({StudentServer.class, ConverterForStudentThriftAndORM.class})
public class StudentThriftServiceImplTest {

    private StudentService studentService;
    private StudentGroupService studentGroupService;
    private StudentThriftServiceImpl studentThriftService;
    private Student student;
    private StudentThrift studentThrift;
    private StudentGroup studentGroup;
    private StudentGroupThrift studentGroupThrift;

    @Before
    public void init(){
        studentThriftService = new StudentThriftServiceImpl();
        PowerMockito.mockStatic(StudentServer.class);
        studentService = mock(StudentServiceImpl.class);
        Mockito.when(StudentServer.getStudentService()).thenReturn(studentService);
        studentGroupService = mock(StudentGroupServiceImpl.class);
        Mockito.when(StudentServer.getStudentGroupService()).thenReturn(studentGroupService);
        studentThrift = new StudentThrift();
        student = new Student();
        studentGroup = new StudentGroup();
        studentGroupThrift = new StudentGroupThrift();
        PowerMockito.mockStatic(ConverterForStudentThriftAndORM.class);
        Mockito.when(ConverterForStudentThriftAndORM.convert(student)).thenReturn(studentThrift);
        Mockito.when(ConverterForStudentThriftAndORM.convert(studentThrift)).thenReturn(student);
        Mockito.when(ConverterForStudentThriftAndORM.convert(studentGroup)).thenReturn(studentGroupThrift);
        Mockito.when(ConverterForStudentThriftAndORM.convert(studentGroupThrift)).thenReturn(studentGroup);
        Mockito.when(StudentServer.getLogger()).thenReturn(mock(Logger.class));
    }

    @Test
    public void saveStudent() throws Exception {
        studentThriftService.saveStudent(studentThrift);
        verify(studentService).save(student);
    }
    @Test
    public void getStudent() throws Exception {
        studentThriftService.getStudent(1);
        verify(studentService).get(1);
    }

    @Test
    public void deleteStudent() throws Exception {
        studentThriftService.deleteStudent(1);
        verify(studentService).delete(1);
    }

    @Test
    public void getAllStudent() throws Exception {
        studentThriftService.getAllStudent();
        verify(studentService).getAll();
    }

    @Test
    public void saveStudentGroup() throws Exception {
        studentThriftService.saveStudentGroup(studentGroupThrift);
        verify(studentGroupService).save(studentGroup);
    }

    @Test
    public void getStudentGroup() throws Exception {
        studentThriftService.getStudentGroup(1);
        verify(studentGroupService).get(1);
    }

    @Test
    public void getStudentGroupByName() throws Exception {
        studentThriftService.getStudentGroupByName("421702");
        verify(studentGroupService).get("421702");
    }

    @Test
    public void deleteStudentGroup() throws Exception {
        studentThriftService.deleteStudentGroup(1);
        verify(studentGroupService).delete(1);
    }

    @Test
    public void getAllStudentGroup() throws Exception {
        studentThriftService.getAllStudentGroup();
        verify(studentGroupService).getAll();
    }

}