
package courses;
//

import person.Student;
import person.Teacher;

import java.util.ArrayList;
import java.util.List;

public class History implements Course {

    String name = "Historia";
    List<Student> studentList = new ArrayList<>();
    Teacher teacher;

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

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


}
