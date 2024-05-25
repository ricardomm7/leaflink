package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RecordEntryController;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.ui.console.utils.Utils;

import java.util.List;

public class RecordEntryUI implements Runnable {
    private RecordEntryController controller;
    private UserSession collaborator;

    public RecordEntryUI() {
        controller = new RecordEntryController();
    }

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

    private AgendaEntryDto selectEntryFromList(List<AgendaEntryDto> entries) {
        System.out.println("Select the entry to be recorded as completed:");
        int index = Utils.showAndSelectIndex(entries, "Entries:");
        if (index >= 0) {
            return entries.get(index);
        }
        return null;
    }

    private boolean confirmEntrySelection(AgendaEntryDto entry) {
        System.out.println("Selected entry:");
        System.out.println("Title: " + entry.getTitle());
        System.out.println("Description: " + entry.getDescription());
        System.out.println("Duration: " + entry.getDuration());
        System.out.println("Status: " + entry.getProgressStatus());
        System.out.println("Start date: " + entry.getStartingDate());

        return Utils.confirm("Confirm the selection of this entry?");
    }

    private void displaySuccessMessage() {
        System.out.println("The entry was successfully recorded as completed.");
    }

    private void displayErrorMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void run() {
        RecordCompleteEntry();

    }
}
