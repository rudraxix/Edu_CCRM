package edu.ccrm.exceptions;

public class MaxCreditLimitExceededException extends Exception{
	public MaxCreditLimitExceededException() {
        super("Enrollment exceeds maximum allowed credits.");
    }

}
