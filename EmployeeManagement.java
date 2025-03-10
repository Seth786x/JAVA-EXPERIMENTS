package PBLJ;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee implements Serializable {
    private String name;
    private int id;
    private String designation;
    private double salary;

    public Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", designation='" + designation + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class EmployeeManagement {
    private static final String FILENAME = "employees.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        // Try to load existing employees at startup
        List<Employee> savedEmployees = loadEmployees();
        if (!savedEmployees.isEmpty()) {
            employees = savedEmployees;
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Employee ID: ");
                    int id;
                    try {
                        id = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid ID. Please enter a number.");
                        scanner.nextLine(); // Clear the invalid input
                        continue;
                    }
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter Designation: ");
                    String designation = scanner.nextLine();

                    System.out.print("Enter Salary: ");
                    double salary;
                    try {
                        salary = scanner.nextDouble();
                    } catch (Exception e) {
                        System.out.println("Invalid salary. Please enter a number.");
                        scanner.nextLine(); // Clear the invalid input
                        continue;
                    }
                    scanner.nextLine(); // Consume newline

                    employees.add(new Employee(name, id, designation, salary));
                    saveEmployees(employees);
                    break;

                case 2:
                    if (employees.isEmpty()) {
                        System.out.println("No employees to display.");
                    } else {
                        System.out.println("\nEmployee Details:");
                        for (Employee emp : employees) {
                            System.out.println(emp);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }

    private static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(employees);
            System.out.println("Employees saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        File file = new File(FILENAME);

        if (!file.exists()) {
            System.out.println("No saved employees found.");
            return employees;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            employees = (List<Employee>) ois.readObject();
            System.out.println("Employees loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading employees: " + e.getMessage());
            e.printStackTrace();
        }
        return employees;
    }
}