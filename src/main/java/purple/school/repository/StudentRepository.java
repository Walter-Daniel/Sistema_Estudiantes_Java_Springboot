package purple.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import purple.school.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
