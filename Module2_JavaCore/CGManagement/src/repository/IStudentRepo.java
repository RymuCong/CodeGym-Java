package repository;

import model.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface IStudentRepo {
    void findAll();

    Student addStudent();

    void save(Student student);

    void delete();

    void edit();

    ArrayList<Student> getStudents();

    void importFromCsv();
}
