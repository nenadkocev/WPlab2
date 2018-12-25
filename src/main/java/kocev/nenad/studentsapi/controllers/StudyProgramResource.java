package kocev.nenad.studentsapi.controllers;

import kocev.nenad.studentsapi.model.StudyProgram;
import kocev.nenad.studentsapi.services.StudyProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/study_programs", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudyProgramResource {
    private final StudyProgramService service;

    public StudyProgramResource(StudyProgramService service) {
        this.service = service;
    }

    @RequestMapping
    public List<StudyProgram> getAllPrograms(){
        return service.getAllStudyPrograms();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewStudyProgram(@RequestBody StudyProgram studyProgram){
        service.addNewStudyProgram(studyProgram.getName());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudyProgram(@PathVariable Long id){
        service.deleteStudyProgram(id);
    }
}
