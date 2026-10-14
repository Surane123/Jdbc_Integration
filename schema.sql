CREATE DATABASE IF NOT EXISTS java_development;
USE java_development;

CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);
