package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RegisterMaintenanceController;
import pt.ipp.isep.dei.project.dto.MaintenanceDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * The RegisterMaintenanceUI class provides a user interface for registering maintenance for vehicles.
 */
public class RegisterMaintenanceUI implements Runnable {
    private static Scanner sc = new Scanner(System.in);
    private static RegisterMaintenanceController controller;

    /**
     * Constructs a new RegisterMaintenanceUI object.
     * Initializes the RegisterMaintenanceController.
     */
    public RegisterMaintenanceUI() {
        controller = new RegisterMaintenanceController();
    }

    /**
     * Registers maintenance for a selected vehicle.
     */
    public void RegisterMaintenance() {
        System.out.println("Select vehicle to register a maintenance.");
        List<String> plates = controller.getPlatesList();
        // present list of vehicles
        for (int i = 0; i < plates.size(); i++) {
            System.out.println((i + 1) + ". " + plates.get(i));
        }
        int plateIdx = sc.nextInt();
        sc.nextLine();
        String plate = plates.get(plateIdx - 1);

        System.out.println("Insert the date of the maintenance (DD-MM-YYYY).");
        String date = sc.nextLine();
        LocalDate date1 = parseDate(date);
        try {
            validateDate(date1);
        } catch (RegisterVehicleUI.InvalidDateException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Insert the current Km of the vehicle.");
        int currentKm = sc.nextInt();
        sc.nextLine();

        // display all data and request confirmation
        System.out.println("Do you want to register this maintenance?");
        System.out.println("Vehicle plate: " + plate);
        System.out.println("Date: " + date1.toString());
        System.out.println("Current Km: " + currentKm);

        // Confirm registration
        System.out.println("\nDo you want to register this maintenance? (Y/N)");
        String decision = sc.nextLine();
        if (decision.trim().equalsIgnoreCase("Y")) {
            MaintenanceDto maintenanceDto = new MaintenanceDto(plate, date1, currentKm);
            controller.createMaintenance(maintenanceDto);
            System.out.println("Maintenance successfully registered!");
        } else {
            System.out.println("Operation cancelled!");
        }
    }

    private void validateDate(LocalDate date) throws RegisterVehicleUI.InvalidDateException {
        if (date.isAfter(LocalDate.now())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            throw new RegisterVehicleUI.InvalidDateException("Date must be before " + LocalDate.now().format(formatter));
        }
    }

    /**
     * Parses a string representation of a date into a Date object.
     *
     * @param dateString the string representation of the date.
     * @return the Date object.
     * @throws IllegalArgumentException if the date format is invalid.
     */
    private LocalDate parseDate(String dateString) {
        // Remove espaços em branco
        String cleanedDateString = dateString.replaceAll("\\s", "");

        // Substitui vírgulas e hífens por barras
        cleanedDateString = cleanedDateString.replace(',', '/').replace('-', '/');

        // Padroniza o formato para DD/MM/YYYY, adicionando zeros à esquerda quando necessário
        String[] parts = cleanedDateString.split("/");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format. Please use DD/MM/YYYY.");
        }

        String day = parts[0].length() == 1 ? "0" + parts[0] : parts[0];
        String month = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
        String year = parts[2];

        String formattedDate = day + "/" + month + "/" + year;

        // Usar o DateTimeFormatter padrão
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(formattedDate, formatter);
    }


    @Override
    public void run() {
        RegisterMaintenance();
    }
}
