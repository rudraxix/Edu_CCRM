package edu.ccrm.domain;

import java.time.LocalDate;

public abstract class Person {
    protected String id;
    protected String fullName;
    protected String email;
    protected LocalDate dob;
    
    public Person(String id, String fullName, String email, LocalDate dob){
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.dob = dob;
    }

    public abstract void printProfile();
    
    
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

}
