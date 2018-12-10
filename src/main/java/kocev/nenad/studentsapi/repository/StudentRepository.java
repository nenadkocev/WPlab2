package kocev.nenad.studentsapi.repository;

import kocev.nenad.studentsapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
