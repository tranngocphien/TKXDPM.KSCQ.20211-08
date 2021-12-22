package entity.bicycle;

import entity.db.CAPSTONDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private Long id;
    private String bikeCode;
    private String name;
    private Long dockId;
    private Long locationAtDock;
    private Integer pin;

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

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Integer getPin() {
        return pin;
    }

    public Vehicle searchVehicleById(Long id) throws SQLException {
        String sql = "SELECT * FROM bike where id = "+id;
        Statement stm = CAPSTONDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()) {
            Vehicle vehicle = new ElectricBicycle();
            vehicle.setId(res.getLong("id"));
            vehicle.setBikeCode(res.getString("bike_code"));
            vehicle.setName(res.getString("name"));
            vehicle.setPin(res.getInt("pin"));
            return vehicle;
        }
        return null;
    }

    public List listBikeByDockId(Long id) throws SQLException {
        return null;
    }

    public void returnBike(String bikeCode, Long dockId) {

    }

    public Double getDeposit(Integer classifyId) {
        String sql = "SELECT * FROM classify_bike WHERE id = "+classifyId;
        try {
            Statement stm = CAPSTONDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);
            if(res.next()) {
                return res.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
