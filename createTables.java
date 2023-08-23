/*=============================================================================
 |   Assignment:  Program #4: Database Design and Implementation 
 |       Author:  Harrison Finkbeiner (hfinkbeiner@arizona.edu)
 |       Grader:  Tanner Finken, and Aayush Pinto
 |
 |       Course:  CSC 460
 |   Instructor:  L. McCann
 |     Due Date:  May 2nd, 2023
 |
 |  Description:  This program will accept a username and password for an orcale 
 |		account and if there is no input then the system will exit.
 | 		The program will then create all the tables needed for the program
 |		then populate the tables with starting data.
 |
 |		  
 |
 |     Language:  Java 16  
 | Ex. Packages:  java.io 
 |		  java.sql
 |		 
 | Deficiencies:   
 |
 *===========================================================================*/

import java.io.*;
import java.sql.*;                 // For access to the SQL interaction methods
import java.util.*;

/*+----------------------------------------------------------------------
 ||
 ||  Class createRelations
 ||
 ||         Author:  Harrison Finkbeiner
 ||
 ||        Purpose:  create the relations for motel460. The relations to 
 ||				be created are: Customer, Room, Booking, Amenity, Task, Shift,
 ||				PaidService, Rating, and Employee.
 || 		      
 ||
 ||  Inherits From:  None.
 ||
 ||     Interfaces:  None.
 ||
 |+-----------------------------------------------------------------------
 ||
 ||      Constants:   oracleURL - URL to connect to Orcale database
 ||
 |+-----------------------------------------------------------------------
 ||
 ||   Constructors:  Just the default constructor.
 ||
 ||  Class Methods: createTablesCustomer(Connection dbconn)
 ||					createTablesEmployee(Connection dbconn)
 ||					loadTables(Connection dbconn, Statement stmt)
 ||		     
 ||		     
 ||
 ||  Inst. Methods:  None.
 ||                  
 ||
 ++-----------------------------------------------------------------------*/

public class createTables
{


