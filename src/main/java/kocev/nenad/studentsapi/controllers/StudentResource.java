package kocev.nenad.studentsapi.controllers;

import kocev.nenad.studentsapi.model.DTOs.StudentDto;
import kocev.nenad.studentsapi.model.Student;
import kocev.nenad.studentsapi.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentResource {

    private final StudentService service;

    public StudentResource(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentDto> getAllStudents(){
        return service.getAllStudents();
    }

    @GetMapping("/{index}")
    public Student getStudent(@PathVariable String index){
        return service.getStudent(index);
    }

    @GetMapping("/by_study_program/{id}")
    public List<Student> getStudentsByProgram(@PathVariable Long id){
        return service.getAllStudentsFromStudyProgram(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewStudent(@RequestBody StudentDto student, HttpServletResponse response){

        service.addNewStudent(
                student.getIndex(),
                student.getName(),
                student.getLastName(),
                student.getStudyProgram());
        response.setHeader("Location", "/students/" + student.getIndex());
    }

    @PostMapping("/update")
    public void updateStudent(@RequestBody StudentDto student){
        service.updateStudent(
                student.getIndex(),
                student.getName(),
                student.getLastName(),
                student.getStudyProgram());
    }

    @DeleteMapping("/delete/{index}")
    public void deleteStudent(@PathVariable String index){
        service.deleteStudent(index);
    }
}
