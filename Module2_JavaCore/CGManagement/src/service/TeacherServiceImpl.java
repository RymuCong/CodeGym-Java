package service;

import model.Teacher;
import repository.ITeacherRepo;
import repository.TeacherRepoImpl;

import java.util.List;

public class TeacherServiceImpl implements ITeacherService {
    private ITeacherRepo teacherRepo = new TeacherRepoImpl();

    @Override
    public void findAll() {
        teacherRepo.findAll();
    }

    @Override
    public Teacher addTeacher() {
        Teacher newTeacher = teacherRepo.addTeacher();
        String errorInput;
        do {
            errorInput = "";
            System.out.println(newTeacher.toString());
            if (!newTeacher.getName().matches("[a-zA-Z]+")) {
                errorInput += "\n- Tên không hợp lệ!";
            }
            if (!newTeacher.getEmail().matches("^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+$")) {
                errorInput += "\n- Email không hợp lệ!";
            }
            if (newTeacher.getSalary() < 0) {
                errorInput += "\n- Lương không hợp lệ!";
            }
            if (!errorInput.isEmpty()) {
                System.out.println(errorInput);
                System.out.println("=== Vui lòng nhập lại! ===");
                newTeacher = teacherRepo.addTeacher();
            }
        } while (!errorInput.isEmpty());
        return newTeacher;
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherRepo.save(teacher);
    }

    @Override
    public void deleteTeacher() {
        teacherRepo.delete();
    }

    @Override
    public void editTeacher() {
        teacherRepo.edit();
    }

    @Override
    public void searchTeacher() {
        teacherRepo.search();
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepo.getTeachers();
    }
}
