
import courses.Course;
import courses.CourseFactory;
import courses.English;
import person.Person;
import person.PersonFactory;
import person.Student;
import person.Teacher;
//


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SchoolSystem {

    private final ArrayList<Teacher> teacherList = new ArrayList<>();
    private final ArrayList<Student> studentList = new ArrayList<>();
    private final ArrayList<Course> courseList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private int input = 20;
    private int ID;

    public SchoolSystem() {

        CourseFactory courseFactory = new CourseFactory();
        PersonFactory personFactory = new PersonFactory();

        personFactory.createPerson("TEACHER", "Rolf", "1563-03-01", studentList, teacherList);
        personFactory.createPerson("TEACHER", "Anna", "1563-03-01", studentList, teacherList);
        personFactory.createPerson("TEACHER", "Kenneth", "1563-03-01", studentList, teacherList);
        personFactory.createPerson("TEACHER", "Christina", "1563-03-01", studentList, teacherList);

        personFactory.createPerson("STUDENT", "Adam", "2003-01-02", studentList, teacherList);
        personFactory.createPerson("STUDENT", "Erika", "2003-01-02", studentList, teacherList);
        personFactory.createPerson("STUDENT", "Ludvig", "2003-01-02", studentList, teacherList);
        personFactory.createPerson("STUDENT", "Anna", "2003-01-02", studentList, teacherList);

        courseFactory.createCourse("ENGLISH", courseList);
        courseFactory.createCourse("HISTORY", courseList);
        courseFactory.createCourse("MATH", courseList);

        for (Teacher teacher : teacherList) {
            System.out.println(teacher.getName());
        }
        for (Student student : studentList) {
            System.out.println(student.getName());
        }
        for (Course course : courseList) {
            System.out.println(course.getName());
        }


        System.out.println("Välkommen till skolsystemet!\n" +
                "Skriv \"1\" om du är en lärare, \"2\" om du är en elev eller \"0\" för att avsluta");

        input = scanner.nextInt();

        if (input < 0 || input > 2)
            System.out.println("Fel vid inmatning, försök igen\n");

        else {

            ID = input;

        while (input != 0) {

                switch (input) {

                    /**
                     * Alternativ "1" - Lärarterminalen
                     */
                    case 1 -> {
                        System.out.println("""
                                ** Lärarterminalen **

                                Vad vill du göra?
                                Skriv en siffra för att komma till respektive meny:
                                                           
                                "1" - Se kurser.
                                "2" - Se Lärarlista.
                                "3" - Se Elevlista.
                                "0" - Avsluta.""");

                        input = scanner.nextInt();

                        teacherOptions();

                    }

                    /**
                     * Alternativ "2" - Elevterminalen
                     */
                    case 2 -> {
                        System.out.println("""
                                ** Elevterminalen **
                                                                
                                Vad vill du göra?
                                Skriv en siffra för att komma till respektive meny
                                                                
                                "1" - Se Kurser.
                                "2" - Se Lärarlista.
                                "3" - Se Elevlista.
                                "0" - Avsluta.""");

                        input = scanner.nextInt();

                        studentOptions();
                    }
                }
            }
        }
        System.out.println("Systemet avslutas");
        System.exit(0);
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<Teacher> getTeacherList() {
        return teacherList;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void teacherOptions(){
        switch (input) {
            case 1:
                seeCourses();

            case 2:
                seeTeachers(teacherList);

            case 3:
                seeStudents(studentList);


        }
    }
    public void seeCourses(){

        System.out.println("""
                                        ** Kurser **

                                        Skriv en siffra för att välja respektiva kurs:
                                        """);

        // Skriver ut alla ämnen som för tillfället ligger i listan
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println("\"" + (i + 1) + "\" - " + courseList.get(i).getName());
        }

        System.out.println("\"0\" - Avsluta");

        input = scanner.nextInt();

        //Switch-case med alla olika ämnen
        courseOptions();
    }
    public void courseOptions(){
        switch (input){
            //Engelska
            case 1: seeEnglish();
            case 2: seeHistory();
            case 3: seeMath();
        }
    }
    public void seeEnglish() {

        if (ID == 1) {
            System.out.println("""
                    ** Engelska **
                    
                    --Visar namn på lärare som undervisar--
                    
                    --Visar elever som går denna kurs--

                    Skriv en siffra för att välja välja vad du vill göra:
                                    
                    "1" - Ta bort en elev.
                    "2" - Lägg till en elev.
                    "3" - Ta bort lärare.
                    "4" - Lägg till lärare.""");
        } else {
            System.out.println("""
                    ** Engelska **

                    --Visar namn på lärare som undervisar--
                    
                    --Visar elever som går denna kurs--""");

        }
    }
    public void seeHistory(){
        if (ID == 1) {
            System.out.println("""
                    ** Historia **
                    
                    --Visar namn på lärare som undervisar--
                    
                    --Visar elever som går denna kurs--

                    Skriv en siffra för att välja välja vad du vill göra:
                                    
                    "1" - Ta bort en elev.
                    "2" - Lägg till en elev.
                    "3" - Ta bort lärare.
                    "4" - Lägg till lärare.""");
        } else {
            System.out.println("""
                    ** Historia **

                    --Visar namn på lärare som undervisar--
                    
                    --Visar elever som går denna kurs--""");

        }
    }
    public void seeMath(){
        if (ID == 1) {
            System.out.println("""
                    ** Matematik **

                    --Visar namn på lärare som undervisar--
                    
                    --Visar elever som går denna kurs--
                    
                    Skriv en siffra för att välja välja vad du vill göra:
                                    
                    "1" - Ta bort en elev.
                    "2" - Lägg till en elev.
                    "3" - Ta bort lärare.
                    "4" - Lägg till lärare.""");
        } else {
            System.out.println("""
                    ** Matematik **

                    --Visar namn på lärare som undervisar--
                    
                    --Visar elever som går denna kurs--""");
        }
    }
    public void seeTeachers(List<Teacher> teacherList){
        System.out.println("** Lärarlista **"); //TODO: Visa befintliga lärare
    }
    public void seeStudents(List<Student> studentList){
        System.out.println("** Elevlista **"); //TODO: Visa befintliga elever
    }
    public void studentOptions(){
        switch (input) {
            case 1:
                seeCourses();

            case 2:
                seeTeachers(teacherList);

            case 3:
                seeStudents(studentList);
        }
    }

    public static void main(String[] args) {
        new SchoolSystem();

    }
}
