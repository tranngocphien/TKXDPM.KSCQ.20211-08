package controller;

import entity.dock.Dock;

import java.sql.SQLException;
import java.util.List;

public class SearchDockController extends BaseController {

    public List<Dock> getListDock() throws SQLException {
        return new Dock().getAllDock();
    }
}
