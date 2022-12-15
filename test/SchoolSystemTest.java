import courses.*;
import courses.Math;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Test;
import person.PersonFactory;
import person.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchoolSystemTest {

    Student adam = new Student("adam","1992-02-10");
    Student erika = new Student("erika","1992-02-10");
    Student bengt = new Student("bengt","1992-02-10");

    List<Student> studentList = List.of(adam, erika, bengt);

    Course historia = new History();
    Course math = new Math();
    Course english = new English();

    List<Course> courseList = List.of(historia, math, english);





    @Test
    void removeStudentFromCourse() {

        courseList.get(0).getStudentList().addAll(studentList);
        courseList.get(1).getStudentList().addAll(studentList);
        courseList.get(2).getStudentList().addAll(studentList);
    }


}