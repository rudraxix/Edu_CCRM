package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String regNo;
    private boolean active = true;
    private List<Enrollment> enrollments = new ArrayList<>();
    
    public Student(String id, String regNo, String fullName, String email, LocalDate dob) {
        super(id, fullName, email, dob);
        this.regNo = regNo;
    }
    
    @Override
    public void printProfile() {
        System.out.println("Student: " + fullName + " (" + regNo + ")");
        System.out.println("Email: " + email);
        System.out.println("Status: " + (active ? "Active" : "Inactive"));
        if (!enrollments.isEmpty()) {
            System.out.println("Enrollments:");
            enrollments.forEach(e -> System.out.println("  " + e));
        }
    }

    // ---- Enrollment handling ----
    public void enrollCourse(Course course) {
        enrollments.add(new Enrollment(this, course, course.getSemester()));
    }
    
    public void unenrollCourse(Course course) {
        enrollments.removeIf(e -> e.getCourse().equals(course));
    }
    
    public void deactivate() { 
        this.active = false; 
    }
    
    public List<Enrollment> getEnrollments() { 
        return enrollments; 
    }
}
