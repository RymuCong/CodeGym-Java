package service;

import model.Teacher;

import java.util.List;

public interface ITeacherService {
    void findAll();

    Teacher addTeacher();

    void saveTeacher(Teacher teacher);

    void deleteTeacher();

    void editTeacher();

    void searchTeacher();

    List<Teacher> getTeachers();
}
