package controller;

import service.IStudentService;
import service.StudentServiceImpl;
import model.Student;

public class StudentController {
    private final IStudentService studentService = new StudentServiceImpl();

    public void displayAllStudents() {
        studentService.findAll();
    }

    public void addNewStudent() {
        Student addStudent = studentService.addNewStudent();
        studentService.save(addStudent);
    }

    public void updateStudent() {

    }

    public void deleteStudent() {
        studentService.delete();
    }

    public void editStudent() {
        studentService.edit();
    }
}
