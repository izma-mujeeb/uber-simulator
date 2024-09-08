//Izma Mujeeb
//501231651
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;
import java.util.Map;
import java.util.TreeMap;


//import Driver.Status;


/*
 * 
 * This class contains the main logic of the system.
 * 
 *  It keeps track of all users, drivers and service requests (RIDE or DELIVERY)
 * 
 */
public class TMUberSystemManager
{
  private static Map<String, User> users; // initalize user treemap
  private ArrayList<User> userList;
  private static ArrayList<Driver> drivers;
  private Queue<TMUberService>[] serviceRequests;
  
  public double totalRevenue; // Total revenues accumulated via rides and deliveries
  
  // Rates per city block
  private static final double DELIVERYRATE = 1.2;
  private static final double RIDERATE = 1.5;
  // Portion of a ride/delivery cost paid to the driver
  private static final double PAYRATE = 0.1;

  //These variables are used to generate user account and driver ids
  int userAccountId = 900;
  int driverId = 700;

  public TMUberSystemManager()
  {
    users   = new TreeMap<String, User>();
    drivers = new ArrayList<Driver>();
    userList = new ArrayList<User>();

    serviceRequests = new Queue[4];

    for (int i = 0; i <= 3; i++) {
      serviceRequests[i] = new LinkedList<TMUberService>();
    }
    
    totalRevenue = 0;
  }

  boolean sort = false;
  // Given user account id, find user in list of users
  // Return null if not found
  public static ArrayList<User> getUserArraylist() {
    ArrayList<User> u = new ArrayList<User>();
    for (String s : users.keySet()) {
      u.add(users.get(s));
    }
    return u;
  }

  public static ArrayList<Driver> getDriverArrayList() {
    ArrayList<Driver> d = new ArrayList<Driver>();
    for (Driver u : drivers) {
      d.add(u);
    }
    return d;
  }

  public User getUser(String accountId)
  {
    // Fill in the code
    for (String u : users.keySet()) { // iterate through the user treemap 
      if (users.get(u).getAccountId().equals(accountId)) { // check to see if accountId mathes 
        return users.get(u);  
      }
    }
    return null; // return null otherwise 
  }

  public Driver getDriver(String accountId) {
    for (Driver d : drivers) { // iterate through driver arraylist
      if (d.getId().equals(accountId)) { // check to see if accountId matches 
        return d;
      }
    }
    return null; // return null otherwise 
  }
  
  // Check for duplicate user
  private boolean userExists(User user)
  {
    // Fill in the code
    for (String u : users.keySet()) { // iterate through the user arraylist 
      if (users.get(u).equals(user)) { // check to see if users equal each other 
        return true;
      } 
    }
    return false; // return false otherwise 
  }
  
 // Check for duplicate driver
 private boolean driverExists(Driver driver)
 {
   // Fill in the code
  for (Driver d : drivers) { // iterate through drivers arraylist 
    if (d.equals(driver)) { // check to see if drivers equal each other 
      return true; 
    }
  }
  return false; // return false otherwise
 }
  
  // Given a user, check if user ride/delivery request already exists in service requests
  private boolean existingRequest(TMUberService req)
  {
    // Fill in the code
    for (TMUberService t : serviceRequests[0]) { // iterate through zone 0 serviceRequest queue
      if (t.equals(req)) { // if there is an existing request
        return true;
      }
    } 
    for (TMUberService t : serviceRequests[1]) { // iterate through zone 1 serviceRequest queue
      if (t.equals(req)) { // if there is an existing request
        return true;
      }
    }
    for (TMUberService t : serviceRequests[2]) { // iterate through zone 2 serviceRequest queue
      if (t.equals(req)) { // if there is an existing request
        return true;
      }
    }
    for (TMUberService t : serviceRequests[3]) { // iterate through zone 3 serviceRequest queue
      if (t.equals(req)) { // if there is an existing request
        return true;
      }
    }
    return false;
  }

  // Calculate the cost of a ride or of a delivery based on distance 
  private double getDeliveryCost(int distance)
  {
    return distance * DELIVERYRATE;
  }

