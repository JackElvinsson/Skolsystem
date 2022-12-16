package database;

import courses.Course;
import courses.CourseFactory;
import person.PersonFactory;
import person.Student;
import person.Teacher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class DataAccessObject {
    private final ArrayList<Course> courseList;
    private final ArrayList<Student> studentList;
    private final ArrayList<Teacher> teacherList;
    private final HashSet<Enrollment> enrollmentSet;
    
    private final PersonFactory personFactory;
    private final CourseFactory courseFactory;

    public DataAccessObject() {

        courseList = new ArrayList<>();
        studentList = new ArrayList<>();
        teacherList = new ArrayList<>();
        enrollmentSet = new HashSet<>();

        personFactory = new PersonFactory();
        courseFactory = new CourseFactory();

    }
    public void initiate() {

        String temp;

        String file = "src/People.txt";
        try(BufferedReader buf = new BufferedReader(new FileReader(file))) {
            while ((temp = buf.readLine()) != null) {
                String[] fileInput = temp.split(",");
                var person = personFactory.createPerson(fileInput[0], fileInput[1], fileInput[2]);
                if (person instanceof Student)
                    studentList.add((Student) person);
                else if (person instanceof Teacher)
                    teacherList.add((Teacher) person);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    /**
     * Används inte?
     */
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
    public Teacher getTeacher(String name) {
        for (Teacher teacher :teacherList) {
            if (teacher.getName().equalsIgnoreCase(name)) {
                return teacher;
            }
        }
        System.out.println("Kunde inte hitta en lärare med namnet: '" + name + "'.");
        return null;
    }

    /**
     * Används inte?
     */
    public void addTeacher(String name, String PID) {
        if (getTeacher(name) == null) {
            teacherList.add((Teacher) personFactory.createPerson("Teacher", name, PID));
            System.out.println(name + " lades till som lärare.");
        } else {
            System.err.println("Det finns redan en lärare  med namnet: '" + name + "'");
        }
    }

    public ArrayList<String> getStudents(String courseName) {
        ArrayList<String> studentList = new ArrayList<>();
        for (Enrollment enrollment : enrollmentSet) {
            if (enrollment.getCourse().equalsIgnoreCase(courseName)) {
                studentList.add(enrollment.getStudent());
            }
        }
        return studentList;
    }

    public ArrayList<String> getStudentCourses(String student) {
        ArrayList<String> courses = new ArrayList<>();
        for (Enrollment enrollment : enrollmentSet) {
            if (enrollment.getStudent().equalsIgnoreCase(student)) {
                courses.add(enrollment.getCourse());
            }
        }
        return courses;
    }

    public ArrayList<String> getTeacherCourses(String teacher) {
        ArrayList<String> teacherCourses = new ArrayList<>();
        for (Course course : courseList) {
            if (course.getTeacher() != null && course.getTeacher().getName().equalsIgnoreCase(teacher)) {
                teacherCourses.add(course.getName());
            }
        }
        return teacherCourses;
    }

    public void enrollStudent(Student student, Course course) {
        enrollmentSet.add(new Enrollment(student.getName(), course.getName()));
    }
    public void removeEnrollment(String name) {
        enrollmentSet.removeIf(enrollment -> enrollment.getStudent().equalsIgnoreCase(name));
    }

    /**
     * Används inte?
     */
    public void removeStudent(String name) {
        studentList.removeIf(student -> student.getName().equalsIgnoreCase(name));
        removeEnrollment(name);
    }

    /**
     * Används inte?
     */
    public void setTeacher(String course, String teacher) {
        getCourse(course).setTeacher(getTeacher(teacher));
    }

    public void removeStudentFromCourse(String studentToRemove, String courseName) {

        for (Enrollment enrollment : enrollmentSet) {
            if(enrollment.getCourse().equalsIgnoreCase(courseName)&& enrollment.getStudent().equalsIgnoreCase(studentToRemove)) {
                enrollmentSet.remove(enrollment);
                System.out.println(ANSI_RED + studentToRemove + " togs bort från kursen: " + courseName + ".\n");
                break;
            } else {
                System.out.println(ANSI_RED +studentToRemove + " Läser för tillfället inte kursen: " + courseName);
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
                        System.out.println(ANSI_RED + "Lärare " + teacherToRemove + " togs bort ifrån kursen " + courseName + "!\n");
                        break;
                    }

                } catch (NullPointerException e) {
                    System.out.println(ANSI_RED + "För tillfället undervisar ingen lärare kursen " + courseName);
                }
            }
        }
    }

    public void addStudentToCourse(String studentToAdd, String courseName) {

        if (getStudent(studentToAdd) == null) {
            System.out.println(ANSI_RED + "Hittade ingen elev med namnet " + studentToAdd);
        } else {

                for (Enrollment enrollment : enrollmentSet) {
                    if(enrollment.getCourse().equalsIgnoreCase(courseName)&& enrollment.getStudent().equalsIgnoreCase(studentToAdd)) {
                        System.out.println(ANSI_RED + studentToAdd + " läser redan " + courseName + "!\n");
                        break;
                    } else {
                        enrollStudent(getStudent(studentToAdd), getCourse(courseName));
                        System.out.println(ANSI_GREEN + studentToAdd + " lades till i kursen " + courseName + "!\n");
                    }
                }
                if (enrollmentSet.isEmpty()) {
                    enrollStudent(getStudent(studentToAdd), getCourse(courseName));
                    System.out.println(ANSI_GREEN + studentToAdd + " lades till i kursen " + courseName + "!\n");
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

                            //System.out.println(course.getTeacher().getName());
                            // System.out.println(course.getTeacher().getCourses());

//                            System.out.println(getTeacherCourses(teacher.getName()));
                            System.out.println(ANSI_GREEN + teacher.getName() + " lades till som lärare i kursen " + courseName + "!\n");
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

    public void printCourseTeacher(String courseName) {
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
            System.out.println(studentList.get(i).getName());
        }
        System.out.println();
    }

    public void printTeachers() {
        for (Teacher teacher : teacherList) {
            System.out.println(teacher.getName());
        }
        System.out.println();
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    /**
     * Används inte?
     */
    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<Teacher> getTeacherList() {
        return teacherList;
    }

    /**
     * Används inte?
     */
    public HashSet<Enrollment> getEnrollments() {
        return enrollmentSet;
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

}
