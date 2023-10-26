package com.example.libraryappjpa;

import com.example.libraryappjpa.dao.*;
import com.example.libraryappjpa.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private DetailsDao detailsDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private BookLoanDao bookLoanDao;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Options:");
            System.out.println("1. Create User and Details");
            System.out.println("2. Find User by UserId");
            System.out.println("3. Find All Users");
            System.out.println("4. Update User Details");
            System.out.println("5. Delete User");
            System.out.println("6. Create Book");
            System.out.println("7. Create Author");
            System.out.println("8. Create Book Loan");
            System.out.println("9. Find Book by BookId");
            System.out.println("10. Find Author by AuthorId");
            System.out.println("11. Find Book Loan by LoanId");
            System.out.println("12. Find All Books");
            System.out.println("13. Find All Authors");
            System.out.println("14. Find All Book Loans");
            System.out.println("15. Update Book");
            System.out.println("16. Update Author");
            System.out.println("17. Update Book Loan");
            System.out.println("18. Delete Book");
            System.out.println("19. Delete Author");
            System.out.println("20. Delete Book Loan");
            System.out.println("21. Return Book");
            System.out.println("22. Exit");
            System.out.print("Enter your choice: ");
            // Add login features??
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
                    createBook(scanner);
                    break;
                case 7:
                    createAuthor(scanner);
                    break;
                case 8:
                    createBookLoan(scanner);
                    break;
                case 9:
                    findBookByBookId(scanner);
                    break;
                case 10:
                    findAuthorByAuthorId(scanner);
                    break;
                case 11:
                    findBookLoanByLoanId(scanner);
                    break;
                case 12:
                    findAllBooks();
                    break;
                case 13:
                    findAllAuthors();
                    break;
                case 14:
                    findAllBookLoans();
                    break;
                case 15:
                    updateBook(scanner);
                    break;
                case 16:
                    updateAuthor(scanner);
                    break;
                case 17:
                    updateBookLoan(scanner);
                    break;
                case 18:
                    deleteBook(scanner);
                    break;
                case 19:
                    deleteAuthor(scanner);
                    break;
                case 20:
                    deleteBookLoan(scanner);
                    break;
                case 21:
                    returnBook(scanner);
                    break;
                case 22:
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
        for (AppUser user : allUsers) {
            System.out.println("User ID: " + user.getUserId());
            System.out.println("Username: " + user.getUsername());
        }
    }

    private void updateUserDetails(Scanner scanner) {
        System.out.print("Enter userId of the user to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        AppUser foundUser = appUserDao.findById(userId);
        if (foundUser != null) {
            while (true) {
                try {
                    System.out.print("Enter updated name: ");
                    String updatedName = scanner.nextLine();

                    System.out.print("Enter updated email: ");
                    String updatedEmail = scanner.nextLine();

                    System.out.print("Enter updated birthdate (YYYY-MM-DD): ");
                    String updatedBirthDate = scanner.nextLine();


                    LocalDate parsedDate = LocalDate.parse(updatedBirthDate);

                    System.out.print("Enter updated username: ");
                    String updatedUsername = scanner.nextLine();

                    System.out.print("Enter updated password: ");
                    String updatedPassword = scanner.nextLine();

                    Details userDetails = foundUser.getDetails();
                    userDetails.setName(updatedName);
                    userDetails.setEmail(updatedEmail);
                    userDetails.setBirthDate(parsedDate);

                    foundUser.setUsername(updatedUsername);
                    foundUser.setPassword(updatedPassword);

                    detailsDao.update(userDetails);
                    appUserDao.update(foundUser);

                    System.out.println("User details updated successfully.");
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
                }
            }
        } else {
            System.out.println("User not found with the given userId.");
        }
    }

    private void deleteUser(Scanner scanner) {
        System.out.print("Enter userId of the user to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        appUserDao.deleteWithDetails(userId);
        System.out.println("User and associated details deleted successfully.");
    }


    private void createBook(Scanner scanner) {
        Book book = new Book();
        book.setAuthors(new HashSet<>());

        System.out.print("Enter ISBN: ");
        book.setIsbn(scanner.nextLine());
        System.out.print("Enter Title: ");
        book.setTitle(scanner.nextLine());

        int maxLoanDays;
        try {
            System.out.print("Enter Max Loan Days: ");
            maxLoanDays = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for Max Loan Days. Please enter a numeric value.");
            return;
        }

        Author author = new Author();
        System.out.print("Enter Author's First Name: ");
        author.setFirstName(scanner.nextLine());
        System.out.print("Enter Author's Last Name: ");
        author.setLastName(scanner.nextLine());

        book.setAvailable(true);

        saveBookAndAuthor(book, author, maxLoanDays);

        bookDao.update(book);
    }

    @Transactional
    public void saveBookAndAuthor(Book book, Author author, int maxLoanDays) {
        try {
            authorDao.create(author);
            book.getAuthors().add(author);
            book.setMaxLoanDays(maxLoanDays);
            bookDao.create(book);
        } catch (Exception e) {
            System.out.println("An error occurred while saving the book and author.");
        }
    }


    private void createAuthor(Scanner scanner) {
        Author author = new Author();

        System.out.print("Enter First Name: ");
        author.setFirstName(scanner.nextLine());
        System.out.print("Enter Last Name: ");
        author.setLastName(scanner.nextLine());

        authorDao.create(author);

        System.out.println("Author created successfully.");
    }

    private void createBookLoan(Scanner scanner) {
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        Book book = bookDao.findById(bookId);

        if (book == null) {
            System.out.println("Book not found with the given Book ID.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("The book is not available for loan.");
            return;
        }

        System.out.print("Enter loan date (YYYY-MM-DD): ");
        LocalDate loanDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter due date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());

        BookLoan bookLoan = new BookLoan();
        bookLoan.setLoanDate(loanDate);
        bookLoan.setDueDate(dueDate);

        System.out.print("Has the book been returned (true/false): ");
        boolean returned = scanner.nextBoolean();
        bookLoan.setReturned(returned);
        scanner.nextLine();

        System.out.print("Enter borrower's User ID: ");
        int borrowerUserId = scanner.nextInt();
        AppUser borrower = appUserDao.findById(borrowerUserId);

        if (borrower == null) {
            System.out.println("User not found with the given User ID.");
            return;
        }

        bookLoan.setBorrower(borrower);
        bookLoan.setBook(book);

        bookLoanDao.create(bookLoan);

        book.setAvailable(false);

        bookDao.update(book);

        System.out.println("Book Loan created successfully.");
    }


    private void findBookByBookId(Scanner scanner) {
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        Book foundBook = bookDao.findById(bookId);

        if (foundBook != null) {
            System.out.println("Found Book:");
            System.out.println("Title: " + foundBook.getTitle());
        } else {
            System.out.println("Book not found with the given Book ID.");
        }
    }

    private void findAuthorByAuthorId(Scanner scanner) {
        System.out.print("Enter Author ID: ");
        int authorId = scanner.nextInt();
        scanner.nextLine();

        Author foundAuthor = authorDao.findById(authorId);

        if (foundAuthor != null) {
            System.out.println("Found Author:");
            System.out.println("Name: " + foundAuthor.getFirstName() + " " + foundAuthor.getLastName());
        } else {
            System.out.println("Author not found with the given Author ID.");
        }
    }

    private void findBookLoanByLoanId(Scanner scanner) {
        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();
        scanner.nextLine();

        BookLoan foundBookLoan = bookLoanDao.findById(loanId);

        if (foundBookLoan != null) {
            System.out.println("Found Book Loan:");
        } else {
            System.out.println("Book Loan not found with the given Loan ID.");
        }
    }

    private void findAllBooks() {
        Iterable<Book> allBooks = bookDao.findAll();
        System.out.println("All Books:");
        for (Book book : allBooks) {
            System.out.println("Title: " + book.getTitle());
        }
    }

    private void findAllAuthors() {
        Iterable<Author> allAuthors = authorDao.findAll();
        System.out.println("All Authors:");
        for (Author author : allAuthors) {
            System.out.println("Name: " + author.getFirstName() + " " + author.getLastName());
        }
    }

    private void findAllBookLoans() {
        Iterable<BookLoan> allBookLoans = bookLoanDao.findAll();
        System.out.println("All Book Loans:");
        for (BookLoan bookLoan : allBookLoans) {
            System.out.println("Loan ID: " + bookLoan.getLoanId());
            System.out.println("Loan Date: " + bookLoan.getLoanDate());
            System.out.println("Due Date: " + bookLoan.getDueDate());
            System.out.println("Returned: " + bookLoan.isReturned());

            AppUser borrower = bookLoan.getBorrower();
            System.out.println("Borrower: " + borrower.getUsername() + " " + borrower.getUserId());

            Book book = bookLoan.getBook();
            System.out.println("Book Title: " + book.getTitle());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println();
        }
    }

    private void updateBook(Scanner scanner) {
        System.out.print("Enter Book ID to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        Book foundBook = bookDao.findById(bookId);

        if (foundBook != null) {
            System.out.print("Enter updated Title: ");
            foundBook.setTitle(scanner.nextLine());
            System.out.print("Enter updated Max Loan Days: ");
            foundBook.setMaxLoanDays(scanner.nextInt());

            bookDao.update(foundBook);

            System.out.println("Book details updated successfully.");
        } else {
            System.out.println("Book not found with the given Book ID.");
        }
    }

    private void updateAuthor(Scanner scanner) {
        System.out.print("Enter Author ID to update: ");
        int authorId = scanner.nextInt();
        scanner.nextLine();

        Author foundAuthor = authorDao.findById(authorId);

        if (foundAuthor != null) {
            System.out.print("Enter updated First Name: ");
            foundAuthor.setFirstName(scanner.nextLine());
            System.out.print("Enter updated Last Name: ");
            foundAuthor.setLastName(scanner.nextLine());

            authorDao.update(foundAuthor);

            System.out.println("Author details updated successfully.");
        } else {
            System.out.println("Author not found with the given Author ID.");
        }
    }

    private void updateBookLoan(Scanner scanner) {
        System.out.print("Enter Loan ID to update: ");
        int loanId = scanner.nextInt();
        scanner.nextLine();

        BookLoan foundBookLoan = bookLoanDao.findById(loanId);

        if (foundBookLoan != null) {

            bookLoanDao.update(foundBookLoan);

            System.out.println("Book Loan details updated successfully.");
        } else {
            System.out.println("Book Loan not found with the given Loan ID.");
        }
    }

    private void deleteBook(Scanner scanner) {
        System.out.print("Enter Book ID to delete: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        bookDao.delete(bookId);

        System.out.println("Book deleted successfully.");
    }

    private void deleteAuthor(Scanner scanner) {
        System.out.print("Enter Author ID to delete: ");
        int authorId = scanner.nextInt();
        scanner.nextLine();

        authorDao.delete(authorId);

        System.out.println("Author deleted successfully.");
    }

    private void deleteBookLoan(Scanner scanner) {
        System.out.print("Enter Loan ID to delete: ");
        int loanId = scanner.nextInt();
        scanner.nextLine();

        bookLoanDao.delete(loanId);

        System.out.println("Book Loan deleted successfully.");
    }

    public void returnBook(Scanner scanner) {
        System.out.print("Enter the Loan ID of the book you want to return: ");
        int loanId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        BookLoan bookLoan = bookLoanDao.findById(loanId);

        if (bookLoan != null) {
            if (bookLoan.isReturned()) {
                System.out.println("The book has already been returned.");
            } else {
                bookLoan.setReturned(true);
                bookLoanDao.update(bookLoan);

                Book book = bookLoan.getBook();

                book.setAvailable(true);

                bookDao.update(book);
                bookLoanDao.update(bookLoan);

                System.out.println("Book successfully marked as returned.");
            }
        } else {
            System.out.println("Book Loan not found with the given Loan ID.");
        }
    }



}
