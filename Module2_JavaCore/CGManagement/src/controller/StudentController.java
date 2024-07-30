package controller;

import service.IStudentService;
import service.StudentServiceImpl;
import model.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentController {
    private final IStudentService studentService = new StudentServiceImpl();

    public void displayAllStudents() {
        studentService.findAll();
    }

    public void addNewStudent() {
        Student addStudent = studentService.addNewStudent();
        studentService.save(addStudent);
    }

    public void deleteStudent() {
        studentService.delete();
    }

    public void editStudent() {
        studentService.edit();
    }

    public void exportToCsv() {
        studentService.exportToCsv();
    }

    public void importToCsv() {
        studentService.importFromCsv();
    }
}
