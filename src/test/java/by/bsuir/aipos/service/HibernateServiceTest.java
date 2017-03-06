package by.bsuir.aipos.service;

import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest(Persistence.class)

public class HibernateServiceTest {

    private StudentService studentService;
    private StudentGroupService studentGroupService;
    private EntityManager entityManager;
    private Student student;
    private StudentGroup studentGroup;

    @Before
    public void init() throws ParseException {
        String dateString = "1996-12-05";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateString);
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
        PowerMockito.mockStatic(Persistence.class);
        EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);
        when(Persistence.createEntityManagerFactory(anyString())).thenReturn(entityManagerFactory);
        entityManager = mock(EntityManager.class);
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        studentService = new StudentServiceImpl();
        studentGroupService = new StudentGroupServiceImpl();
    }

    @Test
    public void saveStudent() throws Exception {
        EntityTransaction transaction = mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(transaction);
        studentService.save(student);
        verify(transaction).begin();
        verify(entityManager).merge(student);
        verify(transaction).commit();
    }

    @Test
    public void getStudent() throws Exception {
        long id = 1;
        when(entityManager.find(Student.class, id)).thenReturn(student);
        Student st = studentService.get(1);
        verify(entityManager).find(Student.class, id);
        assert (st.getId() == student.getId());
    }

    @Test
    public void deleteStudent() throws Exception {
        EntityTransaction transaction = mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(transaction);
        long id = 1;
        when(studentService.get(id)).thenReturn(student);
        studentService.delete(id);
        verify(transaction).begin();
        verify(entityManager).remove(student);
        verify(transaction).commit();
    }

    @Test
    public void getAllStudent() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(student);
        TypedQuery<Student> queue = mock(TypedQuery.class);
        when(queue.getResultList()).thenReturn(students);
        when(entityManager.createNamedQuery("Student.getAll", Student.class)).thenReturn(queue);
        List<Student> studentsGetAll = studentService.getAll();
        verify(entityManager).createNamedQuery("Student.getAll", Student.class);
        assert students.equals(studentsGetAll);
    }


    @Test
    public void saveStudentGroup() throws Exception {
        EntityTransaction transaction = mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(transaction);
        studentGroupService.save(studentGroup);
        verify(transaction).begin();
        verify(entityManager).merge(studentGroup);
        verify(transaction).commit();
    }

    @Test
    public void getStudentGroup() throws Exception {
        long id = 1;
        when(entityManager.find(StudentGroup.class, id)).thenReturn(studentGroup);
        StudentGroup stG = studentGroupService.get(id);
        verify(entityManager).find(StudentGroup.class, id);
        assert (stG.getId() == studentGroup.getId());
    }

    @Test
    public void getStudentGroupByName() throws Exception {
        String name = "421702";
        List<StudentGroup> studentGroups = new ArrayList<>();
        studentGroups.add(studentGroup);
        TypedQuery<StudentGroup> queue = mock(TypedQuery.class);
        when(queue.getResultList()).thenReturn(studentGroups);
        when(entityManager.createNamedQuery("StudentGroup.getByName", StudentGroup.class)).thenReturn(queue);
        when(queue.setParameter("name", name)).thenReturn(queue);
        StudentGroup stG = studentGroupService.get(name);
        verify(entityManager).createNamedQuery("StudentGroup.getByName", StudentGroup.class);
        assert (stG.getName().equals(studentGroup.getName()));
    }

    @Test
    public void deleteStudentGroup() throws Exception {
        EntityTransaction transaction = mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(transaction);
        long id = 1;
        when(studentGroupService.get(id)).thenReturn(studentGroup);
        studentGroupService.delete(id);
        verify(transaction).begin();
        verify(entityManager).remove(studentGroup);
        verify(transaction).commit();
    }

    @Test
    public void getAllStudentGroup() throws Exception {
        List<StudentGroup> studentGroups = new ArrayList<>();
        studentGroups.add(studentGroup);
        TypedQuery<StudentGroup> queue = mock(TypedQuery.class);
        when(queue.getResultList()).thenReturn(studentGroups);
        when(entityManager.createNamedQuery("StudentGroup.getAll", StudentGroup.class)).thenReturn(queue);
        List<StudentGroup> studentsGroupGetAll = studentGroupService.getAll();
        verify(entityManager).createNamedQuery("StudentGroup.getAll", StudentGroup.class);
        assert studentGroups.equals(studentsGroupGetAll);
    }

}
