import java.util.Arrays;
import java.util.Scanner;

// The city consists of a grid of 9 X 9 City Blocks

// Streets are east-west (1st street to 9th street)
// Avenues are north-south (1st avenue to 9th avenue)

// Example 1 of Interpreting an address:  "34 4th Street"
// A valid address *always* has 3 parts.
// Part 1: Street/Avenue residence numbers are always 2 digits (e.g. 34).
// Part 2: Must be 'n'th or 1st or 2nd or 3rd (e.g. where n => 1...9)
// Part 3: Must be "Street" or "Avenue" (case insensitive)

// Use the first digit of the residence number (e.g. 3 of the number 34) to determine the avenue.
// For distance calculation you need to identify the the specific city block - in this example 
// it is city block (3, 4) (3rd avenue and 4th street)

// Example 2 of Interpreting an address:  "51 7th Avenue"
// Use the first digit of the residence number (i.e. 5 of the number 51) to determine street.
// For distance calculation you need to identify the the specific city block - 
// in this example it is city block (7, 5) (7th avenue and 5th street)
//
// Distance in city blocks between (3, 4) and (7, 5) is then == 5 city blocks
// i.e. (7 - 3) + (5 - 4) 

public class CityMap
{
  // Checks for string consisting of all digits
  // An easier solution would use String method matches()
  private static boolean allDigits(String s)
  {
    String regex = "^[0-9]+[0-9]$"; // regular expression 
    return s.matches(regex); //checks if string contains all digits (true or false return type)
  }

  // Get all parts of address string
  private static String[] getParts(String address)
  {
    String parts[] = new String[3]; // initalizing a String array that has 3 elements 
    if (address == null || address.length() == 0) { //checking for edge cases 
      parts = new String[0]; //reinitalizing the parts String array to have one element
      return parts; 
    }
    parts = address.split(" "); //splitting the address into 3 main parts 
    return parts; // returning the split address 
  }

  // Checks for a valid address
  public static boolean validAddress(String address)
  {
    String [] s = getParts(address); //calling the getParts(String address) method to get all 3 parts of the address 
    if (s.length != 3) { //testing for an edge case
      return false; 
    }
    String two = s[1].substring(s[1].length() - 2, s[1].length()); //extracting the 2nd part of the address 
    if (Integer.valueOf(s[0]) < 10 || Integer.valueOf(s[0]) > 99) { //test case to check if the residents number is between 10 and 99
      return false;
    } else if (allDigits(s[1].substring(0,1))) { //test case to check if the street/avenue number is a digit 
      return false;
    } else if (s[1].length() != 3) { // test case to see if the 2nd part of the address has length 3 
      return false;
    } else if (!two.equals("th") && !two.equals("rd") && !two.equals("nd") && !two.equals("st")) { //test case to see if the number ends in its appropriate ending 
      return false;
    } else if (!s[2].equalsIgnoreCase("avenue") && !s[2].equalsIgnoreCase("street")) { // test case to see if the last part of the address contains a Street or Avenue
      return false;
    }
    return true; //return true if address is a validAddress 
  }

  public static int[] getCityBlock(String address)
  {
    int[] block = {-1, -1};

    // Fill in the code
    int one = Integer.valueOf(address.charAt(0) - '0'); // getting the street number 
    int two = Integer.valueOf(address.charAt(3) - '0'); // getting the avenue number 
    if (validAddress(address) && address.substring(address.length()-6).equalsIgnoreCase("street")) { // if address contains the word street
      block[0] = one; // first number is street
      block[1] = two; // second number is avenue 
    } else if (validAddress(address) && address.substring(address.length()-6).equalsIgnoreCase("avenue")) { // if address contains the word avenue
      block[0] = two; // first number is avenue 
      block[1] = one; // second number is street
    }
    return block;
  }
  
  public static int getDistance(String from, String to)
  {
    int[] block_1 = getCityBlock(from); // calling the getCityBlockMethod(String s) to get the appropriate city blocks 
    int[] block_2 = getCityBlock(to); // calling the getCityBlockMethod(String s) to get the appropriate city blocks
    return Math.abs((block_2[0] - block_1[0])) + Math.abs((block_2[1] - block_1[1])); //returning the absolute value of the two addresses 
  }

  public static int getCityZone(String address) {
    int avenue = 0, street = 0, zone = -1;
    if (address.substring(address.length()-6).equalsIgnoreCase("Street")) {
      avenue = Integer.valueOf(address.charAt(0) - '0');
      street = Integer.valueOf(address.charAt(3) - '0');
    } else if (address.substring(address.length()-6).equalsIgnoreCase("Avenue")){
      avenue = Integer.valueOf(address.charAt(3) - '0');
      street = Integer.valueOf(address.charAt(0) - '0');
    }
    zone = (avenue >= 1 && avenue <= 5 && street >= 6 && street <= 9) ? 0 : zone;
    zone = (avenue >= 6 && avenue <= 9 && street >= 6 && street <= 9) ? 1 : zone;
    zone = (avenue >= 6 && avenue <= 9 && street >= 1 && street <= 5) ? 2 : zone;
    zone = (avenue >= 1 && avenue <= 5 && street >= 1 && street <= 5) ? 3 : zone;
    return zone;
  }
}