  private double getRideCost(int distance)
  {
    return distance * RIDERATE;
  }

  // Print Information (printInfo()) about all registered users in the system
  public void listAllUsers()
  { 
    if (sort) {
      int index = 1;
      System.out.println();
        for (User u : userList) // iterate through users arraylist 
        {
          System.out.printf("%-2s. ", index);
          u.printInfo(); // print information about user 
          System.out.println(); 
          index++; // increment counter variable index 
        }
    } else {
      int index = 1;
      System.out.println();
        for (String u : users.keySet()) // iterate through users arraylist 
        {
          System.out.printf("%-2s. ", index);
          users.get(u).printInfo(); // print information about user 
          System.out.println(); 
          index++; // increment counter variable index
        }
    }
  }

  // Print Information (printInfo()) about all registered drivers in the system
  public void listAllDrivers()
  {
    // Fill in the code
    System.out.println();
    int index = 1;
    for (Driver driver : drivers) { // iterate through drivers arraylist 
      System.out.printf("%-2s", index);
      driver.printInfo(); // print information about driver 
      System.out.println();
      index++; // increment index because requests start at 1 (not 0)
    }
  }

  // Print Information (printInfo()) about all current service requests
  public void listAllServiceRequests()
  {
    // Fill in the code
    System.out.println("ZONE 0");
    System.out.println("------\n------");
    int index = 1;
    for (TMUberService request : serviceRequests[0]) { // iterate through serviceRequests arraylist 
      System.out.printf("%-2s", index + ".  ------------------------------------------------------------");
      request.printInfo(); // print information about the service request
      System.out.println();
      index++; // increment index because requests start at 1 (not 0) 
    }

    System.out.println("ZONE 1");
    System.out.println("------\n------");
    index = 1;
    for (TMUberService request : serviceRequests[1]) { // iterate through serviceRequests arraylist 
      System.out.printf("%-2s", index + ".  ------------------------------------------------------------");
      request.printInfo(); // print information about the service request
      System.out.println();
      index++; // increment index because requests start at 1 (not 0) 
    }

    System.out.println("ZONE 2");
    System.out.println("------\n------");
    index = 1;
    for (TMUberService request : serviceRequests[2]) { // iterate through serviceRequests arraylist 
      System.out.printf("%-2s", index + ".  ------------------------------------------------------------");
      request.printInfo(); // print information about the service request
      System.out.println();
      index++; // increment index because requests start at 1 (not 0) 
    }

    System.out.println("ZONE 3");
    System.out.println("------\n------");
    index = 1;
    for (TMUberService request : serviceRequests[3]) { // iterate through serviceRequests arraylist 
      System.out.printf("%-2s", index + ".  ------------------------------------------------------------");
      request.printInfo(); // print information about the service request
      System.out.println();
      index++; // increment index because requests start at 1 (not 0) 
    }
  }
  
  // Add a new user to the system
  public void registerNewUser(String name, String address, double wallet)
  {
    // Fill in the code. Before creating a new user, check paramters for validity
    // See the assignment document for list of possible erros that might apply
    // Write the code like (for example):
    // if (address is *not* valid)
    // {
    //    set errMsg string variable to "Invalid Address "
    //    return false
    // }
    // If all parameter checks pass then create and add new user to array list users
    // Make sure you check if this user doesn't already exist!
    if (name == null || name.equals("")) { // checking is name is an empty string or null 
      throw new IllegalArgumentException("Invalid User Name"); // exception handling
    } else if (wallet < 0) { // checking is wallet is below 0 
      throw new IllegalArgumentException("Invalid Money in Wallet"); // exception handling
    } else if (!CityMap.validAddress(address)){ // checking is the adress given is valid  
      throw new InvalidAddressException("Invalid User Address");  // exception handling
    } 
    User newUser = new User(Integer.toString(9000 + users.size()), name, address, wallet); // creating a user based on parameters above
    if (userExists(newUser)) { // checking is user already exists in system 
      throw new ExistsInSystemException("User Already Exists in the System"); // exception handling
    }
    users.put(Integer.toString(9000 + users.size()), newUser); // otherwise, add the user to the users treemap 
  }

