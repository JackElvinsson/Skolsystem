
package courses;
//

import java.util.List;

public class CourseFactory {
    public void createCourse(String courseType, List<Course> courseList) {
        if (courseType.equalsIgnoreCase("HISTORY")) {

            courseList.add(new History());
        }
        else if (courseType.equalsIgnoreCase("MATH")) {

            courseList.add(new Math());
        }
        else if (courseType.equalsIgnoreCase("ENGLISH")) {

            courseList.add(new English());
        }
        else {
            System.err.println("Couldn't create course.");
        }

    }
}
