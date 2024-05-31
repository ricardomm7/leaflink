package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.project.mappers.VehicleMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.util.List;

/**
 * The AssignVehiclesController class handles the logic for assigning vehicles to agenda entries.
 * It provides methods for retrieving agenda entries for a specific user, getting the list of available vehicles,
 * updating vehicle availability, and updating agenda entries with assigned vehicles.
 */
public class AssignVehiclesController {
    private final Repositories repositories;
    private final EntryRepository entryRepository;
    private final VehicleRepository vehicleRepository;

    /**
     * Constructs a new AssignVehiclesController object and initializes the repositories.
     */
    public AssignVehiclesController() {
        this.repositories = Repositories.getInstance();
        this.entryRepository = repositories.getEntryRepository();
        this.vehicleRepository = repositories.getVehicleRepository();
    }

    /**
     * Gets the list of agenda entries for the given user.
     *
     * @param u the UserSession object representing the user
     * @return the list of AgendaEntryDto objects for the user
     */
    public List<AgendaEntryDto> getAgendaEntryList(UserSession u) {

        return AgendaEntryMapper.toDtoList(entryRepository.getAgendaEntryListByGSM(u));
    }

    /**
     * Gets the list of available vehicles.
     *
     * @return the list of available VehicleDto objects
     */
    public List<VehicleDto> getVehicleList() {

        return VehicleMapper.toDtoList(vehicleRepository.getAvailableVehicleList());
    }

    /**
     * Sets the availability of a vehicle at the specified index.
     *
     * @param vehicleIndex the index of the vehicle
     * @param isAvailable  the availability status to be set (true for available, false for unavailable)
     */
    public void setVehicleAvailability(int vehicleIndex, Boolean isAvailable) {
        vehicleRepository.setVehicleAvailability(vehicleIndex, isAvailable);
    }

    /**
     * Updates an agenda entry with the assigned vehicles.
     *
     * @param entryIndex     the index of the agenda entry
     * @param vehicleDtoList the list of VehicleDto objects representing the assigned vehicles
     */
    public void updateEntryWithVehicles(int entryIndex, List<VehicleDto> vehicleDtoList) {
        entryRepository.updateVehiclesAgendaEntry(entryIndex, vehicleDtoList);
    }
}
