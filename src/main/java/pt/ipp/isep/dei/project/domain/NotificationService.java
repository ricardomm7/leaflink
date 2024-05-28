package pt.ipp.isep.dei.project.domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The NotificationService class is responsible for managing notifications to teams and collaborators
 * about changes or updates to agenda entries and to-do entries.
 */
public class NotificationService {

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
    public static boolean notifyTeam(List<Collaborator> collaborators, ToDoEntry toDoEntry, LocalDate newDate) {
        List<String> emails = new ArrayList<>();
        for (Collaborator collaborator : collaborators) {
            emails.add(collaborator.getEmail());
        }

        String subject = "Entrada adiada: " + toDoEntry.getDescription();
        String body = buildEmailBody(collaborators, toDoEntry, newDate);

        try {
            // Creates the "Notifications" directory if it does not exist
            File directory = new File("Notifications");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Creates the notification file
            File file = new File(directory, subject + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            // Writes the subject and body of the message to the file
            writer.write(subject);
            writer.newLine();
            writer.write(body);

            // Closes the file
            writer.close();

            System.out.println("Notificação escrita no arquivo: " + file.getAbsolutePath());
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
    private static String buildEmailBody(List<Collaborator> collaborators, ToDoEntry toDoEntry, LocalDate newDate) {
        StringBuilder emailBuilder = new StringBuilder();

        for (Collaborator collaborator : collaborators) {
            emailBuilder.append("Caro ").append(collaborator.getName()).append(",\n\n");
            emailBuilder.append("Esta é uma notificação sobre o adiamento de uma entrada na agenda.\n\n");
            emailBuilder.append("Entrada: ").append(toDoEntry.getDescription()).append("\n");
            emailBuilder.append("Parque: ").append(toDoEntry.getGreenSpace().getName()).append("\n");
            emailBuilder.append("Nova data: ").append(newDate).append("\n\n");
            emailBuilder.append("Por favor, tome nota desta alteração e ajuste seus planos conforme necessário.\n\n");
        }

        emailBuilder.append("Atenciosamente,\n");
        emailBuilder.append("Equipe de Gestão de Parques");

        return emailBuilder.toString();
    }
}
