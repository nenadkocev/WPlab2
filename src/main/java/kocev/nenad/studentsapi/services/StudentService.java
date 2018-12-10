package kocev.nenad.studentsapi.services;

import kocev.nenad.studentsapi.model.Student;
import kocev.nenad.studentsapi.model.exceptions.BadParametersException;
import kocev.nenad.studentsapi.model.exceptions.StudentNotFoundException;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudent(String index) throws StudentNotFoundException;

    List<Student> getAllStudentsFromStudyProgram(Long id);

    void updateStudent(String index, String fName, String lName, String studyProgramName) throws StudentNotFoundException, BadParametersException;

//    index, name, lastName, studyProgramName  (Сите параметри се од тип String)

    void addNewStudent(String index, String name, String lastName, String studyProgramName) throws BadParametersException;

    void deleteStudent(String index) throws StudentNotFoundException;
}
