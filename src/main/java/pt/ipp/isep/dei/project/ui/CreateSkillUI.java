package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.CreateSkillController;

import java.util.Scanner;

/**
 * This class represents the user interface for creating a new skill.
 */
public class CreateSkillUI implements Runnable {

    private static Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user to create a new skill.
     */
    public void createNewSkill() {
        CreateSkillController jc = new CreateSkillController();
        System.out.println("What is the skill designation");
        String designation = sc.nextLine();

        System.out.println("You want to create a skill with the name " + designation + "? (Y/N)");
        String decis = sc.nextLine();
        if (decis.trim().equals("Y")) {
            jc.createSkill(designation);
            System.out.println("Operation successfully completed!");
        } else {
            System.out.println("Operation cancelled!");
        }
    }

    /**
     * Runs the user interface for creating a new skill.
     */
    public void run() {
        createNewSkill();
    }
}