  // Add a new driver to the system
  public void registerNewDriver(String name, String carModel, String carLicencePlate, String address)
  {
    // Fill in the code - see the assignment document for error conditions
    // that might apply. See comments above in registerNewUser
    
    if (name == null || name.equals("")) { // checking if name is null or an empty string 
      throw new IllegalArgumentException("Invalid Driver Name"); // exception handling
    } else if (carModel == null || carModel.equals("")) { // checking if carModel is null or an empty string
      throw new IllegalArgumentException("Invalid Car Model");  // exception handling
    } else if (carLicencePlate == null || carLicencePlate.equals("")) { // checking if license plate ib null or an empty stirng 
      throw new IllegalArgumentException("Invalid Licence Plate"); // exception handling
    } 
    Driver newDriver = new Driver(Integer.toString(7000 + drivers.size()), name, carModel, carLicencePlate, address); // creating a driver based on parameters above 
    if (driverExists(newDriver)) { // checking if the driver already exists in the system 
      throw new ExistsInSystemException("Driver Already Exists in the System"); // exception handling
    }
    drivers.add(newDriver); // othewise, add the driver to the drivers arraylist 
  }

  // Request a ride. User wallet will be reduced when drop off happens
  public void requestRide(String accountId, String from, String to)
  {
    // Check for valid parameters
	  // Use the account id to find the user object in the list of users
    // Get the distance for this ride
    // Note: distance must be > 1 city block!
    // Find an available driver
    // Create the TMUberRide object
    // Check if existing ride request for this user - only one ride request per user at a time!
    // Change driver status
    // Add the ride request to the list of requests
    // Increment the number of rides for this user
    
    if (getUser(accountId) == null) { // checking if accountId and addresses are valid 
      throw new UserNotFoundException("Invalid User Id");  // exception handling
    } else if (!CityMap.validAddress(from)) {
      throw new InvalidAddressException("Invalid Address"); // exception handling
    } else if (!CityMap.validAddress(to)) {
      throw new InvalidAddressException("Invalid Address"); // exception handling
    } else if (CityMap.getDistance(from , to) < 1) {
      throw new InsufficientDistanceException("Insufficent Distance");
    }

    User user = null; // initalizing user to null
    for (String u : users.keySet()) { // iterate through the users treemap
      if (users.get(u).getAccountId().equals(accountId)) { // checking if accoundId equals any accountId's in the users arraylist
        user = users.get(u); // user is now equal to the user in the arraylist
        if (user.getRides() >= 1) { // checking is user already has ride requests 
          throw new DupliceServiceException("User Already Has a Ride Request"); // exception handling
        } 
        if (user.getWallet() < getRideCost(CityMap.getDistance(from, to))) { // checking if user has enough funds for the ride 
          throw new InsufficientFundsException("Insufficient Funds"); // exception handling
        }
      }
    } 
    TMUberRide ride = new TMUberRide(from, to, user, CityMap.getDistance(from, to), getRideCost(CityMap.getDistance(from, to))); // create a ride request object
    if (CityMap.getCityZone(from) == 0) {
      serviceRequests[0].add(ride); // add the ride to the serviceRequests queue 
    } else if (CityMap.getCityZone(from) == 1) {
      serviceRequests[1].add(ride); // add the ride to the serviceRequests queue
    } else if (CityMap.getCityZone(from) == 2) {
      serviceRequests[2].add(ride); // add the ride to the serviceRequests queue
    } else {
      serviceRequests[3].add(ride); // add the ride to the serviceRequests queue
    }
    user.addRide(); // add a ride for user   
  }

