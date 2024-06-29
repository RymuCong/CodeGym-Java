package service;
import model.Student;
import repository.IStudentRepo;
import repository.StudentRepoImpl;

public class StudentServiceImpl implements IStudentService{
    private IStudentRepo studentRepo = new StudentRepoImpl();

    public void findAll() {
        studentRepo.findAll();
    }

    @Override
    public Student addNewStudent() {
        Student newStudent = studentRepo.addStudent();
        String errorInput;
        do {
            errorInput = "";
            System.out.println(newStudent.toString());
            if (!newStudent.getName().matches("[a-zA-Z]+")) // changed regex
            {
                errorInput += "\n- Tên không hợp lệ!";
            }
            if (!newStudent.getEmail().matches("^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+$"))
            {
                errorInput += "\n- Email không hợp lệ!";
            }
            if (!errorInput.isEmpty())
            {
                System.out.println(errorInput);
                System.out.println("=== Vui lòng nhập lại! ===");
                newStudent = studentRepo.addStudent();
            }
        } while (!errorInput.isEmpty());
        return newStudent;
    }

    @Override
    public void save(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void delete() {
        studentRepo.delete();
    }

    @Override
    public void edit() {
        studentRepo.edit();
    }
}
