package edu.ccrm.cli;

import edu.ccrm.domain.*;
import edu.ccrm.exceptions.DuplicateEnrollmentException;
import edu.ccrm.exceptions.MaxCreditLimitExceededException;
import edu.ccrm.service.Management;

import java.time.LocalDate;
import java.util.Scanner;

public class MainCLI {
    public static void main(String[] args) {
        Management ms = new Management();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== CCRM Management System ===");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Enrollment & Grades");
            System.out.println("4. Import/Export Data");
            System.out.println("5. Backup & Reports");
            System.out.println("6. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> manageStudents(ms, sc);
                case 2 -> manageCourses(ms, sc);
                case 3 -> manageEnrollment(ms, sc);
                case 4 -> manageImportExport(ms, sc);
                case 5 -> manageBackupReports(ms, sc);
                case 6 -> {
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                }
                default -> System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }

    // ------------------ STUDENT MANAGEMENT ------------------
    private static void manageStudents(Management ms, Scanner sc) {
        System.out.println("\n--- Manage Students ---");
        System.out.println("1. Add Student");
        System.out.println("2. List Students");
        System.out.println("3. Deactivate Student");
        System.out.println("4. Print Student Profile");
        System.out.print("Choice: ");
        int choice = sc.nextInt(); sc.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter ID: "); String id = sc.nextLine();
                System.out.print("Enter RegNo: "); String reg = sc.nextLine();
                System.out.print("Enter Full Name: "); String name = sc.nextLine();
                System.out.print("Enter Email: "); String email = sc.nextLine();
                System.out.print("Enter DOB (yyyy-mm-dd): "); LocalDate dob = LocalDate.parse(sc.nextLine());
                ms.addStudent(new Student(id, reg, name, email, dob));
                System.out.println("Student added successfully.");
            }
            case 2 -> {
                System.out.println("Listing Students:");
                ms.listStudents().forEach(Student::printProfile);
            }
            case 3 -> {
                System.out.print("Enter Student ID to deactivate: "); String id = sc.nextLine();
                ms.deactivateStudent(id);
                System.out.println("Student deactivated.");
            }
            case 4 -> {
                System.out.print("Enter Student ID to view profile: "); String id = sc.nextLine();
                ms.listStudents().stream()
                        .filter(s -> s.getId().equals(id))
                        .findFirst()
                        .ifPresent(Student::printProfile);
            }
        }
    }

    // ------------------ COURSE MANAGEMENT ------------------
    private static void manageCourses(Management ms, Scanner sc) {
        System.out.println("\n--- Manage Courses ---");
        System.out.println("1. Add Course");
        System.out.println("2. List Courses");
        System.out.print("Choice: ");
        int choice = sc.nextInt(); sc.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter Code: "); String code = sc.nextLine();
                System.out.print("Enter Title: "); String title = sc.nextLine();
                System.out.print("Enter Credits: "); int credits = sc.nextInt(); sc.nextLine();
                System.out.print("Enter Instructor: "); String instr = sc.nextLine();
                System.out.print("Enter Semester (SPRING/SUMMER/FALL): "); 
                Semester sem = Semester.valueOf(sc.nextLine().toUpperCase());
                System.out.print("Enter Department: "); String dept = sc.nextLine();
                ms.addCourse(new Course(code, title, credits, instr, sem, dept));
                System.out.println("Course added successfully.");
            }
            case 2 -> {
                System.out.println("Listing Courses:");
                ms.listCourses().forEach(System.out::println);
            }
        }
    }

    // ------------------ ENROLLMENT & GRADES ------------------
 // ------------------ ENROLLMENT & GRADES ------------------
    private static void manageEnrollment(Management ms, Scanner sc) {
        System.out.println("\n--- Enrollment & Grades ---");
        System.out.println("1. Enroll Student in Course");
        System.out.println("2. Unenroll Student from Course");
        System.out.println("3. Print Student Transcript");
        System.out.print("Choice: ");
        int choice = sc.nextInt(); sc.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter Student ID: "); 
                String sid = sc.nextLine();
                System.out.print("Enter Course Code: "); 
                String ccode = sc.nextLine();
                try {
                    ms.enrollStudent(sid, ccode);
                    System.out.println("Enrollment successful.");
                } catch (DuplicateEnrollmentException e) {
                    System.out.println("Error: Student is already enrolled in this course.");
                } catch (MaxCreditLimitExceededException e) {
                    System.out.println("Error: Enrollment exceeds maximum allowed credits.");
                }
            }
            case 2 -> {
                System.out.print("Enter Student ID: "); 
                String sid = sc.nextLine();
                System.out.print("Enter Course Code: "); 
                String ccode = sc.nextLine();
                ms.unenrollStudent(sid, ccode);
                System.out.println("Unenrollment done.");
            }
            case 3 -> {
                System.out.print("Enter Student ID: "); 
                String sid = sc.nextLine();
                ms.listStudents().stream()
                        .filter(s -> s.getId().equals(sid))
                        .findFirst()
                        .ifPresentOrElse(
                            Student::printProfile,
                            () -> System.out.println("Student not found.")
                        );
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    // ------------------ IMPORT / EXPORT DATA ------------------
    private static void manageImportExport(Management ms, Scanner sc) {
        System.out.println("\n--- Import/Export Data ---");
        System.out.println("1. Import Students/Courses (placeholder)");
        System.out.println("2. Export Data (placeholder)");
        System.out.print("Choice: ");
        sc.nextLine(); // Just consume input
        System.out.println("Feature not implemented yet (placeholder).");
    }

    // ------------------ BACKUP & REPORTS ------------------
    private static void manageBackupReports(Management ms, Scanner sc) {
        System.out.println("\n--- Backup & Reports ---");
        System.out.println("1. Backup Data (placeholder)");
        System.out.println("2. Reports (placeholder)");
        System.out.print("Choice: ");
        sc.nextLine(); // Just consume input
        System.out.println("Feature not implemented yet (placeholder).");
    }
}