  // Request a food delivery. User wallet will be reduced when drop off happens
  public void requestDelivery(String accountId, String from, String to, String restaurant, String foodOrderId)
  {
    // See the comments above and use them as a guide
    // For deliveries, an existing delivery has the same user, restaurant and food order id
    // Increment the number of deliveries the user has had
    if (getUser(accountId) == null) { // checking if accountId and addresses are valid 
      throw new UserNotFoundException("Invalid User Id"); // exception handling 
    } else if (!CityMap.validAddress(from)) {
      throw new InvalidAddressException("Invalid Address"); // exception handling
    } else if (!CityMap.validAddress(to)) {
      throw new InvalidAddressException("Invalid Address"); // exception handling
    } else if (CityMap.getDistance(from , to) < 1) {
      throw new InsufficientDistanceException("Insufficent Distance"); // exception handling
    } else if (restaurant == null || restaurant.equals("")) { // checking if the restaurant is null or an empty string
      throw new InvalidRestaurantException("Invalid Restaurant Name");
    } else if (Integer.valueOf(foodOrderId) < 0 || foodOrderId == "" || !foodOrderId.matches("^[0-9]+[0-9]$")) { // checking if food order id is less than 0, an empty string or not all digits 
      throw new InvalidFoodOrderIdException("Invalid Food Order Number"); // exception handling
    }

    User user = null; // initalizing user to null
    for (String u : users.keySet()) { // iterating through the users arraylist 
      if (users.get(u).getAccountId().equals(accountId)) { // checking if accountId is equal to an accountId in users 
        user = users.get(u); // user is now equal to the user in the arraylist 
        if (user.getWallet() < getDeliveryCost(CityMap.getDistance(from, to))) { // if the user does not have enoughd funds 
          throw new InsufficientFundsException("Insufficient Funds"); // exception handling
        }
      }
    }
    TMUberDelivery ride = new TMUberDelivery(from, to, user, CityMap.getDistance(from, to), getDeliveryCost(CityMap.getDistance(from, to)), restaurant, foodOrderId); // create a delivery object
    if (existingRequest(ride)) {
      throw new DupliceServiceException("Request Already Exists"); // exception handling 
    }
    if (CityMap.getCityZone(from) == 0) {
      serviceRequests[0].add(ride); // add the ride to the serviceRequests queue 
    } else if (CityMap.getCityZone(from) == 1) {
      serviceRequests[1].add(ride); // add the ride to the serviceRequests queue
    } else if (CityMap.getCityZone(from) == 2) {
      serviceRequests[2].add(ride); // add the ride to the serviceRequests queue
    } else {
      serviceRequests[3].add(ride); // add the ride to the serviceRequests queue
    } 
    user.addDelivery(); // add a delivery to users 
  }


  // Cancel an existing service request. 
  // parameter int request is the index in the serviceRequests array list
  public void cancelServiceRequest(int request, int zone)
  {
    // Check if valid request #
    // Remove request from list
    // Also decrement number of rides or number of deliveries for this user
    // since this ride/delivery wasn't completed
    Queue<TMUberService> tempzone = null;
    tempzone = (zone == 0) ? serviceRequests[0] : tempzone; // check to see which queue to iterate over 
    tempzone = (zone == 1) ? serviceRequests[1] : tempzone; // check to see which queue to iterate over 
    tempzone = (zone == 2) ? serviceRequests[2] : tempzone; // check to see which queue to iterate over 
    tempzone = (zone == 3) ? serviceRequests[3] : tempzone; // check to see which queue to iterate over 
    if (request < 0 || request > tempzone.size()) { // checking if the parameter request is valid 
      throw new InvalidRequestException("Invalid Request #" + request); // exception handling 
    } 
    if (zone < 0 || zone >= 4) {
      throw new InvalidZoneException("Invalid Zone Number"); // exception handling 
    }
    int service = 1;
    for (TMUberService t : tempzone) { // iterate through the correct zone queue 
      if (service == request) { 
        if (t instanceof TMUberRide) { // if the service is a ride 
          t.getUser().subtractRide(); // increment # of rides for user 
        } else if (t instanceof TMUberDelivery) { // if service is a delivery 
          t.getUser().subtractDelivery(); // increment # of deliveries for a user 
        }
        if (zone == 0) {
          serviceRequests[0].remove(t); // remove the request from the specific zone 
        } else if (zone == 1) {
          serviceRequests[1].remove(t); // remove the request from the specific zone 
        } else if (zone == 2) {
          serviceRequests[2].remove(t); // remove the request from the specific zone 
        } else {
          serviceRequests[3].remove(t); // remove the request from the specific zone 
        } 
      }
      service++; // increment the counter variable 
    }
  }
  
