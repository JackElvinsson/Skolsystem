package database;

import courses.Course;
import person.Student;
import person.Teacher;

import java.util.ArrayList;

public class DataAccessObject {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Enrollment> enrollments;

    private PersonFactory2 personFactory;
    private CourseFactory2 courseFactory;

    public DataAccessObject() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        enrollments = new ArrayList<>();

        personFactory = new PersonFactory2();
        courseFactory = new CourseFactory2();
    }
    public Student getStudent(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        System.out.println("Couldn't find person with name: '" + name + "'.");
        return null;
    }

    public void addStudent(String name, String PID) {
        if (getStudent(name) == null) {
            students.add((Student) personFactory.createPerson("Student", name, PID));
            System.out.println(name + " added as a student.");
        } else {
            System.err.println("Student with name '" + name + "' already exists.");
        }
    }
    public Course getCourse(String courseName) {
        for (Course course : courses) {
            if (course.getName().equalsIgnoreCase(courseName)) {
                return  course;
            }
        }
        System.err.println("Couldn't find course '" + courseName + "'.");
        return null;
    }

    public void addCourse(String name) {
        courses.add(courseFactory.createCourse(name));
    }

    public ArrayList<Student> getStudents(String courseName) {
        ArrayList<Student> studentList = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourse().getName().equalsIgnoreCase(courseName)) {
                studentList.add(enrollment.getStudent());
            }
        }
        return studentList;
    }
    public ArrayList<Course> getStudentCourses(String student) {
        ArrayList<Course> courses = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().getName().equalsIgnoreCase(student)) {
                courses.add(enrollment.getCourse());
            }
        }
        return courses;
    }

    public ArrayList<Course> getTeacherCourses(String teacher) {
        ArrayList<Course> teacherCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getTeacher().getName().equalsIgnoreCase(teacher)) {
                teacherCourses.add(course);
            }
        }
        return teacherCourses;

    }
    public void enrollStudent(Student student, Course course) {
        enrollments.add(new Enrollment(student, course));
    }
    public void removeEnrollment(String name) {
        enrollments.removeIf(enrollment -> enrollment.getStudent().getName().equalsIgnoreCase(name));
    }

    public void removeStudent(String name) {
        students.removeIf(student -> student.getName().equalsIgnoreCase(name));
        removeEnrollment(name);
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public ArrayList<Course> getAllCourses() {
        return courses;
    }

    public ArrayList<Teacher> getAllTeachers() {
        return teachers;
    }

    public ArrayList<Enrollment> getAllEnrollments() {
        return enrollments;
    }

}
