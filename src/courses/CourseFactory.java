
package courses;
//

public class CourseFactory {
    public Course createCourse(String courseName) {
        if (courseName.equalsIgnoreCase("HISTORY")) {
            return new History();
        }
        else if (courseName.equalsIgnoreCase("MATH")) {
            return new Math();
        }
        else if (courseName.equalsIgnoreCase("ENGLISH")) {
            return new English();
        }
        else {
            System.err.println("Couldn't create course.");
        }
        return null;
    }
}
