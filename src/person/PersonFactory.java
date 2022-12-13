
package person;
//


import java.util.List;

public class PersonFactory {

    public void createPerson(String personType, String name, String PID, List<Student> studentList, List<Teacher> teacherList) {
        if (personType.equalsIgnoreCase("STUDENT")) {

            studentList.add(new Student(name,PID));

        }
        else if (personType.equalsIgnoreCase("TEACHER")) {

            teacherList.add(new Teacher(name, PID));
        }
        else {
            System.err.println("Couldn't create person.");
        }
    }
}
