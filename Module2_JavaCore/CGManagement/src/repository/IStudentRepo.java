package repository;

import model.Student;

public interface IStudentRepo {
    void findAll();

    Student addStudent();

    void save(Student student);

    void delete();

    void edit();
}
