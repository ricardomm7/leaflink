package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RegisterVehicleController;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 * The RegisterVehicleUI class provides a user interface for registering a new vehicle.
 */
public class RegisterVehicleUI implements Runnable {
    public static  final Scanner scanner = new Scanner(System.in);


    /**
     * Register vehicle UI.
     */
    public void registerVehicle() {
        RegisterVehicleController controller = new RegisterVehicleController();
        boolean flag = false;

        do {
            try {
                System.out.println("Enter VIN:");
                String vin = scanner.nextLine();
                validateVin(vin);

                System.out.println("Enter Brand name:");
                String brand = scanner.nextLine();
                validateBrand(brand);

                System.out.println("Enter Model name:");
                String model = scanner.nextLine();
                validateModel(model);

                System.out.println("Enter vehicle type:");
                String type = scanner.nextLine();
                validateType(type);

                System.out.println("Enter vehicle plate:");
                String vehiclePlate = scanner.nextLine();
                validateVehiclePlate(vehiclePlate);

                System.out.println("Enter tare weight:");
                double tareWeight = Double.parseDouble(scanner.nextLine());
                validateTareWeight(tareWeight);

                System.out.println("Enter gross weight:");
                double grossWeight = Double.parseDouble(scanner.nextLine());
                validateGrossWeight(grossWeight);

                System.out.println("Enter current km:");
                int currentKm = Integer.parseInt(scanner.nextLine());
                validateCurrentKm(currentKm);

                System.out.println("Enter registration date (DD/MM/YYYY):");
                String registrationDateStr = scanner.nextLine();
                Date registrationDate = parseDate(registrationDateStr);

                System.out.println("Enter acquisition date (DD/MM/YYYY):");
                String acquisitionDateStr = scanner.nextLine();
                Date acquisitionDate = parseDate(acquisitionDateStr);
                validateAcquisitionDate(registrationDate, acquisitionDate);

                System.out.println("Enter maintenance frequency (in km):");
                int maintenanceFrequency = Integer.parseInt(scanner.nextLine());
                validateMaintenanceFrequency(maintenanceFrequency);

                // Confirm Vehicle data
                System.out.println("\nConfirms Vehicle data:\n");
                System.out.println("Vin: " + vin + "\nBrand: " + brand + "\nModel: " + model + "\nVehicle Plate: " + vehiclePlate + "\nTare Weight: "
                        + tareWeight + "\nGross Weight: " + grossWeight + "\nCurrent Kilometers: " + currentKm + "\nRegistration date: " + registrationDateStr + "\nAcquisition date: "
                        + acquisitionDateStr + "\nMaintenance Frequency: " + maintenanceFrequency);

                System.out.println("\nTypes 'Yes' to confirm data:");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("yes")) {
                    if (controller.registerVehicle(vin, brand, model, type, vehiclePlate, tareWeight, grossWeight,
                            currentKm, registrationDate, acquisitionDate, maintenanceFrequency)) {
                        System.out.println("Vehicle registered successfully!");
                        flag = true;
                    } else {
                        System.out.println("Failed to register vehicle.");
                    }
                } else {
                    System.out.println("Vehicle registration canceled.");
                    flag = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for maintenance frequency: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            } catch (ParseException e) {
                System.out.println("Failed to parse date: " + e.getMessage());
            } catch (InvalidDateException e) {
                System.out.println("Invalid date: " + e.getMessage());
            }
        } while (!flag);
    }

    /**
     * Validates the input parameter.
     * @param vin
     */
    private void validateVin(String vin) {
        if (vin == null || vin.length() != 17 || !vin.matches("[a-zA-Z0-9]{17}")) {
            throw new IllegalArgumentException("VIN must have 17 alphanumeric characters (letters and numbers).");
        }
    }

    /**
     * Validates the input parameter.
     * @param brand
     */
    private void validateBrand(String brand) {
        if (brand == null || !brand.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Brand name must have only alphanumeric characters (letters and numbers).");
        }
    }

    /**
     * Validates the input parameter.
     * @param model
     */
    private void validateModel(String model) {
        if (model == null || !model.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Model name must have only alphanumeric characters (letters and numbers).");
        }
    }

    /**
     * Validates the input parameter.
     * @param type
     */
    private void validateType(String type) {
        if (type == null || !type.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Vehicle type must have only alphanumeric characters (letters and numbers).");
        }
    }

    /**
     * Validates the input parameter.
     * @param vehiclePlate
     */
    private void validateVehiclePlate(String vehiclePlate) {
        if (vehiclePlate == null || !vehiclePlate.matches("[a-zA-Z0-9]{6}")) {
            throw new IllegalArgumentException("Vehicle plate must have 6 alphanumeric characters (letters and numbers).\n");
        }
    }

    /**
     * Validates the input parameter.
     * @param tareWeight
     */
    private void validateTareWeight(double tareWeight) {
        if (tareWeight <= 0) {
            throw new IllegalArgumentException("Tare weight must be greater than zero.");
        }
    }

    /**
     * Validates the input parameter.
     * @param grossWeight
     */
    private void validateGrossWeight(double grossWeight) {
        if (grossWeight <= 0) {
            throw new IllegalArgumentException("Gross weight must be greater than 0 Kg.");
        }
    }

    /**
     * Validates the input parameter.
     * @param currentKm
     */
    private void validateCurrentKm(int currentKm) {
        if (currentKm < 0) {
            throw new IllegalArgumentException("Current kilometers must be greater than 0 Km.");
        }
    }

    /**
     * Validates the input parameter.
     * @param registrationDate
     * @param acquisitionDate
     */
    private void validateAcquisitionDate(Date registrationDate, Date acquisitionDate) {
        if (acquisitionDate.before(registrationDate)) {
            throw new InvalidDateException("Acquisition date must be after registration date.");
        }
    }

    /**
     * Validates the input parameter.
     * @param maintenanceFrequency
     */
    private void validateMaintenanceFrequency(int maintenanceFrequency) {
        if (maintenanceFrequency <= 0) {
            throw new IllegalArgumentException("Maintenance frequency must be greater than 0 Km.");
        }
    }

    /**
     * Parse the input date (String) to Date.
     * @param dateString
     * @return
     * @throws ParseException
     */
    private Date parseDate(String dateString) throws ParseException {
        String[] parts = dateString.split("[-,. /]");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format. Please use DD/MM/YYYY.");
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        if (day > 31 || month > 12) {
            throw new InvalidDateException("Invalid date. Please try again using DD/MM/YYYY:");
        }

        // Adjust month value to be 0-based (0 for January)
        month--;

        // Create a Date object
        return new Date(year - 1900, month, day);
    }

    @Override
    public void run() {
        registerVehicle();
    }

    /**
     * The type Invalid date exception.
     */
    static class InvalidDateException extends RuntimeException {
        /**
         * Instantiates a new Invalid date exception.
         *
         * @param message The message
         */
        public InvalidDateException(String message) {
            super(message);
        }
    }
}



