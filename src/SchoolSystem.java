
import courses.Course;
import courses.CourseFactory;
import person.PersonFactory;
import person.Student;
import person.Teacher;
//


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SchoolSystem {

    private final ArrayList<Teacher> teacherList = new ArrayList<>();
    private final ArrayList<Student> studentList = new ArrayList<>();
    private final ArrayList<Course> courseList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private int input;
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

//----------------------------------- START -------------------------------------------------

        System.out.println(ANSI_RESET + "Välkommen till skolsystemet!\n" +
                "Skriv \"1\" om du är en skoladministratör, \"2\" om du är en elev eller \"0\" för att avsluta");

        input = Integer.parseInt(scanner.nextLine());

        if (input < 0 || input > 2)
            System.out.println(ANSI_RED + "Fel vid inmatning, försök igen\n");

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
        System.out.println(ANSI_RED + "Systemet avslutas");
        System.exit(0);
    }
//----------------------------- METHODS -----------------------------------------------------


//    public ArrayList<Course> getCourseList() {
//        return courseList;
//    }
//
//    public ArrayList<Teacher> getTeacherList() {
//        return teacherList;
//    }
//
//    public ArrayList<Student> getStudentList() {
//        return studentList;
//    }

    public void seeTeacherTerminal() {
        System.out.println(ANSI_RESET + """
                ** Lärarterminalen **

                Vad vill du göra?
                Skriv en siffra för att komma till respektive meny:
                                           
                "1" - Se kurser.
                "2" - Se Lärarlista.
                "3" - Se Elevlista.
                "0" - Avsluta.""");

        input = Integer.parseInt(scanner.nextLine());

        if (input < 0 || input > 3) {
            System.out.println(ANSI_RED + "Fel vid inmatning. Försök igen\n");

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
            seeTeachers(teacherList);
            input = 20;

        } else if (input == 3) {
            seeStudents(studentList);
            input = 20;
        } else if (input == 0) {
            System.out.println(ANSI_RED + "Systemet avslutas");
            System.exit(0);
        }
    }

    public void seeCourses() {

        System.out.println(ANSI_RESET + """
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
            System.out.println(ANSI_RED + "Fel vid inmatning. Försök igen\n");
            seeCourses();

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
        } else if (input == 0) {
            System.out.println(ANSI_RED + "Systemet avslutas");
            System.exit(0);
        }
    }

    public void seeEnglish() {

        if (ID == 1) {
            System.out.println(ANSI_RESET + "** Engelska **\n");

            System.out.println(ANSI_RESET + "Lärare: ");
            printCourseTeacher(courseList, "Engelska");

            System.out.println(ANSI_RESET + "\nElever: ");
            printCourseStudents(courseList, "Engelska");

            System.out.println(ANSI_RESET + """
                    Skriv en siffra för att välja välja vad du vill göra:
                                    
                    "1" - Ta bort en elev.
                    "2" - Lägg till en elev.
                    "3" - Ta bort lärare.
                    "4" - Lägg till lärare.
                    "5" - Backa.
                    "0" - Avsluta.""");


            input = Integer.parseInt(scanner.nextLine());

            if (input < 0 || input > 5) {
                System.out.println(ANSI_RED + "Fel vid inmatning. Försök igen\n");

            } else {
                loadSelectedCourseAlternatives("Engelska");
            }
        } else {
            System.out.println(ANSI_RESET + "** Engelska **\n");

            printCourseTeacher(courseList, "Engelska");
            printCourseStudents(courseList, "Engelska");

            System.out.println(ANSI_RESET + """
                    Skriv en siffra för att välja välja vad du vill göra:
                                        
                    "1" - Backa.
                    "2" - Avsluta.""");

        }
    }

    public void seeHistory() {
        if (ID == 1) {
            System.out.println(ANSI_RESET + "** Historia **\n");

            System.out.println(ANSI_RESET + "Lärare: ");
            printCourseTeacher(courseList, "Historia");

            System.out.println(ANSI_RESET + "\nElever: ");
            printCourseStudents(courseList, "Historia");

            System.out.println(ANSI_RESET + """
                    Skriv en siffra för att välja välja vad du vill göra:
                                    
                    "1" - Ta bort en elev.
                    "2" - Lägg till en elev.
                    "3" - Ta bort lärare.
                    "4" - Lägg till lärare.
                    "5" - Backa.
                    "0" - Avsluta.""");


            input = Integer.parseInt(scanner.nextLine());

            if (input < 0 || input > 5) {
                System.out.println(ANSI_RED + "Fel vid inmatning. Försök igen\n");

            } else {
                loadSelectedCourseAlternatives("Historia");
            }
        } else {
            System.out.println(ANSI_RESET + "** Historia **\n");

            printCourseTeacher(courseList, "Historia");
            printCourseStudents(courseList, "Historia");

            System.out.println(ANSI_RESET + """
                    Skriv en siffra för att välja välja vad du vill göra:
                                        
                    "1" - Backa.
                    "2" - Avsluta.""");

        }
    }

    public void seeMath() {
        if (ID == 1) {
            System.out.println(ANSI_RESET + "** Matematik **\n");

            System.out.println(ANSI_RESET + "Lärare: ");
            printCourseTeacher(courseList, "Matematik");

            System.out.println(ANSI_RESET + "\nElever: ");
            printCourseStudents(courseList, "Matematik");

            System.out.println(ANSI_RESET + """
                    Skriv en siffra för att välja välja vad du vill göra:
                                    
                    "1" - Ta bort en elev.
                    "2" - Lägg till en elev.
                    "3" - Ta bort lärare.
                    "4" - Lägg till lärare.
                    "5" - Backa.
                    "0" - Avsluta.""");


            input = Integer.parseInt(scanner.nextLine());

            if (input < 0 || input > 5) {
                System.out.println(ANSI_RED + "Fel vid inmatning. Försök igen\n");

            } else {
                loadSelectedCourseAlternatives("Matematik");
            }
        } else {
            System.out.println(ANSI_RESET + "** Matematik **\n");

            printCourseTeacher(courseList, "Matematik");
            printCourseStudents(courseList, "Matematik");

            System.out.println(ANSI_RESET + """
                    Skriv en siffra för att välja välja vad du vill göra:
                                        
                    "1" - Backa.
                    "2" - Avsluta.""");
        }
    }

    public void loadSelectedCourseAlternatives(String courseName) {
        if (input == 1) {

            System.out.println(ANSI_RESET + "Vilken elev vill du ta bort från kursen?");

            String studentToRemove = scanner.nextLine();
            removeStudentFromCourse(courseList, studentToRemove, courseName);

        } else if (input == 2) {

            System.out.println(ANSI_RESET + "Vilken elev vill du lägga till kursen?");

            String studentToAdd = scanner.nextLine();
            addStudentToCourse(courseList, studentToAdd, courseName);

        } else if (input == 3) {

            System.out.println("Vilken Lärare vill du ta bort från kursen?");

            String teacherToRemove = scanner.nextLine();
            removeTeacherFromCourse(courseList, teacherToRemove, courseName);

        } else if (input == 4) {

            System.out.println("Vilken lärare vill du lägga till kursen?");

            String teacherToAdd = scanner.nextLine();
            addTeacherToCourse(courseList, teacherToAdd, courseName);

        } else if (input == 5) {
//            input = 20;
            seeCourses();

        } else if (input == 0) {
            System.out.println(ANSI_RED + "Systemet avslutas");
            System.exit(0);
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
        if (input == 1) {
            seeCourses();

        } else if (input == 2) {
            seeTeachers(teacherList);

        } else if (input == 3) {
            seeStudents(studentList);

        } else if (input == 0) {
            System.out.println(ANSI_RED + "Systemet avslutas");
            System.exit(0);
        }
    }

    public void removeStudentFromCourse(List<Course> courseList, String studentToRemove, String courseName) {

        studentToRemove = studentToRemove.trim();

        // Söker efter given kurs i kurslistan.
        // När kursen hittas kommer vald elev att tas bort från vald kurs om den finns i kurslistan

        for (Course course : courseList) {
            if (Objects.equals(courseName, course.getName())) {

                if (course.getStudentList().size() > 0) {

                    for (int j = 0; j < course.getStudentList().size(); j++) {
                        if (course.getStudentList().get(j).getName().equalsIgnoreCase(studentToRemove)) {
                            course.getStudentList().remove(course.getStudentList().get(j));
                            System.out.println(ANSI_RED + studentToRemove + " togs bort ifrån kursen " + courseName + "!\n");
                            break;
                        }
                    }

                } else {
                    System.out.println(ANSI_RED + "För tillfället läser inte några elever " + courseName);
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

    public void addStudentToCourse(List<Course> courseList, String studentToAdd, String courseName) {

        boolean found = false;
        studentToAdd = studentToAdd.trim();

        // Söker efter given kurs i kurslistan.
        // När kursen hittas kollar metoden upp om studenten redan finns med i listan
        // Om studenten redan finns händer inget, annars läggs den till i kurslistan

        for (Course course : courseList) {
            if (Objects.equals(courseName, course.getName())) {

                if (course.getStudentList().size() > 0) {

                    for (int j = 0; j < course.getStudentList().size(); j++) {
                        if (course.getStudentList().get(j).getName().equalsIgnoreCase(studentToAdd)) {
                            System.out.println(ANSI_RED + studentToAdd + " läser redan " + courseName + "!\n");

                        } else {
                            for (Student student : studentList) {
                                if (student.getName().equalsIgnoreCase(studentToAdd)) {
                                    course.getStudentList().add(student);
                                    System.out.println(ANSI_GREEN + studentToAdd + " lades till i kursen " + courseName + "!\n");
                                    j = course.getStudentList().size();
                                    break;
                                }
                            }
                        }
                    }
                    break;


                } else {
                    for (Student student : studentList) {
                        if (student.getName().equalsIgnoreCase(studentToAdd)) {
                            course.getStudentList().add(student);
                            System.out.println(ANSI_GREEN + studentToAdd + " lades till i kursen " + courseName + "!\n");
                        } else {
                            System.out.println(ANSI_RED + "Hittade ingen elev med namnet " + studentToAdd);
                        }
                        break;
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

    public void removeTeacherFromCourse(List<Course> courseList, String teacherToRemove, String courseName) {

        teacherToRemove = teacherToRemove.trim();

        // Söker efter given kurs i kurslistan.
        // När kursen hittas kommer vald lärare att tas bort från vald kurs om den finns i tillagd till kursen

        for (Course course : courseList) {
            if (Objects.equals(courseName, course.getName())) {

                try {
                    if (course.getTeacher().getName().equalsIgnoreCase(teacherToRemove)) {
                        course.setTeacher(null);
                        System.out.println(ANSI_RED + teacherToRemove + " togs bort ifrån kursen " + courseName + "!\n");
                        break;
                    }

                } catch (NullPointerException e) {
                    System.out.println(ANSI_RED + "För tillfället undervisar ingen lärare kursen " + courseName);
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

    public void addTeacherToCourse(List<Course> courseList, String teacherToAdd, String courseName) {
        teacherToAdd = teacherToAdd.trim();

        // Söker efter given kurs i kurslistan.
        // När kursen hittas kollar metoden upp om studenten redan finns med i listan
        // Om studenten redan finns händer inget, annars läggs den till i kurslistan

        for (Course course : courseList) {
            if (Objects.equals(courseName, course.getName())) {

                if (course.getTeacher() != null) {

                    System.out.println(ANSI_RED + course.getTeacher().getName() + " Undervisar redan " + courseName + "!\n");

                } else if (course.getTeacher() == null) {
                    for (Teacher teacher : teacherList) {
                        if (teacher.getName().equalsIgnoreCase(teacherToAdd)) {
                            course.setTeacher(teacher);
                            System.out.println(course.getTeacher().getName());
                            System.out.println(course.getTeacher().getCourses());
                            System.out.println(teacherList.get(0).getCourses());
                            System.out.println(ANSI_GREEN + teacherToAdd + " lades till som lärare i kursen " + courseName + "!\n");
                            break;
                        }
                    }
                } else {
                    System.out.println("Kunde inte hitta en lärare med namnet " + teacherToAdd);
                    break;
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

    public void printCourseStudents(List<Course> courseList, String courseName) {

        courseName = courseName.trim();

        for (Course course : courseList) {
            if (course.getName().equalsIgnoreCase(courseName)) {

                if (course.getStudentList().size() == 0) {
                    System.out.println(ANSI_RED + "För tillfället läser inte några elever " + courseName + ".");

                } else {
                    for (int j = 0; j < course.getStudentList().size(); j++) {
                        System.out.println(course.getStudentList().get(j).getName());
                    }
                }
                System.out.println();
            }
        }
    }

    public void printCourseTeacher(List<Course> courseList, String courseName) {
        for (Course course : courseList) {
            if (course.getName().equalsIgnoreCase(courseName)) {

                try {
                    if (course.getTeacher().getName() == null) {
                        //catch

                    } else {
                        System.out.println(course.getTeacher().getName());
                        break;
                    }

                } catch (NullPointerException e) {
                    System.out.println(ANSI_RED + "Det är för tillfället ingen lärare som undervisar i kursen " + courseName + ".");
                }
            }
        }
    }

    public void printStudentCourseList() {
    }

    public void printTeacherCourseList() {
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";


    public static void main(String[] args) {
        new SchoolSystem();

    }
}
