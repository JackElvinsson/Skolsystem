
package person;

public class PersonFactory {

    public Person createPerson(String personType, String name, String PID) {

        if (personType.equalsIgnoreCase("STUDENT"))
            return new Student(name,PID);
        else if (personType.equalsIgnoreCase("TEACHER"))
            return new Teacher(name, PID);
        else
            return null;
    }
}
