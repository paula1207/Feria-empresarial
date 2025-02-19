# Feria Empresarial Application

Feria Empresarial is a console-based Java application designed to manage an entrepreneurial fair. It allows users to register and manage companies, stands, visitors, and visits, while also providing reporting and interaction functionalities.

## Getting Started

This project is built using **Java** and managed with **Maven** for dependency management. It supports **JUnit 5** and **Mockito** for unit testing.

### Prerequisites

Before running the application, ensure you have the following installed:

- **Java 11+** (JDK)
- **Maven**
- **Visual Studio Code** (or another Java-supported IDE)

### Folder Structure

The project follows a Maven-based structure:

```
Feria-empresarial/
 │-- src/
 │ ├── main/
 │ │ ├── java/ # Java source files
 │ │ │ ├── edu/ean/feriaempresarial/
 │ │ │ │ ├── model/ # Business logic and entities
 │ │ │ │ ├── views/ # Console UI screens
 │ ├── test/
 │ │ ├── java/ # Unit tests
 │-- pom.xml # Maven configuration file
 │-- README.md # Project documentation
```

### Running the Application

To build and run the project:

```sh
mvn clean package
java -jar target/feriaempresarial-1.0-SNAPSHOT.jar
```

Alternatively, you can run it using VS Code's Java Projects panel.

### Dependency Management

This project uses Maven for managing dependencies. The key dependencies include:

    JUnit 5 – Used for unit testing
    Mockito – Used for mocking dependencies in tests

To install dependencies, ensure you have Maven installed and run:

```sh
mvn clean install
```

### Running Tests

Unit tests are written using JUnit 5 and Mockito. To execute tests, run:

```sh
mvn test
```

You can also check the test coverage with:
```sh
mvn jacoco:report
```

### Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m "Add new feature"`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.


