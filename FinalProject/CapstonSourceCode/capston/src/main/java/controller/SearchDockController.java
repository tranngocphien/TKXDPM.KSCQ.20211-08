package controller;

import entity.dock.Dock;

import java.sql.SQLException;
import java.util.List;

public class SearchDockController extends BaseController {

    public List<Dock> getListDock() throws SQLException {
        return new Dock().getAllDock();
    }

    public List<Dock> searchDockByString(String search) throws SQLException {
        return new Dock().searchDockByString(search);
    }
}
