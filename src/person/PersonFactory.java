
package person;
//


public class PersonFactory {

    public Person createPerson(String type, String name, String PID) {
        if (type.equalsIgnoreCase("STUDENT")) {
            return new Student(name, PID);
        }
        else if (type.equalsIgnoreCase("TEACHER")) {
            return new Teacher(name, PID);
        }
        else {
            System.err.println("Couldn't create person.");
        }
        return null;
    }
}
