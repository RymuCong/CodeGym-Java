package repository;

import model.Teacher;

import java.util.List;

public interface ITeacherRepo {
    void findAll();

    Teacher addTeacher();

    void save(Teacher teacher);

    void delete();

    void edit();

    void search();

    List<Teacher> getTeachers();
}
