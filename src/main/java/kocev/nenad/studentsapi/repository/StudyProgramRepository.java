package kocev.nenad.studentsapi.repository;

import kocev.nenad.studentsapi.model.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyProgramRepository extends JpaRepository<StudyProgram, Long> {
}
