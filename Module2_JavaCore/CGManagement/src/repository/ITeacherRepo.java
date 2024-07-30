package repository;

import model.Teacher;

import java.util.List;

public interface ITeacherRepo extends Crud {

    Teacher addTeacher();

    void save (Teacher teacher);

    List<Teacher> getTeachers();
}
