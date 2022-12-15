
package person;
//

import courses.Course;

import java.util.ArrayList;

public class Teacher implements Person {

    private final String name;
    private final String personalID;
    //private final ArrayList<Course> courseList;

    public Teacher(String name, String personalID) {
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
    //public ArrayList getCourses() {
    //    return courseList;
    //}


}
