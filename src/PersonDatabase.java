import person.PersonFactory;
import person.Student;
import person.Teacher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonDatabase {
    private final List<Student> allStudens = new ArrayList<>();
    private final List<Teacher> allTechers = new ArrayList<>();
    PersonFactory pf = new PersonFactory();

    public PersonDatabase() {
        loadAllPeople();
    }

    private void loadAllPeople() {

        String temp;

        String file = "src/People.txt";
        try(BufferedReader buf = new BufferedReader(new FileReader(file))) {
            while ((temp = buf.readLine()) != null) {
                String[] fileInput = temp.split(",");
                var person = pf.createPerson(fileInput[0], fileInput[1], fileInput[2]);
                if (person instanceof Student)
                    allStudens.add((Student) person);
                else if (person instanceof Teacher)
                    allTechers.add((Teacher) person);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getAllStudens() {
        return allStudens;
    }

    public List<Teacher> getAllTechers() {
        return allTechers;
    }
}
