package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter @Setter
public class SchoolClass extends ToJSON {
    private String name;
    private int studentCapacity;
    private List<Teacher> teachers;
    private List<Student> students;


    public SchoolClass(String name, int studentCapacity) {
        this.name = name;
        this.studentCapacity = studentCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        SchoolClass that = (SchoolClass) o;
        return studentCapacity == that.studentCapacity && Objects.equals(name, that.name) && Objects.equals(teachers, that.teachers) && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + studentCapacity;
        result = 31 * result + Objects.hashCode(teachers);
        result = 31 * result + Objects.hashCode(students);
        return result;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "name='" + name + '\'' +
                ", studentCapacity=" + studentCapacity +
                ", teachers=" + teachers +
                ", students=" + students +
                '}';
    }
}
