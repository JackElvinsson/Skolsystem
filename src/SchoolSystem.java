
import database.DataAccessObject;
import person.Student;
import person.Teacher;

import java.util.List;
import java.util.Scanner;

public class SchoolSystem {

    private final DataAccessObject DAO;
    private final Scanner SCANNER = new Scanner(System.in);
    private int input;
    private int id;
    private String stringInput = "";

    public SchoolSystem() {

        DAO = new DataAccessObject();
        DAO.initiate();

        DAO.addCourse("ENGLISH");
        DAO.addCourse("HISTORY");
        DAO.addCourse("MATH");

//----------------------------------- START -------------------------------------------------

        System.out.println(ANSI_GREEN + "Välkommen till skolsystemet! Skriv en siffra för att gå vidare:\n\n" +
                ANSI_RESET + "\"1\" Skoladministratör.\n\"2\" Elev.\n\"0\" Avsluta.");

        input = Integer.parseInt(SCANNER.nextLine());

        if (input < 0 || input > 2)
            System.out.println(ANSI_RED + "Fel vid inmatning, försök igen\n");

        else {

            id = input;

//------------------------------- STARTING LOOP -----------------------------------------------

            while (input != 0) {

                /**
                 * Alternativ "1" - Administratörsterminalen
                 */

                if (id == 1) {
                    seeAdminTerminal();


                    /**
                     * Alternativ "2" - Elevterminalen
                     */

                } else if (id == 2) {
                    seeStudentTerminal();
                }
            }
        }
//---------------------------- SYSTEM EXIT --------------------------------------------------
        System.out.println(ANSI_RED + "Systemet avslutas");
        System.exit(0);
    }
//----------------------------- METHODS -----------------------------------------------------