    /*---------------------------------------------------------------------
    |  Method createTablesCustomer
    |
    |  Purpose: Creates the relations needed to handle customers for motel460. 
	|			The relations are created in the database.
	|	
    |
    |  Pre-condition:  The connnection to the database must be made before creating the table.
    |   
    |
    |  Post-condition: The tables will be created within the database.
    |
    |  Parameters:
    |      dbconn -- The connection to the database that the queries will
	|				use to get the data.
    |
    |  Returns:  None.
    *-------------------------------------------------------------------*/
	private static void createTablesCustomer(Connection dbconn){
        Statement stmt = null;
        try {
			dbconn.setAutoCommit(false);
       		stmt = dbconn.createStatement();
			String createCustomer = "create table Customer("
				+ "Customer_Id  Number(38,0) NOT NULL, " 
				+ "First_Name VARCHAR2(100), "
				+ "Last_Name VARCHAR2(100), "
				+ "Customer_Type VARCHAR2(50), " //adjust once determine all customer types
				+ "Club460_Member Number(38,0), " // will only be 0 for not member and 1 for is member
				+ "CardType VARCHAR2(100), "
				+ "PRIMARY KEY(Customer_Id))"; 

			String dropCustomer = "DROP table Customer purge";
			stmt.executeUpdate(dropCustomer);			
			stmt.executeUpdate(createCustomer);
			String grantAccess = "GRANT SELECT ON Customer TO PUBLIC";
			stmt.executeQuery(grantAccess);


			String createRoom = "create table Room("
				+ "Room_Id Number(38,0) NOT NULL, " 
				+ "Room_Size Number(38,0), "
				+ "Price Number(38,2), "
				+ "Bed_Num Number(38,0),  "
				+ "Bath_Num Number(38,0), "
				+ "Type VARCHAR2(50), " 
				+ "Bed_Type VARCHAR2(30), "
				+ "PRIMARY KEY(Room_Id)) "; 

			String dropRoom = "DROP table Room purge";
			stmt.executeUpdate(dropRoom);			
			stmt.executeUpdate(createRoom);
			grantAccess = "GRANT SELECT ON Room TO PUBLIC";
			stmt.executeQuery(grantAccess);
				   
		   String createBooking = "create table Booking("
				+ "Booking_Id  Number(38,0) NOT NULL, " 
				+ "Room_Id Number(38,0), "
				+ "Customer_Id Number(38,0), "
				+ "Date_To DATE NOT NULL,  "
				+ "Date_From DATE NOT NULL, "
				+ "Tip Number(38,2), " 
				+ "PRIMARY KEY(Booking_Id)) "; 

			String dropBooking = "DROP table Booking purge";
			stmt.executeUpdate(dropBooking);			
			stmt.executeUpdate(createBooking);
			grantAccess = "GRANT SELECT ON Booking TO PUBLIC";
			stmt.executeQuery(grantAccess);
			stmt.executeQuery("COMMIT");

		   String createAmenity = "create table Amenity("
				+ "Amenity_Id  Number(38,0) NOT NULL, " 
				+ "Room_Id Number(38,0), "
				+ "Time_Open Number(38,0) NOT NULL,  "
				+ "Time_Close Number(38,0) NOT NULL, "
				+ "Type VARCHAR2(100), "
				+ "Price Number(38,2), " 
				+ "PRIMARY KEY(Amenity_Id)) "; 

			String dropAmenity = "DROP table Amenity purge";
			stmt.executeUpdate(dropAmenity);			
			stmt.executeUpdate(createAmenity);
			grantAccess = "GRANT SELECT ON Amenity TO PUBLIC";
			stmt.executeQuery(grantAccess);
			stmt.executeQuery("COMMIT");


		   String createPaidService = "create table Paid_Service("
				+ "Service_Id  Number(38,0) NOT NULL, " 
				+ "Customer_Id Number(38,0), "
				+ "Booking_Id Number(38,0), "
				+ "Amenity_Id Number(38,0), "
				+ "Service_Date DATE NOT NULL,  "
				+ "Tip Number(38,2), " 
				+ "PRIMARY KEY(Service_Id)) "; 

			String dropPaidService = "DROP table Paid_Service purge";
			stmt.executeUpdate(dropPaidService);			
			stmt.executeUpdate(createPaidService);
			grantAccess = "GRANT SELECT ON Paid_Service TO PUBLIC";
			stmt.executeQuery(grantAccess);
			stmt.executeQuery("COMMIT");
		   
		   String createRating = "create table Rating("
				+ "Rating_Id  Number(38,0) NOT NULL, " 
				+ "Customer_Id Number(38,0), "
				+ "Amenity_Id Number(38,0), "
				+ "Star_Level Number(38,0),  "
				+ "Rating_Date DATE NOT NULL, " 
				+ "PRIMARY KEY(Rating_Id)) "; 

			String dropRating = "DROP table Rating purge";
			stmt.executeUpdate(dropRating);			
			stmt.executeUpdate(createRating);
			grantAccess = "GRANT SELECT ON Rating TO PUBLIC";
			stmt.executeQuery(grantAccess);
			stmt.executeQuery("COMMIT");

				stmt.close();  

        } catch (SQLException e) {

                System.err.println("*** SQLException:  "
                    + "Could not fetch query results.");
                System.err.println("\tMessage:   " + e.getMessage());
                System.err.println("\tSQLState:  " + e.getSQLState());
                System.err.println("\tErrorCode: " + e.getErrorCode());
                System.exit(-1);
        }
	}

