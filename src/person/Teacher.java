
package person;
//

public class Teacher implements Person {

    private final String name;
    private final String personalID;

    public Teacher(String name, String personalID) {
        this.name = name;
        this.personalID = personalID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPID() {
        return personalID;
    }

}
