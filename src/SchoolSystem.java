
import courses.Course;
import courses.CourseFactory;
import person.Person;
import person.PersonFactory;
import person.Student;
import person.Teacher;

import java.util.ArrayList;
import java.util.List;

public class SchoolSystem {
    private final ArrayList<Course> courseList = new ArrayList<>();
    private static final ArrayList<Person> teacherList = new ArrayList<>();
    private static final ArrayList<Person> studentList = new ArrayList<>();
    private PersonDatabase pd = new PersonDatabase();
    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public SchoolSystem() {

        PersonFactory personFactory = new PersonFactory();
        CourseFactory courseFactory = new CourseFactory();

        personFactory.createPerson("TEACHER", "ROLF", "1563-03-01");
        Teacher rolf = (Teacher) personFactory.createPerson("TEACHER", "ROLF", "1563-03-01");
        Student adam = (Student) personFactory.createPerson("STUDENT", "ADAM", "2003-01-02");
        teacherList.add(rolf);
        studentList.add(adam);

        courseFactory.createCourse("English");
        courseFactory.createCourse("History");
        courseFactory.createCourse("Math");

        System.out.println(teacherList.get(0).getName() + "\n" + studentList.get(0).getName());

        List<Student> allStudens= pd.getAllStudens();
        for (Student s : allStudens)
            System.out.println(s.getName());

    }
    public static void main(String[] args) {
        new SchoolSystem();


    }
}
