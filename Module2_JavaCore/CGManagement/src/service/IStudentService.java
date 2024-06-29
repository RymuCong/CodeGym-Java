package service;
import model.Student;

public interface IStudentService {
    void findAll();

    Student addNewStudent();

    void save(Student student);

    void delete();

    void edit();
}
