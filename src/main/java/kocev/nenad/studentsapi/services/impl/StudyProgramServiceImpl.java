package kocev.nenad.studentsapi.services.impl;

import kocev.nenad.studentsapi.model.StudyProgram;
import kocev.nenad.studentsapi.model.exceptions.StudyProgramNotEmptyException;
import kocev.nenad.studentsapi.repository.StudentRepository;
import kocev.nenad.studentsapi.repository.StudyProgramRepository;
import kocev.nenad.studentsapi.services.StudyProgramService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {

    private final StudyProgramRepository repository;
    private final StudentRepository studentRepository;

    public StudyProgramServiceImpl(StudyProgramRepository repository, StudentRepository studentRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
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
        boolean notEmpty = studentRepository.findAll().stream()
                .anyMatch(s -> s.getProgram().getId().equals(id));
        if(notEmpty)
            throw new StudyProgramNotEmptyException();
        repository.deleteById(id);
    }
}
