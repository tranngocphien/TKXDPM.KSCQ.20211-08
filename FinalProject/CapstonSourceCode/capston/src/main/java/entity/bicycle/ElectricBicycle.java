package entity.bicycle;

import entity.db.CAPSTONDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ElectricBicycle extends Vehicle{
    private Integer pin;

    public ElectricBicycle() {

    }

    public ElectricBicycle(Integer pin) {
        this.pin = pin;
    }

    public ElectricBicycle(Long id, String bikeCode, String name, Integer pin) {
        super(id, bikeCode, name);
        this.pin = pin;
    }

    public ElectricBicycle(Long id, String bikeCode, String name, Long dockId, Long locationAtDock, Integer pin) {
        super(id, bikeCode, name, dockId, locationAtDock);
        this.pin = pin;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    @Override
    public Vehicle searchVehicleById(Long bikeId) throws SQLException {
        String sql = "SELECT * FROM bike b left join dock_bike db on db.bike_id = b.id where b.id = "+bikeId;
        Statement stm = CAPSTONDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        List<Bicycle> bicycleList = new ArrayList<>();
        if(res.next()) {
            ElectricBicycle bicycle = new ElectricBicycle();
            bicycle.setId(res.getLong("b.id"));
            bicycle.setBikeCode(res.getString("bike_code"));
            bicycle.setName(res.getString("name"));
            bicycle.setPin(res.getInt("pin"));
            bicycle.setDockId(res.getLong("dock_id"));
            bicycle.setLocationAtDock(res.getLong("location_at_dock"));
            return bicycle;
        }
        return null;
    }

    @Override
    public List listBikeByDockId(Long dockId) throws SQLException {
        String sql = "SELECT * FROM bike b left join dock_bike db on db.bike_id = b.id where b.classify_id = 3 and db.dock_id = "+dockId;
        Statement stm = CAPSTONDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        List<ElectricBicycle> bicycleList = new ArrayList<>();
        while (res.next()) {
            ElectricBicycle bicycle = new ElectricBicycle();
            bicycle.setId(res.getLong("b.id"));
            bicycle.setBikeCode(res.getString("bike_code"));
            bicycle.setPin(res.getInt("pin"));
            bicycle.setName(res.getString("name"));
            bicycle.setDockId(res.getLong("dock_id"));
            bicycle.setLocationAtDock(res.getLong("location_at_dock"));
            bicycleList.add(bicycle);
        }
        return bicycleList;
    }
}
