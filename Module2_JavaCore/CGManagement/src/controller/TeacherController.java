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


    public void exportToCsv() {
        try {
            FileWriter fileWriter = new FileWriter("src/view/teachers.csv", false);
            fileWriter.write("ID,Code,Name,Email\n");
            List<Teacher> teachers = teacherService.getTeachers();
            for (Teacher teacher : teachers) {
                fileWriter.write(teacher.getId() + "," + teacher.getCode() + "," + teacher.getName() + "," + teacher.getEmail() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
