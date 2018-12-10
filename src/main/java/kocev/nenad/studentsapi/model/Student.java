package kocev.nenad.studentsapi.model;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    private String index;

    @Column(name = "fname")
    private String firstName;

    @Column(name = "lname")
    private String lastName;

    @ManyToOne
    private StudyProgram program;

    public Student(String index, String firstName, String lastName, StudyProgram program) {
        this.index = index;
        this.firstName = firstName;
        this.lastName = lastName;
        this.program = program;
    }

    public Student() {
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public StudyProgram getProgram() {
        return program;
    }

    @Override
    public String toString() {
        return "Student{" +
                "index='" + index + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setProgram(StudyProgram program) {
        this.program = program;
    }
}