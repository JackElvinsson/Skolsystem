package database;

import courses.Course;
import person.Student;

public class Enrollment {
    private String studentName;
    private String courseName;

    public Enrollment(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public String getStudent() {
        return studentName;
    }
    public String getCourse() {
        return courseName;
    }
}
