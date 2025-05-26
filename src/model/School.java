package model;

import lombok.Getter;
import lombok.Setter;
import model.SchoolClass;
import model.Classroom;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class School extends ToJSON {
    private String schoolName;
    private List<SchoolClass> classes;
    private List<Classroom> classrooms;
    private int maxClassCapacity;

    public School(String schoolName, int maxClassCapacity){
        this.schoolName = schoolName;
        this.maxClassCapacity = maxClassCapacity;
    }

    public String getSchoolName() {
        return schoolName.toLowerCase();
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setClasses(List<SchoolClass> classes) {
        this.classes = classes;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public int getMaxClassCapacity() {
        return maxClassCapacity;
    }

    public void setMaxClassCapacity(int maxClassCapacity) {
        this.maxClassCapacity = maxClassCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        School school = (School) o;
        return maxClassCapacity == school.maxClassCapacity && Objects.equals(schoolName, school.schoolName) && Objects.equals(classes, school.classes) && Objects.equals(classrooms, school.classrooms);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(schoolName);
        result = 31 * result + Objects.hashCode(classes);
        result = 31 * result + Objects.hashCode(classrooms);
        result = 31 * result + maxClassCapacity;
        return result;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolName='" + schoolName + '\'' +
                ", classes=" + classes +
                ", classrooms=" + classrooms +
                ", maxClassCapacity=" + maxClassCapacity +
                '}';
    }
}

