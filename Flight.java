package flight;
import java.util.Scanner;
class Flight {
    int id;
    String origin;
    String destination;
    String departureDate;
    String returnDate;
    String airline;
    double price;

    Flight(int id, String origin, String destination, String departureDate, String returnDate, String airline, double price) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.airline = airline;
        this.price = price;
    }

    
    public String toString() {
        return "Flight ID: " + id + ", Airline: " + airline + ", Origin: " + origin + ", Destination: " + destination + 
               ", Departure: " + departureDate + ", Return: " + returnDate + ", Price: $" + price;
    }
}

class User {
    int id;
    String username;
    String email;
    String password;

    User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

class Booking {
    int id;
    int userId;
    int flightId;
    String passengerName;
    String dateOfBirth;
    String passportNumber;

    Booking(int id, int userId, int flightId, String passengerName, String dateOfBirth, String passportNumber) {
        this.id = id;
        this.userId = userId;
        this.flightId = flightId;
        this.passengerName = passengerName;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
    }
}

public class FlightBookingSystem {

    private static int userIdCounter = 1;
    private static int flightIdCounter = 1;
    private static int bookingIdCounter = 1;
    private static User[] users = new User[100];
    private static Flight[] flights = new Flight[100];
    private static Booking[] bookings = new Booking[100];
    private static int userCount = 0;
    private static int flightCount = 0;
    private static int bookingCount = 0;

   
    private static void displayMainMenu() {
        System.out.println("Welcome to the Flight Booking System");
        System.out.println("Please select an option:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Add Flight");
        System.out.println("4. Exit");
    }

    private static void register(Scanner scanner) {
        System.out.println("Enter your details to register:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        users[userCount++] = new User(userIdCounter++, username, email, password);
        System.out.println("Registration successful!");
    }

    private static User login(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        for (int i = 0; i < userCount; i++) {
            if (users[i].email.equals(email) && users[i].password.equals(password)) {
                System.out.println("Login successful!");
                return users[i];
            }
        }
        System.out.println("Login failed! Please check your email and password.");
        return null;
    }

    private static void userMenu(Scanner scanner, User user) {
        String choice;

        do {
            displayUserMenu(user);
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    searchAndSortFlights(scanner);
                    break;
                case "2":
                    bookFlight(scanner, user);
                    break;
                case "3":
                    System.out.println("You have been logged out. Thank you for using the Flight Booking System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (!choice.equals("3"));
    }

    private static void displayUserMenu(User user) {
        System.out.println("Welcome, " + user.username + "!");
        System.out.println("Please select an option:");
        System.out.println("1. Search and Sort Flights");
        System.out.println("2. Book Flight");
        System.out.println("3. Logout");
    }

    private static void searchAndSortFlights(Scanner scanner) {
        Flight[] searchResults = new Flight[100];
        int searchCount = 0;

        System.out.print("Origin: ");
        String origin = scanner.nextLine();
        System.out.print("Destination: ");
        String destination = scanner.nextLine();
        System.out.print("Departure Date (YYYY-MM-DD): ");
        String departureDate = scanner.nextLine();
        System.out.print("Return Date (YYYY-MM-DD): ");
        String returnDate = scanner.nextLine();

        Flight[] resultFlights = searchFlights(origin, destination, departureDate, returnDate);
        for (Flight flight : resultFlights) {
            if (flight != null) {
                searchResults[searchCount++] = flight;
            }
        }

        // Sort search results by price
        if (searchCount > 0) {
            quickSort(searchResults, 0, searchCount - 1);

            System.out.println("Search Results (sorted by price):");
            for (int i = 0; i < searchCount; i++) {
                System.out.println(searchResults[i]);
            }
        } else {
            System.out.println("No flights found.");
        }
    }

    private static Flight[] searchFlights(String origin, String destination, String departureDate, String returnDate) {
        Flight[] resultFlights = new Flight[100];
        int resultCount = 0;
        for (int i = 0; i < flightCount; i++) {
            if (flights[i].origin.equalsIgnoreCase(origin) && flights[i].destination.equalsIgnoreCase(destination) 
                && flights[i].departureDate.equals(departureDate) && flights[i].returnDate.equals(returnDate)) {
                resultFlights[resultCount++] = flights[i];
            }
        }

        Flight[] trimmedResults = new Flight[resultCount];
        System.arraycopy(resultFlights, 0, trimmedResults, 0, resultCount);
        return trimmedResults;
    }

    private static void bookFlight(Scanner scanner, User user) {
        searchAndSortFlights(scanner);

        System.out.print("Select a flight to book (Enter Flight ID): ");
        int flightId = Integer.parseInt(scanner.nextLine());

        Flight selectedFlight = null;
        for (int i = 0; i < flightCount; i++) {
            if (flights[i].id == flightId) {
                selectedFlight = flights[i];
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Invalid Flight ID.");
            return;
        }

        System.out.print("Full Name: ");
        String passengerName = scanner.nextLine();
        System.out.print("Date of Birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();
        System.out.print("Passport Number: ");
        String passportNumber = scanner.nextLine();

        bookings[bookingCount++] = new Booking(bookingIdCounter++, user.id, flightId, passengerName, dateOfBirth, passportNumber);
        System.out.println("Booking successful! A confirmation email has been sent.");
    }

    private static void addFlight(Scanner scanner) {
        System.out.println("Enter flight details to add:");
        System.out.print("Origin: ");
        String origin = scanner.nextLine();
        System.out.print("Destination: ");
        String destination = scanner.nextLine();
        System.out.print("Departure Date (YYYY-MM-DD): ");
        String departureDate = scanner.nextLine();
        System.out.print("Return Date (YYYY-MM-DD): ");
        String returnDate = scanner.nextLine();
        System.out.print("Airline: ");
        String airline = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        flights[flightCount++] = new Flight(flightIdCounter++, origin, destination, departureDate, returnDate, airline, price);
        System.out.println("Flight added successfully!");
    }

    private static void quickSort(Flight[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Flight[] arr, int low, int high) {
        double pivot = arr[high].price;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].price < pivot) {
                i++;
                Flight temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Flight temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            displayMainMenu();
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    register(scanner);
                    break;
                case "2":
                    User user = login(scanner);
                    if (user != null) {
                        userMenu(scanner, user);
                    }
                    break;
                case "3":
                    addFlight(scanner);
                    break;
                case "4":
                    System.out.println("Thank you for using the Flight Booking System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (!choice.equals("4"));

        scanner.close();
    }

}
