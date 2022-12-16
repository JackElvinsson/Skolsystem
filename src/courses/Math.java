
package courses;
//

import person.Student;
import person.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Math implements Course {

    String name = "Matematik";
    List<Student> studentList = new ArrayList<>();
    Teacher teacher;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


}
