package database;

import courses.Course;
import person.Student;
import person.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataAccessObject {
    public final ArrayList<Course> courseList;
    public final ArrayList<Student> studentList;
    public final ArrayList<Teacher> teacherList;
    private final ArrayList<Enrollment> enrollmentList;
    
    private final PersonFactory2 personFactory;
    private final CourseFactory2 courseFactory;

    public DataAccessObject() {

        courseList = new ArrayList<>();
        studentList = new ArrayList<>();
        teacherList = new ArrayList<>();
        enrollmentList = new ArrayList<>();

        personFactory = new PersonFactory2();
        courseFactory = new CourseFactory2();

    }
    public Student getStudent(String name) {
        for (Student student : studentList) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        System.out.println("Kunde inte hitta en elev med namnet: '" + name + "'.");
        return null;
    }

    public void addStudent(String name, String PID) {
        if (getStudent(name) == null) {
            studentList.add((Student) personFactory.createPerson("Student", name, PID));
            System.out.println(name + " lades till som elev.");
        } else {
            System.err.println("Det finns redan en elev med namnet: '" + name + "'");
        }
    }

    public Course getCourse(String courseName) {
        for (Course course : courseList) {
            if (course.getName().equalsIgnoreCase(courseName)) {
                return  course;
            }
        }
        System.err.println("Det finns ingen kurs med namnet: '" + courseName + "'.");
        return null;
    }

    public void addCourse(String name) {
        courseList.add(courseFactory.createCourse(name));
    }

    public ArrayList<Student> getStudents(String courseName) {
        ArrayList<Student> studentList = new ArrayList<>();
        for (Enrollment enrollment : enrollmentList) {
            if (enrollment.getCourse().getName().equalsIgnoreCase(courseName)) {
                studentList.add(enrollment.getStudent());
            }
        }
        return studentList;
    }

    public ArrayList<Course> getStudentCourses(String student) {
        ArrayList<Course> courses = new ArrayList<>();
        for (Enrollment enrollment : enrollmentList) {
            if (enrollment.getStudent().getName().equalsIgnoreCase(student)) {
                courses.add(enrollment.getCourse());
            }
        }
        return courses;
    }

    public ArrayList<Course> getTeacherCourses(String teacher) {
        ArrayList<Course> teacherCourses = new ArrayList<>();
        for (Course course : courseList) {
            if (course.getTeacher().getName().equalsIgnoreCase(teacher)) {
                teacherCourses.add(course);
            }
        }
        return teacherCourses;

    }

    public void enrollStudent(Student student, Course course) {
        enrollmentList.add(new Enrollment(student, course));
    }
    public void removeEnrollment(String name) {
        enrollmentList.removeIf(enrollment -> enrollment.getStudent().getName().equalsIgnoreCase(name));
    }

    public void removeStudent(String name) {
        studentList.removeIf(student -> student.getName().equalsIgnoreCase(name));
        removeEnrollment(name);
    }

    public void removeStudentFromCourse(String studentToRemove, String courseName) {

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
    }

    public void removeTeacherFromCourse(String teacherToRemove, String courseName) {

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
    }

    public void addStudentToCourse(String studentToAdd, String courseName) {

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
    }

    public void addTeacherToCourse(String teacherToAdd, String courseName) {
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

    public void printCourses() {
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println("\"" + (i + 1) + "\" - " + courseList.get(i).getName());
        }
    }

    public void printStudents() {
        for (int i = 0; !(i >= studentList.size()); i++) {
            System.out.println("\"" + (i + 1) + "\" - " + studentList.get(i).getName() + ".");
        }
    }

    public void printTeachers() {
        for (int i = 0; i < teacherList.size(); i++) {
            System.out.println("\"" + (i + 1) + "\" - " + teacherList.get(i).getName() + ".");
        }
    }

    public ArrayList<Student> getAllStudents() {
        return studentList;
    }

    public ArrayList<Course> getAllCourses() {
        return courseList;
    }

    public ArrayList<Teacher> getAllTeachers() {
        return teacherList;
    }

    public ArrayList<Enrollment> getAllEnrollments() {
        return enrollmentList;
    }
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

}
