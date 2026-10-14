package com.weintern.jdbc;

/** Simple row model used by the JDBC DAO example. */
public final class Student {
    private final int id;
    private final String name;
    private final int age;
    private final String email;
    public Student(int id, String name, int age, String email) { this.id = id; this.name = name; this.age = age; this.email = email; }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    @Override public String toString() { return String.format("Student{id=%d, name='%s', age=%d, email='%s'}", id, name, age, email); }
}
