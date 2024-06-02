/**
 * The RegisterGreenSpaceUI class represents a user interface for registering a new green space.
 * It allows users to input information such as name, type, area, manager, street, ZIP code, and city
 * to create a new green space.
 */
package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;

import java.util.Scanner;

public class RegisterGreenSpaceUI implements Runnable {

    private final RegisterGreenSpaceController controller;

    /**
     * Constructs a new RegisterGreenSpaceUI object.
     */
    public RegisterGreenSpaceUI() {
        this.controller = new RegisterGreenSpaceController();
    }

    /**
     * Prompts the user to input information for creating a new green space.
     * If the input is valid, creates the green space using the provided information.
     * Displays appropriate error messages for invalid input or unexpected errors.
     */
    public void createNewGS() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the name of the green space: ");
            String name = scanner.nextLine();

            GreenSpaceType[] greeST = GreenSpaceType.values();
            System.out.print("Select the type of the green space:\n");
            for (int i = 0; i < greeST.length; i++) {
                System.out.println((i + 1) + ". " + greeST[i]);
            }
            GreenSpaceType type = greeST[scanner.nextInt() - 1];
            scanner.nextLine();

            System.out.print("Enter the area of the green space (in hectares): ");
            double area = Double.parseDouble(scanner.nextLine());

            UserSession manager = ApplicationSession.getInstance().getCurrentSession();

            System.out.print("Enter the street of the green space: ");
            String street = scanner.nextLine();

            System.out.print("Enter the ZIP code of the green space (####-###): ");
            String zipCode = scanner.nextLine();

            System.out.print("Enter the city of the green space: ");
            String city = scanner.nextLine();

            // Display all entered information for confirmation
            System.out.println("\nPlease review the entered information:");
            System.out.println("Name: " + name);
            System.out.println("Type: " + type.toString());
            System.out.println("Street: " + street);
            System.out.println("Zip-Code: " + zipCode);
            System.out.println("City: " + city);
            System.out.println("Area: " + area);
            System.out.println("Manager (you): " + manager.getUserName());

            // Confirm registration
            System.out.println("\nDo you want to register this green space? (Y/N)");
            String decision = scanner.nextLine();
            if (decision.trim().equalsIgnoreCase("Y")) {
                controller.createNewGS(name, street, zipCode, area, city, manager, type);
                System.out.println("Green space registered successfully!");
            } else {
                System.out.println("Operation cancelled!");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Runs the createNewGS method.
     */
    @Override
    public void run() {
        createNewGS();
    }
}
