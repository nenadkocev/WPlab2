package kocev.nenad.studentsapi.controllers;

import kocev.nenad.studentsapi.model.Student;
import kocev.nenad.studentsapi.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentResource {

    private final StudentService service;

    public StudentResource(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAllStudents(){
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
    public void addNewStudent(@RequestBody Map<String, String> payload, HttpServletResponse response) throws IOException {

        service.addNewStudent(
                payload.get("index"),
                payload.get("fname"),
                payload.get("lname"),
                payload.get("studyProgram"));
        response.setHeader("Location", "/students/" + payload.get("index"));
    }

    @PatchMapping
    public void updateStudent(HttpServletRequest request){
        service.updateStudent(
                request.getParameter("index"),
                request.getParameter("fname"),
                request.getParameter("lname"),
                request.getParameter("studyProgram"));
    }

    @DeleteMapping("/delete/{index}")
    public void deleteStudent(@PathVariable String index){
        service.deleteStudent(index);
    }
}
