package by.bsuir.aipos.service;

import by.bsuir.aipos.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by andrey on 10/02/17.
 */
public class StudentServiceImpl implements StudentService{

    public EntityManager em = Persistence.createEntityManagerFactory("STUDENT_DB").createEntityManager();

    public Student save(Student student){
        em.getTransaction().begin();
        if (student.getId() > 0) {
            em.persist(student);
        } else {
            em.merge(student);
        }
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
