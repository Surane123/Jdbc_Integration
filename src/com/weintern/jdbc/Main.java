package com.weintern.jdbc;

import java.sql.SQLException;

/** Small executable CRUD demonstration. Use a dedicated demo database. */
public final class Main {
    public static void main(String[] args) {
        StudentDao dao = new StudentDao();
        try {
            dao.createTable();
            // The demo row is inserted only when absent, so rerunning the example is safe.
            Student demo = new Student(1, "Asha Rao", 21, "asha.rao@example.com");
            if (!dao.findById(demo.getId()).isPresent()) dao.insert(demo);
            System.out.println("After insert/select: " + dao.findAll());
            dao.update(new Student(1, "Asha Rao", 22, "asha.rao@example.com"));
            System.out.println("After update: " + dao.findById(1).orElse(null));
            dao.delete(1);
            System.out.println("After delete: " + dao.findAll());
        } catch (SQLException ex) {
            System.err.println("Database operation failed: " + ex.getMessage());
            System.err.println("Check that MySQL is running, the schema exists, and DB_URL/DB_USER/DB_PASSWORD are correct.");
        }
    }
}
