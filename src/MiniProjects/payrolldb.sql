-- PayrollDB Full SQL Schema and Sample Data

-- SESSION 1: Create Database and Employees Table
DROP DATABASE IF EXISTS PayrollDB;
CREATE DATABASE PayrollDB;
USE PayrollDB;

CREATE TABLE Departments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE Employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    department INT,
    salary DECIMAL(10,2),
    joining_date DATE,
    email VARCHAR(150) UNIQUE,
    FOREIGN KEY (department) REFERENCES Departments(id)
);

INSERT INTO Departments (name) VALUES
('HR'),
('Finance'),
('Engineering'),
('Marketing');

INSERT INTO Employees (name, department, salary, joining_date, email) VALUES
('Amit Sharma', 1, 50000, '2023-01-10', 'amit.sharma@example.com'),
('Priya Singh', 2, 65000, '2022-11-05', 'priya.singh@example.com'),
('Rahul Verma', 3, 72000, '2023-03-15', 'rahul.verma@example.com');

-- SESSION 3: Additional Employees for DML
INSERT INTO Employees (name, department, salary, joining_date, email) VALUES
('Sneha Rao', 3, 68000, '2022-06-20', 'sneha.rao@example.com'),
('Karan Mehta', 2, 58000, '2021-09-12', 'karan.mehta@example.com'),
('Riya Das', 1, 48000, '2023-02-18', 'riya.das@example.com'),
('Mohit Jain', 4, 45000, '2022-10-25', 'mohit.jain@example.com'),
('Nikhil Patel', 3, 80000, '2021-12-10', 'nikhil.patel@example.com'),
('Anjali Nair', 4, 52000, '2023-04-08', 'anjali.nair@example.com'),
('Deepak Rao', 2, 60000, '2022-03-30', 'deepak.rao@example.com');

-- Update Salary Example
UPDATE Employees SET salary = 90000 WHERE name = 'Nikhil Patel';

-- Delete Row Example
DELETE FROM Employees WHERE name = 'Mohit Jain';

-- SESSION 5: Projects and Many-to-Many Example
CREATE TABLE Projects (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE EmployeeProjects (
    employee_id INT,
    project_id INT,
    PRIMARY KEY (employee_id, project_id),
    FOREIGN KEY (employee_id) REFERENCES Employees(id),
    FOREIGN KEY (project_id) REFERENCES Projects(id)
);

INSERT INTO Projects (name) VALUES
('Payroll Revamp'),
('Mobile App'),
('AI Automation');

INSERT INTO EmployeeProjects (employee_id, project_id) VALUES
(1, 1),
(2, 1),
(3, 2),
(3, 3),
(5, 2);

-- SESSION 6: Views and Indexes
CREATE VIEW HighEarners AS
SELECT e.*
FROM Employees e
WHERE e.salary > (
    SELECT AVG(salary) FROM Employees WHERE department = e.department
);

CREATE INDEX idx_salary ON Employees(salary);

-- SESSION 7: Transactions Example (Commented for Safety)
-- START TRANSACTION;
-- UPDATE Employees SET salary = salary - 5000 WHERE id = 1;
-- UPDATE Employees SET salary = salary + 5000 WHERE id = 2;
-- COMMIT;

