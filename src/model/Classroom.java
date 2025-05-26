package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
public class Classroom extends ToJSON{
    private int roomNumber;
    private float roomArea;
    private SchoolClass classInRoom;

    public Classroom(int roomNumber, float roomArea, SchoolClass classInRoom) {
        this.roomNumber = roomNumber;
        this.roomArea = roomArea;
        this.classInRoom = classInRoom;
    }
    public Classroom(int roomNumber, float roomArea) {
        this.roomNumber = roomNumber;
        this.roomArea = roomArea;
        this.classInRoom = null;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Classroom classroom = (Classroom) o;
        return roomNumber == classroom.roomNumber && Float.compare(roomArea, classroom.roomArea) == 0 && Objects.equals(classInRoom, classroom.classInRoom);
    }

    @Override
    public int hashCode() {
        int result = roomNumber;
        result = 31 * result + Float.hashCode(roomArea);
        result = 31 * result + Objects.hashCode(classInRoom);
        return result;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "roomNumber=" + roomNumber +
                ", roomArea=" + roomArea +
                ", classInRoom=" + classInRoom +
                '}';
    }
}
