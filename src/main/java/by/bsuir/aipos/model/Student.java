package by.bsuir.aipos.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
@NamedQuery(name = "Student.getAll", query = "select s from Student s")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column()
    private String midleName;

    @Column(nullable = false)
    @Type(type="date")
    private Date dateOfBirth;

    @Column(nullable = false)
    private String homeAddress;

    @ManyToOne
    private StudentGroup studentGroup;

    public Student (){
    }

    public Student(String firstName, String lastName, Date dateOfBirth, String homeAddress, StudentGroup studentGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.homeAddress = homeAddress;
        this.studentGroup = studentGroup;
    }

    public Student(String firstName, String lastName, String midleName, Date dateOfBirth, String homeAddress, StudentGroup studentGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.midleName = midleName;
        this.dateOfBirth = dateOfBirth;
        this.homeAddress = homeAddress;
        this.studentGroup = studentGroup;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMidleName() {
        return midleName;
    }

    public void setMidleName(String midleName) {
        this.midleName = midleName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", midleName='" + midleName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", homeAddress='" + homeAddress + '\'' +
                ", studentGroup=" + studentGroup +
                '}';
    }
}
