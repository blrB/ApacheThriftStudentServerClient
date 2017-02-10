package by.bsuir.aipos.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student_group")
@NamedQueries({
    @NamedQuery(name = "StudentGroup.getAll", query = "select g from StudentGroup g"),
    @NamedQuery(name = "StudentGroup.getByName", query = "select g from StudentGroup g where g.name = :name")
})
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "studentGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<Student>();

    public StudentGroup() {
    }

    public StudentGroup(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "name='" + name + '\'' +
                '}';
    }
}
