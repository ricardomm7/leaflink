package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.project.domain.DocumentType;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.JobDto;
import pt.ipp.isep.dei.project.mappers.JobMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * The RegisterCollaboratorUI class provides a user interface for registering a new collaborator.
 */
public class RegisterCollaboratorUI implements Runnable {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Registers a new collaborator based on user input.
     */
    public void registerCollaborator() {
        RegisterCollaboratorController rc = new RegisterCollaboratorController();
        System.out.println("Enter collaborator's name:");
        String name = sc.nextLine();

        System.out.println("Enter collaborator's birthdate (DD/MM/YYYY):");
        String birthdate = sc.nextLine();
        LocalDate birthdate1 = parseDate(birthdate);

        System.out.println("Enter collaborator's mobile contact:");
        int contactMobile = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter collaborator's email:");
        String email = sc.nextLine();

        System.out.println("Enter collaborator's address:");
        String address = sc.nextLine();

        System.out.println("Enter collaborator's zip code (format: ####-###):");
        String zipCode = sc.nextLine();

        System.out.println("Enter collaborator's city:");
        String city = sc.nextLine();

        DocumentType[] documentTypes = DocumentType.values();
        System.out.println("Select a Document Type:");
        for (int i = 0; i < documentTypes.length; i++) {
            System.out.println((i + 1) + ". " + documentTypes[i]);
        }
        DocumentType documentType = documentTypes[sc.nextInt() - 1];
        sc.nextLine();

        System.out.println("Enter collaborator's identification number:");
        String identificationNumber = sc.nextLine();

        System.out.println("Enter collaborator's admission date (DD/MM/YYYY):");
        String admissionDate = sc.nextLine();
        LocalDate admissionDate1 = parseDate(admissionDate);

        System.out.println("Enter collaborator's taxpayer number:");
        int taxpayerNumber = sc.nextInt();
        sc.nextLine(); // consume the newline character

        System.out.println("Select a collaborator's job title:");
        List<JobDto> jobTitles = rc.getJobs();
        for (int i = 0; i < jobTitles.size(); i++) {
            System.out.println((i + 1) + ". " + jobTitles.get(i).getTitle());
        }

        int jobIndex = sc.nextInt();
        sc.nextLine();
        if (jobIndex < 1 || jobIndex > jobTitles.size()) {
            System.out.println("Invalid selection. Please select a valid index.");
            return;
        }
        JobDto jobDTO = jobTitles.get(jobIndex - 1);

        // Display all entered information for confirmation
        System.out.println("\nPlease review the entered information:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate1);
        System.out.println("Mobile Contact: " + contactMobile);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
        System.out.println("Zip Code: " + zipCode);
        System.out.println("City: " + city);
        System.out.println("Document Type: " + documentType);
        System.out.println("Identification Number: " + identificationNumber);
        System.out.println("Admission Date: " + admissionDate1);
        System.out.println("Taxpayer Number: " + taxpayerNumber);
        System.out.println("Job Title: " + jobDTO.getTitle());

        // Confirm registration
        System.out.println("\nDo you want to register this collaborator? (Y/N)");
        String decision = sc.nextLine();
        if (decision.trim().equalsIgnoreCase("Y")) {
            CollaboratorDto collDto = new CollaboratorDto(name, birthdate1, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate1, JobMapper.toDomain(jobDTO));
            rc.createCLB(collDto);
            System.out.println("Collaborator successfully registered!");
        } else {
            System.out.println("Operation cancelled!");
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

    @Override
    public void run() {
        registerCollaborator();
    }

}