    /*---------------------------------------------------------------------
    |  Method createTablesEmployee
    |
    |  Purpose: Creates the relations needed to handle Employees for motel460. 
	|			The relations are created in the database.
	|	
    |
    |  Pre-condition:  The connnection to the database must be made before creating the table.
    |   
    |
    |  Post-condition: The tables will be created within the database.
    |
    |  Parameters:
    |      dbconn -- The connection to the database that the queries will
	|				use to get the data.
    |
    |  Returns:  None.
    *-------------------------------------------------------------------*/
	private static void createTablesEmployee(Connection dbconn){
        Statement stmt = null;
        try {
			dbconn.setAutoCommit(false);
       		stmt = dbconn.createStatement();
			String createTask = "create table Task("
				+ "Task_Id  Number(38,0) NOT NULL, " 
				+ "Task_Name VARCHAR2(100), "
				+ "PRIMARY KEY(Task_Id))"; 

			String dropTask = "DROP table Task purge";
			stmt.executeUpdate(dropTask);			
			stmt.executeUpdate(createTask);
			String grantAccess = "GRANT SELECT ON Task TO PUBLIC";
			stmt.executeQuery(grantAccess);


			String createShift = "create table Shift("
				+ "Shift_Id  Number(38,0) NOT NULL, " 
				+ "Employee_Id  Number(38,0) NOT NULL, " 
				+ "Task_Id  Number(38,0) NOT NULL, " 
				+ "Shift_Date Date NOT NULL,"
				+ "Start_Time Number(38,0) NOT NULL, "
				+ "Finish_Time Number(38,0) NOT NULL, "
				+ "PRIMARY KEY(Shift_Id)) "; 

			String dropShift = "DROP table Shift purge";
			stmt.executeUpdate(dropShift);			
			stmt.executeUpdate(createShift);
			grantAccess = "GRANT SELECT ON Shift TO PUBLIC";
			stmt.executeQuery(grantAccess);
				   
		   	String createEmployee = "create table Employee("
				+ "Employee_Id  Number(38,0) NOT NULL, " 
				+ "Time_Employed DATE NOT NULL, "
				+ "Employment_End DATE NOT NULL, "
				+ "Position VARCHAR2(100),  "
				+ "PRIMARY KEY(Employee_Id)) "; 

			String dropEmployee = "DROP table Employee purge";
			stmt.executeUpdate(dropEmployee);			
			stmt.executeUpdate(createEmployee);
			grantAccess = "GRANT SELECT ON Employee TO PUBLIC";
			stmt.executeQuery(grantAccess);
			stmt.executeQuery("COMMIT");


			stmt.close();  

        } catch (SQLException e) {

                System.err.println("*** SQLException:  "
                    + "Could not fetch query results.");
                System.err.println("\tMessage:   " + e.getMessage());
                System.err.println("\tSQLState:  " + e.getSQLState());
                System.err.println("\tErrorCode: " + e.getErrorCode());
                System.exit(-1);
        }
	}

