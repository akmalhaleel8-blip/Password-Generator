# Password Generator

## 1. Project Overview

The Password Generator is a Java-based desktop application that generates random passwords based on user-selected preferences. It provides a simple graphical user interface (GUI) that allows users to choose password length and select different character types.

The application is developed using Java Swing in Eclipse IDE. MySQL is used to store password generation history, including password length, selected character types, and the generation timestamp.

## 2. Objectives

- To generate random passwords of different lengths.
- To allow users to select uppercase letters, lowercase letters, numbers, and special characters.
- To provide a simple and user-friendly interface.
- To allow users to copy generated passwords to the clipboard.
- To store password generation settings in a MySQL database.
- To understand Java GUI development and database connectivity using JDBC.

## 3. Technologies Used

- **Programming Language:** Java
- **GUI Framework:** Java Swing
- **IDE:** Eclipse IDE
- **Java Version:** JDK 8
- **Database:** MySQL 8.0
- **Database Connectivity:** JDBC
- **JDBC Driver:** MySQL Connector/J 8.0.33

## 4. Features

### Password Generation
Generates random passwords according to the selected length and character types.

### Password Length Selection
Allows users to select the desired password length.

### Character Selection
Users can choose from:
- Uppercase letters (A–Z)
- Lowercase letters (a–z)
- Numbers (0–9)
- Special characters (!@#$)

### Copy Password
Allows users to copy the generated password to the clipboard.

### Clear Options
Clears the generated password and resets the input options.

### MySQL Database Connectivity
Stores password generation history, including password length, selected character types, and generation date and time.

**Security note:** The database stores generation settings rather than the actual generated passwords.

## 5. Software Requirements

- Windows operating system
- Eclipse IDE for Java Developers
- JDK 8
- MySQL Server 8.0
- MySQL Workbench
- MySQL Connector/J 8.0.33

## 6. Project Structure

```text
PasswordGenerator/
├── src/
│   └── passwordgenerator/
│       ├── PasswordGeneratorApp.java
│       ├── DBConnectionTest.java
│       └── HistoryDAO.java
├── Referenced Libraries/
│   └── mysql-connector-j-8.0.33.jar
└── README.md
```

## 7. Database Setup

Open MySQL Workbench and execute the following SQL commands:

```sql
CREATE DATABASE IF NOT EXISTS password_generator;

USE password_generator;

CREATE TABLE IF NOT EXISTS generation_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    password_length INT NOT NULL,
    uppercase_selected BOOLEAN NOT NULL,
    lowercase_selected BOOLEAN NOT NULL,
    numbers_selected BOOLEAN NOT NULL,
    symbols_selected BOOLEAN NOT NULL,
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

This creates the database and the table used to store password generation history.

## 8. Installation and Configuration

### Step 1: Install Java
Install JDK 8 and configure it in Eclipse.

### Step 2: Install MySQL
Install MySQL Server and remember the username and password configured during installation.

### Step 3: Configure Eclipse
Create a Java project named `PasswordGenerator`.

### Step 4: Add MySQL Connector/J
Right-click the project and select:

`Build Path → Configure Build Path → Libraries → Add External JARs`

Select the `mysql-connector-j-8.0.33.jar` file and click **Apply and Close**.

### Step 5: Create the Database
Execute the SQL commands provided in the Database Setup section.

### Step 6: Configure Database Credentials
Open `HistoryDAO.java` and `DBConnectionTest.java`, where applicable, and enter the correct MySQL connection details.

Do not share your database password publicly.

## 9. How to Run the Project

1. Open Eclipse.
2. Open the `PasswordGenerator` project.
3. Verify that the MySQL Connector/J JAR is included in the Build Path.
4. Ensure that the MySQL Server is running.
5. Run `DBConnectionTest.java` to check the database connection.
6. Run `PasswordGeneratorApp.java` using **Run As → Java Application**.
7. Select the password length and desired character types.
8. Click **Generate Password**.
9. Use **Copy Password** to copy the result or **Clear** to reset the form.

## 10. Database Verification

To view saved password generation history, execute the following query in MySQL Workbench:

```sql
USE password_generator;

SELECT * FROM generation_history;
```

Each successful generation should create a record when database saving is connected correctly.

## 11. Testing

The following tests can be performed:

- Generate a password using all character types.
- Generate a password using uppercase letters only.
- Generate a password using numbers and special characters.
- Verify the generated password length.
- Test the Copy Password button.
- Test the Clear button.
- Verify the warning when no character type is selected.
- Check whether generation history is saved in MySQL.

## 12. Advantages

- Easy to use.
- Generates passwords quickly.
- Supports different password lengths and character types.
- Provides clipboard functionality.
- Demonstrates Java Swing and JDBC integration.
- Stores generation history for basic record keeping.

## 13. Limitations

- The application requires Java to run.
- Database history requires a working MySQL connection.
- The current version does not provide user authentication.
- The database does not store generated passwords.
- The application is intended as an educational project.

## 14. Future Enhancements

- Add user login and authentication.
- Add password strength evaluation.
- Add a configurable character set.
- Improve the interface design.
- Add reports for generation history.
- Provide an option to export generation history.
- Add stronger security controls and improved error handling.

## 15. Conclusion

The Password Generator project demonstrates the development of a Java Swing desktop application and its integration with a MySQL database using JDBC. It helps users generate random passwords according to their preferences and demonstrates fundamental concepts of GUI programming, database connectivity, and data management.

The project can be extended with additional security features and improved functionality in future versions.
