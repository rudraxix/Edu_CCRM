package edu.ccrm.domain;

public class Enrollment {
    private Student student;
    private Course course;
    private Semester semester;
    private Grade grade;

    public Enrollment(Student student, Course course, Semester semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.grade = null; // initially no grade
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public Semester getSemester() { return semester; }

    public Grade getGrade() { return grade; }
    public void setGrade(Grade grade) { this.grade = grade; } // FIXED

    @Override
    public String toString() {
        return course + " | " + semester + " | Grade: " + (grade != null ? grade : "In Progress");
    }
}
