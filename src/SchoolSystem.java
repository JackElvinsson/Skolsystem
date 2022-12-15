
import database.DataAccessObject;
import person.Student;
import person.Teacher;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SchoolSystem {

    private final DataAccessObject DAO;
    private final Scanner scanner = new Scanner(System.in);
    private int input;
    private int ID;

    public SchoolSystem() {

        DAO = new DataAccessObject();
        DAO.initiate();

        DAO.addCourse("ENGLISH");
        DAO.addCourse("HISTORY");
        DAO.addCourse("MATH");

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
                 * Alternativ "1" - Administratörterminalen
                 */

                if (ID == 1) {
                    seeAdminPanel();


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

    public void seeAdminPanel() {
        System.out.println(ANSI_RESET + """
                ** Administratörterminalen **

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
                loadAdminOptions();
            }
        }
    }

    public void loadAdminOptions() {

        if (input == 1) {
            seeCourses();
            input = 20;

        } else if (input == 2) {
            seeTeachers(DAO.getTeacherList());
            input = 20;

        } else if (input == 3) {
            seeStudents(DAO.getStudentList());
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

        DAO.printCourses();

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
            seeAdminPanel();

        } else if (input == 0) {
            System.out.println(ANSI_RED + "Systemet avslutas");
            System.exit(0);
        }
    }

    public void seeEnglish() {

        if (ID == 1) {
            System.out.println(ANSI_RESET + "** Engelska **\n");

            System.out.println(ANSI_RESET + "Lärare: ");
            DAO.printCourseTeacher("Engelska");

            System.out.println(ANSI_RESET + "\nElever: ");
            printCourseStudents("Engelska");

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

            DAO.printCourseTeacher("Engelska");
            printCourseStudents("Engelska");

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
            DAO.printCourseTeacher("Historia");

            System.out.println(ANSI_RESET + "\nElever: ");
            printCourseStudents("Historia");

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

            DAO.printCourseTeacher("Historia");
            printCourseStudents("Historia");

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
            DAO.printCourseTeacher("Matematik");

            System.out.println(ANSI_RESET + "\nElever: ");
            printCourseStudents("Matematik");

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

            DAO.printCourseTeacher("Matematik");
            printCourseStudents("Matematik");

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
            removeStudentFromCourse(studentToRemove, courseName);

        } else if (input == 2) {

            System.out.println(ANSI_RESET + "Vilken elev vill du lägga till kursen?");

            String studentToAdd = scanner.nextLine();
            addStudentToCourse(studentToAdd, courseName);

        } else if (input == 3) {

            System.out.println("Vilken Lärare vill du ta bort från kursen?");

            String teacherToRemove = scanner.nextLine();
            removeTeacherFromCourse(teacherToRemove, courseName);

        } else if (input == 4) {

            System.out.println("Vilken lärare vill du lägga till kursen?");

            String teacherToAdd = scanner.nextLine();
            addTeacherToCourse(teacherToAdd, courseName);

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

        System.out.println("Skriv in ett namn från listan för att se information\n");

        DAO.printTeachers();

        System.out.println("\"" + (teacherList.size() + 1) + "\" - Backa.");
        System.out.println("\"0\" - Avsluta.");
    }

    public void seeStudents(List<Student> studentList) {

        System.out.println("** Elevlista **\n");

        System.out.println("Skriv en siffra för att välja välja ett namn:\n");

        DAO.printStudents();

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
            seeTeachers(DAO.getTeacherList());

        } else if (input == 3) {
            seeStudents(DAO.getStudentList());

        } else if (input == 0) {
            System.out.println(ANSI_RED + "Systemet avslutas");
            System.exit(0);
        }
    }

    public void removeStudentFromCourse(String studentToRemove, String courseName) {

        DAO.removeStudentFromCourse(studentToRemove, courseName);

        if (courseName.equals("Engelska")) {
            seeEnglish();
        } else if (courseName.equals("Historia")) {
            seeHistory();
        } else {
            seeMath();
        }
    }

    public void addStudentToCourse(String studentToAdd, String courseName) {

        DAO.addStudentToCourse(studentToAdd, courseName);

        if (courseName.equals("Engelska")) {
            seeEnglish();
        } else if (courseName.equals("Historia")) {
            seeHistory();
        } else {
            seeMath();
        }
    }

    public void removeTeacherFromCourse(String teacherToRemove, String courseName) {

        DAO.removeTeacherFromCourse(teacherToRemove, courseName);

        if (courseName.equals("Engelska")) {
            seeEnglish();
        } else if (courseName.equals("Historia")) {
            seeHistory();
        } else {
            seeMath();
        }
    }

    public void addTeacherToCourse(String teacherToAdd, String courseName) {

        DAO.addTeacherToCourse(teacherToAdd,courseName);

        if (courseName.equals("Engelska")) {
            seeEnglish();
        } else if (courseName.equals("Historia")) {
            seeHistory();
        } else {
            seeMath();
        }
    }

    public void printCourseStudents(String courseName) {

        courseName = courseName.trim();

        var courseStudents = DAO.getStudents(courseName);
        if (courseStudents.isEmpty()) {
            System.out.println(ANSI_RED + "För tillfället läser inte några elever " + courseName + ".\n");
        } else {
            for (String student : courseStudents) {
                System.out.println(student);
            }
            System.out.println();
        }

//        courseName = courseName.trim();
//
//        for (Course course : courseList) {
//            if (course.getName().equalsIgnoreCase(courseName)) {
//
//                if (course.getStudentList().size() == 0) {
//                    System.out.println(ANSI_RED + "För tillfället läser inte några elever " + courseName + ".");
//
//                } else {
//                    for (int j = 0; j < course.getStudentList().size(); j++) {
//                        System.out.println(course.getStudentList().get(j).getName());
//                    }
//                }
//                System.out.println();
//            }
//        }
    }


    public void printStudentCourseList(String student) {
        var courses = DAO.getStudentCourses(student);
        for (String course : courses) {
            System.out.println(course);
        }
        System.out.println();

    }

    public void printTeacherCourseList() {
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";


    public static void main(String[] args) {
        new SchoolSystem();

    }
}
