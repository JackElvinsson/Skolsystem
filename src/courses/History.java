
package courses;
//

import person.Teacher;

import java.util.ArrayList;
import java.util.List;

public class History implements Course {

    String name = "Historia";
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
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


}
