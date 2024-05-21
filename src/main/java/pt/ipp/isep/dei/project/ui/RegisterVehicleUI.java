package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.project.domain.VehicleType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * The RegisterVehicleUI class provides a user interface for registering a new vehicle.
 */
public class RegisterVehicleUI implements Runnable {
    public static final Scanner scanner = new Scanner(System.in);
    private final RegisterVehicleController controller;

    public RegisterVehicleUI() {
        this.controller = new RegisterVehicleController();
    }

    /**
     * Register vehicle UI.
     */
    public void registerVehicle() {
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

                displayVehicleTypes();
                int typeChoice = Integer.parseInt(scanner.nextLine());
                VehicleType type = getVehicleTypeByChoice(typeChoice);

                System.out.println("Enter registration date (DD/MM/YYYY):");
                String registrationDateStr = scanner.nextLine();
                LocalDate registrationDate = parseDate(registrationDateStr);

                System.out.println("Enter vehicle plate:");
                displayVehiclePlateFormat(registrationDate.getYear());
                String vehiclePlate = scanner.nextLine();
                validateVehiclePlate(vehiclePlate, registrationDate.getYear());

                System.out.println("Enter tare weight:");
                double tareWeight = Double.parseDouble(scanner.nextLine());
                validateTareWeight(tareWeight);

                System.out.println("Enter gross weight:");
                double grossWeight = Double.parseDouble(scanner.nextLine());
                validateGrossWeight(grossWeight);

                System.out.println("Enter current km:");
                int currentKm = Integer.parseInt(scanner.nextLine());
                validateCurrentKm(currentKm);

                System.out.println("Enter acquisition date (DD/MM/YYYY):");
                String acquisitionDateStr = scanner.nextLine();
                LocalDate acquisitionDate = parseDate(acquisitionDateStr);
                validateAcquisitionDate(registrationDate, acquisitionDate);

                System.out.println("Enter maintenance frequency (in km):");
                int maintenanceFrequency = Integer.parseInt(scanner.nextLine());
                validateMaintenanceFrequency(maintenanceFrequency);

                // Confirm Vehicle data
                System.out.println("\nConfirms Vehicle data:\n");
                System.out.println("Vin: " + vin + "\nBrand: " + brand + "\nModel: " + model + "\nVehicle Type: " + type.name() + "\nVehicle Plate: " + vehiclePlate + "\nTare Weight: "
                        + tareWeight + "\nGross Weight: " + grossWeight + "\nCurrent Kilometers: " + currentKm + "\nRegistration date: " + registrationDateStr + "\nAcquisition date: "
                        + acquisitionDateStr + "\nMaintenance Frequency: " + maintenanceFrequency);

                System.out.println("\nType 'Y' to confirm data:");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("Y")) {
                    if (controller.registerVehicle(vin, brand, model, type, registrationDate, vehiclePlate, tareWeight, grossWeight,
                            currentKm, acquisitionDate, maintenanceFrequency)) {
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
            } catch (InvalidDateException e) {
                System.out.println("Invalid date: " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format: " + e.getMessage());
            }
        } while (!flag);
    }

    /**
     * Validates the input parameter.
     *
     * @param vin
     */
    private void validateVin(String vin) {
        if (vin == null || vin.length() != 17 || !vin.matches("[a-zA-Z0-9]{17}")) {
            throw new IllegalArgumentException("VIN must have 17 alphanumeric characters (letters and numbers).");
        }
    }

    /**
     * Validates the input parameter.
     *
     * @param brand
     */
    private void validateBrand(String brand) {
        if (brand == null || !brand.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Brand name must have only alphanumeric characters (letters and numbers).");
        }
    }

    /**
     * Validates the input parameter.
     *
     * @param model
     */
    private void validateModel(String model) {
        if (model == null || !model.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Model name must have only alphanumeric characters (letters and numbers).");
        }
    }

    /**
     * Validates the input parameter.
     *
     * @param vehiclePlate
     */
    private void validateVehiclePlate(String vehiclePlate, int year) {
        if (vehiclePlate == null || !vehiclePlate.matches("[a-zA-Z0-9]{6}")) {
            throw new IllegalArgumentException("Vehicle plate must have 6 alphanumeric characters (letters and numbers).");
        }

        if (year >= 2020) {
            if (!vehiclePlate.matches("^[a-zA-Z]{2}\\d{2}[a-zA-Z]{2}$")) {
                throw new IllegalArgumentException("Vehicle plate should be in this format: AA00AA");
            }
        } else if (year >= 2005) {
            if (!vehiclePlate.matches("^\\d{2}[a-zA-Z]{2}\\d{2}$")) {
                throw new IllegalArgumentException("Vehicle plate should be in this format: 00AA00");
            }
        } else if (year >= 1992) {
            if (!vehiclePlate.matches("^\\d{4}[a-zA-Z]{2}$")) {
                throw new IllegalArgumentException("Vehicle plate should be in this format: 0000AA");
            }
        } else {
            if (!vehiclePlate.matches("^[a-zA-Z]{2}\\d{4}$")) {
                throw new IllegalArgumentException("Vehicle plate should be in this format: AA0000");
            }
        }
    }

    /**
     * Validates the input parameter.
     *
     * @param tareWeight
     */
    private void validateTareWeight(double tareWeight) {
        if (tareWeight <= 0) {
            throw new IllegalArgumentException("Tare weight must be greater than zero.");
        }
    }

    /**
     * Validates the input parameter.
     *
     * @param grossWeight
     */
    private void validateGrossWeight(double grossWeight) {
        if (grossWeight <= 0) {
            throw new IllegalArgumentException("Gross weight must be greater than 0 Kg.");
        }
    }

    /**
     * Validates the input parameter.
     *
     * @param currentKm
     */
    private void validateCurrentKm(int currentKm) {
        if (currentKm < 0) {
            throw new IllegalArgumentException("Current kilometers must be greater than 0 Km.");
        }
    }

    /**
     * Validates the input parameter.
     *
     * @param registrationDate
     * @param acquisitionDate
     */
    private void validateAcquisitionDate(LocalDate registrationDate, LocalDate acquisitionDate) {
        if (acquisitionDate.isBefore(registrationDate)) {
            throw new InvalidDateException("Acquisition date must be after registration date.");
        }

    }



    /**
     * Validates the input parameter.
     *
     * @param maintenanceFrequency
     */
    private void validateMaintenanceFrequency(int maintenanceFrequency) {
        if (maintenanceFrequency <= 0) {
            throw new IllegalArgumentException("Maintenance frequency must be greater than 0 Km.");
        }
    }

    /**
     * Parse the input date (String) to LocalDate.
     *
     * @param dateString
     * @return
     * @throws DateTimeParseException
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


    // Método para exibir todos os tipos de veículos na interface do usuário
    private void displayVehicleTypes() {
        System.out.println("Select vehicle type:");
        VehicleType[] types = VehicleType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println(i + 1 + ". " + types[i]);
        }
    }

    // Método para obter o tipo de veículo com base na escolha do usuário
    private VehicleType getVehicleTypeByChoice(int choice) {
        VehicleType[] types = VehicleType.values();
        if (choice < 1 || choice > types.length) {
            throw new IllegalArgumentException("Invalid vehicle type choice.");
        }
        return types[choice - 1];
    }

    // Método para exibir o formato da placa do veículo com base no ano de registro
    private void displayVehiclePlateFormat(int year) {
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

    @Override
    public void run() {
        registerVehicle();
    }

    /**
     * The type Invalid date exception.
     */
    public static class InvalidDateException extends RuntimeException {
        public InvalidDateException(String message) {
            super(message);
        }
    }
}