    /*---------------------------------------------------------------------
    |  Method loadCustomerTables
    |
    |  Purpose: Added data to the tables in the database so queries can be made to the database 
	|		and hold data from the user interface.
    |
    |  Pre-condition:  The connnection to the database must be made before creating the table.
	|			The Statement object must be created.
    |   
    |
    |  Post-condition: The table will be populated with all the values that we determined that 
	|		will be sufficient to test and demostrate the program. 
    |
    |  Parameters:
    |      dbconn -- The connection to the database that the queries will
	|				use to get the data.
	|	   stmnt -- A Statement that will be used to execute the insert queries.
	|	   
	|	   user -- The username for the user using connected to the database.
    |
    |  Returns:  None.
    *-------------------------------------------------------------------*/
	private static void loadCustomerTables(Connection dbconn,String user){
        	Statement stmt = null;
        	try {
       			stmt = dbconn.createStatement();
       	    	File fileRef = null;                     // provides exists() method
       	      	BufferedReader reader = null;            // provides buffered text I/O
       	    	File fileRef1 = null;                     // provides exists() method
       	      	BufferedReader reader1 = null;            // provides buffered text I/O
       	    	File fileRef2 = null;                     // provides exists() method
       	      	BufferedReader reader2 = null;            // provides buffered text I/O
       	    	File fileRef3 = null;                     // provides exists() method
       	      	BufferedReader reader3 = null;            // provides buffered text I/O
       	    	File fileRef4 = null;                     // provides exists() method
       	      	BufferedReader reader4 = null;            // provides buffered text I/O
       	    	File fileRef5 = null;                     // provides exists() method
       	      	BufferedReader reader5 = null;            // provides buffered text I/O
				
				String route = "/home/hfinkbeiner/CSc-460/Programs/Program4/";
	   	 		String fileCustomer = route + "customer_test.csv";
	   	 		String fileRoom = route + "room_test.csv";
	   	 		String fileBooking = route + "booking_test.csv";
	   	 		String fileAmenity = route + "amenity_test.csv";
	   	 		String filePaidService = route + "paidService_test.csv";
	   	 		String fileRating = route + "rating_test.csv";
	        	fileRef = new File(fileCustomer);
	        	fileRef1 = new File(fileRoom);
	        	fileRef2 = new File(fileBooking);
	        	fileRef3 = new File(fileAmenity);
	        	fileRef4 = new File(filePaidService);
	        	fileRef5 = new File(fileRating);
       	 		
              		// If the CSV file doesn't exist, we can't proceed.
	 	  		if (!fileRef.exists() || !fileRef1.exists() ||!fileRef2.exists() || 
					!fileRef3.exists() || !fileRef4.exists() || !fileRef5.exists()) {
       	       		  System.out.println("PROBLEM:  The input files for customer" 
					  	+ "does not exist in the current directory.");
       	       		  System.out.println("          Create or copy the file to the "
					  	+ "current directory and try again.");
       	       	 	  System.exit(-1);
     	       	}
       		    reader = new BufferedReader(new FileReader(fileRef));
           	    String line = null;  // content of one line/record of the CSV file
		  	    line = reader.readLine(); //Skips over the field names in the CSV file
			
       		    reader1 = new BufferedReader(new FileReader(fileRef1));
           	    String line1 = null;  // content of one line/record of the CSV file
		  	    line1 = reader1.readLine(); //Skips over the field names in the CSV file

       		    reader2 = new BufferedReader(new FileReader(fileRef2));
           	    String line2 = null;  // content of one line/record of the CSV file
		  	    line2 = reader2.readLine(); //Skips over the field names in the CSV file

       		    reader3 = new BufferedReader(new FileReader(fileRef3));
           	    String line3 = null;  // content of one line/record of the CSV file
		  	    line3 = reader3.readLine(); //Skips over the field names in the CSV file

       		    reader4 = new BufferedReader(new FileReader(fileRef4));
           	    String line4 = null;  // content of one line/record of the CSV file
		  	    line4 = reader4.readLine(); //Skips over the field names in the CSV file

       		    reader5 = new BufferedReader(new FileReader(fileRef5));
           	    String line5 = null;  // content of one line/record of the CSV file
		  	    line5 = reader5.readLine(); //Skips over the field names in the CSV file
				String[] field;
				String insert;

			   while((line = reader.readLine()) != null) {
					field = line.split(",");
					String member = "";
					if( field[4].equals("true")){
						member = "1";
					}else{
						member = "0";
					}
					insert = "INSERT INTO " + user + ".Customer  VALUES ("
					+ field[0] + ",'" + field[1] + "','" + field[2] + "','"
					+ field[3] + "'," + member + ",'" + field[5] + "')";
					stmt.executeQuery(insert);
				}

			   while((line1 = reader1.readLine()) != null) {
					field = line1.split(",");
					insert = "INSERT INTO " + user + ".Room  VALUES ("
					+ field[0] + "," + field[1] + "," + field[2].substring(1) 
					+ "," + field[3] + "," + field[4] + ",'" + field[5] 
					+ "','" + field[6] + "')";
					stmt.executeQuery(insert);
				}

			   while((line2 = reader2.readLine()) != null) {
					field = line2.split(",");
					String[] splitStartDate = field[3].split("/");
					String[] splitEndDate = field[4].split("/");
					String formattedStartDate = splitStartDate[2] + '-' + splitStartDate[0] +'-'
						+ splitStartDate[1];
					String formattedEndDate = splitEndDate[2] + '-' + splitEndDate[0] +'-'
						+ splitEndDate[1];
		
					insert = "INSERT INTO " + user + ".Booking  VALUES ( "
						+ field[0] + "," + field[1]  + "," + field[2]
						+ ", DATE '" + formattedStartDate + "'," + "DATE '" + formattedEndDate
						+  "'," + field[5].substring(1) + ")";
					stmt.executeQuery(insert);

				}


			   while((line3 = reader3.readLine()) != null) {
					field = line3.split(",");
					String hhStart = "";
					String mmStart = "";
					String[] timeStart = field[2].split(":");

					String hhEnd = "";
					String mmEnd = "";
					String[] timeEnd = field[3].split(":");

					if( field[2].endsWith("PM")){
						int twentyFourTime = ((Integer.parseInt(timeStart[0])%12) + 12);
						hhStart = String.valueOf(twentyFourTime);
					}else{
						hhStart = timeStart[0];
					}
					mmStart = timeStart[1].substring(0,2);
					
					if( field[3].endsWith("PM")){
						int twentyFourTimeEnd = ((Integer.parseInt(timeEnd[0])%12) + 12);
						hhEnd = String.valueOf(twentyFourTimeEnd);
					}else{
						hhEnd = timeEnd[0];
					}
					mmEnd = timeEnd[1].substring(0,2);

					insert = "INSERT INTO " + user + ".Amenity  VALUES ( "
						+ field[0] + "," + field[1] 
						+ "," + hhStart+ mmStart + "," + hhEnd + mmEnd + ",'" 
						+  field[4] + "'," + field[5].substring(1) + ")";
					stmt.executeQuery(insert);

				}
			   while((line4 = reader4.readLine()) != null) {
					field = line4.split(",");
					String[] splitDate = field[4].split("/");
					String formattedDate = splitDate[2] + '-' + splitDate[0] +'-'
						+ splitDate[1];
					insert = "INSERT INTO " + user + ".Paid_Service  VALUES ( "
						+ field[0] + "," + field[1]  + "," + field[2]
						+ "," + field[3] + ", DATE '" + formattedDate +"'," 
						+  field[4].substring(1) + ")";
					stmt.executeQuery(insert);
				}

			   while((line5 = reader5.readLine()) != null) {
					field = line5.split(",");
					String[] splitDate = field[4].split("/");
					String formattedDate = splitDate[2] + '-' + splitDate[0] +'-'
						+ splitDate[1];
					insert = "INSERT INTO " + user + ".Rating  VALUES ( "
						+ field[0] + "," + field[1] + "," + field[2] + "," 
						+ field[3] + "," + " DATE '" + formattedDate
						+ "')";
				stmt.executeQuery(insert);

				}

				stmt.executeQuery("COMMIT");
				stmt.close();  

				try {
        		    reader.close();
        		    reader1.close();
        		    reader2.close();
        		    reader3.close();
        		    reader4.close();
        		    reader5.close();
        		} catch (IOException e) {
           	    	 System.out.println("VERY STRANGE I/O ERROR: Couldn't close "
                	             + "the CSV file!");
           	    	 System.exit(-1);
				}
       		} catch (IOException e) {
                    System.out.println("I/O ERROR: Couldn't open, or couldn't read "
                             + "from, the CSV file.");
            	    System.exit(-1);
        	} catch (SQLException e) {
					System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
               		 System.err.println("\tMessage:   " + e.getMessage());
               		 System.err.println("\tSQLState:  " + e.getSQLState());
               		 System.err.println("\tErrorCode: " + e.getErrorCode());
	             	 System.exit(-1);
        	} 
			return;
	}


