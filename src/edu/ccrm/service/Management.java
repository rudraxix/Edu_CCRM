package edu.ccrm.service;

import java.util.*;
import java.util.stream.Collectors;

import edu.ccrm.domain.*;
import edu.ccrm.exceptions.*;

public class Management {

    private Map<String, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();
    private final int MAX_CREDITS = 18;

    // ---------------- STUDENT MANAGEMENT ----------------
    public void addStudent(Student s) {
        students.put(s.getId(), s);
    }

    public Collection<Student> listStudents() {
        return students.values();
    }

    public void deactivateStudent(String id) {
        Student s = students.get(id);
        if (s != null) s.deactivate();
    }

    // ---------------- COURSE MANAGEMENT ----------------
    public void addCourse(Course c) {
        courses.put(c.getCode(), c);
    }

    public Collection<Course> listCourses() {
        return courses.values();
    }

    public List<Course> searchCourses(String instructor, String dept, Semester sem) {
        return courses.values().stream()
                .filter(c -> (instructor == null || c.getInstructor().equalsIgnoreCase(instructor)) &&
                             (dept == null || c.getDepartment().equalsIgnoreCase(dept)) &&
                             (sem == null || c.getSemester() == sem))
                .collect(Collectors.toList());
    }

    // ---------------- ENROLLMENT ----------------
    public void enrollStudent(String sid, String courseCode) 
            throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        Student s = students.get(sid);
        Course c = courses.get(courseCode);

        if (s == null || c == null) return;

        boolean alreadyEnrolled = s.getEnrollments().stream()
                                   .anyMatch(e -> e.getCourse().equals(c));
        if (alreadyEnrolled) throw new DuplicateEnrollmentException();

        int currentCredits = s.getEnrollments().stream()
                              .mapToInt(e -> e.getCourse().getCredits())
                              .sum();
        if (currentCredits + c.getCredits() > MAX_CREDITS) 
            throw new MaxCreditLimitExceededException();

        s.enrollCourse(c); // FIXED to match Student
    }

    public void unenrollStudent(String sid, String courseCode) {
        Student s = students.get(sid);
        Course c = courses.get(courseCode);
        if (s != null && c != null) {
            s.unenrollCourse(c); // FIXED
        }
    }

    // ---------------- GRADES ----------------
    public void assignGrade(String sid, String courseCode, Grade grade) {
        Student s = students.get(sid);
        if (s == null) return;

        s.getEnrollments().stream()
         .filter(e -> e.getCourse().getCode().equals(courseCode))
         .findFirst()
         .ifPresent(e -> e.setGrade(grade)); // FIXED
    }
}
