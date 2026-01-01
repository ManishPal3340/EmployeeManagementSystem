package EmpProject;


import EmpProject.ConnectionDB;
import ProjectDB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class EmployeeManagementSystem {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        EmployeeManagementSystem obj = new EmployeeManagementSystem();
        obj.menu();
    }

    public void menu() {
        while (true) {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Create Table");
            System.out.println("2. Add Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Fetch Employee By ID");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createTable();
                    break;
                case 2:
                    addEmployee();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    fetchEmployee();
                    break;
                case 6:
                    System.out.println("Thank you 😊");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice ❌");
            }
        }
    }

    // Create table
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS employee ("
                + "empId INT PRIMARY KEY,"
                + "empName VARCHAR(50),"
                + "empEmail VARCHAR(50),"
                + "empSalary DOUBLE)";

        try (Connection conn = DBConnection.dbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.execute();
            System.out.println("Employee table created successfully ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add employee
    public void addEmployee() {
        String sql = "INSERT INTO employee VALUES (?,?,?,?)";

        try (Connection conn = DBConnection.dbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            sc.nextLine(); // buffer clear

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Employee Email: ");
            String email = sc.next();

            System.out.print("Enter Employee Salary: ");
            double salary = sc.nextDouble();

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setDouble(4, salary);

            int count = ps.executeUpdate();

            if (count > 0) {
                System.out.println("Employee added successfully ✅");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete employee
    public void deleteEmployee() {
        String sql = "DELETE FROM employee WHERE empId=?";

        try (Connection conn = DBConnection.dbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.print("Enter Employee ID to delete: ");
            int id = sc.nextInt();

            ps.setInt(1, id);
            int count = ps.executeUpdate();

            if (count > 0) {
                System.out.println("Employee deleted successfully ✅");
            } else {
                System.out.println("Employee not found ❌");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update employee
    public void updateEmployee() {
        String sql = "UPDATE employee SET empName=?, empEmail=?, empSalary=? WHERE empId=?";

        try (Connection conn = DBConnection.dbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.print("Enter Employee ID to update: ");
            int id = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter New Name: ");
            String name = sc.nextLine();

            System.out.print("Enter New Email: ");
            String email = sc.next();

            System.out.print("Enter New Salary: ");
            double salary = sc.nextDouble();

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setDouble(3, salary);
            ps.setInt(4, id);

            int count = ps.executeUpdate();

            if (count > 0) {
                System.out.println("Employee updated successfully ✅");
            } else {
                System.out.println("Employee not found ❌");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fetch employee
    public void fetchEmployee() {
        String sql = "SELECT * FROM employee WHERE empId=?";

        try (Connection conn = DBConnection.dbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\nEmployee ID: " + rs.getInt("empId"));
                System.out.println("Name: " + rs.getString("empName"));
                System.out.println("Email: " + rs.getString("empEmail"));
                System.out.println("Salary: " + rs.getDouble("empSalary"));
            } else {
                System.out.println("Employee not found ❌");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
