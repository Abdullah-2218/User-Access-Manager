# User Access Manager

A Java application that manages user accounts and verifies user login credentials.

## Features

- Load user accounts from a file
- Add and remove user accounts
- Verify usernames and passwords
- Encrypt passwords before verification
- Track failed login attempts
- Lock accounts after repeated failed attempts
- Handle errors using custom exceptions

## Technologies Used

- Java
- JUnit
- Git
- GitHub

## Project Structure

- `UserAccount.java` - Represents an individual user account
- `UserAccessManager.java` - Manages user accounts and access verification
- `Main.java` - Runs the application
- `Utilities.java` - Provides password encryption functionality
- Custom exception classes - Handle invalid commands, incorrect passwords, locked accounts, duplicate users, and missing users
- JUnit test classes - Test the functionality of the application

## What I Learned

Through this project, I practiced object-oriented programming, exception handling, file input, collections, password verification, and unit testing in Java.