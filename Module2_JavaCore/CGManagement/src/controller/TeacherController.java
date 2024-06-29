package controller;

import model.Teacher;
import service.ITeacherService;
import service.TeacherServiceImpl;

public class TeacherController {
    private ITeacherService teacherService = new TeacherServiceImpl();

    public void displayAllTeachers() {
        teacherService.findAll();
    }

    public void addNewTeacher() {
        Teacher newTeacher = teacherService.addTeacher();
        teacherService.saveTeacher(newTeacher);
    }

    public void updateTeacher(Teacher teacher) {

    }

    public void deleteTeacher() {
        teacherService.deleteTeacher();
    }

    public void editTeacher() {
        teacherService.editTeacher();
    }
}
