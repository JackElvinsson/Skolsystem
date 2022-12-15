package database;

import person.Person;
import person.Student;
import person.Teacher;

import java.util.List;

public class PersonFactory2 {
    public Person createPerson(String personType, String name, String PID) {
        if (personType.equalsIgnoreCase("STUDENT")) {
            return new Student(name, PID);
            }
            else if (personType.equalsIgnoreCase("TEACHER")) {
            return new Teacher(name, PID);
            }
            else {
                System.err.println("Couldn't create person.");
                return null;
            }
        }

}
