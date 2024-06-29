package repository;

import model.Teacher;

public interface ITeacherRepo {
    void findAll();

    Teacher addTeacher();

    void save(Teacher teacher);

    void delete();

    void edit();
}
