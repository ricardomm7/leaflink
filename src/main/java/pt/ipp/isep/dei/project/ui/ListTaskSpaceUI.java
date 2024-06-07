package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.ListTaskController;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.ProgressStatus;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * The type List task space ui.
 */
public class ListTaskSpaceUI implements Runnable {

    private ListTaskController controller;

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Instantiates a new List task space ui.
     */
    public ListTaskSpaceUI() {
        controller = new ListTaskController();
    }

    private void ListTaskSpace() {
        Collaborator clb = getCollaboratorTroughEmail();
        if (clb == null) {
            System.out.println("Error. Email not valid.");
        }

        System.out.println("Select beginning and end date.");
        LocalDate beginningDate = getValidDate("Enter the new date (dd-mm-yyy)");
        if (beginningDate == null) {
            return;
        }

        LocalDate endDate = getValidDate("Enter the new date (dd-mm-yyy)");
        if (endDate == null) {
            return;
        }

        ProgressStatus[] statuses = ProgressStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println((i + 1) + ". " + statuses[i]);
        }

        System.out.println("Select a status:");
        int selectedStatusIndex = scanner.nextInt() - 1;

        if (selectedStatusIndex < 0 || selectedStatusIndex >= statuses.length) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }


        List<AgendaEntryDto> DatesList = controller.getDatesList(beginningDate, endDate, statuses[selectedStatusIndex], clb);
        if (DatesList.isEmpty()) {
            System.out.println("No entries found for the GSM.");
            return;
        }
        System.out.println("List of entries:");
        for (int i = 0; i < DatesList.size(); i++) {
            AgendaEntryDto entry = DatesList.get(i);
            System.out.println((i + 1) + ". " + entry.getDescription());
        }

    }

    private String getCollaboratorEmail() {
        return ApplicationSession.getInstance().getCurrentSession().getUserEmail();
    }

    private Collaborator getCollaboratorTroughEmail() {
        String email = getCollaboratorEmail();
        return ListTaskController.getCollaboratorTroughEmail(email);
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
                System.out.println("Invalid date format. Please use dd/mm/yyyy");
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

        // Padroniza o formato para DD/MM/YYYY, adicionando zeros à esquerda quando necessário
        String formattedDate = getFormattedDate(dateString, cleanedDateString);

        // Usar o DateTimeFormatter padrão
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(formattedDate, formatter);
    }

    private static String getFormattedDate(String dateString, String cleanedDateString) {
        String[] parts = cleanedDateString.split("/");
        if (parts.length != 3) {
            throw new DateTimeParseException("Invalid date format. Please use dd/mm/yyyy or d/m/yyyy.", dateString, 0);
        }

        String day = parts[0].length() == 1 ? "0" + parts[0] : parts[0];
        String month = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
        String year = parts[2];

        return day + "/" + month + "/" + year;
    }


    @Override
    public void run() {
        ListTaskSpace();
    }
}

