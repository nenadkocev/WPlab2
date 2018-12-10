package kocev.nenad.studentsapi.model;

import javax.persistence.*;

@Entity
@Table(name = "study_program")
public class StudyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public StudyProgram(String name) {
        this.name = name;
    }
    public StudyProgram() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StudyProgram{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
