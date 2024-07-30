package repository;

import model.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface IStudentRepo extends Crud {

    Student addStudent();

    void save(Student student);

    ArrayList<Student> getStudents();

}
