package controller;

import entity.bicycle.Vehicle;
import entity.dock.Dock;

import java.sql.SQLException;

/**
 * This class controls the flow of view detail 1 dock in Capston project
 *
 * @author PhienTran
 */
public class ViewDockController extends BaseController {
    public Dock getDetailDock(long id) throws SQLException {
        return new Dock().getDockById(id);

    }

    public Vehicle getVehicleInDockByCode(String code, long id) throws SQLException {
        Vehicle vehicle = new Vehicle().searchVehicleByCode(code, id);
        return vehicle;
    }



}

