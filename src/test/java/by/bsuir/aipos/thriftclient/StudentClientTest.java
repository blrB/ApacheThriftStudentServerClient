package by.bsuir.aipos.thriftclient;

import by.bsuir.aipos.thriftlib.StudentThriftService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StudentClientTest {

    private StudentThriftService.Client client;
    private StudentClient studentClient;

    @Before
    public void init(){
        MainWindow mainWindow = mock(MainWindow.class);
        studentClient = new StudentClient("host", 8080, mainWindow);
        client = mock(StudentThriftService.Client.class);
        Whitebox.setInternalState(studentClient, "client", client);
    }

    @Test
    public void saveStudent() throws Exception {
        studentClient.saveStudent(anyObject());
        verify(client).saveStudent(anyObject());
    }

    @Test
    public void getStudent() throws Exception {
        studentClient.getStudent(anyLong());
        verify(client).getStudent(anyLong());
    }

    @Test
    public void deleteStudent() throws Exception {
        studentClient.deleteStudent(anyLong());
        verify(client).deleteStudent(anyLong());
    }

    @Test
    public void getAllStudent() throws Exception {
        studentClient.getAllStudent();
        verify(client).getAllStudent();
    }

    @Test
    public void saveStudentGroup() throws Exception {
        studentClient.saveStudentGroup(anyObject());
        verify(client).saveStudentGroup(anyObject());
    }

    @Test
    public void getStudentGroup() throws Exception {
        studentClient.getStudentGroup(anyLong());
        verify(client).getStudentGroup(anyLong());
    }

    @Test
    public void getStudentGroupByName() throws Exception {
        studentClient.getStudentGroupByName(anyString());
        verify(client).getStudentGroupByName(anyString());
    }

    @Test
    public void deleteStudentGroup() throws Exception {
        studentClient.deleteStudentGroup(anyLong());
        verify(client).deleteStudentGroup(anyLong());
    }

    @Test
    public void getAllStudentGroup() throws Exception {
        studentClient.getAllStudentGroup();
        verify(client).getAllStudentGroup();
    }

}