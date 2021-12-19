package entity.bicycle;

import java.sql.SQLException;
import java.util.List;

public class Vehicle {
    private Long id;
    private String bikeCode;
    private String name;
    private Long dockId;
    private Long locationAtDock;

    public Vehicle() {

    }

    public Vehicle(Long id, String bikeCode, String name) {
        this.id = id;
        this.bikeCode = bikeCode;
        this.name = name;
    }

    public Vehicle(Long id, String bikeCode, String name, Long dockId, Long locationAtDock) {
        this.id = id;
        this.bikeCode = bikeCode;
        this.name = name;
        this.dockId = dockId;
        this.locationAtDock = locationAtDock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBikeCode() {
        return bikeCode;
    }

    public void setBikeCode(String bikeCode) {
        this.bikeCode = bikeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDockId() {
        return dockId;
    }

    public void setDockId(Long dockId) {
        this.dockId = dockId;
    }

    public Long getLocationAtDock() {
        return locationAtDock;
    }

    public void setLocationAtDock(Long locationAtDock) {
        this.locationAtDock = locationAtDock;
    }

    public Vehicle searchVehicleById(Long id) throws SQLException {
        return null;
    }

    public List listBikeByDockId(Long id) throws SQLException {
        return null;
    }

    public void returnBike(String bikeCode, Long dockId) {

    }

}
