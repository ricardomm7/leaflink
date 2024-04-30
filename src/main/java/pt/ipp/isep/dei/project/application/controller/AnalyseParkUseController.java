package pt.ipp.isep.dei.project.application.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AnalyseParkUseController {
    public static void runsPythonScript(String filename) throws FileNotFoundException {
        checksFormattingErrors(filename);
        //é suposto executar mas não sei como fazer
    }

    private static void checksFormattingErrors(String filename) throws FileNotFoundException {
        // Abre o arquivo CSV
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            // Verifica a primeira linha
            String firstLine = scanner.nextLine();
            if (!firstLine.equals("Escalao;Y/N;Visits")) {
                throw new IllegalArgumentException("Error: The first line does not have the correct format.");
            }

            // Verifica as linhas restantes
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                // Verifica o número de partes na linha
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Error: Invalid format in the line: " + line);
                }

                // Verifica se a primeira parte é um número de 1 a 3
                int escalao = Integer.parseInt(parts[0]);
                if (escalao < 1 || escalao > 3) {
                    throw new NumberFormatException("Error: The first part is not a valid number in the line: " + line);
                }

                // Verifica se a segunda parte é Y ou N
                if (!parts[1].equals("Y") && !parts[1].equals("N")) {
                    throw new IllegalArgumentException("Error: The second part is not Y or N in the line: " + line);
                }

                // Verifica se a terceira parte é um inteiro
                int visits = Integer.parseInt(parts[2]);
            }
        } catch (NumberFormatException e) {
            // Captura exceções de formato de número
            System.out.println(e.getMessage());
        }
        // Fecha o scanner
    }
}