  // Drop off a ride or a delivery. This completes a service.
  // parameter request is the index in the serviceRequests array list
  public void dropOff(String driverId)
  {
    // See above method for guidance
    // Get the cost for the service and add to total revenues
    // Pay the driver
    // Deduct driver fee from total revenues
    // Change driver status
    // Deduct cost of service from user
    if (getDriver(driverId) == null) {
      throw new DriverNotFoundException("Invalid Driver Id"); // exception handling 
    }
    Driver driver = null; // initalize objects 
    TMUberService t = null;
    for (Driver d : drivers) { // iterate through drivers arraylist 
      if (d.getId().equals(driverId) && d.getStatus().equals(Driver.Status.DRIVING)) { // if we have found the correct driver and the driver is currently driving 
        driver = d; 
        t = driver.getService(); // get the service of driver 
        totalRevenue += t.getCost(); // add the cost of the service to revenue 
        driver.pay(t.getCost() * PAYRATE); // pay the driver 
        totalRevenue -= t.getCost() * PAYRATE; // deduce driver fee from revenue 
        t.getUser().payForService(t.getCost()); // user must pay for the service 
        if (t instanceof TMUberRide) { // if service is a ride 
          t.getUser().subtractRide(); // subtract # of rides from user 
        } else {
          t.getUser().subtractDelivery(); // otherwise, subtract # of deliveries from user 
        }
        driver.setStatus(Driver.Status.AVAILABLE); // set driver status back to avaliable 
        driver.setService(null); // set service in driver class to null
        driver.setAddress(t.getTo()); // change the driver address to the to address of the service 
        driver.setZone(CityMap.getCityZone(t.getTo())); // change the zone the driver is currently in 
        }
      }
    }

  // Sort users by name
  // Then list all users
  public void sortByUserName()
  {
    sort = true;
    userList = new ArrayList<User>(); // create a temporary arraylist 
    for (User u : users.values()) {
      userList.add(u); // add all users to arraylist 
    }
    Collections.sort(userList, new NameComparator()); // sort users by first name 
    listAllUsers(); // print all users 
  }

  // Helper class for method sortByUserName
  private class NameComparator implements Comparator<User>
  {
    public int compare(User a, User b) {
      if (a.getName().compareTo(b.getName()) > 0) {
        return 1; // return 1 if User a comes before User b
      } else if (a.getName().compareTo(b.getName()) < 0) {
        return -1; // return -1 if User a comes after User b
      } else {
        return 0; // return 0 if both users are the same 
      }
    }
  }

  // Sort users by number amount in wallet
  // Then ist all users
  public void sortByWallet()
  {
    sort = true;
    userList = new ArrayList<User>(); // create a temporary arraylist 
    for (User u : users.values()) {
      userList.add(u); // add all users to the arraylist 
    }
    Collections.sort(userList, new UserWalletComparator()); // sort users by wallet 
    listAllUsers(); // list all the users 
  }

  // Helper class for use by sortByWallet
  private class UserWalletComparator implements Comparator<User>
  {
    public int compare(User a, User b) {
      if (a.getWallet() > b.getWallet()) { 
        return 1; // return 1 if User a has more money than User b
      } else if (a.getWallet() < b.getWallet()) {
        return -1; // return -1 is User a has less money than User b
      } else {
        return 0; // return 0 if they have the same amount of money
      }
    }
  }

