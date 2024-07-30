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
        return teacherRepo.addTeacher();
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

    @Override
    public void importFromCsv() {
        teacherRepo.importFromCsv();
    }

    @Override
    public void exportToCsv() {
        teacherRepo.exportToCsv();
    }
}
