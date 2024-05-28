package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RecordEntryController;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.ui.console.utils.Utils;

import java.util.List;

/**
 * The type Record entry ui.
 */
public class RecordEntryUI implements Runnable {
    /**
     * The Controller.
     */
    private RecordEntryController controller;
    /**
     * The Collaborator.
     */
    private UserSession collaborator;

    /**
     * Instantiates a new Record entry ui.
     */
    public RecordEntryUI() {
        controller = new RecordEntryController();
    }

    /**
     * Record complete entry.
     */
    public void RecordCompleteEntry() {
        List<AgendaEntryDto> assignedEntries = controller.getAgendaEntryOfCollaboratorList(collaborator);
        AgendaEntryDto selectedEntry = selectEntryFromList(assignedEntries);

        if (selectedEntry != null) {
            boolean confirmed = confirmEntrySelection(selectedEntry);
            if (confirmed) {
                boolean success = controller.recordEntryCompletion(selectedEntry);
                if (success) {
                    displaySuccessMessage();
                } else {
                    displayErrorMessage("Error recording the completion of the entry.");
                }
            }
        }
    }

    /**
     * Select entry from list agenda entry dto.
     *
     * @param entries the entries
     * @return the agenda entry dto
     */
    private AgendaEntryDto selectEntryFromList(List<AgendaEntryDto> entries) {
        System.out.println("Select the entry to be recorded as completed:");
        int index = Utils.showAndSelectIndex(entries, "Entries:");
        if (index >= 0) {
            return entries.get(index);
        }
        return null;
    }

    /**
     * Confirm entry selection boolean.
     *
     * @param entry the entry
     * @return the boolean
     */
    private boolean confirmEntrySelection(AgendaEntryDto entry) {
        System.out.println("Selected entry:");
        System.out.println("Title: " + entry.getTitle());
        System.out.println("Description: " + entry.getDescription());
        System.out.println("Duration: " + entry.getDuration());
        System.out.println("Status: " + entry.getProgressStatus());
        System.out.println("Start date: " + entry.getStartingDate());

        return Utils.confirm("Confirm the selection of this entry?");
    }

    /**
     * Display success message.
     */
    private void displaySuccessMessage() {
        System.out.println("The entry was successfully recorded as completed.");
    }

    /**
     * Display error message.
     *
     * @param message the message
     */
    private void displayErrorMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void run() {
        RecordCompleteEntry();

    }
}
