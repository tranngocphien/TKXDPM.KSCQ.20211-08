package controller;

import entity.bicycle.Vehicle;
import entity.dock.Dock;
import entity.dock.DockBike;

import java.sql.SQLException;
import java.util.List;

public class ReturnBikeController extends BaseController{
    public List<Dock> getListDock()  {
        try {
            return new Dock().getAllDock();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void returnBike(Vehicle vehicle, Long dockId) {
        try {
            List<DockBike> dockBikeEmpty = new DockBike().getDockBikeByDockIdAndEmpty(dockId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
