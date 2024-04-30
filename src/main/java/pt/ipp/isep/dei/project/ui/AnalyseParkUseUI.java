package pt.ipp.isep.dei.project.ui;


import pt.ipp.isep.dei.project.application.controller.AnalyseParkUseController;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class AnalyseParkUseUI {
    private String filepath;

    public void execute() throws FileNotFoundException {
        AnalyseParkUseController.runsPythonScript(filepath);
    }

    public void uploadFile() {
        System.out.print("Indique o caminho do ficheiro: ");
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        this.filepath = a;
    }
}
