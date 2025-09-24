package edu.ccrm.domain;

import java.util.Objects;

public class Course {
	private final String code;
    private String title;
    private int credits;
    private String instructor;
    private Semester semester;
    private String department;

    public Course(String code, String title, int credits, String instructor,
                  Semester semester, String department){
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.instructor = instructor;
        this.semester = semester;
        this.department = department;
    }

    @Override
    public String toString(){
        return code + " - " + title + " | " + credits + " Credits | " + instructor;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return code.equals(course.code);
    }

    @Override
    public int hashCode(){ return Objects.hash(code); }

    public String getCode(){ return code; }
    public String getInstructor(){ return instructor; }
    public String getDepartment(){ return department; }
    public Semester getSemester(){ return semester; }
    public int getCredits(){ return credits; }

}
