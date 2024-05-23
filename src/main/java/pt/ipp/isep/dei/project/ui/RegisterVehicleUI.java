package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.project.domain.VehicleType;
import pt.ipp.isep.dei.project.dto.VehicleDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class RegisterVehicleUI implements Runnable {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RegisterVehicleController controller = new RegisterVehicleController();

    public static void main(String[] args) {
        registerVehicle();
    }

    public RegisterVehicleUI() {
    }

    private static void registerVehicle() {
        System.out.println("Enter Vehicle Details:");

        String vin = getValidInput("VIN", "VIN must have 17 alphanumeric characters (letters and numbers).", RegisterVehicleUI::validateVin);
        String brand = getValidInput("Brand name", "Brand name must have only alphanumeric characters (letters and numbers).", RegisterVehicleUI::validateBrand);
        String model = getValidInput("Model name", "Model name must have only alphanumeric characters (letters and numbers).", RegisterVehicleUI::validateModel);
        VehicleType type = getVehicleType();
        LocalDate registrationDate = getValidDate("Registration date (DD/MM/YYYY)");
        String vehiclePlate = getValidVehiclePlate(registrationDate.getYear());
        double tareWeight = getValidDouble("Tare weight", "Tare weight must be greater than zero.", weight -> weight > 0);
        double grossWeight = getValidDouble("Gross weight", "Gross weight must be greater than 0 Kg.", weight -> weight > 0);
        int currentKm = getValidInt("Current kilometers", "Current kilometers must be greater than 0 Km.", km -> km >= 0);
        LocalDate acquisitionDate = getValidAcquisitionDate(registrationDate);
        int maintenanceFrequency = getValidInt("Maintenance frequency (in km)", "Maintenance frequency must be greater than 0 Km.", frequency -> frequency > 0);

        VehicleDto vehicleDto = new VehicleDto(vin, brand, model, type, registrationDate, vehiclePlate, tareWeight, grossWeight, currentKm, acquisitionDate, maintenanceFrequency);

        if (confirmVehicleData(vehicleDto)) {
            if (controller.registerVehicle(vehicleDto)) {
                System.out.println("Vehicle registered successfully!");
            } else {
                System.out.println("Failed to register vehicle.");
            }
        } else {
            System.out.println("Vehicle registration canceled.");
        }
    }

    private static String getValidInput(String fieldName, String errorMessage, InputValidator validator) {
        String input;
        boolean isValid;
        do {
            System.out.print(fieldName + ": ");
            input = scanner.nextLine();
            isValid = validator.validate(input);
            if (!isValid) {
                System.out.println(errorMessage);
            }
        } while (!isValid);
        return input;
    }

    private static double getValidDouble(String fieldName, String errorMessage, DoubleValidator validator) {
        double value = -1;
        boolean isValid;
        do {
            System.out.print(fieldName + ": ");
            String input = scanner.nextLine();
            try {
                value = Double.parseDouble(input);
                isValid = validator.validate(value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                isValid = false;
            }
            if (!isValid) {
                System.out.println(errorMessage);
            }
        } while (!isValid);
        return value;
    }

    private static int getValidInt(String fieldName, String errorMessage, IntValidator validator) {
        int value = -1;
        boolean isValid;
        do {
            System.out.print(fieldName + ": ");
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                isValid = validator.validate(value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                isValid = false;
            }
            if (!isValid) {
                System.out.println(errorMessage);
            }
        } while (!isValid);
        return value;
    }

    private static LocalDate getValidDate(String fieldName) {
        LocalDate date = null;
        boolean isValid;
        do {
            System.out.print(fieldName + ": ");
            String input = scanner.nextLine();
            try {
                date = parseDate(input);
                isValid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use DD/MM/YYYY");
                isValid = false;
            }
        } while (!isValid);
        return date;
    }

    private static LocalDate parseDate(String dateString) {
        // Remove espaços em branco
        String cleanedDateString = dateString.replaceAll("\\s", "");

        // Substitui vírgulas e hífens por barras
        cleanedDateString = cleanedDateString.replace(',', '/').replace('-', '/');

        // Divide a string em partes (dia, mês, ano)
        String[] parts = cleanedDateString.split("/");
        if (parts.length != 3) {
            throw new DateTimeParseException("Invalid date format.", dateString, 0);
        }

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Cria um objeto LocalDate com os valores fornecidos
        return LocalDate.of(year, month, day);
    }


    private static LocalDate getValidAcquisitionDate(LocalDate registrationDate) {
        LocalDate acquisitionDate = null;
        boolean isValid;
        do {
            System.out.print("Acquisition date (DD/MM/YYYY): ");
            String input = scanner.nextLine();
            try {
                acquisitionDate = parseDate(input);
                isValid = !acquisitionDate.isBefore(registrationDate);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use DD/MM/YYYY or D/M/YYYY.");
                isValid = false;
            }
            if (!isValid) {
                System.out.println("Acquisition date must be after registration date.");
            }
        } while (!isValid);
        return acquisitionDate;
    }


    private static String getValidVehiclePlate(int year) {
        String vehiclePlate;
        boolean isValid;
        do {
            System.out.print("Vehicle plate: ");
            vehiclePlate = scanner.nextLine();
            isValid = validateVehiclePlate(vehiclePlate, year);
            if (!isValid) {
                displayVehiclePlateFormat(year);
            }
        } while (!isValid);
        return vehiclePlate;
    }

    private static boolean validateVehiclePlate(String vehiclePlate, int year) {
        if (vehiclePlate == null || !vehiclePlate.matches("[a-zA-Z0-9]{6}")) {
            return false;
        }

        if (year >= 2020) {
            return vehiclePlate.matches("^[a-zA-Z]{2}\\d{2}[a-zA-Z]{2}$");
        } else if (year >= 2005) {
            return vehiclePlate.matches("^\\d{2}[a-zA-Z]{2}\\d{2}$");
        } else if (year >= 1992) {
            return vehiclePlate.matches("^\\d{4}[a-zA-Z]{2}$");
        } else {
            return vehiclePlate.matches("^[a-zA-Z]{2}\\d{4}$");
        }
    }

    private static void displayVehiclePlateFormat(int year) {
        if (year >= 2020) {
            System.out.println("Vehicle plate should be in this format: AA00AA");
        } else if (year >= 2005) {
            System.out.println("Vehicle plate should be in this format: 00AA00");
        } else if (year >= 1992) {
            System.out.println("Vehicle plate should be in this format: 0000AA");
        } else {
            System.out.println("Vehicle plate should be in this format: AA0000");
        }
    }

    private static VehicleType getVehicleType() {
        System.out.println("Select vehicle type:");
        VehicleType[] types = VehicleType.values();

        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }

        int choice = -1;
        boolean isValid;

        do {
            System.out.print("Choice: ");
            String input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);
                isValid = choice >= 1 && choice <= types.length;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                isValid = false;
            }

            if (!isValid) {
                System.out.println("Invalid vehicle type choice.");
            }
        } while (!isValid);

        return types[choice - 1];
    }

    private static boolean confirmVehicleData(VehicleDto vehicleDto) {
        System.out.println("\nConfirm Vehicle Data:");
        System.out.println("VIN: " + vehicleDto.getVIN());
        System.out.println("Brand: " + vehicleDto.getBrand());
        System.out.println("Model: " + vehicleDto.getModel());
        System.out.println("Vehicle Type: " + vehicleDto.getType());
        System.out.println("Vehicle Plate: " + vehicleDto.getVehiclePlate());
        System.out.println("Tare Weight: " + vehicleDto.getTareWeight());
        System.out.println("Gross Weight: " + vehicleDto.getGrossWeight());
        System.out.println("Current Kilometers: " + vehicleDto.getCurrentKm());
        System.out.println("Registration Date: " + formatDate(vehicleDto.getRegistrationDate()));
        System.out.println("Acquisition Date: " + formatDate(vehicleDto.getAcquisitionDate()));
        System.out.println("Maintenance Frequency: " + vehicleDto.getMaintenanceFrequency());

        System.out.print("\nType 'Y' to confirm data: ");
        String confirmation = scanner.nextLine();
        return confirmation.equalsIgnoreCase("Y");
    }

    private static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }


    private static boolean validateVin(String vin) {
        return vin != null && vin.length() == 17 && vin.matches("[a-zA-Z0-9]{17}");
    }

    private static boolean validateBrand(String brand) {
        return brand != null && brand.matches("^[a-zA-Z0-9- /]+$");
    }

    private static boolean validateModel(String model) {
        return model != null && model.matches("^[a-zA-Z0-9- /]+$");
    }

    @Override
    public void run() {
        registerVehicle();
    }

    private interface InputValidator {
        boolean validate(String input);
    }

    private interface DoubleValidator {
        boolean validate(double value);
    }

    private interface IntValidator {
        boolean validate(int value);
    }

    public static class InvalidDateException extends Throwable {
        public InvalidDateException(String string) {
        }
    }
}
