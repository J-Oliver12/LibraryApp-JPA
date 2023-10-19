package com.example.libraryappjpa;

import com.example.libraryappjpa.dao.AppUserDao;
import com.example.libraryappjpa.dao.DetailsDao;
import com.example.libraryappjpa.entity.AppUser;
import com.example.libraryappjpa.entity.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication
public class LibraryAppJpaApplication implements CommandLineRunner {
    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private DetailsDao detailsDao;

    public static void main(String[] args) {
        SpringApplication.run(LibraryAppJpaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Options:");
            System.out.println("1. Create User and Details");
            System.out.println("2. Find User by UserId");
            System.out.println("3. Find All Users");
            System.out.println("4. Update User Details");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createUserAndDetails(scanner);
                    break;
                case 2:
                    findUserById(scanner);
                    break;
                case 3:
                    findAllUsers();
                    break;
                case 4:
                    updateUserDetails(scanner);
                    break;
                case 5:
                    deleteUser(scanner);
                    break;
                case 6:
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void createUserAndDetails(Scanner scanner) {
        Details details = new Details();
        System.out.print("Enter email: ");
        details.setEmail(scanner.nextLine());
        System.out.print("Enter name: ");
        details.setName(scanner.nextLine());
        System.out.print("Enter birthdate (YYYY-MM-DD): ");
        details.setBirthDate(LocalDate.parse(scanner.nextLine()));

        AppUser user = new AppUser();
        System.out.print("Enter username: ");
        user.setUsername(scanner.nextLine());
        System.out.print("Enter password: ");
        user.setPassword(scanner.nextLine());
        user.setRegdate(LocalDate.now());

        detailsDao.create(details);

        user.setDetails(details);
        details.setAppUser(user);

        appUserDao.create(user);

        System.out.println("User and Details created successfully.");
    }

    private void findUserById(Scanner scanner) {
        System.out.print("Enter userId: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        AppUser foundUser = appUserDao.findById(userId);

        if (foundUser != null) {
            System.out.println("Found user: " + foundUser.getUsername());
        } else {
            System.out.println("User not found with the given userId.");
        }
    }

    private void findAllUsers() {
        Iterable<AppUser> allUsers = appUserDao.findAll();
        System.out.println("All Users:");
        for (AppUser u : allUsers) {
            System.out.println(u.getUsername());
        }
    }

    private void updateUserDetails(Scanner scanner) {
        System.out.print("Enter userId of the user to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        AppUser foundUser = appUserDao.findById(userId);
        if (foundUser != null) {
            System.out.print("Enter updated name: ");
            String updatedName = scanner.nextLine();

            System.out.print("Enter updated email: ");
            String updatedEmail = scanner.nextLine();

            System.out.print("Enter updated birthdate (YYYY-MM-DD): ");
            String updatedBirthDate = scanner.nextLine();

            System.out.print("Enter updated username: ");
            String updatedUsername = scanner.nextLine();

            System.out.print("Enter updated password: ");
            String updatedPassword = scanner.nextLine();

            Details userDetails = foundUser.getDetails();
            userDetails.setName(updatedName);
            userDetails.setEmail(updatedEmail);
            userDetails.setBirthDate(LocalDate.parse(updatedBirthDate));

            foundUser.setUsername(updatedUsername);
            foundUser.setPassword(updatedPassword);

            detailsDao.update(userDetails);
            appUserDao.update(foundUser);

            System.out.println("User details updated successfully.");
        } else {
            System.out.println("User not found with the given userId.");
        }
    }

    private void deleteUser(Scanner scanner) {
        System.out.print("Enter userId of the user to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        AppUser foundUser = appUserDao.findById(userId);
        if (foundUser != null) {
            appUserDao.delete(userId);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found with the given userId.");
        }
    }
}