    /*---------------------------------------------------------------------
    |  Method loadTables
    |
    |  Purpose: Added data to the tables in the database so queries can be made to the database 
	|		and hold data from the user interface.
    |
    |  Pre-condition:  The connnection to the database must be made before creating the table.
	|			The Statement object must be created.
    |   
    |
    |  Post-condition: The table will be populated with all the values that we determined that 
	|		will be sufficient to test and demostrate the program. 
    |
    |  Parameters:
    |      dbconn -- The connection to the database that the queries will
	|				use to get the data.
	|	   stmnt -- A Statement that will be used to execute the insert queries.
	|	   
	|	   user -- The username for the user using connected to the database.
    |
    |  Returns:  None.
    *-------------------------------------------------------------------*/
	private static void loadEmployee(Connection dbconn, String user){
        	Statement stmt = null;
        	try {
       			stmt = dbconn.createStatement();
       	    	File fileRef = null;                     // provides exists() method
       	      	BufferedReader reader = null;            // provides buffered text I/O
       	    	File fileRef1 = null;                     // provides exists() method
       	      	BufferedReader reader1 = null;            // provides buffered text I/O
       	    	File fileRef2 = null;                     // provides exists() method
       	      	BufferedReader reader2 = null;            // provides buffered text I/O
				String route = "/home/hfinkbeiner/CSc-460/Programs/Program4/";
	   	 		String fileShift = route + "shift_test.csv";
	   	 		String fileTask = route + "task_test.csv";
	   	 		String fileEmployee = route + "employee_test.csv";
	        	fileRef = new File(fileShift);
	        	fileRef1 = new File(fileTask);
	        	fileRef2 = new File(fileEmployee);
       	 		
              		// If the CSV file doesn't exist, we can't proceed.
	 	  		if (!fileRef.exists() || !fileRef1.exists() ||!fileRef2.exists()) {
					// Fix to display the correct file name.
       	       		  System.out.println("PROBLEM:  The input file for eployee tables"
					  	+ "does not exist in the current directory.");
       	       		  System.out.println("          Create or copy the file to the "
					  	+ "current directory and try again.");
       	       	 	  System.exit(-1);
     	       	}
       		    reader = new BufferedReader(new FileReader(fileRef));
           	    String line = null;  // content of one line/record of the CSV file
		  	    line = reader.readLine(); //Skips over the field names in the CSV file
			
       		    reader1 = new BufferedReader(new FileReader(fileRef1));
           	    String line1 = null;  // content of one line/record of the CSV file
		  	    line1 = reader1.readLine(); //Skips over the field names in the CSV file

       		    reader2 = new BufferedReader(new FileReader(fileRef2));
           	    String line2 = null;  // content of one line/record of the CSV file
		  	    line2 = reader2.readLine(); //Skips over the field names in the CSV file
	  		   
				String[] field;
				String insert;

			   while((line = reader.readLine()) != null) {
					field = line.split(",");
					String[] splitDate = field[3].split("/");
					String formattedDate = splitDate[2] + '-' + splitDate[0] +'-'
						+ splitDate[1];

					String hhStart = "";
					String mmStart = "";
					String[] timeStart = field[4].split(":");

					String hhEnd = "";
					String mmEnd = "";
					String[] timeEnd = field[5].split(":");
					if( field[4].endsWith("PM")){
						int twentyFourTime = ((Integer.parseInt(timeStart[0])%12) + 12);
						hhStart = String.valueOf(twentyFourTime);
					}else{
						hhStart = timeStart[0];
					}
					mmStart = String.valueOf(timeStart[1].substring(0,2));
					
					if( field[5].endsWith("PM")){
						int twentyFourTimeEnd = ((Integer.parseInt(timeEnd[0])%12) + 12);
						hhEnd = String.valueOf(twentyFourTimeEnd);
					}else{
						hhEnd = timeEnd[0];
					}
					mmEnd = timeEnd[1].substring(0,2);
			   	

				insert = "INSERT INTO " + user + ".Shift  VALUES ( "
				+ field[0] + "," + field[1] + "," + field[2] + ","
				+ " DATE '" + formattedDate + "'," 
				+ hhStart + mmStart +"," + hhEnd + mmEnd + ")";
				stmt.executeQuery(insert);
          
				}
			   
			   while((line1 = reader1.readLine()) != null) {
				field = line1.split(",");
				insert = "INSERT INTO " + user + ".Task  VALUES ( "
				+ field[0] + ",'" + field[1] + "')";
				stmt.executeQuery(insert);
				}
			
			   while((line2 = reader2.readLine()) != null) {
					field = line2.split(",");
					String[] splitStartDate = field[1].split("/");
					String[] splitEndDate = field[2].split("/");
					String formattedStartDate = splitStartDate[2] + '-' + splitStartDate[0] +'-'
						+ splitStartDate[1];
					String formattedEndDate = splitEndDate[2] + '-' + splitEndDate[0] +'-'
						+ splitEndDate[1];
					insert = "INSERT INTO " + user + ".Employee  VALUES ( "
						+ field[0] + ", DATE '" + formattedStartDate + "', DATE '" + formattedEndDate
						+"','" +  field[3] + "')";
				stmt.executeQuery(insert);
			
				}
				stmt.executeQuery("COMMIT");
       		
				// Shut down the connection to the DBMS.
				stmt.close();  
				dbconn.close();
        		
				try {
        		    reader.close();
        		    reader1.close();
        		    reader2.close();
        		} catch (IOException e) {
           	    	 System.out.println("VERY STRANGE I/O ERROR: Couldn't close "
                	             + "the CSV file!");
           	    	 System.exit(-1);
				}
       		} catch (IOException e) {
                    System.out.println("I/O ERROR: Couldn't open, or couldn't read "
                             + "from, the CSV file.");
            	    System.exit(-1);
        	} catch (SQLException e) {
					System.err.println("*** SQLException:  "
                    	+ "Could not fetch query results.");
               		 System.err.println("\tMessage:   " + e.getMessage());
               		 System.err.println("\tSQLState:  " + e.getSQLState());
               		 System.err.println("\tErrorCode: " + e.getErrorCode());
	             	 System.exit(-1);
        	} 
			return;
	}
    public static void main (String [] args)
    {
		String username = "";
		String password = "";
        final String oracleURL =   // Magic lectura -> aloe access spell
                        "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";

        if (args.length != 2) {    // Check that command line args are supplied
			System.out.println("Provide a username and password");
			System.exit(-1);
        }else{
            username = args[0];
            password = args[1];
		}
            // load the (Oracle) JDBC driver by initializing its base
            // class, 'oracle.jdbc.OracleDriver'.

        try {

                Class.forName("oracle.jdbc.OracleDriver");

        } catch (ClassNotFoundException e) {

                System.err.println("*** ClassNotFoundException:  "
                    + "Error loading Oracle JDBC driver.  \n"
                    + "\tPerhaps the driver is not on the Classpath?");
                System.exit(-1);

        }

            // make and return a database connection to the user's
            // Oracle database

        Connection dbconn = null;

        try {
                dbconn = DriverManager.getConnection
                               (oracleURL,username,password);

        } catch (SQLException e) {

                System.err.println("*** SQLException:  "
                    + "Could not open JDBC connection.");
                System.err.println("\tMessage:   " + e.getMessage());
                System.err.println("\tSQLState:  " + e.getSQLState());
                System.err.println("\tErrorCode: " + e.getErrorCode());
                System.exit(-1);

        }
			createTablesCustomer(dbconn);
			createTablesEmployee(dbconn);
			loadCustomerTables(dbconn,username); 
			loadEmployee(dbconn,username); 

    }

}