  public void pickup(String driverId) {
    Driver driver = null; // initalize objects 
    TMUberService s = null;
    if (getDriver(driverId) == null) {
      throw new DriverNotFoundException("Invalid Driver Id"); // exception handling 
    }
    for (Driver d : drivers) { // iterate through drivers arraylist 
      if (d.getId().equals(driverId)) { // find the correct driver 
        driver = d;
      }
    }
    int zone = CityMap.getCityZone(driver.getAddress()); // find the zone the driver is currently in 
    if (zone == 0) { // if driver is in zone 0
      if (serviceRequests[0].size() < 1) {
        throw new NoServiceRequestException("No Service Request in Zone 0"); // exception handling 
      }
      s = serviceRequests[0].remove();  // remove the service from service queue
    } else if (zone == 1) { // if driver is in zone 1 
      if (serviceRequests[1].size() < 1) {
        throw new NoServiceRequestException("No Service Request in Zone 1"); // exception handling 
      }
      s = serviceRequests[1].remove(); // remove the service from service queue
    } else if (zone == 2) { // if driver is in zone 2 
      if (serviceRequests[2].size() < 1) {
        throw new NoServiceRequestException("No Service Request in Zone 2"); // exception handling 
      }
      s = serviceRequests[2].remove(); // remove the service from service queue
    } else { // if driver is in zone 3 
      if (serviceRequests[3].size() < 1) {
        throw new NoServiceRequestException("No Service Request in Zone 3"); // exception handling 
      }
      s = serviceRequests[3].remove(); // remove the service from service queue
    } 
    driver.setService(s); // set the service of the driver to that specific service request
    driver.setStatus(Driver.Status.DRIVING); // set the status of the driver to driving 
    driver.setAddress(s.getFrom()); // change driver address 
  }

  public void driveTo(String driverId, String address) {
    Driver driver = null; // initalize object 
    if (getDriver(driverId) == null) { 
      throw new DriverNotFoundException("Invalid Driver Id"); // exception handling 
    }
    if (!CityMap.validAddress(address)) {
      throw new InvalidAddressException("Invalid Address"); // exception handling 
    }
    for (Driver d : drivers) { // iterate through drivers arraylist 
      if (d.getId().equals(driverId) && d.getStatus().equals(Driver.Status.AVAILABLE) && CityMap.validAddress(address)) { // find the correct driver 
        driver = d;
        driver.setAddress(address); // change driver address 
        driver.setZone(CityMap.getCityZone(address)); // change driver zone 
      }
    }
  }

  public void setUsers(ArrayList<User> userList) {
    for (User u : userList) { // iterate through arraylist
      users.put(u.getAccountId(), u); // set all users into the users treemap
    }
  }

  public void setDrivers(ArrayList<Driver> driver) {
    for (Driver d : driver) { // iterate through driver arraylist
      drivers.add(d); // set all drivers to this.drivers arraylist
    }
  }
  public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(String message) { // driver not found exception 
      super(message);
    }
  }
  public class InvalidAddressException extends RuntimeException {
    public InvalidAddressException(String message) { // invalid address exception 
      super(message);
    }
  }
  public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) { // invalid request exception 
      super(message);
    }
  }
  public class InvalidZoneException extends RuntimeException {
    public InvalidZoneException(String message) { // invalid zone exception 
      super(message);
    }
  }
  public class InvalidRestaurantException extends RuntimeException {
    public InvalidRestaurantException(String message) { // invalid restaurant exception 
      super(message);
    }
  }
  public class InvalidFoodOrderIdException extends RuntimeException {
    public InvalidFoodOrderIdException(String message) { // invalid food order id exception 
      super(message);
    }
  }
  public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) { // user not found exception 
      super(message);
    }
  }
  public class InsufficientDistanceException extends RuntimeException {
    public InsufficientDistanceException(String message) { // insufficient distance exception 
      super(message);
    }
  }
  public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) { // insufficient funds exception 
      super(message);
    }
  }
  public class NoAvaliableDriverException extends RuntimeException {
    public NoAvaliableDriverException(String message) { // no avaliable driver exception 
      super(message);
    }
  }
  public class DupliceServiceException extends RuntimeException {
    public DupliceServiceException(String message) { // duplicate service exception 
      super(message);
    }
  }
  public class ExistsInSystemException extends RuntimeException {
    public ExistsInSystemException(String message) { // exists in system exception 
      super(message);
    }
  }
  public class NoServiceRequestException extends RuntimeException {
    public NoServiceRequestException(String message) { // no service request exception 
      super(message);
    }
  }
}




