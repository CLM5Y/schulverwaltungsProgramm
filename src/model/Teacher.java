package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

public class Teacher extends ToJSON {
    private String name;
    private String lastname;
    private int age;
    private List<Subject> subjects;

    public Teacher(String name, String lastname, int age, List<Subject> subjects) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;
        return age == teacher.age &&
                Objects.equals(name, teacher.name) &&
                Objects.equals(lastname, teacher.lastname) &&
                Objects.equals(subjects, teacher.subjects);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(lastname);
        result = 31 * result + age;
        result = 31 * result + Objects.hashCode(subjects);
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", subjects=" + subjects +
                '}';
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("lastname", lastname);
        json.put("age", age);

        JSONArray subjectArray = new JSONArray();
        for (Subject s : this.subjects) {
            subjectArray.put(s.getName());
        }
        json.put("subjects", subjectArray);

        return json;
    }
}
