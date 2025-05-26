package model;

import java.util.List;
import java.util.Objects;

public class Student extends ToJSON{
    private String name;
    private String lastname;
    private int age;
    private List<Subject> favouriteSubject;

    public Student(String name, String lastname, int age, List<Subject> favouriteSubject) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.favouriteSubject = favouriteSubject;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name) && Objects.equals(lastname, student.lastname) && Objects.equals(favouriteSubject, student.favouriteSubject);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(lastname);
        result = 31 * result + age;
        result = 31 * result + Objects.hashCode(favouriteSubject);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", favouriteSubject=" + favouriteSubject +
                '}';
    }
}
