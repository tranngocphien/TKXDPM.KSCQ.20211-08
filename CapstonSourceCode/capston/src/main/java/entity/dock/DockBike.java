package entity.dock;

import entity.bicycle.Vehicle;
import entity.db.CAPSTONDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Dock bike lưu thông tin xe của từng bãi xe
 * @author THT
 *
 */
public class DockBike {
    private Long id;
    private Long dockId;
    private Long bikeId;
    private Long locationAtDock; //Vị trí của xe đó trong bãi

    public DockBike() {

    }

    public DockBike(Long id, Long dockId, Long bikeId, Long locationAtDock) {
        this.id = id;
        this.dockId = dockId;
        this.bikeId = bikeId;
        this.locationAtDock = locationAtDock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDockId() {
        return dockId;
    }

    public void setDockId(Long dockId) {
        this.dockId = dockId;
    }

    public Long getBikeId() {
        return bikeId;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }

    public Long getLocationAtDock() {
        return locationAtDock;
    }

    public void setLocationAtDock(Long locationAtDock) {
        this.locationAtDock = locationAtDock;
    }

    public List getDockBikeByDockId(Long dockId) throws SQLException {
        String sql = "SELECT * FROM dock_bike db left join bike b on db.bike_id = b.id where dock_id = "+dockId;
        Statement stm = CAPSTONDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        List<DockBike> dockBikeList = new ArrayList<>();
        if(res.next()) {
            DockBike dockBike = new DockBike();
            dockBike.setId(res.getLong("id"));
            dockBike.setBikeId(res.getLong("bike_id"));
            dockBike.setDockId(res.getLong("dock_id"));
            dockBike.setLocationAtDock(res.getLong("location_at_dock"));
            dockBikeList.add(dockBike);
        }
        return dockBikeList;
    }

    public List getDockBikeByDockIdAndEmpty(Long dockId) throws SQLException {
        String sql = "SELECT * FROM dock_bike db where db.bike_id = 0 and db.where dock_id = "+dockId;
        Statement stm = CAPSTONDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        List<DockBike> dockBikeList = new ArrayList<>();
        if(res.next()) {
            DockBike dockBike = new DockBike();
            dockBike.setId(res.getLong("id"));
            dockBike.setBikeId(res.getLong("bike_id"));
            dockBike.setDockId(res.getLong("dock_id"));
            dockBike.setLocationAtDock(res.getLong("location_at_dock"));
            dockBikeList.add(dockBike);
        }
        return dockBikeList;
    }
}
