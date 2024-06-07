package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

/**
 * The NotificationService class is responsible for managing notifications to teams and collaborators
 * about changes or updates to agenda entries and to-do entries.
 */
public abstract class NotificationService {

    /**
     * Retrieves the team assigned to the specified agenda entry.
     *
     * @param agendaEntry the agenda entry
     * @return the team assigned to the agenda entry
     */
    public static Team getTeamByEntry(AgendaEntry agendaEntry) {
        return agendaEntry.getAssignedTeam();
    }

    /**
     * Retrieves the list of collaborators from the specified team.
     *
     * @param team the team
     * @return the list of collaborators in the team
     */
    public static List<Collaborator> getCollaboratorsList(Team team) {
        return team.getCollaborators();
    }

    /**
     * Notifies the team of collaborators about a postponed to-do entry by sending an email notification.
     * The notification is written to a file in the "Notifications" directory.
     *
     * @param collaborators the list of collaborators to notify
     * @param toDoEntry     the to-do entry that has been postponed
     * @param newDate       the new date for the to-do entry
     * @return true if the notification is successfully written to a file, false otherwise
     */
    public static boolean notifyTeam(List<Collaborator> collaborators, AgendaEntry toDoEntry, LocalDate newDate) {
        return writeNotification(collaborators, "Entrada adiada: " + toDoEntry.getTitle(), buildEmailBody(collaborators, toDoEntry, newDate));
    }

    /**
     * Notifies the team of collaborators about a canceled to-do entry by sending an email notification.
     * The notification is written to a file in the "Notifications" directory.
     *
     * @param collaborators the list of collaborators to notify
     * @param toDoEntry     the to-do entry that has been canceled
     * @return true if the notification is successfully written to a file, false otherwise
     */
    public static boolean notifyTeamCancel(List<Collaborator> collaborators, ToDoEntry toDoEntry) {
        return writeNotification(collaborators, "Entrada cancelada: " + toDoEntry.getTitle(), buildEmailBodyCancel(collaborators, toDoEntry));
    }

    /**
     * Notifies the team of collaborators about a created to-do entry by sending an email notification.
     * The notification is written to a file in the "Notifications" directory.
     *
     * @param collaborators the list of collaborators to notify
     * @param toDoEntry     the to-do entry that has been created
     * @return true if the notification is successfully written to a file, false otherwise
     */
    public static boolean notifyTeamCreate(List<Collaborator> collaborators, AgendaEntry toDoEntry) {
        return writeNotification(collaborators, "Entrada criada: " + toDoEntry.getTitle(), buildEmailBodyCreate(collaborators, toDoEntry));
    }

    /**
     * Writes the notification to a file.
     *
     * @param collaborators the list of collaborators to notify
     * @param subject       the subject of the notification
     * @param body          the body of the notification
     * @return true if the notification is successfully written to a file, false otherwise
     */
    private static boolean writeNotification(List<Collaborator> collaborators, String subject, String body) {
        try {
            // Creates the notification file
            String name = "Email";
            LocalDate date = LocalDate.now();
            String filename = "Notification_ "+ ".txt";
            try (PrintWriter writer = new PrintWriter("Notifications/"+name+"_"+date+filename)) {
                // Writes the subject and body of the message to the file
                writer.write(subject);
                writer.println();
                writer.write(body);
                writer.close();

            }
            ShowError.showAlertConfirm("Notification","Notifications sent",null);
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao escrever a notificação: " + e.getMessage());
            return false;
        }
    }

    /**
     * Builds the body of the email notification to be sent to the collaborators.
     *
     * @param collaborators the list of collaborators to notify
     * @param toDoEntry     the to-do entry that has been postponed
     * @param newDate       the new date for the to-do entry
     * @return the body of the email notification as a String
     */
    private static String buildEmailBody(List<Collaborator> collaborators, AgendaEntry toDoEntry, LocalDate newDate) {
        StringBuilder emailBuilder = new StringBuilder();
        for (Collaborator collaborator : collaborators) {
            emailBuilder.append("Caro ").append(collaborator.getName()).append(",\n\n");
            emailBuilder.append("Esta é uma notificação sobre o adiamento de uma entrada na agenda.\n\n");
            emailBuilder.append("Entrada: ").append(toDoEntry.getTitle()).append("\n");
            emailBuilder.append("Parque: ").append(toDoEntry.getGreenSpace().getName()).append("\n");
            emailBuilder.append("Nova data: ").append(newDate).append("\n\n");
            emailBuilder.append("Por favor, tome nota desta alteração e ajuste os seus planos conforme necessário.\n\n");
        }
        emailBuilder.append("Atenciosamente,\n");
        emailBuilder.append("Equipe de Gestão de Parques");
        return emailBuilder.toString();
    }

    /**
     * Builds the body of the email notification to be sent to the collaborators for a canceled entry.
     *
     * @param collaborators the list of collaborators to notify
     * @param toDoEntry     the to-do entry that has been canceled
     * @return the body of the email notification as a String
     */
    private static String buildEmailBodyCancel(List<Collaborator> collaborators, ToDoEntry toDoEntry) {
        StringBuilder emailBuilder = new StringBuilder();
        for (Collaborator collaborator : collaborators) {
            emailBuilder.append("Caro ").append(collaborator.getName()).append(",\n\n");
            emailBuilder.append("Esta é uma notificação sobre o cancelamento de uma entrada na agenda.\n\n");
            emailBuilder.append("Entrada: ").append(toDoEntry.getTitle()).append("\n");
            emailBuilder.append("Parque: ").append(toDoEntry.getGreenSpace().getName()).append("\n");
            emailBuilder.append("Por favor, tome nota desta alteração e ajuste os seus planos conforme necessário.\n\n");
        }
        emailBuilder.append("Atenciosamente,\n");
        emailBuilder.append("Equipe de Gestão de Parques");
        return emailBuilder.toString();
    }

    /**
     * Builds the body of the email notification to be sent to the collaborators for a created entry.
     *
     * @param collaborators the list of collaborators to notify
     * @param toDoEntry     the to-do entry that has been created
     * @return the body of the email notification as a String
     */
    private static String buildEmailBodyCreate(List<Collaborator> collaborators, AgendaEntry toDoEntry) {
        StringBuilder emailBuilder = new StringBuilder();
        for (Collaborator collaborator : collaborators) {
            emailBuilder.append("Caro ").append(collaborator.getName()).append(",\n\n");
            emailBuilder.append("Esta é uma notificação sobre a atribuição de uma entrada na agenda.\n\n");
            emailBuilder.append("Entrada: ").append(toDoEntry.getTitle()).append("\n");
            emailBuilder.append("Parque: ").append(toDoEntry.getGreenSpace().getName()).append("\n");
            emailBuilder.append("Data: ").append(toDoEntry.getStartingDate()).append("\n");
            emailBuilder.append("Por favor, tome nota desta atribuição e ajuste os seus planos conforme necessário.\n\n");
        }
        emailBuilder.append("Atenciosamente,\n");
        emailBuilder.append("Equipe de Gestão de Parques");
        return emailBuilder.toString();
    }
}
