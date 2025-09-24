package edu.ccrm.exceptions;

public class DuplicateEnrollmentException extends Exception{
	public DuplicateEnrollmentException() {
        super("Student already enrolled in this course.");
    }
}
