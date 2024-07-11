package controller;

import model.Student;
import model.Teacher;
import service.ITeacherService;
import service.TeacherServiceImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

    public void searchTeacher() {
        teacherService.searchTeacher();
    }



}
