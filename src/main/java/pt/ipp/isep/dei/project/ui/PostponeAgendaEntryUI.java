package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.PostponeAgendaEntryController;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * The type Postpone agenda entry ui.
 */
public class PostponeAgendaEntryUI implements Runnable {
    /**
     * The constant scanner.
     */
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * The constant controller.
     */
    private static final PostponeAgendaEntryController controller = new PostponeAgendaEntryController();

    /**
     * Instantiates a new Postpone agenda entry ui.
     */
    public PostponeAgendaEntryUI() {

    }

    /**
     * Postpone agenda entry.
     */
    private static void postponeAgendaEntry() {
        List<AgendaEntryDto> entryList = controller.getAgendaEntryList(getGSMLogged());

        if (entryList.isEmpty()) {
            System.out.println("No entries found for the GSM.");
            return;
        }

        System.out.println("List of entries:");
        for (int i = 0; i < entryList.size(); i++) {
            AgendaEntryDto entry = entryList.get(i);
            System.out.println((i + 1) + ". " + entry.getDescription());
        }

        AgendaEntryDto selectedEntry = selectEntry(entryList);
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
                System.out.println("ToDoEntry postponed successfully.");
            } else {
                System.out.println("Failed to postpone the entry.");
            }
        } else {
            System.out.println("Operation canceled.");
        }
    }

    /**
     * Gets gsm logged.
     *
     * @return the gsm logged
     */
    private static UserSession getGSMLogged() {
        return ApplicationSession.getInstance().getCurrentSession();
    }

    /**
     * Select entry agenda entry dto.
     *
     * @param entryList the entry list
     * @return the agenda entry dto
     */
    private static AgendaEntryDto selectEntry(List<AgendaEntryDto> entryList) {
        System.out.print("Select an entry to postpone (enter the number): ");
        int entryIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume the newline character

        if (entryIndex < 0 || entryIndex >= entryList.size()) {
            System.out.println("Invalid entry selection.");
            return null;
        }

        return entryList.get(entryIndex);
    }

    /**
     * Gets valid date.
     *
     * @param fieldName the field name
     * @return the valid date
     */
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

    /**
     * Parse date local date.
     *
     * @param dateString the date string
     * @return the local date
     */
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

    /**
     * Gets formatted date.
     *
     * @param dateString        the date string
     * @param cleanedDateString the cleaned date string
     * @return the formatted date
     */
    private static String getFormattedDate(String dateString, String cleanedDateString) {
        String[] parts = cleanedDateString.split("/");
        if (parts.length != 3) {
            throw new DateTimeParseException("Invalid date format. Please use dd/mm/yyyy or d/m/yyyy.", dateString, 0);
        }

        String day = parts[0].length() == 1 ? "0" + parts[0] : parts[0];
        String month = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
        String year = parts[2];

        String formattedDate = day + "/" + month + "/" + year;
        return formattedDate;
    }

    /**
     * Format date string.
     *
     * @param date the date
     * @return the string
     */
    private static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    @Override
    public void run() {
        postponeAgendaEntry();
    }
}
