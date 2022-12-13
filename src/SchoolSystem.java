
import courses.Course;
import courses.CourseFactory;
import person.PersonFactory;
import person.Student;
import person.Teacher;
//


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

//----------------------------------- START -------------------------------------------------

        System.out.println("Välkommen till skolsystemet!\n" +
                "Skriv \"1\" om du är en lärare, \"2\" om du är en elev eller \"0\" för att avsluta");

        input = scanner.nextInt();

        if (input < 0 || input > 2)
            System.out.println("Fel vid inmatning, försök igen\n");

        else {

            ID = input;

//------------------------------- STARTING LOOP -----------------------------------------------

            while (input != 0) {

                /**
                 * Alternativ "1" - Lärarterminalen
                 */

                if (ID == 1) {
                    seeTeacherTerminal();


                    /**
                     * Alternativ "2" - Elevterminalen
                     */

                } else if (ID == 2) {
                    seeStudentTerminal();
                }
            }
        }
//---------------------------- SYSTEM EXIT --------------------------------------------------
        System.out.println("Systemet avslutas");
        System.exit(0);
    }
//----------------------------- METHODS -----------------------------------------------------


    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<Teacher> getTeacherList() {
        return teacherList;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void seeTeacherTerminal() {
        System.out.println("""
                ** Lärarterminalen **

                Vad vill du göra?
                Skriv en siffra för att komma till respektive meny:
                                           
                "1" - Se kurser.
                "2" - Se Lärarlista.
                "3" - Se Elevlista.
                "0" - Avsluta.""");

        input = scanner.nextInt();

        if (input < 0 || input > 3) {
            System.out.println("Fel vid inmatning. Försök igen\n");

        } else {

            while (input != 0) {
                seeTeacherOptions();
            }
        }
    }
    public void seeTeacherOptions() {

        if (input == 1) {
            seeCourses();

        } else if (input == 2) {
            seeTeachers(teacherList);

        } else if (input == 3) {
            seeStudents(studentList);
        }
    }
    public void seeCourses() {

        System.out.println("""
                ** Kurser **

                Skriv en siffra för att välja respektive kurs:
                """);

        // Skriver ut alla ämnen som för tillfället ligger i listan
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println("\"" + (i + 1) + "\" - " + courseList.get(i).getName());
        }

        System.out.println("\"0\" - Avsluta");

        input = scanner.nextInt();

        if (input < 0 || input > 3) {
            System.out.println("Fel vid inmatning. Försök igen\n");

        } else {
            seeCourseOptions();
        }
    }
    public void seeCourseOptions() {
        if (input == 1) {

            seeEnglish();
            input = 20;

        } else if (input == 2) {

            seeHistory();
            input = 20;

        } else if (input == 3) {

            seeMath();
            input = 20;

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
    public void seeHistory() {
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
    public void seeMath() {
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
    public void seeTeachers(List<Teacher> teacherList) {
        System.out.println("** Lärarlista **"); //TODO: Visa befintliga lärare
    }
    public void seeStudents(List<Student> studentList) {
        System.out.println("** Elevlista **"); //TODO: Visa befintliga elever
    }
    public void seeStudentTerminal() {
        System.out.println("""
                ** Elevterminalen **
                                                
                Vad vill du göra?
                Skriv en siffra för att komma till respektive meny
                                                
                "1" - Se Kurser.
                "2" - Se Lärarlista.
                "3" - Se Elevlista.
                "0" - Avsluta.""");

        input = scanner.nextInt();

        seeStudentOptions();
    }
    public void seeStudentOptions() {
        switch (input) {
            case 1:
                seeCourses();

            case 2:
                seeTeachers(teacherList);

            case 3:
                seeStudents(studentList);
        }
    }

    public void removeStudentFromCourse(List<Course> courseList, Student student){}
    public void addStudentToCourse(List<Course> courseList, Student student){}
    public void removeTeacherFromCourse(){}
    public void addTeacherToCourse(){}
    public void printCourseStudents(List<Course> courseList){}
    public void printCourseTeacher(){}
    public void printStudentCourseList(){}
    public void printTeacherCourseList(){}







    public static void main(String[] args) {
        new SchoolSystem();

    }
}
