package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.PostponeAgendaEntryController;
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

    public PostponeAgendaEntryUI(){

    }
    private static void postponeAgendaEntry() {
        String gsmEmail = getGSMEmail();
        List<EntryDto> entryList = controller.getEntryList(gsmEmail);

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

        LocalDate newDate = getValidDate("Enter the new date (yyyy-mm-dd)");
        if (newDate == null) {
            return;
        }

        System.out.println("Postponing entry: " + selectedEntry.getDescription() + " to " + newDate);
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

    private static String getGSMEmail() {
        // Lógica para obter o email do GSM logado
        return "gsm@example.com"; // Exemplo de email do GSM
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
                System.out.println("Invalid date format. Please use yyyy-mm-dd");
                isValid = false;
            }
        } while (!isValid);
        return date;
    }

    private static LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }

    @Override
    public void run() {
        postponeAgendaEntry();
    }
}
