package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.AssignVehiclesController;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssignVehiclesUI implements Runnable {
    private final AssignVehiclesController controller;

    public AssignVehiclesUI() {
        this.controller = new AssignVehiclesController();
    }

    public void assignVehicles() {
        List<AgendaEntryDto> a = controller.getAgendaEntryList();

        if (a.isEmpty()) {
            throw new IllegalArgumentException("The agenda entry is empty.");
        }
        int i = 1;
        for (AgendaEntryDto d : a) {
            System.out.println(i + d.toString());
            i++;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Select an entry:");
        int entry = sc.nextInt() - 1;

        List<VehicleDto> vv = new ArrayList<>();
        boolean loop = true;
        List<VehicleDto> b = controller.getVehicleList();

        if (b.isEmpty()) {
            throw new IllegalArgumentException("The vehicle list is empty.");
        }

        while (loop) {
            int o = 1;
            for (VehicleDto d : b) {
                System.out.println(o + d.toString());
                o++;
            }
            System.out.println("Select a vehicle:");
            int index = sc.nextInt() - 1;
            vv.add(b.get(index));
            controller.setVehicleAvailability(index, false);
            System.out.println("Do you want to select another vehicle? (Y/N)");
            String decision = sc.nextLine();
            loop = decision.trim().equalsIgnoreCase("Y");
        }
        controller.updateEntryWithVehicles(entry, b);

    }

    @Override
    public void run() {
        assignVehicles();
    }
}
