package org.example;

import models.Student;
import repositories.StudentsRepository;

import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws SQLException {
        StudentsRepository studentsRepository = new StudentsRepository();
        // studentsRepository.createTable();
//        Student dilbara = new Student("Dilbara",(byte) 19);
//        Student dinara = new Student("Dinara",(byte) 18);
//
//        studentsRepository.save(dilbara);
//        studentsRepository.save(dinara);
        studentsRepository.findAll().forEach(System.out::println);

    }
}
