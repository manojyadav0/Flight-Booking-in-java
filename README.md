# Flight-Booking-in-java

How the Code Runs Step by Step

1. Execution Starts from main()
The main() method initializes a Scanner object for user input.
A do-while loop runs until the user selects the option to exit (4).
It calls displayMainMenu() and takes user input to decide the next operation.
2. Main Menu Functionalities
Option 1: Register a User

The register(Scanner scanner) method asks for username, email, and password.
A new User object is created and added to the users array.
The userIdCounter ensures each user gets a unique ID.
The program prints "Registration successful!".
Option 2: User Login

The login(Scanner scanner) method asks for email and password.
It searches the users array for a matching user.
If found, "Login successful!" is displayed, and the userMenu() function is called.
Option 3: Add a Flight

The addFlight(Scanner scanner) method asks for flight details:
Origin, Destination, Departure Date, Return Date, Airline, Price
A new Flight object is created and stored in the flights array.
Unique flight IDs are assigned using flightIdCounter++.
Option 4: Exit

The program prints "Thank you for using the Flight Booking System. Goodbye!" and exits.
3. User Menu Functionalities
After logging in, the userMenu() method presents the following options:

Option 1: Search and Sort Flights

Calls searchAndSortFlights(Scanner scanner).
The user enters:
Origin
Destination
Departure Date
Return Date
It calls searchFlights() to filter flights based on user input.
If flights are found:
quickSort() is used to sort flights by price (ascending order).
Sorted flights are displayed.
Option 2: Book a Flight

Calls bookFlight(Scanner scanner, User user).
The user searches for flights and enters a Flight ID.
If a valid flight is found, they provide:
Passenger Name
Date of Birth
Passport Number
A new Booking is created and added to the bookings array.
Option 3: Logout

The user is logged out and returned to the main menu.
4. Sorting Algorithm: Quick Sort
The quickSort() method sorts flights based on price:

The partition() method picks the pivot (last element).
It moves all flights with lower prices before the pivot.
Recursively sorts the left and right partitions.
