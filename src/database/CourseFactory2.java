package database;

import courses.Course;
import courses.English;
import courses.History;
import courses.Math;

import java.util.List;

public class CourseFactory2 {
    public Course createCourse(String courseType) {
        if (courseType.equalsIgnoreCase("HISTORY")) {
            return new History();
        }
        else if (courseType.equalsIgnoreCase("MATH")) {
            return new Math();
        }
        else if (courseType.equalsIgnoreCase("ENGLISH")) {
            return new English();
        }
        else {
            System.err.println("Couldn't create course.");
            return null;
        }

    }
}


