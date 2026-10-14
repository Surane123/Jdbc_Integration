package com.weintern.jdbc;

import java.sql.*;
import java.util.*;

/** Data access object; all values are bound through PreparedStatement parameters. */
public final class StudentDao {
    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS students (id INT PRIMARY KEY, name VARCHAR(100) NOT NULL, age INT NOT NULL, email VARCHAR(255) NOT NULL UNIQUE)";
        try (Connection c = DatabaseConnection.getConnection(); Statement s = c.createStatement()) { s.executeUpdate(sql); }
    }
    public void insert(Student student) throws SQLException {
        String sql = "INSERT INTO students (id, name, age, email) VALUES (?, ?, ?, ?)";
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            bind(p, student); p.executeUpdate();
        }
    }
    public List<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement p = c.prepareStatement("SELECT id, name, age, email FROM students ORDER BY id"); ResultSet r = p.executeQuery()) {
            while (r.next()) students.add(map(r));
        }
        return students;
    }
    public Optional<Student> findById(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement p = c.prepareStatement("SELECT id, name, age, email FROM students WHERE id = ?")) {
            p.setInt(1, id);
            try (ResultSet r = p.executeQuery()) { return r.next() ? Optional.of(map(r)) : Optional.empty(); }
        }
    }
    public boolean update(Student student) throws SQLException {
        String sql = "UPDATE students SET name = ?, age = ?, email = ? WHERE id = ?";
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, student.getName()); p.setInt(2, student.getAge()); p.setString(3, student.getEmail()); p.setInt(4, student.getId());
            return p.executeUpdate() == 1;
        }
    }
    public boolean delete(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement p = c.prepareStatement("DELETE FROM students WHERE id = ?")) {
            p.setInt(1, id); return p.executeUpdate() == 1;
        }
    }
    private static void bind(PreparedStatement p, Student s) throws SQLException {
        p.setInt(1, s.getId()); p.setString(2, s.getName()); p.setInt(3, s.getAge()); p.setString(4, s.getEmail());
    }
    private static Student map(ResultSet r) throws SQLException { return new Student(r.getInt("id"), r.getString("name"), r.getInt("age"), r.getString("email")); }
}
