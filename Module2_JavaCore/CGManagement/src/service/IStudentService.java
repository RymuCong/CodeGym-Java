package service;
import model.Student;

import java.util.ArrayList;

public interface IStudentService {
    void findAll();

    Student addNewStudent();

    void save(Student student);

    void delete();

    void edit();

    ArrayList<Student> getStudents();

    void importFromCsv();
}
