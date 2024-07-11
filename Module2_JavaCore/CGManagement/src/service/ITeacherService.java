package service;

import model.Teacher;

public interface ITeacherService {
    void findAll();

    Teacher addTeacher();

    void saveTeacher(Teacher teacher);

    void deleteTeacher();

    void editTeacher();

    void searchTeacher();
}
