package pt.ipp.isep.dei.project.Dto;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;

import java.util.Objects;

public class GreenSpaceDto {

    private String name;
    private GreenSpaceType type;
    private double area;

    //Se quiseres faz o UserSessionDto
    private UserSession manager;

    //Em vez de criar o AddressDto
    private String address;
    private String city;
    private String zipCode;

    public GreenSpaceDto(String name, GreenSpaceType type, double area, UserSession manager, String address, String city, String zipCode) {
        this.name = name;
        this.type = type;
        this.area = area;
        this.manager = manager;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GreenSpaceType getType() {
        return type;
    }

    public void setType(GreenSpaceType type) {
        this.type = type;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public UserSession getManager() {
        return manager;
    }

    public void setManager(UserSession manager) {
        this.manager = manager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GreenSpaceDto that = (GreenSpaceDto) o;
        return Double.compare(area, that.area) == 0 && Objects.equals(name, that.name) && type == that.type && Objects.equals(manager, that.manager) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, area, manager, address, city, zipCode);
    }

    @Override
    public String toString() {
        return "GreenSpaceDto{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", area=" + area +
                ", manager=" + manager +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
