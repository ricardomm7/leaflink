package pt.ipp.isep.dei.project.domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    public static Team getTeamByEntry(AgendaEntry agendaEntry) {
        return agendaEntry.getAssignedTeam();
    }

    public static List<Collaborator> getCollaboratorsList(Team team) {
        return team.getCollaborators();
    }

    public static boolean notifyTeam(List<Collaborator> collaborators, ToDoEntry toDoEntry, LocalDate newDate) {
        List<String> emails = new ArrayList<>();
        for (Collaborator collaborator : collaborators) {
            emails.add(collaborator.getEmail());
        }

        String subject = "Entrada adiada: " + toDoEntry.getDescription();
        String body = buildEmailBody(collaborators, toDoEntry, newDate);

        try {
            // Cria o diretório "Notifications" se não existir
            File directory = new File("Notifications");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Cria o arquivo de notificação
            File file = new File(directory, subject + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            // Escreve o assunto e o corpo da mensagem no arquivo
            writer.write(subject);
            writer.newLine();
            writer.write(body);

            // Fecha o arquivo
            writer.close();

            System.out.println("Notificação escrita no arquivo: " + file.getAbsolutePath());
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao escrever a notificação: " + e.getMessage());
            return false;
        }
    }

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




