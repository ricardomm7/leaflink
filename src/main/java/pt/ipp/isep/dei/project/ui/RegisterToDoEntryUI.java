package pt.ipp.isep.dei.project.ui;

import org.w3c.dom.ls.LSOutput;
import pt.ipp.isep.dei.project.Dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.project.application.controller.RegisterToDoEntryController;
import pt.ipp.isep.dei.project.domain.DegreeOfUrgency;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class RegisterToDoEntryUI implements Runnable {

    private final RegisterToDoEntryController controller;

    /**
     * Constructs a new RegisterGreenSpaceUI object.
     */
    public RegisterToDoEntryUI() {
        this.controller = new RegisterToDoEntryController();
    }

    public void registerToDoEntry(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Available green spaces: ");
            List<GreenSpaceDto> greenSpaceDtos = controller.getGreenSpacesDto();
            int index = 1;
            for(GreenSpaceDto gs : greenSpaceDtos){
                System.out.println(index + ". " + gs);
                index++;
            }
            int gsIndex = sc.nextInt();
            GreenSpaceDto greenSpaceDto = greenSpaceDtos.get(gsIndex-1);

            System.out.println("Entry description: ");
            String description = sc.nextLine();

            System.out.println("Select degree of urgency: ");
            DegreeOfUrgency[] dgUrg = DegreeOfUrgency.values();
            for (int i = 0; i < dgUrg.length; i++) {
                System.out.println((i+1) + "." + dgUrg[i]);
            }
            DegreeOfUrgency urg = dgUrg[sc.nextInt()-1];
            sc.nextLine();

            System.out.println("Entry entry duration: ");
            String duration = sc.nextLine();

            System.out.println("\nDo you want to register this Entry? (Y/N)");
            String decision = sc.nextLine();
            if (decision.trim().equalsIgnoreCase("Y")) {
                controller.createNewEntry(greenSpaceDto, description,urg,duration);
                System.out.println("Green space registered successfully!");
            } else {
                System.out.println("Operation cancelled!");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }

    }


    @Override
    public void run() {
        registerToDoEntry();
    }
}
