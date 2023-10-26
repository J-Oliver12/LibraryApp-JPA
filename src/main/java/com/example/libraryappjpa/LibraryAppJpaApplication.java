package com.example.libraryappjpa;

import com.example.libraryappjpa.dao.*;
import com.example.libraryappjpa.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;



@SpringBootApplication
public class LibraryAppJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryAppJpaApplication.class, args);
    }
}

