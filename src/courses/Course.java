
package courses;
//

import person.Teacher;

public interface Course {

    String getName();
    Teacher getTeacher();
    void setTeacher(Teacher teacher);
}
