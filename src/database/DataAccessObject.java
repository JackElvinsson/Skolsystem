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
    private ArrayList<Course> courseList = new ArrayList<>();
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Teacher> teacherList = new ArrayList<>();
    private HashSet<Enrollment> enrollmentSet = new HashSet<>();
    private PersonFactory personFactory = new PersonFactory();
    private CourseFactory courseFactory = new CourseFactory();

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

    public Course getCourse(String courseName) {
        for (Course course : courseList) {
            if (course.getName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        System.err.println("Det finns ingen kurs med namnet: '" + courseName + "'.");
        return null;
    }

    public void addCourse(String name) {
        courseList.add(courseFactory.createCourse(name));
    }

    public Teacher getTeacher(String name) {
        for (Teacher teacher : teacherList) {
            if (teacher.getName().equalsIgnoreCase(name)) {
                return teacher;
            }
        }
        System.out.println("Kunde inte hitta en l??rare med namnet: '" + name + "'.");
        return null;
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

    private void enrollStudent(Student student, Course course) {
        enrollmentSet.add(new Enrollment(student.getName(), course.getName()));
    }

    public void removeStudentFromCourse(String studentToRemove, String courseName) {

        for (Enrollment enrollment : enrollmentSet) {
            if(enrollment.getCourse().equalsIgnoreCase(courseName)&& enrollment.getStudent().equalsIgnoreCase(studentToRemove)) {
                enrollmentSet.remove(enrollment);
                System.out.println(ANSI_RED + studentToRemove + " togs bort fr??n kursen: " + courseName + ".\n");
                break;
            } else {
                System.out.println(ANSI_RED +studentToRemove + " L??ser f??r tillf??llet inte kursen: " + courseName);
            }
        }
    }

    public void removeTeacherFromCourse(String teacherToRemove, String courseName) {

        teacherToRemove = teacherToRemove.trim();

        for (Course course : courseList) {
            if (Objects.equals(courseName, course.getName())) {
                try {
                    if (course.getTeacher().getName().equalsIgnoreCase(teacherToRemove)) {
                        course.setTeacher(null);
                        System.out.println(ANSI_RED + "L??rare " + teacherToRemove + " togs bort ifr??n kursen " + courseName + "!\n");
                        break;
                    }
                } catch (NullPointerException e) {
                    System.out.println(ANSI_RED + "F??r tillf??llet undervisar ingen l??rare kursen " + courseName);
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
                        System.out.println(ANSI_RED + studentToAdd + " l??ser redan " + courseName + "!\n");
                    } else {
                        enrollStudent(getStudent(studentToAdd), getCourse(courseName));
                        System.out.println(ANSI_GREEN + studentToAdd + " lades till i kursen " + courseName + "!\n");
                    }
                    break;
                }
                if (enrollmentSet.isEmpty()) {
                    enrollStudent(getStudent(studentToAdd), getCourse(courseName));
                    System.out.println(ANSI_GREEN + studentToAdd + " lades till i kursen " + courseName + "!\n");
                }
            }
        }

    public void addTeacherToCourse(String teacherToAdd, String courseName) {

        teacherToAdd = teacherToAdd.trim();

        for (Course course : courseList) {
            if (Objects.equals(courseName, course.getName())) {
                if (course.getTeacher() != null) {
                    System.out.println(ANSI_RED + course.getTeacher().getName() + " Undervisar redan " + courseName + "!\n");
                } else if (course.getTeacher() == null) {
                    for (Teacher teacher : teacherList) {
                        if (teacher.getName().equalsIgnoreCase(teacherToAdd)) {
                            course.setTeacher(teacher);
                            System.out.println(ANSI_GREEN + teacher.getName() + " lades till som l??rare i kursen " + courseName + "!\n");
                            break;
                        }
                    }
                } else {
                    System.out.println("Kunde inte hitta en l??rare med namnet " + teacherToAdd);
                    break;
                }
            }
        }
    }

    public void printCourseTeacher(String courseName) {
        for (Course course : courseList) {
            if (course.getName().equalsIgnoreCase(courseName)) {
                try {
                    System.out.println(course.getTeacher().getName());
                } catch (NullPointerException e) {
                    System.out.println(ANSI_RED + "Det ??r f??r tillf??llet ingen l??rare som undervisar i kursen " + courseName + ".");
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
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

}
