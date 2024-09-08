import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


// Simulation of a Simple Command-line based Uber App 

// This system supports "ride sharing" service and a delivery service

public class TMUberUI
{
  public static void main(String[] args)
  {
    // Create the System Manager - the main system code is in here 


    TMUberSystemManager tmuber = new TMUberSystemManager();
    
    Scanner scanner = new Scanner(System.in);
    System.out.print(">");

    // Process keyboard actions
    while (scanner.hasNextLine())
    {
      String action = scanner.nextLine();

      if (action == null || action.equals("")) 
      {
        System.out.print("\n>");
        continue;
      }
      // Quit the App
      else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
        return;
      // Print all the registered drivers
      else if (action.equalsIgnoreCase("DRIVERS"))  // List all drivers
      {
        tmuber.listAllDrivers(); 
      }
      // Print all the registered users
      else if (action.equalsIgnoreCase("USERS"))  // List all users
      {
        tmuber.listAllUsers(); 
      }
      // Print all current ride requests or delivery requests
      else if (action.equalsIgnoreCase("REQUESTS"))  // List all requests
      {
        tmuber.listAllServiceRequests(); 
      }
      // Register a new driver
      else if (action.equalsIgnoreCase("REGDRIVER")) 
      {
        String name = "";
        System.out.print("Name: ");
        if (scanner.hasNextLine())
        {
          name = scanner.nextLine();
        }
        String carModel = "";
        System.out.print("Car Model: ");
        if (scanner.hasNextLine())
        {
          carModel = scanner.nextLine();
        }
        String license = "";
        System.out.print("Car License: ");
        if (scanner.hasNextLine())
        {
          license = scanner.nextLine();
        }
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine()) {
          address = scanner.nextLine();
        }
        try {
          tmuber.registerNewDriver(name, carModel, license, address);
          System.out.printf("Driver: %-15s Car Model: %-15s License Plate: %-10s Address: %-10s", name, carModel, license, address);
        } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage());
        } catch (TMUberSystemManager.ExistsInSystemException e) { // exception handling 
          System.out.println(e.getMessage());
        }
          
      }
      // Register a new user
      else if (action.equalsIgnoreCase("REGUSER")) 
      {
        String name = "";
        System.out.print("Name: ");
        if (scanner.hasNextLine())
        {
          name = scanner.nextLine();
        }
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        double wallet = 0.0;
        System.out.print("Wallet: ");
        if (scanner.hasNextDouble())
        {
          wallet = scanner.nextDouble();
          scanner.nextLine(); // consume nl!! Only needed when mixing strings and int/double
        }
        try {
          tmuber.registerNewUser(name, address, wallet);
          System.out.printf("User: %-15s Address: %-15s Wallet: %2.2f", name, address, wallet); 
        } catch (IllegalArgumentException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch (TMUberSystemManager.ExistsInSystemException e) { // exception handling 
          System.out.println(e.getMessage());
        }
          
      }
      // Request a ride
      else if (action.equalsIgnoreCase("REQRIDE")) 
      {
        // Get the following information from the user (on separate lines)
        // Then use the TMUberSystemManager requestRide() method properly to make a ride request
        // "User Account Id: "      (string)
        // "From Address: "         (string)
        // "To Address: "           (string)
        String accountId = "";
        System.out.print("User Account Id: ");
        if (scanner.hasNextLine()) {
          accountId = scanner.nextLine();
        }
        String fromAddress = "";
        System.out.print("From Address: ");
        if (scanner.hasNextLine()) {
          fromAddress = scanner.nextLine();
        }
        String toAddress = "";
        System.out.print("To Address: ");
        if (scanner.hasNextLine()) {
          toAddress = scanner.nextLine(); 
        }
        try {
          tmuber.requestRide(accountId, fromAddress, toAddress);
          System.out.printf("RIDE for: %-15s From: %-15s To: %-15s", tmuber.getUser(accountId).getName(), fromAddress, toAddress);
        } catch (TMUberSystemManager.UserNotFoundException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch(TMUberSystemManager.InvalidAddressException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch (TMUberSystemManager.InsufficientDistanceException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch (TMUberSystemManager.DupliceServiceException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch (TMUberSystemManager.InsufficientFundsException e) { // exception handling 
          System.out.println(e.getMessage());
        } 
      }
      
      // Request a food delivery
      else if (action.equalsIgnoreCase("REQDLVY")) 
      {
        // Get the following information from the user (on separate lines)
        // Then use the TMUberSystemManager requestDelivery() method properly to make a ride request
        // "User Account Id: "      (string)
        // "From Address: "         (string)
        // "To Address: "           (string)
        // "Restaurant: "           (string)
        // "Food Order #: "         (string)

        String userAccountId = "";
        System.out.print("User Account Id: ");
        if (scanner.hasNextLine()) {
          userAccountId = scanner.nextLine();
        }
        String fromAddress = "";
        System.out.print("From Address: ");
        if (scanner.hasNextLine()) {
          fromAddress = scanner.nextLine();
        }
        String toAddress = "";
        System.out.print("To Address: ");
        if (scanner.hasNextLine()) {
          toAddress = scanner.nextLine();
        }
        String restaurant = "";
        System.out.print("Restaurant: ");
        if (scanner.hasNextLine()) {
          restaurant = scanner.nextLine();
        }
        String foodOrderNum = "";
        System.out.print("Food Order #: ");
        if (scanner.hasNextLine()) {
          foodOrderNum = scanner.nextLine();
        }
        try {
          tmuber.requestDelivery(userAccountId, fromAddress, toAddress, restaurant, foodOrderNum);
          System.out.printf("DELIVERY for: %-15s From: %-15s To: %-15s", tmuber.getUser(userAccountId).getName(), fromAddress, toAddress);
        } catch (TMUberSystemManager.UserNotFoundException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch(TMUberSystemManager.InvalidAddressException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch (TMUberSystemManager.InsufficientDistanceException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch (TMUberSystemManager.DupliceServiceException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch (TMUberSystemManager.InvalidRequestException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch (TMUberSystemManager.InvalidFoodOrderIdException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch (TMUberSystemManager.InsufficientFundsException e) { // exception handling 
          System.out.println(e.getMessage());
        }
      }
      // Sort users by name
      else if (action.equalsIgnoreCase("SORTBYNAME")) 
      {
        tmuber.sortByUserName();
      }
      // Sort users by number of ride they have had
      else if (action.equalsIgnoreCase("SORTBYWALLET")) 
      {
        tmuber.sortByWallet();
      }
      // Cancel a current service (ride or delivery) request
      else if (action.equalsIgnoreCase("CANCELREQ")) 
      {
        int request = -1;
        System.out.print("Request #: ");
        if (scanner.hasNextInt())
        {
          request = scanner.nextInt();
          scanner.nextLine(); // consume nl character
        }
        int zone = -1;
        System.out.print("Zone Number: ");
        if (scanner.hasNextInt()) {
          zone = scanner.nextInt();
          scanner.nextLine();
        }
        try {
          tmuber.cancelServiceRequest(request, zone);
          System.out.println("Service request #" + request + " cancelled");
        } catch (TMUberSystemManager.InvalidRequestException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch (TMUberSystemManager.InvalidZoneException e) { // exception handling 
          System.out.println(e.getMessage());
        }
      }
      // Drop-off the user or the food delivery to the destination address
      else if (action.equalsIgnoreCase("DROPOFF")) 
      {
        String driverId = "";
        System.out.print("Driver Id: ");
        if (scanner.hasNextLine())
        {
          driverId = scanner.nextLine();
        }
        try {
          tmuber.dropOff(driverId);
          System.out.printf("Driver %s Dropping Off", driverId);
        } catch (TMUberSystemManager.DriverNotFoundException e) { // exception handling 
          System.out.println(e.getMessage());
        }
      }
      // Get the Current Total Revenues
      else if (action.equalsIgnoreCase("REVENUES")) 
      {
        System.out.println("Total Revenue: " + tmuber.totalRevenue);
      }
      // Unit Test of Valid City Address 
      else if (action.equalsIgnoreCase("ADDR")) 
      {
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        System.out.print(address);
        if (CityMap.validAddress(address))
          System.out.println("\nValid Address"); 
        else
          System.out.println("\nBad Address"); 
      }
      // Unit Test of CityMap Distance Method
      else if (action.equalsIgnoreCase("DIST")) 
      {
        String from = "";
        System.out.print("From: ");
        if (scanner.hasNextLine())
        {
          from = scanner.nextLine();
        }
        String to = "";
        System.out.print("To: ");
        if (scanner.hasNextLine())
        {
          to = scanner.nextLine();
        }
        System.out.print("\nFrom: " + from + " To: " + to);
        System.out.println("\nDistance: " + CityMap.getDistance(from, to) + " City Blocks");
      }
      else if (action.equalsIgnoreCase("PICKUP")) {
        String driverId = "";
        System.out.print("Driver Id: "); // driver id 
        if (scanner.hasNextLine()) {
          driverId = scanner.nextLine();
        }
        try {
          tmuber.pickup(driverId);
          System.out.println("Driver " + driverId + " picking up in Zone " + CityMap.getCityZone(tmuber.getDriver(driverId).getAddress()));
        } catch (TMUberSystemManager.DriverNotFoundException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch (TMUberSystemManager.NoServiceRequestException e) { // exception handling 
          System.out.println(e.getMessage());
        }
      }
      else if (action.equalsIgnoreCase("LOADUSERS")) {
        String userFile = "";
        System.out.print("User File: ");
        if (scanner.hasNextLine()) {
          userFile = scanner.nextLine();
        }
        try {
          tmuber.setUsers(TMUberRegistered.loadPreregisteredUsers(userFile));
          System.out.println("Users Loaded");
        } catch (IOException e) {
          System.out.println("User File: " + userFile + " Not Found");
        } 
      }
      else if (action.equalsIgnoreCase("LOADDRIVERS")) {
        String driverFile = "";
        System.out.print("Driver File: "); // driver file 
        if (scanner.hasNextLine()) {
          driverFile = scanner.nextLine();
        }
        try {
          tmuber.setDrivers(TMUberRegistered.loadPreregisteredDrivers(driverFile));
          System.out.println("Drivers Loaded"); // drivers loaded 
        } catch (IOException e) {
          System.out.println("Driver File: " + driverFile + " Not Found"); // drivers not found 
        }
      }
      else if (action.equalsIgnoreCase("DRIVETO")) {
        String driverId = "";
        System.out.print("Driver Id: "); // driver id 
        if (scanner.hasNextLine()) {
          driverId = scanner.nextLine();
        }
        String address = "";
        System.out.print("Address: "); // user address 
        if (scanner.hasNextLine()) {
          address = scanner.nextLine();
        }
        try {
          tmuber.driveTo(driverId, address);
          System.out.printf(" Driver %s Now in Zone %d", driverId, CityMap.getCityZone(address));
        } catch (TMUberSystemManager.DriverNotFoundException e) { // exception handling 
          System.out.println(e.getMessage());
        } catch (TMUberSystemManager.InvalidAddressException e) { // exception handling 
          System.out.println(e.getMessage());
        }
      }
      System.out.print("\n>");
    }
  }
}

