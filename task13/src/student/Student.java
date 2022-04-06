package student;

import java.util.Objects;

public class Student {
    private final String name;
    private final String secondName;

    public Student(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
    }
    public String getName() {
        return name;
    }
    public String getSecondName() {
        return secondName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return name.equals(student.name) && secondName.equals(student.secondName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, secondName);
    }

    @Override
    public String toString() {
        return String.format("%s %s ", name, secondName);
    }
}
