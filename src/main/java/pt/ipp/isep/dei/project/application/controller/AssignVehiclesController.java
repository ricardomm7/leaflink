package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.util.List;

public class AssignVehiclesController {
    private final Repositories repositories;
    private final EntryRepository entryRepository;
    private final VehicleRepository vehicleRepository;

    public AssignVehiclesController() {
        this.repositories = Repositories.getInstance();
        this.entryRepository = repositories.getEntryRepository();
        this.vehicleRepository = repositories.getVehicleRepository();
    }

    public List<AgendaEntryDto> getAgendaEntryList(UserSession u) {
        return entryRepository.getAgendaEntryListByGSM(u);
    }

    public List<VehicleDto> getVehicleList() {
        return vehicleRepository.getAvailableVehicleList();
    }

    public void setVehicleAvailability(int vehicleIndex, Boolean isAvailable) {
        vehicleRepository.setVehicleAvailability(vehicleIndex, isAvailable);
    }

    public void updateEntryWithVehicles(int entryIndex, List<VehicleDto> vehicleDtoList) {
        entryRepository.updateVehiclesAgendaEntry(entryIndex, vehicleDtoList);
    }
}
