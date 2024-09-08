import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TMUberRegistered
{
    // These variables are used to generate user account and driver ids
    private static int firstUserAccountID = 900;
    private static int firstDriverId = 700;

    // Generate a new user account id
    public static String generateUserAccountId(ArrayList<User> current)
    {
        return "" + firstUserAccountID + current.size();
    }

    // Generate a new driver id
    public static String generateDriverId(ArrayList<Driver> current)
    {
        return "" + firstDriverId + current.size();
    }
    public static ArrayList<User> loadPreregisteredUsers(String filename) throws IOException
    {
        ArrayList<User> users = new ArrayList<User>(); // create a temporary user arraylist 
        File inputFile = new File(filename); // create a file object for the given file name 
        Scanner scan = new Scanner(inputFile); // create a scanner object 
        while (scan.hasNextLine()) { // iterate through the file 
            User u = new User(Integer.toString(9000 + users.size() + TMUberSystemManager.getUserArraylist().size()), scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine()));
            users.add(u); // add the user to the users arraylist 
        }
        scan.close();
        return users; // return users 
    }

    // Database of Preregistered users 
    public static ArrayList<Driver> loadPreregisteredDrivers(String filename) throws IOException
    {
        ArrayList<Driver> drivers = new ArrayList<Driver>(); // create a temporary driver arraylist 
        File inputFile = new File(filename); // create a file object for the given file name
        Scanner scan = new Scanner(inputFile); // create a scanner object 
        while (scan.hasNextLine()) { // iterate through the file 
            Driver d = new Driver(Integer.toString(7000 + drivers.size() + TMUberSystemManager.getDriverArrayList().size()), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine());
            drivers.add(d); // add the driver to the drivers arraylist 
        }
        scan.close();
        return drivers; // return drivers 
    }
}

