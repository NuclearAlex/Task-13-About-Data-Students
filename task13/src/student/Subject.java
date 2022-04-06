package student;

import java.util.Objects;

public class Subject {
    private final String subjectName;
    private final int mark;

    public Subject(String subjectName, int mark) {
        this.subjectName = subjectName;
        this.mark = mark;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public int getMark() {
        return mark;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return mark == subject.mark && subjectName.equals(subject.subjectName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(subjectName, mark);
    }

    @Override
    public String toString() {
        return String.format("%s = %d", subjectName, mark);
    }
}
