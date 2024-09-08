//Izma Mujeeb
//501231651
/*
 * 
 * This class simulates a food delivery service for a simple Uber app
 * 
 * A TMUberDelivery is-a TMUberService with some extra functionality
 */
public class TMUberDelivery extends TMUberService
{
  public static final String TYPENAME = "DELIVERY";
 
  private String restaurant; 
  private String foodOrderId;
   
   // Constructor to initialize all inherited and new instance variables 
  public TMUberDelivery(String from, String to, User user, int distance, double cost,
                        String restaurant, String order)
  {
    // Fill in the code - make use of the super method
    super(from, to, user, distance, cost, TYPENAME);
    this.restaurant = restaurant; // initialize instance variables 
    this.foodOrderId = order; // initialize instance varaibles 
  }
 
  public String getServiceType()
  {
    return TYPENAME;
  }
  public String getRestaurant()
  {
    return restaurant;
  }
  public void setRestaurant(String restaurant)
  {
    this.restaurant = restaurant;
  }
  public String getFoodOrderId()
  {
    return foodOrderId;
  }
  public void setFoodOrderId(String foodOrderId)
  {
    this.foodOrderId = foodOrderId;
  }
  /*
   * Two Delivery Requests are equal if they are equal in terms of TMUberServiceRequest
   * and the restaurant and food order id are the same  
   */
  public boolean equals(Object other)
  {
    // First check to see if other is a Delivery type
    // Cast other to a TMUService reference and check type
    // If not a delivery, return false
    TMUberService otherA = (TMUberService) other; // casting other Object to TMUberService object 
    if (otherA instanceof TMUberDelivery) { // checking if its a delivery type 
      TMUberDelivery otherB = (TMUberDelivery) otherA; // casting it to a delivery type 
      if (super.equals(otherB) && this.restaurant.equals(otherB.restaurant) && this.foodOrderId.equals(otherB.foodOrderId)) { //checking if restaurant and foodOrderId equal eachother
        return true;
      }
    }
    return false; // return false otherwise 
    
    // If this and other are deliveries, check to see if they are equal
  }
  /*
   * Print Information about a Delivery Request
   */
  public void printInfo()
  {
    // Fill in the code
    // Use inheritance to first print info about a basic service request
    super.printInfo(); 
    // Then print specific subclass info
    System.out.printf("\nRestaurant: %-9s Food Order #: %-3s", restaurant, foodOrderId); // print restaurant name and foodOrderId
  }
}
