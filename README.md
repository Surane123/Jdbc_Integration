# JDBC Student CRUD Application

A Java 11+ console demonstration of JDBC and the DAO pattern. It creates a MySQL table and demonstrates parameterized INSERT, SELECT, UPDATE, and DELETE operations.

## Requirements

- JDK 11 or newer
- MySQL Server 8.x (or a compatible MySQL server)
- MySQL Connector/J
- Eclipse, IntelliJ IDEA, or a terminal with `javac` and `java` on `PATH`

## Set up the database

Run [`schema.sql`](schema.sql) in MySQL Workbench or the MySQL command line. It creates the `java_development` database and `students` table.

## Configure connection credentials

The application reads these environment variables:

| Variable | Default | Purpose |
| --- | --- | --- |
| `DB_URL` | `jdbc:mysql://localhost:3306/java_development?useSSL=false&serverTimezone=UTC` | JDBC connection URL |
| `DB_USER` | `root` | MySQL username |
| `DB_PASSWORD` | empty | MySQL password |

Set the variables in your shell or in Eclipse under **Run Configurations → Environment**. Do not commit real database passwords.

## Add Connector/J and run in Eclipse

Download Connector/J from the [official MySQL download page](https://dev.mysql.com/downloads/connector/j/). Add its JAR to the project with **Build Path → Configure Build Path → Libraries → Add External JARs…**. Then run `com.weintern.jdbc.Main` as a Java Application.

## Run from a terminal

Set the environment variables first. Then, from this project directory in PowerShell, replace the JAR path with the location on your machine:

```powershell
javac -d out (Get-ChildItem src -Recurse -Filter *.java | ForEach-Object FullName)
java -cp 'out;C:\path\mysql-connector-j.jar' com.weintern.jdbc.Main
```

The demo uses student ID `1`: it inserts the row if missing, updates it, prints it, and deletes it. Use a practice database because it removes that demo row each time it runs.

## Project structure

```text
src/com/weintern/jdbc/
  DatabaseConnection.java  Connection configuration
  Student.java             Row model
  StudentDao.java          Parameterized SQL CRUD operations
  Main.java                CRUD demonstration
schema.sql                 Database and table definition
class-diagram.puml         UML class diagram source
```
