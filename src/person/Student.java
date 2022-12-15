
package person;
//

import courses.Course;

import java.util.ArrayList;

public class Student implements Person {

    private final String name;
    private final String personalID;
    //private final ArrayList<Course> courseList;

    public Student(String name, String personalID) {
        this.name = name;
        this.personalID = personalID;
        //courseList = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPID() {
        return personalID;
    }

    //@Override
    //public ArrayList<Course> getCourses() {
    //    return courseList;
    //}
}
