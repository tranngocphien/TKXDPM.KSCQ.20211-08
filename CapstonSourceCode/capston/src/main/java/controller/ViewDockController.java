package controller;

import entity.dock.Dock;

import java.sql.SQLException;

public class ViewDockController extends BaseController {
    public Dock getDetailDock(long id) throws SQLException {
        return new Dock().getDockById(id);

    }
}
