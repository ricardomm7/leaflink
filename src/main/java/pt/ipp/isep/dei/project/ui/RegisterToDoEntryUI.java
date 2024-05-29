package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RegisterToDoEntryController;
import pt.ipp.isep.dei.project.domain.UrgencyStatus;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;

import java.util.List;
import java.util.Scanner;

/**
 * The RegisterToDoEntryUI class provides a user interface for registering a ToDo entry.
 * It interacts with the user through the console to gather information required to create a ToDo entry.
 * Implements Runnable to allow the registration process to run on a separate thread if needed.
 */
public class RegisterToDoEntryUI implements Runnable {

    private final RegisterToDoEntryController controller;

    /**
     * Constructs a new RegisterToDoEntryUI instance and initializes the controller.
     */
    public RegisterToDoEntryUI() {
        this.controller = new RegisterToDoEntryController();
    }

    /**
     * Registers a new ToDo entry by interacting with the user through the console.
     * It collects the title, description, green space, urgency status, and duration from the user,
     * and then attempts to register the ToDo entry using the controller.
     */
    public void registerToDoEntry() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("ToDoEntry title:");
            String title = sc.nextLine();

            System.out.println("ToDoEntry description: ");
            String description = sc.nextLine();

            System.out.println("Available green spaces: ");
            List<GreenSpaceDto> greenSpaceDtos = controller.getGreenSpacesDto();
            int index = 1;
            for (GreenSpaceDto gs : greenSpaceDtos) {
                System.out.println(index + ". " + gs);
                index++;
            }
            int gsIndex = sc.nextInt();
            GreenSpaceDto greenSpaceDto = greenSpaceDtos.get(gsIndex - 1);

            System.out.println("Select degree of urgency: ");
            UrgencyStatus[] dgUrg = UrgencyStatus.values();
            for (int i = 0; i < dgUrg.length; i++) {
                System.out.println((i + 1) + "." + dgUrg[i]);
            }
            UrgencyStatus urg = dgUrg[sc.nextInt() - 1];
            sc.nextLine();

            System.out.println("ToDoEntry entry duration (in hours): ");
            int duration = Integer.parseInt(sc.nextLine());

            System.out.println("\nDo you want to register this ToDoEntry? (Y/N)");
            String decision = sc.nextLine();
            if (decision.trim().equalsIgnoreCase("Y")) {
                controller.createNewToDoEntry(title, description, duration, urg, greenSpaceDto);
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
     * Runs the registration process for a ToDo entry.
     * This method is part of the Runnable interface and allows the registration process to be executed on a separate thread.
     */
    @Override
    public void run() {
        registerToDoEntry();
    }
}
