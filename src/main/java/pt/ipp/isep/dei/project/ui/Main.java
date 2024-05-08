package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.ui.console.menu.MainMenuUI;

public class Main {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();
            bootstrap.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}