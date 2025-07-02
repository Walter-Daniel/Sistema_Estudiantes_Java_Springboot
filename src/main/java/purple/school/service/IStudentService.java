package purple.school.service;

import purple.school.model.Student;

import java.util.List;

public interface IStudentService {
    public List<Student> getAllStudents();

    public Student findStudentById(Integer idStudent);

    public void saveStudent(Student student);

    public void deleteStudent(Student student);

}
