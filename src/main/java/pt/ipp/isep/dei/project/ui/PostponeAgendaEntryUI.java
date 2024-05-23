package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.PostponeAgendaEntryController;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.EntryDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class PostponeAgendaEntryUI implements Runnable {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PostponeAgendaEntryController controller = new PostponeAgendaEntryController();

    public static void main(String[] args) {
        postponeAgendaEntry();
    }

    public PostponeAgendaEntryUI() {

    }

    private static void postponeAgendaEntry() {
        List<EntryDto> entryList = controller.getEntryList(getGSMLogged());

        if (entryList.isEmpty()) {
            System.out.println("No entries found for the GSM.");
            return;
        }

        System.out.println("List of entries:");
        for (int i = 0; i < entryList.size(); i++) {
            EntryDto entry = entryList.get(i);
            System.out.println((i + 1) + ". " + entry.getDescription());
        }

        EntryDto selectedEntry = selectEntry(entryList);
        if (selectedEntry == null) {
            return;
        }

        LocalDate newDate = getValidDate("Enter the new date (dd-mm-yyy)");
        if (newDate == null) {
            return;
        }

        System.out.println("Postponing entry: " + selectedEntry.getDescription() + " to " + formatDate(newDate));
        System.out.print("Confirm (y/n)? ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            boolean success = controller.postponeEntry(selectedEntry, newDate);
            if (success) {
                System.out.println("Entry postponed successfully.");
            } else {
                System.out.println("Failed to postpone the entry.");
            }
        } else {
            System.out.println("Operation canceled.");
        }
    }

    private static UserSession getGSMLogged() {
        return ApplicationSession.getInstance().getCurrentSession();
    }

    private static EntryDto selectEntry(List<EntryDto> entryList) {
        System.out.print("Select an entry to postpone (enter the number): ");
        int entryIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume the newline character

        if (entryIndex < 0 || entryIndex >= entryList.size()) {
            System.out.println("Invalid entry selection.");
            return null;
        }

        return entryList.get(entryIndex);
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
        String[] parts = cleanedDateString.split("/");
        if (parts.length != 3) {
            throw new DateTimeParseException("Invalid date format. Please use dd/mm/yyyy or d/m/yyyy.", dateString, 0);
        }

        String day = parts[0].length() == 1 ? "0" + parts[0] : parts[0];
        String month = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
        String year = parts[2];

        String formattedDate = day + "/" + month + "/" + year;

        // Usar o DateTimeFormatter padrão
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(formattedDate, formatter);
    }

    private static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    @Override
    public void run() {
        postponeAgendaEntry();
    }
}
