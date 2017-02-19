package by.bsuir.aipos.service;

import by.bsuir.aipos.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentServiceImpl implements StudentService{

    public EntityManager em = Persistence.createEntityManagerFactory("STUDENT_DB").createEntityManager();

    public Student save(Student student){
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
        return student;
    }

    public Student get(long id){
        return em.find(Student.class, id);
    }

    public void delete(long id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public List<Student> getAll(){
        TypedQuery<Student> namedQuery = em.createNamedQuery("Student.getAll", Student.class);
        return namedQuery.getResultList();
    }
}
