package kocev.nenad.studentsapi.services.impl;

import kocev.nenad.studentsapi.model.StudyProgram;
import kocev.nenad.studentsapi.repository.StudyProgramRepository;
import kocev.nenad.studentsapi.services.StudyProgramService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {

    private final StudyProgramRepository repository;

    public StudyProgramServiceImpl(StudyProgramRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StudyProgram> getAllStudyPrograms() {
        return repository.findAll();
    }

    @Override
    public void addNewStudyProgram(String programName) {
        repository.save(new StudyProgram(programName));
    }

    @Override
    public void deleteStudyProgram(Long id) {
        repository.deleteById(id);
    }
}
