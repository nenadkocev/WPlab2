package kocev.nenad.studentsapi.services.impl;

import kocev.nenad.studentsapi.model.DTOs.StudentDto;
import kocev.nenad.studentsapi.model.Student;
import kocev.nenad.studentsapi.model.StudyProgram;
import kocev.nenad.studentsapi.model.exceptions.BadParametersException;
import kocev.nenad.studentsapi.model.exceptions.StudentNotFoundException;
import kocev.nenad.studentsapi.repository.StudentRepository;
import kocev.nenad.studentsapi.repository.StudyProgramRepository;
import kocev.nenad.studentsapi.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final StudyProgramRepository studyProgramRepository;

    public StudentServiceImpl(StudentRepository repository, StudyProgramRepository studyProgramRepository) {
        this.studyProgramRepository = studyProgramRepository;
        this.repository = repository;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return repository.findAll()
                .stream()
                .map(s ->  new StudentDto(s.getIndex(), s.getFirstName(), s.getLastName(), s.getProgram().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Student getStudent(String index) throws StudentNotFoundException{
        Optional<Student> student = repository.findById(index);
        if(!student.isPresent())
            throw new StudentNotFoundException();

        return student.get();
    }

    @Override
    public List<Student> getAllStudentsFromStudyProgram(Long id) {
        return repository.findAll().stream()
                .filter(s -> s.getProgram().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public void updateStudent(String index, String fName, String lName, String studyProgramName) throws StudentNotFoundException, BadParametersException {
        Optional<Student> student = repository.findById(index);
        if(!student.isPresent())
            throw new StudentNotFoundException();

        //  check for null because is optional
        if(studyProgramName != null){
            Optional<StudyProgram> program =
                    studyProgramRepository.findAll().stream()
                            .filter(p -> p.getName().equals(studyProgramName))
                            .findAny();
            if(!program.isPresent())
                throw new BadParametersException();
            student.get().setProgram(program.get());
        }

        if(fName != null)
            student.get().setFirstName(fName);
        if(lName != null)
            student.get().setLastName(lName);

        repository.save(student.get());
    }

    @Override
    public void addNewStudent(String index, String name, String lastName, String studyProgramName) throws BadParametersException {
        if(index == null || name == null || lastName == null || studyProgramName == null)
            throw new BadParametersException();

        if(index.length() != 6)
            throw new BadParametersException();
        try {
            Integer.parseInt(index);
        }
        catch (NumberFormatException ex){
            throw new BadParametersException();
        }

        Optional<StudyProgram> program = studyProgramRepository.findAll().stream()
                .filter(s -> s.getName().equals(studyProgramName))
                .findAny();
        if(!program.isPresent()){
            throw new BadParametersException();
        }

        repository.save(new Student(index, name, lastName, program.get()));
    }

    @Override
    public void deleteStudent(String index) throws StudentNotFoundException{
        Optional<Student> student = repository.findById(index);
        if(!student.isPresent())
            throw new StudentNotFoundException();
        repository.delete(student.get());
    }
}
