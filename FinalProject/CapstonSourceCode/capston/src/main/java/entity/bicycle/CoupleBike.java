package entity.bicycle;

import entity.db.CAPSTONDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoupleBike extends Vehicle{
    @Override
    public Vehicle searchVehicleById(Long bikeId) throws SQLException {
        String sql = "SELECT * FROM bike b left join dock_bike db on db.bike_id = b.id where b.id = "+bikeId;
        Statement stm = CAPSTONDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        List<Bicycle> bicycleList = new ArrayList<>();
        if(res.next()) {
            Bicycle bicycle = new Bicycle();
            bicycle.setId(res.getLong("b.id"));
            bicycle.setBikeCode(res.getString("bike_code"));
            bicycle.setName(res.getString("name"));
            bicycle.setDockId(res.getLong("dock_id"));
            bicycle.setLocationAtDock(res.getLong("location_at_dock"));
            return bicycle;
        }
        return null;
    }

    @Override
    public List listBikeByDockId(Long dockId) throws SQLException {
        String sql = "SELECT * FROM bike b left join dock_bike db on db.bike_id = b.id where b.classify_id = 2 and db.dock_id = "+dockId;
        Statement stm = CAPSTONDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        List<CoupleBike> bicycleList = new ArrayList<>();
        while (res.next()) {
            CoupleBike bicycle = new CoupleBike();
            bicycle.setId(res.getLong("b.id"));
            bicycle.setBikeCode(res.getString("bike_code"));
            bicycle.setName(res.getString("name"));
            bicycle.setDockId(res.getLong("dock_id"));
            bicycle.setLocationAtDock(res.getLong("location_at_dock"));
            bicycleList.add(bicycle);
        }
        return bicycleList;
    }
}
