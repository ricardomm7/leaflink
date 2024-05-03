package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RegisterMaintenanceController;
import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.util.Date;
import java.util.Scanner;

public class RegisterMaintenanceUI implements Runnable{
    private static Scanner sc = new Scanner(System.in);

    public void RegisterMaintenance() {
        RegisterMaintenanceController rm = new RegisterMaintenanceController();
        System.out.println("Select vehicle to register a maintenance.");
        // apresentar lista de veículos (verificar se está correto)
        VehicleRepository vr = new VehicleRepository();
        for (Vehicle vehicle : vr.getVehicleList()) {
            System.out.println(vehicle);
        }
        //Select option
        //get vehicle plate number

        System.out.println("Insert the date of the maintenance (DD-MM-YYYY).");
        String date = sc.nextLine();
        Date date1 = parseDate(date);


        System.out.println("Insert the current Km of the vehicle.");
        String currentKm = sc.nextLine();


        //displays all data and requests confirmation
        System.out.println("Do you want to register this maintenance?");
        System.out.println("Vehicle plate: " + "VEHICLE PLATE NUMBER");
        System.out.println("Date: " + date1.toString());
        System.out.println("Current Km: " + currentKm);

        // Confirm registration
        System.out.println("\nDo you want to register this collaborator? (Y/N)");
        String decision = sc.nextLine();
        if (decision.trim().equalsIgnoreCase("Y")) {
            rm.createMNT("Vehicle plate" + date1 + currentKm);
            System.out.println("Collaborator successfully registered!");
        } else {
            System.out.println("Operation cancelled!");
        }
    }

    private Date parseDate(String dateString) {
        String[] parts = dateString.split("[/-]");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format. Please use DD/MM/YYYY.");
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Adjust month value to be 0-based (0 for January)
        month--;

        // Create a Date object
        return new Date(year - 1900, month, day);
    }

    @Override
    public void run() {
        RegisterMaintenance();
    }
}
