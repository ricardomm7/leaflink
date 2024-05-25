package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;

import java.util.List;

public class ListGreenSpacesUI implements Runnable {
    private final ListGreenSpacesController controller;


    public ListGreenSpacesUI() {
        controller = new ListGreenSpacesController();
    }

    public void getOrganizedList() {
        UserSession loggedUser = ApplicationSession.getInstance().getCurrentSession();
        List<GreenSpaceDto> u = controller.getList(loggedUser);
        for (GreenSpaceDto w : u) {
            System.out.println("Name: " + w.getName() + " | Area: " + w.getArea() + " | Address: " + w.getAddress() + " | Type: " + w.getType().toString());
        }
    }

    @Override
    public void run() {
        getOrganizedList();
    }
}
