/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class QueueExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Student> queue = new LinkedList<>();
        Set<String> studentIds = new HashSet<>(); // Set để lưu ID của sinh viên

        try {
            System.out.print("Enter the number of students to manage: ");
            int numberOfStudents = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Input student details
            for (int i = 0; i < numberOfStudents; i++) {
                System.out.println("\nEnter details for Student " + (i + 1) + ":");
                String id;

                // Ensure ID is numeric and unique
                while (true) {
                    System.out.print("Student ID (numeric): ");
                    id = scanner.nextLine();
                    try {
                        Integer.parseInt(id); // Check if ID is numeric
                        if (studentIds.contains(id)) { // Check if ID is unique
                            System.out.println("This ID already exists. Please enter a unique ID.");
                        } else {
                            break; // Exit loop if ID is valid and unique
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numeric Student ID.");
                    }
                }

                String name;
                // Ensure name contains only letters
                while (true) {
                    System.out.print("Student Name: ");
                    name = scanner.nextLine();
                    if (name.matches("[a-zA-Z ]+")) { // Check if name contains only letters
                        break;
                    } else {
                        System.out.println("Invalid name. Name should only contain letters and spaces.");
                    }
                }

                double marks;
                // Ensure marks are between 0 and 10
                while (true) {
                    try {
                        System.out.print("Marks (0-10): ");
                        marks = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        if (marks >= 0 && marks <= 10) {
                            break; // Valid marks
                        } else {
                            System.out.println("Invalid marks. Please enter a value between 0 and 10.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a numeric value between 0 and 10.");
                        scanner.nextLine(); // Clear invalid input
                    }
                }

                Student student = new Student(id, name, marks);
                queue.add(student);
                studentIds.add(id); // Add the ID to the Set
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numeric values where required.");
            scanner.nextLine(); // Clear the scanner buffer
        }

        // Menu for operations
        int choice;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Display all students");
            System.out.println("2. Add a new student");
            System.out.println("3. Delete a student by ID");
            System.out.println("4. Search for a student by ID");
            System.out.println("5. Edit a student's information by ID");
            System.out.println("6. Sort students by marks");
            System.out.println("7. Sort students by ID using Bubble Sort");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("\nAll Students:");
                        display(queue);
                        break;
                    case 2:
                        String newId;
                        while (true) {
                            System.out.print("Enter Student ID (numeric): ");
                            newId = scanner.nextLine();
                            try {
                                Integer.parseInt(newId); // Ensure ID is numeric
                                if (studentIds.contains(newId)) { // Check if ID is unique
                                    System.out.println("This ID already exists. Please enter a unique ID.");
                                } else {
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a numeric Student ID.");
                            }
                        }

                        String newName;
                        while (true) {
                            System.out.print("Enter Student Name: ");
                            newName = scanner.nextLine();
                            if (newName.matches("[a-zA-Z ]+")) { // Check if name contains only letters
                                break;
                            } else {
                                System.out.println("Invalid name. Name should only contain letters and spaces.");
                            }
                        }

                        double newMarks;
                        while (true) {
                            try {
                                System.out.print("Enter Marks (0-10): ");
                                newMarks = scanner.nextDouble();
                                scanner.nextLine(); // Consume newline
                                if (newMarks >= 0 && newMarks <= 10) {
                                    break;
                                } else {
                                    System.out.println("Invalid marks. Please enter a value between 0 and 10.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a numeric value between 0 and 10.");
                                scanner.nextLine(); // Clear invalid input
                            }
                        }

                        queue.add(new Student(newId, newName, newMarks));
                        studentIds.add(newId); // Add the ID to the Set
                        System.out.println("Student added successfully.");
                        break;
                    case 3:
                        System.out.print("Enter Student ID to delete: ");
                        String deleteId = scanner.nextLine();
                        if (delete(queue, studentIds, deleteId)) {
                            System.out.println("Student deleted successfully.");
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter Student ID to search: ");
                        String searchId = scanner.nextLine();
                        Student foundStudent = search(queue, searchId);
                        if (foundStudent != null) {
                            System.out.println("Student found:");
                            foundStudent.display();
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter Student ID to edit: ");
                        String editId = scanner.nextLine();
                        Student studentToEdit = search(queue, editId);
                        if (studentToEdit != null) {
                            System.out.print("Enter new Student Name: ");
                            String updatedName = scanner.nextLine();

                            double updatedMarks;
                            while (true) {
                                try {
                                    System.out.print("Enter new Marks (0-10): ");
                                    updatedMarks = scanner.nextDouble();
                                    scanner.nextLine(); // Consume newline
                                    if (updatedMarks >= 0 && updatedMarks <= 10) {
                                        break;
                                    } else {
                                        System.out.println("Invalid marks. Please enter a value between 0 and 10.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a numeric value between 0 and 10.");
                                    scanner.nextLine(); // Clear invalid input
                                }
                            }

                            studentToEdit.setName(updatedName);
                            studentToEdit.setMarks(updatedMarks);
                            System.out.println("Student information updated successfully.");
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 6:
                        System.out.println("Sorting students by marks...");
                        sortByMarks(queue);
                        System.out.println("Students sorted successfully.");
                        break;
                    case 7:
                        System.out.println("Sorting students by ID using Bubble Sort...");
                        bubbleSortById(queue);
                        System.out.println("Students sorted by ID successfully.");
                        break;
                    case 8:
                        System.out.println("Exiting program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                scanner.nextLine(); // Clear the scanner buffer
                choice = -1; // Reset choice to continue loop
            }
        } while (choice != 8);

        scanner.close();
    }

    // Display all students
    public static void display(Queue<Student> queue) {
        for (Student student : queue) {
            student.display();
        }
    }

    // Delete a student by ID
    public static boolean delete(Queue<Student> queue, Set<String> studentIds, String id) {
        boolean removed = queue.removeIf(student -> student.getId().equals(id));
        if (removed) {
            studentIds.remove(id); // Remove ID from the Set
        }
        return removed;
    }

    // Search for a student by ID
    public static Student search(Queue<Student> queue, String id) {
        for (Student student : queue) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // Sort students by marks
    public static void sortByMarks(Queue<Student> queue) {
    // Chuyển Queue thành mảng để xử lý sắp xếp
    Student[] studentsArray = queue.toArray(new Student[0]);

    // Bubble Sort để sắp xếp theo Marks
    for (int i = 0; i < studentsArray.length - 1; i++) {
        for (int j = 0; j < studentsArray.length - i - 1; j++) {
            if (studentsArray[j].getMarks() > studentsArray[j + 1].getMarks()) {
                // Hoán đổi vị trí nếu Marks lớn hơn
                Student temp = studentsArray[j];
                studentsArray[j] = studentsArray[j + 1];
                studentsArray[j + 1] = temp;
            }
        }
    }

    // Hiển thị danh sách sau khi sắp xếp
    System.out.println("Students sorted by marks:");
    for (Student student : studentsArray) {
        student.display();
    }
}

    // Bubble sort students by ID
    public static void bubbleSortById(Queue<Student> queue) {
        Student[] studentsArray = queue.toArray(new Student[0]);
        for (int i = 0; i < studentsArray.length - 1; i++) {
            for (int j = 0; j < studentsArray.length - i - 1; j++) {
                if (studentsArray[j].getId().compareTo(studentsArray[j + 1].getId()) > 0) {
                    // Swap
                    Student temp = studentsArray[j];
                    studentsArray[j] = studentsArray[j + 1];
                    studentsArray[j + 1] = temp;
                }
            }
        }
        for (Student student : studentsArray) {
            student.display();
        }
    }
}

class Student {
    private String id;
    private String name;
    private double marks;

    public Student(String id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // Method to calculate rank based on marks
    public String getRank() {
    if (marks >= 0 && marks < 5) {
        return "Fail";
    } else if (marks < 6.5) {
        return "Medium";
    } else if (marks < 7.5) {
        return "Good";
    } else if (marks < 9.0) {
        return "Very Good";
    } else if (marks <= 10) {
        return "Excellent";
    }
    return "Invalid"; // In case marks are out of range
}

    // Display student information with rank
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + getRank());
    }
}
