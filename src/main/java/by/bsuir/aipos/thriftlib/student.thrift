namespace java by.bsuir.aipos.thriftlib

typedef i32 int
typedef i64 long

struct StudentGroupThrift {
    1 : long id
    2 : string name
}

struct StudentThrift {
   1 : long id
   2 : string firstName
   3 : string lastName
   4 : string middleName
   5 : string dateOfBirth
   6 : string homeAddress
   7 : StudentGroupThrift studentGroup
}

service StudentThriftService {

    StudentThrift saveStudent(1 : StudentThrift studentThrift),
    StudentThrift getStudent(1 : long id),
    void deleteStudent(1 : long id),
    list<StudentThrift> getAllStudent(),

    StudentGroupThrift saveStudentGroup(1 : StudentGroupThrift studentGroupThrift),
    StudentGroupThrift getStudentGroup(1 : long id),
    StudentGroupThrift getStudentGroupByName(1 : string name),
    void deleteStudentGroup(1 : long id),
    list<StudentGroupThrift> getAllStudentGroup(),
}