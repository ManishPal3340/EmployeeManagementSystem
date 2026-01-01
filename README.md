# EmployeeManagementSystem
Employee Management System is a Java console-based application developed using JDBC and MySQL.

It allows users to perform CRUD operations such as creating tables, adding, updating, deleting, and fetching employee records from a database.
This project is beginner-friendly and ideal for Java & JDBC interview preparation and academic mini-projects.
-- =========================================
-- Employee Management System Database File
-- =========================================

-- 1. Create Database
CREATE DATABASE IF NOT EXISTS projectdb;

-- 2. Use Database
USE projectdb;

-- 3. Create Employee Table
CREATE TABLE IF NOT EXISTS employee_tbl (
    empId INT PRIMARY KEY,
    empName VARCHAR(50) NOT NULL,
    empEmail VARCHAR(50) UNIQUE,
    empSalary DOUBLE
);

-- 4. Insert Sample Employee Records
INSERT INTO employee_tbl (empId, empName, empEmail, empSalary) VALUES


-- 5. View All Records
SELECT * FROM employee_tbl;
