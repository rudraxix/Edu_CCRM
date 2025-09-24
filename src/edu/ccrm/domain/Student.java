package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
	private String regNo;
    private boolean active = true;
    private List<Course> enrolledCourses = new ArrayList<>();
    
    public Student(String id, String regNo, String fullName, String email, LocalDate dob){
        super(id, fullName, email, dob);
        this.regNo = regNo;
    }
    public void printProfile(){
        System.out.println("Student: " + fullName + " (" + regNo + ")");
        System.out.println("Email: " + email);
        System.out.println("Status: " + (active ? "Active" : "Inactive"));
        if(!enrolledCourses.isEmpty()){
            System.out.println("Enrolled Courses:");
            enrolledCourses.forEach(c -> System.out.println("  " + c));
        }
    }
    public void enrollCourse(Course c){ enrolledCourses.add(c); }
    public void unenrollCourse(Course c){ enrolledCourses.remove(c); }
    public void deactivate(){ this.active = false; }
    public List<Course> getEnrolledCourses(){ return enrolledCourses; }

}
