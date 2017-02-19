package by.bsuir.aipos.service;

import by.bsuir.aipos.model.StudentGroup;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentGroupServiceImpl implements StudentGroupService {

    public EntityManager em = Persistence.createEntityManagerFactory("STUDENT_DB").createEntityManager();

    public StudentGroup save(StudentGroup studentGroup){
        em.getTransaction().begin();
        em.merge(studentGroup);
        em.getTransaction().commit();
        return studentGroup;
    }

    public StudentGroup get(String name){
        TypedQuery<StudentGroup> namedQuery = em.createNamedQuery("StudentGroup.getByName", StudentGroup.class)
                .setParameter("name", name);
        return namedQuery.getResultList().get(0) != null ? namedQuery.getResultList().get(0) : null;
    }

    public StudentGroup get(long id){
        return em.find(StudentGroup.class, id);
    }

    public void delete(long id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public List<StudentGroup> getAll(){
        TypedQuery<StudentGroup> namedQuery = em.createNamedQuery("StudentGroup.getAll", StudentGroup.class);
        return namedQuery.getResultList();
    }
}
