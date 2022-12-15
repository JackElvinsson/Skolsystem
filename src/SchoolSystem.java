
import courses.Course;
import courses.CourseFactory;
import person.Person;
import person.PersonFactory;
import person.Student;
import person.Teacher;
//


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SchoolSystem {
    private final List<Course> courseList = new ArrayList<>();
    private PersonDatabase pd = new PersonDatabase();
    private final Scanner scanner = new Scanner(System.in);
    private int input = 20;
    private int ID;

    public SchoolSystem() {

        CourseFactory courseFactory = new CourseFactory();

        courseFactory.createCourse("ENGLISH", courseList);
        courseFactory.createCourse("HISTORY", courseList);
        courseFactory.createCourse("MATH", courseList);

        for (Teacher teacher : pd.getAllTechers()) {
            System.out.println(teacher.getName());
        }
        for (Student student : pd.getAllStudens()) {
            System.out.println(student.getName());
        }
        for (Course course : courseList) {
            System.out.println(course.getName());
        }

//----------------------------------- START -------------------------------------------------

        System.out.println("Välkommen till skolsystemet!\n" +
                "Skriv \"1\" om du är en lärare, \"2\" om du är en elev eller \"0\" för att avsluta");

        input = Integer.parseInt(scanner.nextLine());

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


    public List<Course> getCourseList() {
        return courseList;
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

        input = Integer.parseInt(scanner.nextLine());

        if (input < 0 || input > 3) {
            System.out.println("Fel vid inmatning. Försök igen\n");

        } else {

            while (input != 0) {
                loadTeacherOptions();
            }
        }
    }

    public void loadTeacherOptions() {

        if (input == 1) {
            seeCourses();
            input = 20;

        } else if (input == 2) {
            seeTeachers(pd.getAllTechers());
            input = 20;

        } else if (input == 3) {
            seeStudents(pd.getAllStudens());
            input = 20;
        }
    }

    public void seeCourses() {

        System.out.println("""
                ** Kurser **

                Skriv en siffra för att välja respektive kurs:
                """);

        // Skriver ut alla kurser som för tillfället ligger i listan
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println("\"" + (i + 1) + "\" - " + courseList.get(i).getName());
        }

        System.out.println("\"4\" - Backa.");
        System.out.println("\"0\" - Avsluta.");

        input = Integer.parseInt(scanner.nextLine());

        if (input < 0 || input > 4) {
            System.out.println("Fel vid inmatning. Försök igen\n");

        } else {
            loadCourseOptions();
        }
    }

    public void loadCourseOptions() {
        if (input == 1) {

            seeEnglish();
            input = 20;

        } else if (input == 2) {

            seeHistory();
            input = 20;

        } else if (input == 3) {

            seeMath();
            input = 20;

        } else if (input == 4) {

            //Backa
            seeTeacherTerminal();
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
                    "4" - Lägg till lärare.
                    "5" - Backa.
                    "0" - Avsluta.""");


            input = Integer.parseInt(scanner.nextLine());

            if (input < 0 || input > 5) {
                System.out.println("Fel vid inmatning. Försök igen\n");

            } else {
                loadSelectedCourseAlternatives("Engelska");
            }
        } else {
            System.out.println("""
                    ** Engelska **

                    --Visar namn på lärare som undervisar--
                                        
                    --Visar elever som går denna kurs--
                                        
                    Skriv en siffra för att välja välja vad du vill göra:
                                        
                    "1" - Backa.
                    "2" - Avsluta.""");

        }
    }

    public void loadSelectedCourseAlternatives(String courseName) {
        if (input == 1) {

            System.out.println("Vilken elev vill du ta bort från kursen?");

            String studentToRemove = scanner.nextLine();
            removeStudentFromCourse(courseList, studentToRemove, courseName);

        } else if (input == 2) {

            System.out.println("Vilken elev vill du lägga till kursen?");

            String studentToAdd = scanner.nextLine();
            addStudentToCourse(courseList, studentToAdd, courseName);
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
                    "4" - Lägg till lärare.
                    "5" - Backa.
                    "0" - Avsluta.""");
        } else {
            System.out.println("""
                    ** Historia **

                    --Visar namn på lärare som undervisar--
                                        
                    --Visar elever som går denna kurs--
                                        
                    Skriv en siffra för att välja välja vad du vill göra:
                                        
                    "1" - Backa.
                    "2" - Avsluta.""");

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
                    "4" - Lägg till lärare.
                    "5" - Backa.
                    "0" - Avsluta.""");
        } else {
            System.out.println("""
                    ** Matematik **

                    --Visar namn på lärare som undervisar--
                                        
                    --Visar elever som går denna kurs--
                                        
                    Skriv en siffra för att välja välja vad du vill göra:
                                        
                    "1" - Backa.
                    "2" - Avsluta.""");
        }
    }

    public void seeTeachers(List<Teacher> teacherList) {

        System.out.println("** Lärarlista **\n");

        ///Skriver ut alla lärare som för tillfället finns med i listan
        for (int i = 0; i < teacherList.size(); i++) {
            System.out.println("\"" + (i + 1) + "\" - " + teacherList.get(i).getName() + ".");
        }
        System.out.println("\"" + (teacherList.size() + 1) + "\" - Backa.");
        System.out.println("\"0\" - Avsluta.");
    }

    public void seeStudents(List<Student> studentList) {

        System.out.println("** Elevlista **\n");

        //Skriver ut alla elever som för tillfället finns med i listan
        for (int i = 0; !(i >= studentList.size()); i++) {
            System.out.println("\"" + (i + 1) + "\" - " + studentList.get(i).getName() + ".");
        }
        System.out.println("\"" + (studentList.size() + 1) + "\" - Backa.");
        System.out.println("\"0\" - Avsluta.");
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

        input = Integer.parseInt(scanner.nextLine());

        seeStudentOptions();
    }

    public void seeStudentOptions() {
        switch (input) {
            case 1:
                seeCourses();

            case 2:
                seeTeachers(pd.getAllTechers());

            case 3:
                seeStudents(pd.getAllStudens());
        }
    }

    public void removeStudentFromCourse(List<Course> courseList, String studentToRemove, String courseName) {

        studentToRemove = studentToRemove.trim();

        // Söker efter given kurs i listan av kurser.
        // När kursen hittas kommer vald elev att tas bort från vald kurs om den finns i kurslistan

        for (int i = 0; i < courseList.size(); i++) {
            if (Objects.equals(courseName, courseList.get(i).getName())) {
                for (int j = 0; j < courseList.get(i).getStudentList().size(); j++) {
                    if (courseList.get(i).getStudentList().get(j).getName().equalsIgnoreCase(studentToRemove)) {
                        courseList.get(i).getStudentList().remove(courseList.get(i).getStudentList().get(j));
                        break;
                    }
                }
            }
        }
    }

    public void addStudentToCourse(List<Course> courseList, String studentToAdd, String courseName) {

        studentToAdd = studentToAdd.trim();

        List<Student> allStudens= pd.getAllStudens();
        for (Student s : allStudens)
            System.out.println(s.getName());

        // Söker efter given kurs i listan av ämnen.
        // När kursen hittas kollar metoden upp om studenten redan finns med i listan
        // Om studenten redan finns händer inget, annars läggs den till i kurslistan

        for (int i = 0; i < courseList.size(); i++) {
            if (Objects.equals(courseName, courseList.get(i).getName())) {

                if (courseList.get(i).getStudentList().size() > 0) {

                    for (int j = 0; j < courseList.get(i).getStudentList().size(); j++) {
                        if (courseList.get(i).getStudentList().get(j).getName().equalsIgnoreCase(studentToAdd)) {
                            System.out.println(studentToAdd + " läser redan " + courseName);
                        }
                    }

                } else {
                    for (int j = 0; j < pd.getAllStudens().size(); j++) {
                        if (pd.getAllStudens().get(i).getName().equalsIgnoreCase(studentToAdd)) {
                            courseList.get(i).getStudentList().add(pd.getAllStudens().get(i));
                            break;
                        }
                    }
                }
            }
        }
        if (courseName.equals("Engelska")) {
            seeEnglish();
        } else if (courseName.equals("Historia")) {
            seeHistory();
        } else {
            seeMath();
        }
    }

    public void removeTeacherFromCourse() {
    }

    public void addTeacherToCourse() {
    }

    public void printCourseStudents(List<Course> courseList) {
    }

    public void printCourseTeacher() {
    }

    public void printStudentCourseList() {
    }

    public void printTeacherCourseList() {
    }


    public static void main(String[] args) {
        new SchoolSystem();


    }
}