    public void seeAdminTerminal() {
        System.out.println(ANSI_RESET + """
                ** ADMINISTRATÖRSTERMINALEN **

                Vad vill du göra?
                Skriv en siffra för att komma till respektive meny:
                                           
                "1" - Se kurser.
                "2" - Se Lärarlista.
                "3" - Se Elevlista.
                "4" - Byt till ELEVTERMINALEN.
                "0" - Avsluta.""");

        try {
            input = Integer.parseInt(SCANNER.nextLine());

            if (input < 0 || input > 4) {
                System.out.println(ANSI_RED + "Fel vid inmatning. Försök igen\n");

            } else {

                while (input != 0) {
                    loadAdminOptions();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Endast siffror tillåtna. Försök igen!\n");
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

        } else if (input == 4) {
            System.out.println(ANSI_RED + "Byter till ** ELEVTERMINALEN **" + ANSI_RESET);
            id = 2;
            seeStudentTerminal();
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

        input = Integer.parseInt(SCANNER.nextLine());

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

        } else if (input == 4 && id == 1) {
            //Backa
            seeAdminTerminal();

        } else if (input == 4 && id == 2) {
            //Backa
            seeStudentTerminal();

        } else if (input == 0) {
            System.out.println(ANSI_RED + "Systemet avslutas");
            System.exit(0);
        }
    }

    public void seeEnglish() {

        if (id == 1) {
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


            input = Integer.parseInt(SCANNER.nextLine());

            if (input < 0 || input > 5) {
                System.out.println(ANSI_RED + "Fel vid inmatning. Försök igen\n");

            } else {
                loadSelectedCourseAlternatives("Engelska");
            }
        } else {
            System.out.println(ANSI_RESET + "** Engelska **\n");

            System.out.println(ANSI_RESET + "Lärare: ");
            DAO.printCourseTeacher("Engelska");

            System.out.println(ANSI_RESET + "\nElever: ");
            printCourseStudents("Engelska");

            System.out.println(ANSI_RESET + """
                    Skriv en siffra för att välja välja vad du vill göra:
                                        
                    "1" - Backa.
                    "0" - Avsluta.""");

            input = Integer.parseInt(SCANNER.nextLine());

            if (input < 0 || input > 2) {
                System.out.println(ANSI_RED + "Fel vid inmatning. Försök igen\n");
            } else {
                loadSelectedCourseAlternatives("Engelska");
            }

        }
    }

    public void seeHistory() {
        if (id == 1) {
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


            input = Integer.parseInt(SCANNER.nextLine());

            if (input < 0 || input > 5) {
                System.out.println(ANSI_RED + "Fel vid inmatning. Försök igen\n");

            } else {
                loadSelectedCourseAlternatives("Historia");
            }
        } else {
            System.out.println(ANSI_RESET + "** Historia **\n");

            System.out.println(ANSI_RESET + "Lärare: ");
            DAO.printCourseTeacher("Historia");

            System.out.println(ANSI_RESET + "\nElever: ");
            printCourseStudents("Historia");

            System.out.println(ANSI_RESET + """
                    Skriv en siffra för att välja välja vad du vill göra:
                                        
                    "1" - Backa.
                    "0" - Avsluta.""");

            input = Integer.parseInt(SCANNER.nextLine());

            if (input < 0 || input > 2) {
                System.out.println(ANSI_RED + "Fel vid inmatning. Försök igen\n");
            } else {
                loadSelectedCourseAlternatives("Historia");
            }

        }
    }

    public void seeMath() {
        if (id == 1) {
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


            input = Integer.parseInt(SCANNER.nextLine());

            if (input < 0 || input > 5) {
                System.out.println(ANSI_RED + "Fel vid inmatning. Försök igen\n");

            } else {
                loadSelectedCourseAlternatives("Matematik");
            }
        } else {
            System.out.println(ANSI_RESET + "** Matematik **\n");

            System.out.println(ANSI_RESET + "Lärare: ");
            DAO.printCourseTeacher("Matematik");

            System.out.println(ANSI_RESET + "\nElever: ");
            printCourseStudents("Matematik");

            System.out.println(ANSI_RESET + """
                    Skriv en siffra för att välja välja vad du vill göra:
                                        
                    "1" - Backa.
                    "0" - Avsluta.""");

            input = Integer.parseInt(SCANNER.nextLine());

            if (input < 0 || input > 2) {
                System.out.println(ANSI_RED + "Fel vid inmatning. Försök igen\n");
            } else {
                loadSelectedCourseAlternatives("Matematik");
            }
        }
    }

    public void loadSelectedCourseAlternatives(String courseName) {
        if (id == 1 && input == 1) {

            System.out.println(ANSI_RESET + "Vilken elev vill du ta bort från kursen?");

            String studentToRemove = SCANNER.nextLine();
            DAO.removeStudentFromCourse(studentToRemove, courseName);
            returnToSeeCourse(courseName);

        } else if (id == 1 && input == 2) {

            System.out.println(ANSI_RESET + "Vilken elev vill du lägga till kursen?");

            String studentToAdd = SCANNER.nextLine();
            DAO.addStudentToCourse(studentToAdd, courseName);
            returnToSeeCourse(courseName);

        } else if (id == 1 && input == 3) {

            System.out.println("Vilken Lärare vill du ta bort från kursen?");

            String teacherToRemove = SCANNER.nextLine();
            DAO.removeTeacherFromCourse(teacherToRemove, courseName);
            returnToSeeCourse(courseName);

        } else if (id == 1 && input == 4) {

            System.out.println("Vilken lärare vill du lägga till kursen?");

            String teacherToAdd = SCANNER.nextLine();
            DAO.addTeacherToCourse(teacherToAdd, courseName);
            returnToSeeCourse(courseName);

        } else if (id == 1 && input == 5 || id == 2 && input == 1) {
//            input = 20;
            seeCourses();

        } else if (id == 1 && input == 0 || id == 2 && input == 0) {
            System.out.println(ANSI_RED + "Systemet avslutas");
            System.exit(0);
        }
    }

    public void seeTeachers(List<Teacher> teacherList) {

        System.out.println("** Lärarlista **\n");

        System.out.println("Skriv in ett" + ANSI_RED + " namn " + ANSI_RESET + "från listan för att se mer information\n");

        DAO.printTeachers();

        System.out.println("\"1\" - Backa.");
        System.out.println("\"0\" - Avsluta.");

        stringInput = SCANNER.nextLine();

        if (stringInput.equals("1")) {
            if (id == 1) {
                seeAdminTerminal();
            } else if (id == 2) {
                seeStudentTerminal();
            }
        } else if (stringInput.equals("0")) {
            System.out.println("Stänger program");
            System.exit(0);
        } else {
            printTeacherInformation();
        }
    }

    public void seeStudents(List<Student> studentList) {

        System.out.println("** Elevlista **\n");

        System.out.println("Skriv in ett" + ANSI_RED + " namn " + ANSI_RESET + "från listan för att se mer information\n");

        DAO.printStudents();

        System.out.println("\"1\" - Backa.");
        System.out.println("\"0\" - Avsluta.");

        stringInput = SCANNER.nextLine();

        if (stringInput.equals("1")) {
            if (id == 1) {
                seeAdminTerminal();
            } else if (id == 2) {
                seeStudentTerminal();
            }
        } else if (stringInput.equals("0")) {
            System.out.println("Stänger program");
            System.exit(0);
        } else {
            printStudentInformation();
        }
    }

    public void printStudentInformation() {

        Student student = DAO.getStudent(stringInput);

        if (stringInput.equalsIgnoreCase(student.getName())) {
            System.out.println("*** Information om " + student.getName() + " ***\n");
            System.out.println("Namn: " + student.getName() + "\nID: " + student.getPID() + "\n");
            System.out.println("Aktiva kurser:");
            printStudentCourseList(student.getName());


        } else {
            System.out.println("Finns ingen information om denna elev\n");
        }

        System.out.println("Välj en siffra för att backa eller avsluta\n");
        System.out.println("\"1\" - Backa\n\"0\" - Avsluta");

        input = Integer.parseInt(SCANNER.nextLine());

        if (input == 1) {
            seeStudents(DAO.getStudentList());
        } else if (input == 0) {

            System.out.println(ANSI_RED + "Systemet avslutas");
            System.exit(0);
        }
    }

    public void printTeacherInformation() {

        Teacher teacher = DAO.getTeacher(stringInput);

        if (DAO.getTeacher(stringInput) != null) {
            System.out.println("*** Information om " + teacher.getName() + " ***\n");
            System.out.println("Namn: " + teacher.getName() + "\nID: " + teacher.getPID() + "\n");

            System.out.println("Undervisar i: ");
            printTeacherCourseList(teacher.getName());

        } else {
            System.out.println("Finns ingen information om denna lärare\n");
        }
        System.out.println("Välj en siffra för att backa eller avsluta:\n");
        System.out.println("\"1\" - Backa\n\"0\" - Avsluta");

        input = Integer.parseInt(SCANNER.nextLine());

        if (input == 1) {
            seeTeachers(DAO.getTeacherList());
        } else if (input == 0) {

            System.out.println(ANSI_RED + "Systemet avslutas");
            System.exit(0);
        }
    }

    public void seeStudentTerminal() {
        System.out.println("""
                ** ELEVTERMINALEN **
                                                
                Vad vill du göra?
                Skriv en siffra för att komma till respektive meny
                                                
                "1" - Se Kurser.
                "2" - Se Lärarlista.
                "3" - Se Elevlista.
                "4" - Byt till ADMINISTRATÖRSTERMINALEN.
                "0" - Avsluta.""");

        input = Integer.parseInt(SCANNER.nextLine());

        if (input < 0 || input > 4) {
            System.out.println(ANSI_RED + "Fel vid inmatning. Försök igen\n");

        } else {

            while (input != 0) {
                loadStudentOptions();
            }
        }
    }

    public void loadStudentOptions() {
        if (input == 1) {
            seeCourses();
            input = 20;

        } else if (input == 2) {
            seeTeachers(DAO.getTeacherList());
            input = 20;

        } else if (input == 3) {
            seeStudents(DAO.getStudentList());
            input = 20;

        } else if (input == 4) {
            System.out.println(ANSI_RED + "Byter till ** ADMINISTRATÖRSTERMINALEN **" + ANSI_RESET);
            id = 1;
            seeAdminTerminal();
            input = 20;

        } else if (input == 0) {
            System.out.println(ANSI_RED + "Systemet avslutas");
            System.exit(0);
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
    }

    public void printStudentCourseList(String student) {
        var courses = DAO.getStudentCourses(student);
        for (String course : courses) {
            System.out.println(course);
        }
        System.out.println();

    }

    public void printTeacherCourseList(String teacher) {
        var courses = DAO.getTeacherCourses(teacher);
        for (String course : courses) {
            System.out.println(course);
        }
        System.out.println();
    }

    public void returnToSeeCourse(String courseName) {
        if (courseName.equals("Engelska")) {
            seeEnglish();
        } else if (courseName.equals("Historia")) {
            seeHistory();
        } else {
            seeMath();
        }
    }

    public boolean isInputNumeric(String input) {
        boolean isValid = true;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isAlphabetic(input.charAt(i))) {
                isValid = false;
            }
        }
        return isValid;
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";


    public static void main(String[] args) {
        new SchoolSystem();

    }
}
