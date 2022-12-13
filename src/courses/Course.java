
package courses;
//

import person.Student;
import person.Teacher;

import java.util.List;

public interface Course {

    String getName();
    Teacher getTeacher();
    List<Student> getStudentList();
    void setTeacher(Teacher teacher);
}
