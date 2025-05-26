package model;

import java.util.List;
import java.util.Objects;

public class Teacher extends ToJSON {
    private String name;
    private String lastname;
    private int age;
    private List<Subject> subjets;

    public Teacher(String name, String lastname, int age, List<Subject> subjets) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.subjets = subjets;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;
        return age == teacher.age && Objects.equals(name, teacher.name) && Objects.equals(lastname, teacher.lastname) && Objects.equals(subjets, teacher.subjets);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(lastname);
        result = 31 * result + age;
        result = 31 * result + Objects.hashCode(subjets);
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", subjets=" + subjets +
                '}';
    }
}
