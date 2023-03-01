package restaurant;

import java.util.ArrayList;
import java.util.Scanner;

public class CJsRestaurant {
    // Initialize menu items as an ArrayList of MenuItem objects
    private static final ArrayList<MenuItem> menuItems = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Populate initial menu items
        menuItems.add(new MenuItem("Burger", 150.00));
        menuItems.add(new MenuItem("Pizza", 950.00));
        menuItems.add(new MenuItem("Fries", 200.00));
        menuItems.add(new MenuItem("Coke", 150.00));

        //bool to check if program is running
        boolean running = true;
        while (running) {
            System.out.println("Welcome to the restaurant menu!");
            System.out.println("1. View menu");
            System.out.println("2. Add item to menu");
            System.out.println("3. Order food and drink");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1 -> viewMenu();
                case 2 -> addItemToMenu();
                case 3 -> orderFoodAndDrink();
                case 4 -> running = false; //stop program
                default -> System.out.println("Invalid choice, please try again.");
            }
        }

        System.out.println("Thank you for visiting the restaurant!");
    }

    // Method to view the current menu items
    private static void viewMenu() {
        System.out.println("Menu:");
        for (MenuItem item : menuItems) {
            System.out.println(item.getName() + " - Ksh. " + item.getPrice());
        }
    }

    // Method to add a new item to the menu
    private static void addItemToMenu() {
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter item price: ");
        double itemPrice = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character

        MenuItem newItem = new MenuItem(itemName, itemPrice);
        menuItems.add(newItem);
        System.out.println(itemName + " added to menu!");
    }

    // Method to allow customer to order food and drink
    private static void orderFoodAndDrink() {
        ArrayList<MenuItem> order = new ArrayList<>();
        double totalCost = 0;
        boolean addingItems = true;

        while (addingItems) {
            System.out.print("Enter item name (or type 'done' to finish order): ");
            String itemName = scanner.nextLine();

            if (itemName.equals("done")) {
                addingItems = false;
            } else {
                MenuItem item = findMenuItem(itemName);
                if (item == null) {
                    System.out.println("Item not found, please try again.");
                } else {
                    order.add(item);
                    totalCost += item.getPrice();
                }
            }
        }

        System.out.println("Order summary:");
        for (MenuItem item : order) {
            System.out.println(item.getName() + " - Ksh. " + item.getPrice());
        }
        System.out.println("Total cost: Ksh. " + totalCost);

        System.out.print("Enter payment amount via mobile money: Ksh. ");
        double paymentAmount = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character

        if (paymentAmount < totalCost) {
            System.out.println("Payment amount is less than total cost, order cancelled.");
        } else {
            System.out.println("Payment received, thank you for your order!");
        }
    }

    // Method to find
// Inner class representing a menu item
    private static class MenuItem {
        private final String name;
        private final double price;

        public MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }


    }

    private static MenuItem findMenuItem(String name) {
        for (MenuItem item : menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null; // item not found
    }
}

