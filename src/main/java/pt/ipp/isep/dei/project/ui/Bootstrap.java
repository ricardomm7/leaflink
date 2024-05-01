package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addUsers();
        addVehicles();
    }
    private void addVehicles(){
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

        // Example 1
        vehicleRepository.registerVehicle("VIN12345678901234", "Toyota", "Corolla", "Sedan", "AB1234", 1500.0, 2000.0,
                50000.0, "15-03-2023" ,  "12-03-2023", 10000);

        // Example 2
        vehicleRepository.registerVehicle("VIN56789012345678", "Ford", "Fiesta", "Hatchback", "CD5678", 1200.0, 1800.0,
                40000.0, "10-10-2020", "30-09-2020", 8000);

        // Example 3
        vehicleRepository.registerVehicle("VIN90123456789012", "Volkswagen", "Golf", "Hatchback", "EF9012", 1400.0, 2100.0,
                60000.0, "10-10-2010", "01-10-2010", 12000);

    }



    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE,
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Collaborator", "collaborator@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);
    }
}