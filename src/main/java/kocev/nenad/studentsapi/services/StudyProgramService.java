package kocev.nenad.studentsapi.services;

import kocev.nenad.studentsapi.model.StudyProgram;
import kocev.nenad.studentsapi.model.exceptions.StudyProgramNotEmptyException;

import java.util.List;

public interface StudyProgramService {

    List<StudyProgram> getAllStudyPrograms();

    void addNewStudyProgram(String programName);

    void deleteStudyProgram(Long id) throws StudyProgramNotEmptyException;
}
