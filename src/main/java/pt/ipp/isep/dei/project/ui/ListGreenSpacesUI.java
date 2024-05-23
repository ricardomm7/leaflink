package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;

import java.util.List;
import java.util.Scanner;

public class ListGreenSpacesUI implements Runnable {
    private final ListGreenSpacesController controller;
    private static final Scanner sc = new Scanner(System.in);


    public ListGreenSpacesUI() {
        controller = new ListGreenSpacesController();
    }

    public void getOrganizedList() {
        //System.out.println("Select the sorting algorithm from the existing ones:");
        UserSession loggedUser = ApplicationSession.getInstance().getCurrentSession();
        List<GreenSpaceDto> u = controller.getList("a", loggedUser);
        for (GreenSpaceDto w : u) {
            System.out.println("Name: " + w.getName() + " | Area: " + w.getArea() + " | Address: " + w.getAddress());
        }
    }

    @Override
    public void run() {
        getOrganizedList();
    }
}
