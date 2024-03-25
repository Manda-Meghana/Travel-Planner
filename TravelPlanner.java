import java.util.*;
 
// Destination class to store destination details
class Destination {
    private String name;
    private Date startDate;
    private Date endDate;
    private List<String> activities;
 
    public Destination(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.activities = new ArrayList<>();
    }
 
    public void addActivity(String activity) {
        activities.add(activity);
    }
 
    public String getName() {
        return name;
    }
 
    public Date getStartDate() {
        return startDate;
    }
 
    public Date getEndDate() {
        return endDate;
    }
 
    public List<String> getActivities() {
        return activities;
    }
}
 
// TravelPlanner class to manage travel itinerary
public class TravelPlanner {
    private List<Destination> destinations;
 
    public TravelPlanner() {
        this.destinations = new ArrayList<>();
    }
 
    public void addDestination(Destination destination) {
        destinations.add(destination);
    }
 
    public void printItinerary() {
        System.out.println("Travel Itinerary:");
        for (Destination destination : destinations) {
            System.out.println("Destination: " + destination.getName());
            System.out.println("Dates: " + destination.getStartDate() + " to " + destination.getEndDate());
            System.out.println("Activities:");
            for (String activity : destination.getActivities()) {
                System.out.println("- " + activity);
            }
            System.out.println();
        }
    }
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TravelPlanner planner = new TravelPlanner();
 
        System.out.print("Enter the number of destinations: ");
        int numDestinations = scanner.nextInt();
        scanner.nextLine(); // Consume newline
 
        for (int i = 0; i < numDestinations; i++) {
            System.out.println("\nDestination " + (i + 1) + ":");
            System.out.print("Enter destination name: ");
            String name = scanner.nextLine();
            System.out.print("Enter start date (YYYY-MM-DD): ");
            String startDateStr = scanner.nextLine();
            System.out.print("Enter end date (YYYY-MM-DD): ");
            String endDateStr = scanner.nextLine();
 
            try {
                Date startDate = java.sql.Date.valueOf(startDateStr);
                Date endDate = java.sql.Date.valueOf(endDateStr);
                Destination destination = new Destination(name, startDate, endDate);
 
                System.out.println("Enter activities for " + name + " (enter 'done' to finish):");
                String activity;
                while (true) {
                    System.out.print("> ");
                    activity = scanner.nextLine();
                    if (activity.equalsIgnoreCase("done")) {
                        break;
                    }
                    destination.addActivity(activity);
                }
 
                planner.addDestination(destination);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                i--; // Repeat this iteration
            }
        }
 
        System.out.println("\nGenerated Travel Itinerary:");
        planner.printItinerary();
 
        scanner.close();
    }
}