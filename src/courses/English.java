
package courses;
//

import person.Teacher;

public class English implements Course {

    String name = "Engelska";
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
