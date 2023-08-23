import java.util.Scanner;
import java.io.*;
import java.sql.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class queryLoop {
	
	public static void main(String[] args) {
		// Setup strings for the connection
    		final String oracleURL =   // Magic lectura -> aloe access spell
                        "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";
        	String username = null,    // Oracle DBMS username
               	       password = null;    // Oracle DBMS password

		// Get username and password from command line arguments
        	if (args.length == 2) {  
			username = args[0];
            		password = args[1];

		// If user did not provide username print error message and exit
        	} else {
            		System.out.println("\nUsage:  java JDBC <username> <password>\n"
                        + "    where <username> is your Oracle DBMS"
                        + " username,\n    and <password> is your Oracle"
                        + " password (not your system password).\n");
           		System.exit(-1);
        	}


            	// Load the (Oracle) JDBC driver by initializing its base
            	// class, 'oracle.jdbc.OracleDriver'.
        	try {
                	Class.forName("oracle.jdbc.OracleDriver");
		// Catch ClassNotFoundException and print information
        	} catch (ClassNotFoundException e) {
                	System.err.println("*** ClassNotFoundException:  "
                    	+ "Error loading Oracle JDBC driver.  \n"
                    	+ "\tPerhaps the driver is not on the Classpath?");
                	System.exit(-1);
        	}


        	// Make and return a database connection to the user's Oracle database
        	Connection dbconn = null;
        	try {
                	dbconn = DriverManager.getConnection(oracleURL,username,password);
		// Catch SQL Exception and print information
        	} catch (SQLException e) {
                	System.err.println("*** SQLException:  "
                    	+ "Could not open JDBC connection.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}


		Scanner input;
		while(true) {
			int nextInt;
			System.out.println("----------------------------------");
			System.out.println("Welcome: ");
			System.out.println("Enter 0 to terminate program.");
			System.out.println("Enter 1 for record insertion.");
			System.out.println("Enter 2 for record deletion.");
			System.out.println("Enter 3 for record updating.");
			System.out.println("Enter 4 for Querys");
			System.out.println("----------------------------------");
			// DO NOT CLOSE ANY SCANNERS
			input = new Scanner(System.in);
			try {
				nextInt = input.nextInt();
			}
			catch(Exception e){
				System.out.println("Please enter 1 number");
				continue;
			}
			// If user selects quit
			if(nextInt == 0) {
				System.out.println("Program terminated");
				return;
			}
			// If user selects insertion
			if(nextInt == 1) {
				System.out.println("----------------------------------");
				insert(dbconn);
				continue;
			}
			// If user selects deletion
			if(nextInt == 2) {
				System.out.println("----------------------------------");
				delete(dbconn);
				continue;
			}
			// If user selects update
			if(nextInt == 3) {
				System.out.println("----------------------------------");
				update(dbconn);
				continue;
			}
			// If user selects query
			if(nextInt == 4) {
				System.out.println("----------------------------------");
				querys(dbconn);
				continue;
			}
			else {
				System.out.println("Please enter a number in range 0-4");
				System.out.println(nextInt);
			}
			
		}
		
	}

	/*------------------------------------------------------------------------*
        |  Method: 		insert
        |
        |  Purpose: 		Handle record insertion command coming from the users.
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					All necessary tables are created in hfinkbeiner's 
	|					database and granted INSERT access to public. 
        |   
        |
        |  Post-condition: 	New records will be added to desired tables. 
        |
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void insert(Connection dbconn) {
		while(true) {
			int nextInt; // Keeps track of user selection
			// Print out options
			System.out.println("----------------------------------");
			System.out.println("Insert Options: ");
			System.out.println("Enter 0 to return to previous menu.");
			System.out.println("Enter 1 to add a Customer.");
			System.out.println("Enter 2 to add a Room.");
			System.out.println("Enter 3 to add a Booking.");
			System.out.println("Enter 4 to add a Amenity");
			System.out.println("Enter 5 to add a Shift");
			System.out.println("Enter 6 to add a Rating");
			System.out.println("Enter 7 to add a Employee");
			System.out.println("Enter 8 to add a PaidService");
			Scanner input = new Scanner(System.in);
			// Make sure user is giving input
			try {
				nextInt = input.nextInt();
			}
			catch(Exception e){
				System.out.println("Please enter a number");
				continue;
			}
			// Return to main menu
			if(nextInt == 0) {
				return;
			}
			// Add a customer
			if(nextInt == 1) {
				System.out.println("----------------------------------");
				addCustomer(dbconn);
				continue;
			}
			// Add a room
			if(nextInt == 2) {
				System.out.println("----------------------------------");
				addRoom(dbconn);
				continue;
			}
			// Add a booking
			if(nextInt == 3) {
				System.out.println("----------------------------------");
				addBooking(dbconn);
				continue;
			}
			// Add an amenity
			if(nextInt == 4) {
				System.out.println("----------------------------------");
				addAmenity(dbconn);
				continue;
			}
			// Add a shift
			if(nextInt == 5) {
				System.out.println("----------------------------------");
				addShift(dbconn);
				continue;
			}
			// Add a rating
			if(nextInt == 6) {
				System.out.println("----------------------------------");
				addRating(dbconn);
				continue;
			}
			// add an employee
			if(nextInt == 7) {
				System.out.println("----------------------------------");
				addEmployee(dbconn);
				continue;
			}
			// Add a paid service
			if(nextInt == 8) {
				System.out.println("----------------------------------");
				addPaidService(dbconn);
				continue;
			}
			else {
				System.out.println("Please enter a number in range 0-9");
			}
			
		}
	}

	/*------------------------------------------------------------------------*
        |  Method: 		addCustomer
        |
        |  Purpose: 		Insert a new record into hfinkbeiner.Customer table. 
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					Customer table is created in hfinkbeiner's database  
	|					and granted INSERT access to public. 
        |   
        |
        |  Post-condition: 	A new record will be added to hfinkbeiner.Customer 
	|					table. 
        |
	|
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
	|
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void addCustomer(Connection dbconn) {
		// Print out the prompt
		System.out.println("Please enter the customer info (customerId, firstName, lastName, customerType, club460Member, cardtype):");
		Scanner input = new Scanner(System.in);
		String[] split = input.nextLine().split(", ");

		// Determine if input is empty
		if (split[0] == "") {
			System.out.println("Insertion cancelled.");
			return;
		}
		String customerId    = split[0]; // Stores the customerId
		String fistName      = split[1]; // Stores the first name
		String lastName      = split[2]; // Stores the last name
		String customerType  = split[3]; // Store the customer type
		String club460Member = split[4]; // Store if the customer is 460 member
		String cardtype      = split[5]; // Stores the customers card type

		// Build the query using variables
		String query = "INSERT INTO hfinkbeiner.Customer VALUES ('" + customerId + "','" + fistName + "','" + lastName
		+ "','" + customerType + "','" + club460Member + "','" + cardtype + "')";

		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		// Print user output
		System.out.println("Adding: (" + customerId + "," + fistName + "," + lastName + "," + customerType + "," + club460Member + "," + cardtype + ")");
		System.out.println("----------------------------------");
	}

	/*------------------------------------------------------------------------*
        |  Method: 			addRoom
        |
        |  Purpose: 		Insert a new record into hfinkbeiner.Room table. 
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					Room table is created in hfinkbeiner's database  
	|					and granted INSERT access to public. 
        |   
        |
        |  Post-condition: 	A new record will be added to hfinkbeiner.Room 
	|					table. 
        |
	|
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
	|
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void addRoom(Connection dbconn) {
		// Print out the prompt
		System.out.println("Please enter the room info (roomId, size, price, bedNum, bathNum, type, bedType):");
		Scanner input = new Scanner(System.in);
		String[] split = input.nextLine().split(", ");

		// Determine if input is empty
		if (split[0] == "") {
			System.out.println("Insertion cancelled.");
			return;
		}
		String roomId  = split[0]; // Stores the roomId
		String size    = split[1]; // Stores the room size
		String price   = split[2]; // Stores the room price
		String bedNum  = split[3]; // Stores the number of beds
		String bathNum = split[4]; // Stores the number of bathrooms
		String type    = split[5]; // Stores the room type
		String bedType = split[6]; // Stores the bed type

		// Construct the query using variables
		String query = "INSERT INTO hfinkbeiner.Room VALUES ('" + roomId + "','" + size + "','" + price
		+ "','" + bedNum + "','" + bathNum + "','" + type + "','" + bedType + "')";
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		// Print user output
		System.out.println("Adding: (" + roomId + "," + size + "," + price + "," + bedNum + "," + bathNum + "," + type + "," + bedType + ")");
		System.out.println("----------------------------------");
		
	}

	/*------------------------------------------------------------------------*
        |  Method: 		addBooking
        |
        |  Purpose: 		Insert a new record into hfinkbeiner.Booking table. 
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					Booking table is created in hfinkbeiner's database  
	|					and granted INSERT access to public. 
        |   
        |
        |  Post-condition: 	A new record will be added to hfinkbeiner.Booking 
	|					table. 
        |
	|
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
	|
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void addBooking(Connection dbconn) {
		// Print out the prompt
		System.out.println("Please enter the booking info (bookingId, roomId, customerId, dateTo, dateFrom, tip):");
		Scanner input = new Scanner(System.in);
		String[] split = input.nextLine().split(", ");

		// Determine the if the input is empty
		if (split[0] == "") {
			System.out.println("Insertion cancelled.");
			return;
		}
		String bookingId  = split[0]; // Stores the bookingId
		String roomId     = split[1]; // Stores the roomId
		String customerId = split[2]; // Stores the customerId
		String dateTo     = split[3]; // Stores the start date
		String dateFrom   = split[4]; // Stores the end data
		String tip        = split[5]; // Stores the tip amount
	
		// Construct the query using variables
		String query = "INSERT INTO hfinkbeiner.Booking VALUES ('" + bookingId + "','" + roomId + "','" + customerId
		+ "','" + dateTo + "','" + dateFrom + "','" + tip +  "')";
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		// Print user output
		System.out.println("Adding: (" + bookingId + "," + roomId + "," + customerId + "," + dateTo + "," + dateFrom + "," + tip + ")");
		System.out.println("----------------------------------");
		
	}

	/*------------------------------------------------------------------------*
        |  Method: 		addAmenity
        |
        |  Purpose: 		Insert a new record into hfinkbeiner.Amenity table. 
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					Amenity table is created in hfinkbeiner's database  
	|					and granted INSERT access to public. 
        |   
        |
        |  Post-condition: 	A new record will be added to hfinkbeiner.Amenity 
	|					table. 
        |
	|
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
	|
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void addAmenity(Connection dbconn) {
		// Print out the prompt
		System.out.println("Please enter the amenity info (amenityId, roomId, timeOpen, timeClose, type, price):");
		Scanner input = new Scanner(System.in);
		String[] split = input.nextLine().split(", ");
		// Determine if user input is empty
		if (split[0] == "") {
			System.out.println("Insertion cancelled.");
			return;
		}

		String amenityId = split[0]; // Stores the amenityId
		String roomId    = split[1]; // Stores the roomId
		String timeOpen  = split[2]; // Stores the time open
		String timeClose = split[3]; // Stores the time closed
		String type      = split[4]; // Stores the amenity type
		String price     = split[5]; // Stores the price
		
		// Construct the query using variables
		String query = "INSERT INTO hfinkbeiner.Amenity VALUES ('" + amenityId + "','" + roomId + "','" + timeOpen
		+ "','" + timeClose + "','" + type + "','" + price +  "')";
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		// Print user output
		System.out.println("Adding: (" + amenityId + "," + roomId + "," + timeOpen + "," + timeClose + "," + type + "," + price + ")");
		System.out.println("----------------------------------");
		
	}

	/*------------------------------------------------------------------------*
        |  Method: 		addShift
        |
        |  Purpose: 		Insert a new record into hfinkbeiner.Shift table. 
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					Shift table is created in hfinkbeiner's database  
	|					and granted INSERT access to public. 
        |   
        |
        |  Post-condition: 	A new record will be added to hfinkbeiner.Shift 
	|					table. 
        |
	|
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
	|
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void addShift(Connection dbconn) {
		// Print out the prompt
		System.out.println("Please enter the shift info (shiftID, employeeId, taskId, startTime, finishTime):");
		Scanner input = new Scanner(System.in);
		String[] split = input.nextLine().split(", ");
		// Determine if user input is empty
		if (split[0] == "") {
			System.out.println("Insertion cancelled.");
			return;
		}

		String shiftId    = split[0]; // Stores the shiftId
		String employeeId = split[1]; // Stores the employeeId
		String taskId     = split[2]; // Stores the taskId
		String startTime  = split[3]; // Stores the start time
		String finishTime = split[4]; // Stores the finish time

		// Construct the query using variables
		String query = "INSERT INTO hfinkbeiner.Shift VALUES ('" + shiftId + "','" + employeeId + "','" + taskId
		+ "','" + startTime + "','" + finishTime + "')";
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		// Print user output
		System.out.println("Adding: (" + shiftId + "," + employeeId + "," + taskId + "," + startTime + "," + finishTime + ")");
		System.out.println("----------------------------------");
		
	}

	/*------------------------------------------------------------------------*
        |  Method: 		addRating
        |
        |  Purpose: 		Insert a new record into hfinkbeiner.Rating table. 
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					Rating table is created in hfinkbeiner's database  
	|					and granted INSERT access to public. 
        |   
        |
        |  Post-condition: 	A new record will be added to hfinkbeiner.Rating 
	|					table. 
        |
	|
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
	|
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void addRating(Connection dbconn) {
		// Print out the prompt
		System.out.println("Please enter the rating info (ratingID, customerId, amentityId, starLevel, date):");
		Scanner input = new Scanner(System.in);
		String[] split = input.nextLine().split(", ");
		// Determine if user input is empty
		if (split[0] == "") {
			System.out.println("Insertion cancelled.");
			return;
		}
		String ratingId   = split[0]; // Stores the ratingId
		String customerId = split[1]; // Stores the customerId
		String amentityId = split[2]; // Stores the amenityId
		String starLevel  = split[3]; // Stores the star level rating
		String date       = split[4]; // Stores the rating date
		
		// Construct the query using variables
		String query = "INSERT INTO hfinkbeiner.Rating VALUES ('" + ratingId + "','" + customerId + "','" + amentityId
		+ "','" + starLevel + "','" + date + "')";
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		// Print user output
		System.out.println("Adding: (" + ratingId + "," + customerId + "," + amentityId + "," + starLevel + "," + date + ")");
		System.out.println("----------------------------------");
		
	}

	/*------------------------------------------------------------------------*
        |  Method: 		addEmployee
        |
        |  Purpose: 		Insert a new record into hfinkbeiner.Employee table. 
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					Employee table is created in hfinkbeiner's database  
	|					and granted INSERT access to public. 
        |   
        |
        |  Post-condition: 	A new record will be added to hfinkbeiner.Employee 
	|					table. 
        |
	|
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
	|
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void addEmployee(Connection dbconn) {
		// Print out the prompt
		System.out.println("Please enter the employee info (employeeID, first_name, last_name, timeEmployed, employmentEnd, position):");
		Scanner input = new Scanner(System.in);
		String[] split = input.nextLine().split(", ");
		// Determine if user input is empty
		if (split[0] == "") {
			System.out.println("Insertion cancelled.");
			return;
		}

		String employeeId    = split[0]; // Stores the employeeId
		String firstName     = split[1]; // Stores the first name
		String lastName      = split[2]; // Stores the last name
		String timeEmployed  = split[3]; // Stores the hire date
		String employmentEnd = split[4]; // Stores the termination date
		String position      = split[5]; // Stores the position
	
		// Construct the query using variables
		String query = "INSERT INTO hfinkbeiner.Employee VALUES ('" + employeeId + "','" + firstName + "','" 
		+ lastName + "','" + timeEmployed + "','" + employmentEnd + "','" + position +  "')";
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		// Print user output
		System.out.println("Adding: (" + employeeId + "," + timeEmployed + "," + employmentEnd + "," + position + ")");
		System.out.println("----------------------------------");
		
	}

	/*------------------------------------------------------------------------*
        |  Method: 		addPaidService
        |
        |  Purpose: 		Insert a new record into hfinkbeiner.Paid_Service table.
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					Paid_Service table is created in hfinkbeiner's database 
	|					and granted INSERT access to public. 
        |   
        |
        |  Post-condition: 	A new record will be added to hfinkbeiner.Paid_Service 
	|					table. 
        |
	|
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
	|
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void addPaidService(Connection dbconn) {
		// Print out the prompt
		System.out.println("Please enter the paid service info (serviceID, customerID, bookingID, amenityID, mm/dd/yyyy, tip):");
		Scanner input = new Scanner(System.in);
		String[] split = input.nextLine().split(", ");
		// Determine if user input is empty
		if (split[0] == "") {
			System.out.println("Insertion cancelled.");
			return;
		}

		String serviceId   = split[0]; // Stores the serviceId
		String customerId = split[1]; // Stores the customerId
		String bookingId = split[2]; // Stores the bookingId
		String amenityId = split[3]; // Stores the amenityId
		String date = split[4]; // Stores the date
		String tip = split[5]; // Stores the tip amount
		
		// Construct the query using variables
		String query = "INSERT INTO hfinkbeiner.Paid_Service VALUES ('" + serviceId + "','" + customerId + "','" + bookingId
		+ "','" + amenityId + "','" + date + "','" + tip + "')";
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		// Print user output
		System.out.println("Adding: (" + serviceId + "," + customerId + "," + bookingId + "," + amenityId + "," + date + "," + tip + ")");
		System.out.println("----------------------------------");
		
	}

	/*------------------------------------------------------------------------*
        |  Method: 		delete
        |
        |  Purpose: 		Handle record deletion command coming from the users.
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					All necessary tables are created in hfinkbeiner's 
	|					database and granted DELETE access to public. 
        |   
        |
        |  Post-condition: 	Records will be deleted from desired tables. 
        |
	|
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
	|
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void delete(Connection dbconn) {
		while(true) {
			int nextInt; // Stores user selected number
			// Print out delete options
			System.out.println("----------------------------------");
			System.out.println("Delete Options: ");
			System.out.println("Enter 0 to return to previous menu.");
			System.out.println("Enter 1 to delete a Customer.");
			System.out.println("Enter 2 to delete a Room.");
			System.out.println("Enter 3 to delete a Booking.");
			System.out.println("Enter 4 to delete a Amenity");
			System.out.println("Enter 5 to delete a Shift");
			System.out.println("Enter 6 to delete a Rating");
			System.out.println("Enter 7 to delete a Employee");
			System.out.println("Enter 8 to delete a PaidService");
			Scanner input = new Scanner(System.in);
			// Make sure input is given
			try {
				nextInt = input.nextInt();
			}
			catch(Exception e){
				System.out.println("Please enter 1 number");
				continue;
			}
			// Return to previous menu
			if(nextInt == 0) {
				return;
			}
			// Delete customer
			if(nextInt == 1) {
				System.out.println("----------------------------------");
				deleteCustomer(dbconn);
				continue;
			}
			// Delete room
			if(nextInt == 2) {
				System.out.println("----------------------------------");
				deleteRoom(dbconn);
				continue;
			}
			// Delete booking
			if(nextInt == 3) {
				System.out.println("----------------------------------");
				deleteBooking(dbconn);
				continue;
			}
			// Delete amenity
			if(nextInt == 4) {
				System.out.println("----------------------------------");
				deleteAmenity(dbconn);
				continue;
			}
			// Delete shift
			if(nextInt == 5) {
				System.out.println("----------------------------------");
				deleteShift(dbconn);
				continue;
			}
			// Delete rating
			if(nextInt == 6) {
				System.out.println("----------------------------------");
				deleteRating(dbconn);
				continue;
			}
			// Delete employee
			if(nextInt == 7) {
				System.out.println("----------------------------------");
				deleteEmployee(dbconn);
				continue;
			}
			// Delete paid service
			if(nextInt == 8) {
				System.out.println("----------------------------------");
				deletePaidService(dbconn);
				continue;
			}
			else {
				System.out.println("Please enter a number in range 0-9");
			}
			
		}
		
	}

	/*------------------------------------------------------------------------*
        |  Method: 		deleteCustomer
        |
        |  Purpose: 		Take the Customer_Id from user and Delete the 
	|					corresponding record record from hfinkbeiner.Customer 
	|					table. 
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					Customer table is created in hfinkbeiner's database 
	|					and granted INSERT access to public. 
        |   
        |
        |  Post-condition: 	A record will be deleted from hfinkbeiner.Customer 
	|					table. 
        |
	|
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
	|
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void deleteCustomer(Connection dbconn) {
		// Determine customerId of customer to delete
		System.out.println("----------------------------------");
		String customerId = getID("customerID");
		// Construct the query
		String query = "DELETE FROM hfinkbeiner.Customer WHERE hfinkbeiner.Customer.Customer_Id = " + customerId;
		if (customerId.equals("")) {
			System.out.println("Deletion cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple removed.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		System.out.println("----------------------------------");	
	}

	/*------------------------------------------------------------------------*
        |  Method: 		deleteRoom
        |
        |  Purpose: 		Take the Room_Id from user and Delete the 
	|					corresponding record record from hfinkbeiner.Room 
	|					table. 
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					Room table is created in hfinkbeiner's database 
	|					and granted INSERT access to public. 
        |   
        |
        |  Post-condition: 	A record will be deleted from hfinkbeiner.Room 
	|					table. 
        |
	|
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
	|
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void deleteRoom(Connection dbconn) {
		// Determine roomId of room to delete
		System.out.println("----------------------------------");
		String roomId = getID("roomID");
		// Construct the query
		String query = "DELETE FROM hfinkbeiner.Room WHERE hfinkbeiner.Room.Room_Id = " + roomId;
		if (roomId.equals("")) {
			System.out.println("Deletion cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple removed.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		System.out.println("----------------------------------");
	}

	/*------------------------------------------------------------------------*
        |  Method: 		deleteBooking
        |
        |  Purpose: 		Take the Booking_Id from user and Delete the 
	|					corresponding record record from hfinkbeiner.Booking 
	|					table. 
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					Booking table is created in hfinkbeiner's database 
	|					and granted INSERT access to public. 
        |   
        |
        |  Post-condition: 	A record will be deleted from hfinkbeiner.Booking 
	|					table. 
        |
	|
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
	|
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void deleteBooking(Connection dbconn) {
		// Determine bookingId of booking to delete
		System.out.println("----------------------------------");
		String bookingId = getID("bookingID");
		// Construct the query
		String query = "DELETE FROM hfinkbeiner.Booking WHERE hfinkbeiner.Booking.Booking_Id = " + bookingId;
		if (bookingId.equals("")) {
			System.out.println("Deletion cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple removed.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		System.out.println("----------------------------------");
		
	}
	
	/*------------------------------------------------------------------------*
        |  Method: 		deleteAmenity
        |
        |  Purpose: 		Take the Amenity_Id from user and Delete the 
	|					corresponding record record from hfinkbeiner.Amenity 
	|					table. 
	|	
	|
        |  Pre-condition:  	The connection to the database is successfully made. 
	|					Amenity table is created in hfinkbeiner's database 
	|					and granted INSERT access to public. 
        |   
        |
        |  Post-condition: 	A record will be deleted from hfinkbeiner.Amenity 
	|					table. 
        |
	|
        |  Parameters:
        |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
        |
	|
        |  Returns:  		None.
        *------------------------------------------------------------------------*/
	private static void deleteAmenity(Connection dbconn) {
		// Determine amenityId of amenity to delete
		System.out.println("----------------------------------");
		String amenityId = getID("amenityID");
		// Construct the query
		String query = "DELETE FROM hfinkbeiner.Amenity WHERE hfinkbeiner.Amenity.Amenity_Id = " + amenityId;
		if (amenityId.equals("")) {
			System.out.println("Deletion cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple removed.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		System.out.println("----------------------------------");
	}

	/*------------------------------------------------------------------------*
    |  Method: 			deleteShift
    |
    |  Purpose: 		Take the Shift_Id from user and Delete the 
	|					corresponding record record from hfinkbeiner.Shift 
	|					table. 
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					Shift table is created in hfinkbeiner's database 
	|					and granted INSERT access to public. 
    |   
    |
    |  Post-condition: 	A record will be deleted from hfinkbeiner.Shift 
	|					table. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void deleteShift(Connection dbconn) {
		// Determine the shiftId of shift to delete
		System.out.println("----------------------------------");
		String shiftId = getID("ShiftID");
		// Construct the query
		String query = "DELETE FROM hfinkbeiner.Shift WHERE hfinkbeiner.Shift.Shift_Id = " + shiftId;
		if (shiftId.equals("")) {
			System.out.println("Deletion cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple removed.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		System.out.println("----------------------------------");
	}

	/*------------------------------------------------------------------------*
    |  Method: 			deleteRating
    |
    |  Purpose: 		Take the Rating_Id from user and Delete the 
	|					corresponding record record from hfinkbeiner.Rating 
	|					table. 
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					Rating table is created in hfinkbeiner's database 
	|					and granted INSERT access to public. 
    |   
    |
    |  Post-condition: 	A record will be deleted from hfinkbeiner.Rating 
	|					table. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void deleteRating(Connection dbconn) {
		// Determine the ratingId of rating the delete
		System.out.println("----------------------------------");
		String ratingId = getID("ratingID");
		// Construct the query
		String query = "DELETE FROM hfinkbeiner.Rating WHERE hfinkbeiner.Rating.Rating_Id = " + ratingId;
		if (ratingId.equals("")) {
			System.out.println("Deletion cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple removed.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		System.out.println("----------------------------------");
	}

	/*------------------------------------------------------------------------*
    |  Method: 			deleteEmployee
    |
    |  Purpose: 		Take the Employee_id from user and Delete the 
	|					corresponding record record from hfinkbeiner.Employee 
	|					table. 
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					Employee table is created in hfinkbeiner's database 
	|					and granted INSERT access to public. 
    |   
    |
    |  Post-condition: 	A record will be deleted from hfinkbeiner.Employee 
	|					table. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void deleteEmployee(Connection dbconn) {
		// Determine employeeId of employee to delete
		System.out.println("----------------------------------");
		String employeeId = getID("employeeId");
		// Construct the query
		String query = "DELETE FROM hfinkbeiner.Employee WHERE hfinkbeiner.Employee.Employee_id = " + employeeId;
		if (employeeId.equals("")) {
			System.out.println("Deletion cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple removed.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		System.out.println("----------------------------------");
		
	}

	/*------------------------------------------------------------------------*
    |  Method: 			deletePaidService
    |
    |  Purpose: 		Take the Service_Id from user and Delete the 
	|					corresponding record record from hfinkbeiner.Paid_Service 
	|					table. 
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					Paid_Service table is created in hfinkbeiner's database 
	|					and granted INSERT access to public. 
    |   
    |
    |  Post-condition: 	A record will be deleted from hfinkbeiner.Paid_Service 
	|					table. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void deletePaidService(Connection dbconn) {
		// Determine serviceId of PaidService to delete
		System.out.println("----------------------------------");
		String serviceId = getID("serviceID");
		// Construct the query
		String query = "DELETE FROM hfinkbeiner.Paid_Service WHERE hfinkbeiner.Paid_Service.Service_Id = " + serviceId;
		if (serviceId.equals("")) {
			System.out.println("Deletion cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple removed.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		System.out.println("----------------------------------");
	}

	/*------------------------------------------------------------------------*
    |  Method: 			update
    |
    |  Purpose: 		Handle record updating command coming from the users.
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					All necessary tables are created in hfinkbeiner's 
	|					database and granted UPDATE access to public. 
    |   
    |
    |  Post-condition: 	Records from desired tables will be updated. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void update(Connection dbconn) {
		while(true) {
			int nextInt; // Stores user selected number
			// Print out the user options
			System.out.println("----------------------------------");
			System.out.println("Update Options: ");
			System.out.println("Enter 0 to return to previous menu.");
			System.out.println("Enter 1 to update a Customer.");
			System.out.println("Enter 2 to update a Room.");
			System.out.println("Enter 3 to update a Booking.");
			System.out.println("Enter 4 to update a Amenity");
			System.out.println("Enter 5 to update a Shift");
			System.out.println("Enter 6 to update a Rating");
			System.out.println("Enter 7 to update a Employee");
			System.out.println("Enter 8 to update a PaidService");
			Scanner input = new Scanner(System.in);
			// Determine if input was given
			try {
				nextInt = input.nextInt();
			}
			catch(Exception e){
				System.out.println("Please enter 1 number");
				continue;
			}
			// Return to previous menu
			if(nextInt == 0) {
				return;
			}
			// Update customer
			if(nextInt == 1) {
				System.out.println("----------------------------------");
				updateCustomer(dbconn);
				continue;
			}
			// Update room
			if(nextInt == 2) {
				System.out.println("----------------------------------");
				updateRoom(dbconn);
				continue;
			}
			// Update booking
			if(nextInt == 3) {
				System.out.println("----------------------------------");
				updateBooking(dbconn);
				continue;
			}
			// Update amenity
			if(nextInt == 4) {
				System.out.println("----------------------------------");
				updateAmenity(dbconn);
				continue;
			}
			// Update shift
			if(nextInt == 5) {
				System.out.println("----------------------------------");
				updateShift(dbconn);
				continue;
			}
			// Update rating
			if(nextInt == 6) {
				System.out.println("----------------------------------");
				updateRating(dbconn);
				continue;
			}
			// Update employee
			if(nextInt == 7) {
				System.out.println("----------------------------------");
				updateEmployee(dbconn);
				continue;
			}
			// Update Paid Service
			if(nextInt == 8) {
				System.out.println("----------------------------------");
				updatePaidService(dbconn);
				continue;
			}
			else {
				System.out.println("Please enter a number in range 1-8");
			}
			
		}
		
	}

	private static String getID(String Idname) {
		String ID;
		while(true) {
			System.out.print("Please enter the " + Idname + " (" + Idname + "):");
			Scanner input = new Scanner(System.in);
			try {
				String[] split = input.nextLine().split(" ");
				ID = split[0];
			}
			catch(Exception e) {
				continue;
			}
			return ID;
		}
	}

	/*------------------------------------------------------------------------*
    |  Method: 			updateCustomer
    |
    |  Purpose: 		Take the Customer_Id and the values it wants to change 
	|					from user. The corresponding record will be updated in 
	|					the hfinkbeiner.Customer table.
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					Customer table is created in hfinkbeiner's database 
	|					and granted UPDATE access to public. 
    |   
    |
    |  Post-condition: 	A record in hfinkbeiner.Customer table will be 
	|					updated. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void updateCustomer(Connection dbconn) {
		// Determine customerId of customer to update
		System.out.println("----------------------------------");
		String customerId = getID("CustomerId");
		if (customerId.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Print out user prompt
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the features to change & their new value in the form...");
		System.out.println("	id='123', name='Alfred', city='Tucson'");
		System.out.print("The avalible features for Customer are: <CUSTOMER_ID, FIRST_NAME, LAST_NAME, CUSTOMER_TYPE, ");
		System.out.println("CLUB460_MEMBER, CARDTYPE>");
		String changes = input.nextLine();
		// Construct the query
		String query = "UPDATE hfinkbeiner.Customer SET " + changes + " WHERE hfinkbeiner.Customer.Customer_Id = " + customerId;
		if (changes.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple updated.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		System.out.println("----------------------------------");
	}

	/*------------------------------------------------------------------------*
    |  Method: 			updateRoom
    |
    |  Purpose: 		Take the Room_Id and the values it wants to change from 
	|					user. The corresponding record will be updated in the 
	|					hfinkbeiner.Paid_Service table.
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					Customer table is created in hfinkbeiner's database 
	|					and granted UPDATE access to public. 
    |   
    |
    |  Post-condition: 	A record in hfinkbeiner.Customer table will be 
	|					updated. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void updateRoom(Connection dbconn) {
		// Determine roomId of room to update
		System.out.println("----------------------------------");
		String roomId = getID("RoomId");
		if (roomId.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Print out user prompt
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the features to change & their new value in the form...");
		System.out.println("	id='123', name='Alfred', city='Tucson'");
		System.out.print("The avalible features for Room are: <ROOM_ID, ROOM_SIZE, PRICE, BED_NUM, ");
		System.out.println("BATH_NUM, TYPE>");
		String changes = input.nextLine();
		// Construct the query
		String query = "UPDATE hfinkbeiner.Room SET " + changes + " WHERE hfinkbeiner.Room.Room_Id = " + roomId;
		if (changes.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple updated.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		System.out.println("----------------------------------");
	}

	/*------------------------------------------------------------------------*
    |  Method: 			updateBooking
    |
    |  Purpose: 		Take the Booking_Id and the values it wants to change 
	|					from user. The corresponding record will be updated in 
	|					the hfinkbeiner.Booking table.
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					Booking table is created in hfinkbeiner's database 
	|					and granted UPDATE access to public. 
    |   
    |
    |  Post-condition: 	A record in hfinkbeiner.Booking table will be 
	|					updated. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void updateBooking(Connection dbconn) {
		// Determine the bookingId of booking to update
		System.out.println("----------------------------------");
		String bookingId = getID("BookingId");
		if (bookingId.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Print out the user prompt
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the features to change & their new value in the form...");
		System.out.println("	id='123', name='Alfred', city='Tucson'");
		System.out.print("The avalible features for Booking are: <BOOKING_ID, ROOM_ID, CUSTOMER_ID, DATE_TO, ");
		System.out.println("DATE_FROM, TIP>");
		String changes = input.nextLine();
		// Construct the query
		String query = "UPDATE hfinkbeiner.Booking SET " + changes + " WHERE hfinkbeiner.Booking.Booking_Id = " + bookingId;
		if (changes.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple updated.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}
		System.out.println("----------------------------------");
	}

	/*------------------------------------------------------------------------*
    |  Method: 			updateAmenity
    |
    |  Purpose: 		Take the Amenity_Id and the values it wants to change 
	|					from user. The corresponding record will be updated in 
	|					the hfinkbeiner.Amenity table.
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					Amenity table is created in hfinkbeiner's database 
	|					and granted UPDATE access to public. 
    |   
    |
    |  Post-condition: 	A record in hfinkbeiner.Amenity table will be 
	|					updated. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void updateAmenity(Connection dbconn) {
		// Determine amenityId of amenity to update
		System.out.println("----------------------------------");
		String amenityId = getID("AmenityId");
		if (amenityId.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Print out user prompt
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the features to change & their new value in the form...");
		System.out.println("	id='123', name='Alfred', city='Tucson'");
		System.out.print("The avalible features for Amenity are: <AMENITY_ID, ROOM_ID, TIME_OPEN, TIME_CLOSE, ");
		System.out.println("TYPE, PRICE>");
		String changes = input.nextLine();
		// Construct the query
		String query = "UPDATE hfinkbeiner.Amenity SET " + changes + " WHERE hfinkbeiner.Amenity.Amenity_Id = " + amenityId;
		if (changes.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple updated.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}


		System.out.println("----------------------------------");
	
	}

	/*------------------------------------------------------------------------*
    |  Method: 			updateShift
    |
    |  Purpose: 		Take the Shift_Id and the values it wants to change 
	|					from user. The corresponding record will be updated in 
	|					the hfinkbeiner.Shift table.
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					Shift table is created in hfinkbeiner's database 
	|					and granted UPDATE access to public. 
    |   
    |
    |  Post-condition: 	A record in hfinkbeiner.Shift table will be 
	|					updated. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void updateShift(Connection dbconn) {
		// Determine shiftId of shift to update
		System.out.println("----------------------------------");
		String shiftId = getID("ShiftId");
		if (shiftId.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Print out user prompt
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the features to change & their new value in the form...");
		System.out.println("	id='123', name='Alfred', city='Tucson'");
		System.out.print("The avalible features for Shift are: <SHIFT_ID, EMPLOYEE_ID, TASK_ID, SHIFT_DATE, ");
		System.out.println("START_TIME, FINISH_TIME>");
		String changes = input.nextLine();
		// Construct the query
		String query = "UPDATE hfinkbeiner.Shift SET " + changes + " WHERE hfinkbeiner.Shift.Shift_Id = " + shiftId;
		if (changes.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}	
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple updated.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}


		System.out.println("----------------------------------");
		
	}

	/*------------------------------------------------------------------------*
    |  Method: 			updateRating
    |
    |  Purpose: 		Take the Rating_Id and the values it wants to change 
	|					from user. The corresponding record will be updated in 
	|					the hfinkbeiner.Rating table.
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					Rating table is created in hfinkbeiner's database 
	|					and granted UPDATE access to public. 
    |   
    |
    |  Post-condition: 	A record in hfinkbeiner.Rating table will be 
	|					updated. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void updateRating(Connection dbconn) {
		// Determine ratingId of rating to update
		System.out.println("----------------------------------");
		String ratingId = getID("RatingId");
		if (ratingId.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Print out user prompt
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the features to change & their new value in the form...");
		System.out.println("	id='123', name='Alfred', city='Tucson'");
		System.out.print("The avalible features for Rating are: <RATING_ID, CUSTOMER_ID, AMENITY_ID, CUSTOMER_TYPE, ");
		System.out.println("STAR_LEVEL, RATING_DATE>");
		String changes = input.nextLine();
		// Construct the query
		String query = "UPDATE hfinkbeiner.Rating SET " + changes + " WHERE hfinkbeiner.Rating.Rating_Id = " + ratingId;
		if (changes.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple updated.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}


		System.out.println("----------------------------------");
		
	}

	/*------------------------------------------------------------------------*
    |  Method: 			updateEmployee
    |
    |  Purpose: 		Take the Employee_Id and the values it wants to change 
	|					from user. The corresponding record will be updated in 
	|					the hfinkbeiner.Employee table.
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					Employee table is created in hfinkbeiner's database 
	|					and granted UPDATE access to public. 
    |   
    |
    |  Post-condition: 	A record in hfinkbeiner.Employee table will be 
	|					updated. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void updateEmployee(Connection dbconn) {
		// Determine employeeId of employee to update
		System.out.println("----------------------------------");
		String employeeId = getID("EmployeeId");
		if (employeeId.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Print out user prompt
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the features to change & their new value in the form...");
		System.out.println("	id='123', name='Alfred', city='Tucson'");
		System.out.print("The avalible features for Employee are: <EMPLOYEE_ID, FIRST_NAME, LAST_NAME, TIME_EMPLOYED, ");
		System.out.println("EMPLOYEMENT_END, POSITION>");
		String changes = input.nextLine();
		// Construct the query
		String query = "UPDATE hfinkbeiner.Employee SET " + changes + " WHERE hfinkbeiner.Employee.Employee_Id = " + employeeId;
		if (changes.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple updated.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}


		System.out.println("----------------------------------");
		
	}

	/*------------------------------------------------------------------------*
    |  Method: 			updatePaidService
    |
    |  Purpose: 		Take the Service_Id and the values it wants to change 
	|					from user. The corresponding record will be updated in 
	|					the hfinkbeiner.Paid_Service table.
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					Paid_Service table is created in hfinkbeiner's database 
	|					and granted UPDATE access to public. 
    |   
    |
    |  Post-condition: 	A record in hfinkbeiner.Paid_Service table will be 
	|					updated. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void updatePaidService(Connection dbconn) {
		// Determine the serviceId of PaidServce to update
		System.out.println("----------------------------------");
		String serviceId = getID("serviceID");
		if (serviceId.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Print out user prompt
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the features to change & their new value in the form...");
		System.out.println("	id='123', name='Alfred', city='Tucson'");
		System.out.print("The avalible features for Paid Service are: <SERVICE_ID, CUSTOMER_ID, BOOKING_ID, ");
		System.out.println("AMENITY_ID, SERVICE_DATE, TIP>");
		String changes = input.nextLine();
		// Construct the query
		String query = "UPDATE hfinkbeiner.Paid_Service SET " + changes + " WHERE hfinkbeiner.Paid_Service.Service_Id = " + serviceId;
		if (changes.equals("")) {
			System.out.println("Update cancelled.");
			return;
		}
		// Execute the query
		try {
			Statement stmt = dbconn.createStatement();
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Tuple updated.");

		} catch (SQLException e) {
            		System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
                	System.err.println("\tMessage:   " + e.getMessage());
                	System.err.println("\tSQLState:  " + e.getSQLState());
                	System.err.println("\tErrorCode: " + e.getErrorCode());
                	System.exit(-1);
        	}


		System.out.println("----------------------------------");
		
	}

	/*------------------------------------------------------------------------*
    |  Method: 			querys
    |
    |  Purpose: 		Handle Querys command coming from the users.
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					All necessary tables are created in hfinkbeiner's 
	|					database and granted SELECT access to public. 
    |   
    |
    |  Post-condition: 	Query results will be printed to screen. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void querys(Connection dbconn) {
		while(true) {
			int nextInt;
			System.out.println("----------------------------------");
			System.out.println("Query Options: ");
			System.out.println("Enter 0 to return to previous menu");
			System.out.println("Enter 1 for the current bills of a customer for their stay and all unpaid amenities.");
			System.out.println("Enter 2 for the customers that are in the hotel on a certain date, sorted by room number and group by category.");
			System.out.println("Enter 3 for the week schedule of staff given a start date.");
			System.out.println("Enter 4 for the average ratings of different amenities recorded within the range of two given dates sorted in descending order. ");
			System.out.println("Enter 5 for TBD\n");
			Scanner input = new Scanner(System.in);
			try {
				nextInt = input.nextInt();
			}
			catch(Exception e){
				System.out.println("Please enter 1 number");
				continue;
			}
			if (nextInt == 0) {
				System.out.println("----------------------------------");
				return;
			}
			if (nextInt == 1) {
				System.out.println("----------------------------------");
				bill(dbconn);
				continue;
			} 
			if (nextInt == 2) {
				System.out.println("----------------------------------");
				customers(dbconn);
				continue;
			}
			if (nextInt == 3) {
				System.out.println("----------------------------------");
				schedule(dbconn);
				continue;
			}
			if (nextInt == 4) {
				System.out.println("----------------------------------");
				averageRating(dbconn);
				continue;
			}
			if (nextInt == 5) {
				System.out.println("----------------------------------");
				tbd(dbconn);
				return;
			}
			else {
				System.out.println("Please enter a number in range 1-5");
			}
		}
		
	}

	/*------------------------------------------------------------------------*
    |  Method: 			bill
    |
    |  Purpose: 		Print a current bill for a customer for their stay and 
	|					all unpaid amenities.
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					All 9 necessary tables are created in hfinkbeiner's 
	|					database and granted SELECT access to public. 
    |   
    |
    |  Post-condition: 	The bills of the given customer is printed to screen. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	@SuppressWarnings({ "resource", "unused" })
	private static void bill(Connection dbconn) {
		// read user input
		Scanner input = new Scanner(System.in);
        // hold first name
		String firstName = "";
		// hold last name
		String lastName = "";
		// hold the query
        String query = "";
        // get the current date in correct format
		java.util.Date date =  Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");  
		// hold the current date
		String today = dateFormat.format(date);			
		// String today = "20-APR-23";	
		System.out.println("----------------------------------");
		while(true) {
			// take info from user
			System.out.print("Please enter the customer name (first last):");
			System.out.println();
			String[] split = input.nextLine().split(" ");
			// Error
			if(split.length != 2) {
				System.out.println("Please enter in the correct format. Try again.");
				continue;
			}
			try{
				firstName = split[0];
				lastName  = split[1];
				
				// query the custoemr record
				// execute a static SQL statement and return the results it produces
				Statement stmt = dbconn.createStatement();	
				query = "SELECT Customer_Id, Customer_Type, Club460_Member, CardType "
						+ "FROM hfinkbeiner.customer "
						+ "WHERE First_Name = '" + firstName + "' AND Last_Name = '" 
						+ lastName + "'";
				ResultSet customer = stmt.executeQuery(query);
				
				String cid = "";
				String customerType = "";
				boolean membership = false;
				String cardType = "";
				while (customer.next()) {
					cid = customer.getString("Customer_Id");
					customerType = customer.getString("Customer_Type");
					membership = customer.getBoolean("Club460_Member");
					cardType = customer.getString("CardType");
				}
				
				// query all current bookings
				query = "SELECT b.Booking_Id, b.Room_Id, b.Date_From, b.Date_To, "
						+ "b.Tip, (b.Date_To - b.Date_From) days "
						+ "FROM hfinkbeiner.booking b, hfinkbeiner.customer c "
						+ "WHERE b.Date_From <= '" + today + "' AND b.Date_To >= '" 
						+ today + "' AND b.Customer_Id = c.Customer_Id AND "
						+ "c.First_Name = '" + firstName + "' AND c.Last_Name = '" 
						+ lastName +"'";
				ResultSet currBills = stmt.executeQuery(query);
				
				// Check Error 
				if (customer == null) {
					System.out.println("Customer not found.");
					return;
				} else if (currBills == null) {
					System.out.println("No current bills for this customer. ");
					return;
				}
				
				// print all current bills 
				long total = 0;
				long currTotal = 0;

				System.out.println();
				while (currBills.next()) {
					Statement loopStmt = dbconn.createStatement();	
					currTotal = 0;
					
					// grab current booking data and print
					String bid = currBills.getString("Booking_Id");
					String rid = currBills.getString("Room_Id");
					float btip = currBills.getFloat("Tip");
					int days = currBills.getInt("Days") - 1;
					
					// Print booking data
					System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("Bill " + bid + ": \n");
					System.out.println("Date From\tDate To\t\t"
										+ "Total Nights\tRoom Price\tTips");
					System.out.print(currBills.getDate("Date_From") + "\t" 
								 	 + currBills.getDate("Date_To") + "\t" 
									 + days + "\t\t");
					
					// grab room info and print
					float roomPrice = 0;
					query = "SELECT r.Price FROM hfinkbeiner.Booking b, hfinkbeiner.Room r "
							+ "WHERE b.Room_Id = r.Room_Id AND b.Room_Id = " + rid;
					ResultSet room = loopStmt.executeQuery(query);
					if (room.next()) roomPrice = room.getFloat("Price");
					// add total room price
					currTotal += days * room.getFloat("Price") + btip;
					
					// print result
					System.out.println(roomPrice + "\t\t" + btip + "\n");
					
					// print all amenities
					query = "SELECT t.Service_Id, t.Service_Date, t.Tip, t.Type, "
							+ "t.Price FROM hfinkbeiner.Booking b, "
							+ "(SELECT p.Service_Id, a.Amenity_Id, p.Booking_Id, "
							+ "p.Service_Date, p.Tip, a.Type, a.Price FROM "
							+ "hfinkbeiner.Amenity a, hfinkbeiner.Paid_Service p "
							+ "WHERE a.Amenity_Id = p.Amenity_Id) t WHERE "
							+ "b.Booking_Id = t.Booking_Id AND b.Booking_Id = " 
							+ bid;
					ResultSet allAmenUsed = loopStmt.executeQuery(query);
					if (allAmenUsed == null) System.out.println("No Amenity Used During Stay.");
					else {
						System.out.println("Amenities Used: ");
						System.out.println("Service Id\t\t\t\tAmenity\t\tDay Used\tPrice\tTip");
						while (allAmenUsed.next()) {
							float price = allAmenUsed.getFloat("Price");
							float tip = allAmenUsed.getFloat("tip");
							System.out.println(allAmenUsed.getString("Service_Id") + "\t" +
											   allAmenUsed.getString("Type") + "\t\t\t" +
											   allAmenUsed.getDate("Service_Date") + "\t" +
											   price + "\t" + tip);
							currTotal += price + tip;
						}
					}
					
					// print end result 
					System.out.println("\nSubtotal: " + currTotal);
					System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					total += currTotal;
				}
				System.out.println("\nTotal before discount: " + total);
				float discounted = total;
				if (cardType.equals("Discover")) discounted *= 0.95;
				else if (cardType.equals("American Express")) discounted *= 0.98;
				if (membership) discounted *= 0.8;
				if (customerType.equals("student")) discounted *= 0.95;
				System.out.println("Total after discount: " + discounted + "\n");
				
			} catch (SQLException e) {
		            System.err.println("*** SQLException:  "
		                + "Could not fetch query results.");
		            System.err.println("\tMessage:   " + e.getMessage());
		            System.err.println("\tSQLState:  " + e.getSQLState());
		            System.err.println("\tErrorCode: " + e.getErrorCode());
					System.out.println();
					e.printStackTrace(System.out);
		    }
			break;
		}
	}
	
	/*------------------------------------------------------------------------*
    |  Method: 			checkDateFormat
    |
    |  Purpose: 		Given a String, check whether it's a correct date in 
    |					the format "dd-MMM-yy".
	|	
	|
    |  Pre-condition:  	N/A
    |   
    |
    |  Post-condition: 	N/A
    |
	|
    |  Parameters:
    |      	date  -- 	A string.
    |
	|
    |  Returns:  		
    |		true	--	If the input string is a valid date in the correct 
    |					format.
    |					
    |		false	--	If the input string is not a valid date or not in the 
    |					correct format.				
    *------------------------------------------------------------------------*/
	private static boolean checkDateFormat(String date) {
		// date format in our database
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		try { 
			dateFormat.parse(date); 
			return true;
		} catch (ParseException e) { return false; }
	}

	/*------------------------------------------------------------------------*
    |  Method: 			customers
    |
    |  Purpose: 		Given a certain date, output the customers that are 
    |					staying at the hoel along with their room numbers.
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					All 9 necessary tables are created in hfinkbeiner's 
	|					database and granted SELECT access to public. 
    |   
    |
    |  Post-condition: 	All customers staying at the hotel at the given date 
    |					was printed to screen along with their room number, 
    |					sorted by room number and group by category. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void customers(Connection dbconn) {
		// read user input
		Scanner input = new Scanner(System.in);
		// hold input date
		String currDate = "";
		// date format in our database
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		System.out.println("----------------------------------");
		while(true) {
			System.out.print("Please enter the date (dd-MMM-yy): ");
			currDate = input.nextLine();
			System.out.println();
			// check input format
			if (!checkDateFormat(currDate)) {
				System.out.println("Not a valid date. Try again. ");
				continue;
			} else break;
		}
		// do the query
		try {
			// hold the query
	        String query = "";
			// execute a static SQL statement and return the results it produces
			Statement stmt = dbconn.createStatement(); 
			// grab all customer categories
			// hold query results
			query = "SELECT DISTINCT Customer_Type FROM hfinkbeiner.Customer";
			ResultSet answer = stmt.executeQuery(query);
			List<String> types = new ArrayList<String>();
			while (answer.next()) types.add(answer.getString("Customer_Type"));
			
			System.out.println("First Name\tLast Name\tRoom Id\t\t\t\t\t  Customer Type");
			System.out.println("==========\t=========\t=======\t\t\t\t\t  =============");

			if (types.size() == 0) {
				query = "SELECT t.Room_Id, c.First_Name, c.Last_Name, c.Customer_Type "
						+ "FROM hfinkbeiner.Customer c, (SELECT b.Booking_Id, r.Room_Id, "
						+ "b.Customer_Id, b.Date_From, b.Date_To FROM hfinkbeiner.Booking b, "
						+ "hfinkbeiner.Room r WHERE b.Room_Id = r.Room_Id) t WHERE "
						+ "c.Customer_Id = t.Customer_Id AND t.Date_From <= '" + currDate
						+ "' AND t.Date_To >= '" + currDate + "' ORDER BY t.Room_Id";
				answer = stmt.executeQuery(query);
				while (answer.next()) {
					System.out.printf("%-16S", answer.getString("First_Name"));
					System.out.printf("%-17S", answer.getString("Last_Name"));
					System.out.printf("%-38S", answer.getString("Room_Id"));
					System.out.println(answer.getString("Customer_Type"));
				}
			} else {
				for (String type: types) {
					query = "SELECT t.Room_Id, c.first_Name, c.Last_Name, c.Customer_Type "
							+ "FROM hfinkbeiner.Customer c, (SELECT b.Booking_Id, r.Room_Id, "
							+ "b.Customer_Id, b.Date_From, b.Date_To FROM hfinkbeiner.Booking b, "
							+ "hfinkbeiner.Room r WHERE b.Room_Id = r.Room_Id) t WHERE "
							+ "c.Customer_Id = t.Customer_Id AND t.Date_From <= '" + currDate
							+ "' AND t.Date_To >= '" + currDate + "' AND c.Customer_Type = '" 
							+ type + "' ORDER BY t.Room_Id";
					answer = stmt.executeQuery(query);
					while (answer.next()) {
						System.out.printf("%-16S", answer.getString("First_Name"));
						System.out.printf("%-16S", answer.getString("Last_Name"));
						System.out.printf("%-42S", answer.getString("Room_Id"));
						System.out.println(answer.getString("Customer_Type"));
					}
					System.out.println();
				}
			}
			System.out.println();
		} catch (SQLException e) {
                System.err.println("*** SQLException:  "
                    + "Could not fetch query results.");
                System.err.println("\tMessage:   " + e.getMessage());
                System.err.println("\tSQLState:  " + e.getSQLState());
                System.err.println("\tErrorCode: " + e.getErrorCode());
				System.out.println();
				e.printStackTrace(System.out);
        }
	}
	
	

	/*------------------------------------------------------------------------*
    |  Method: 			schedule
    |
    |  Purpose: 		Given the start date of a week, print out the schedule
    |					of staff of that week. 
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					All 9 necessary tables are created in hfinkbeiner's 
	|					database and granted SELECT access to public. 
    |   
    |
    |  Post-condition: 	Week schedule and staff members' working hours are
    |					printed on screen. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void schedule(Connection dbconn) {
		// read user input
		Scanner input = new Scanner(System.in);
		// hold user input date
		String weekStart = "";
		System.out.println("----------------------------------");
		while(true) {
			System.out.print("Please enter the date (dd-MMM-yy): ");
			weekStart = input.nextLine();
			System.out.println();
			// check input format
			if (!checkDateFormat(weekStart)) {
				System.out.println("Not a valid date. Try again. ");
				continue;
			} else break;
		}
		// do the query
		try {
			// hold the query
	        String query = "";
			// execute a static SQL statement and return the results it produces
			Statement stmt = dbconn.createStatement(); 
			// grab all customer categories
			// hold query results
			query = "SELECT Employee_Id, Shift_Date, Start_Time, finish_Time, "
					+ "((Finish_Time - Start_Time)/100) Total_Hours FROM "
					+ "hfinkbeiner.Shift WHERE Shift_Date >= '" + weekStart 
					+ "' AND Shift_Date - 6 <= '" + weekStart + "' "
					+ "ORDER BY Shift_Date";
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Schedule of the week starting " + weekStart + ": \n");
			System.out.println("Employee ID\t\t\t\tShift Date\tStart Time\tEnd Time\tToal Hours");
			System.out.println("===========\t\t\t\t==========\t==========\t========\t==========");
			while (answer.next()) {
				System.out.println(answer.getString("Employee_Id") + "\t" + 
								   answer.getDate("Shift_Date") + "\t" + 
								   answer.getString("Start_Time") + "\t\t" + 
								   answer.getString("Finish_Time") + "\t\t" + 
								   answer.getInt("Total_Hours"));
			}
			System.out.println();
		} catch (SQLException e) {
                System.err.println("*** SQLException:  "
                    + "Could not fetch query results.");
                System.err.println("\tMessage:   " + e.getMessage());
                System.err.println("\tSQLState:  " + e.getSQLState());
                System.err.println("\tErrorCode: " + e.getErrorCode());
				System.out.println();
				e.printStackTrace(System.out);
        }
	}
	
	/*------------------------------------------------------------------------*
    |  Method: 			averageRating
    |
    |  Purpose: 		Given the start date of a week, print out the schedule
    |					of staff of that week. 
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					All 9 necessary tables are created in hfinkbeiner's 
	|					database and granted SELECT access to public. 
    |   
    |
    |  Post-condition: 	Week schedule and staff members' working hours are
    |					printed on screen. 
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void averageRating(Connection dbconn) {
		// read user input
		Scanner input = new Scanner(System.in);
		// hold input start date
		String start = "";
		// hold input end date
		String end = "";
		// date format in our database
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		System.out.println("----------------------------------");
		while(true) {
			System.out.print("Please enter the start date (dd-MMM-yy): ");
			start = input.nextLine();
			// check input format
			if (!checkDateFormat(start)) {
				System.out.println("\nNot a valid start date. Try again. \n");
				continue;
			} else break;
		}
		while (true) {
			System.out.print("Please enter the end date (dd-MMM-yy): ");
			end = input.nextLine();
			System.out.println();
			// check input format
			if (!checkDateFormat(end)) {
				System.out.println("Not a valid end date. Try again. \n");
				continue;
			} else break;
		}
		// do the query
		try {
			// hold the query
	        String query = "";
			// execute a static SQL statement and return the results it produces
			Statement stmt = dbconn.createStatement(); 
			// grab all customer categories
			// hold query results
			query = "SELECT a.Type, AVG(r.Star_Level) Average_Rating FROM "
					+ "hfinkbeiner.Rating r, hfinkbeiner.Amenity a WHERE "
					+ "r.Amenity_Id = a.Amenity_Id AND r.Rating_Date >= '" 
					+ start + "' AND r.Rating_Date <= '" + end + "' GROUP BY "
							+ "a.Type ORDER BY Average_Rating DESC";
			ResultSet answer = stmt.executeQuery(query);
			System.out.println("Query result from " + start + " to " + end + ": \n");
			System.out.println("Amenity\t\t\tAverage Rating");
			System.out.println("=======\t\t\t==============");
			while (answer.next()) {
				System.out.printf("%-24S", answer.getString("Type"));
				System.out.println(answer.getFloat("Average_Rating"));
			}
			System.out.println();
		} catch (SQLException e) {
                System.err.println("*** SQLException:  "
                    + "Could not fetch query results.");
                System.err.println("\tMessage:   " + e.getMessage());
                System.err.println("\tSQLState:  " + e.getSQLState());
                System.err.println("\tErrorCode: " + e.getErrorCode());
				System.out.println();
				e.printStackTrace(System.out);
        }
	}

	/*------------------------------------------------------------------------*
    |  Method: 			tbd
    |
    |  Purpose: 		
	|	
	|
    |  Pre-condition:  	The connection to the database is successfully made. 
	|					All 9 necessary tables are created in hfinkbeiner's 
	|					database and granted SELECT access to public. 
    |   
    |
    |  Post-condition: 	
    |
	|
    |  Parameters:
    |      dbconn   -- 	The connection to the database that the queries will
	|				 	use to get the data.
    |
	|
    |  Returns:  		None.
    *------------------------------------------------------------------------*/
	private static void tbd(Connection dbconn) {
		// TODO Auto-generated method stub
		
	}

	

	
}
