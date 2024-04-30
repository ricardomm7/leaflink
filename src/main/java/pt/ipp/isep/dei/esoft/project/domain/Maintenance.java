package pt.ipp.isep.dei.esoft.project.domain;

public class Maintenance {
    private final String data;
    private final String vehiclePlate;
    private final int km;

    public Maintenance(String data, String vehiclesPlate, int kilometragem)  {
        this.data = data;
        this.vehiclePlate = vehiclesPlate;
        this.km = kilometragem;
    }

    public String getData() {
        return data;
    }

    public int getKm() {
        return km;
    }

    public String getVehiclePlate(){
        return vehiclePlate;
    }
}
