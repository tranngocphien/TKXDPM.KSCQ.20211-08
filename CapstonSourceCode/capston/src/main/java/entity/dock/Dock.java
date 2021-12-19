package entity.dock;

import entity.bicycle.Bicycle;
import entity.bicycle.CoupleBike;
import entity.bicycle.ElectricBicycle;
import entity.bicycle.Vehicle;
import entity.db.CAPSTONDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dock {
    private Long id;
    private String name;
    private String address;
    private Double area;
    private Long totalBike;

    private List<Vehicle> listBicycle;
    private List<Vehicle> listCoupleBike;
    private List<Vehicle> listElectricBike;

    public Dock() {
    }

    public Dock(Long id, String name, String address, Double area, Long totalBike) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.area = area;
        this.totalBike = totalBike;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Long getTotalBike() {
        return totalBike;
    }

    public void setTotalBike(Long totalBike) {
        this.totalBike = totalBike;
    }

    public List<Vehicle> getListBicycle() {
        return listBicycle;
    }

    public void setListBicycle(List<Vehicle> listBicycle) {
        this.listBicycle = listBicycle;
    }

    public List<Vehicle> getListCoupleBike() {
        return listCoupleBike;
    }

    public void setListCoupleBike(List<Vehicle> listCoupleBike) {
        this.listCoupleBike = listCoupleBike;
    }

    public List<Vehicle> getListElectricBike() {
        return listElectricBike;
    }

    public void setListElectricBike(List<Vehicle> listElectricBike) {
        this.listElectricBike = listElectricBike;
    }

    public List<Dock> getAllDock() throws SQLException {
        Statement stm = CAPSTONDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from dock");
        List<Dock> list = new ArrayList<>();
        while (res.next()) {
            Dock dock = new Dock();
            dock.setId(res.getLong("id"));
            dock.setAddress(res.getString("address"));
            dock.setArea(res.getDouble("area"));
            dock.setName(res.getString("name"));
            dock.setTotalBike(res.getLong("total_bike"));
            list.add(dock);
        }
        return list;
    }

    public List<Dock> searchDockByString(String search) throws SQLException {
        Statement stm = CAPSTONDB.getConnection().createStatement();
        String sql = "SELECT * FROM dock where LOCATE('"+search+"', CONCAT_WS(', ', address, name))";
        ResultSet res = stm.executeQuery(sql);
        List<Dock> list = new ArrayList<>();
        while (res.next()) {
            Dock dock = new Dock();
            dock.setId(res.getLong("id"));
            dock.setAddress(res.getString("address"));
            dock.setArea(res.getDouble("area"));
            dock.setName(res.getString("name"));
            dock.setTotalBike(res.getLong("total_bike"));
            list.add(dock);
        }
        return list;
    }

    public Dock getDockById(Long id) throws SQLException{
        String sql = "SELECT * FROM dock where id = "+id;
        Statement stm = CAPSTONDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()) {
            Dock dock = new Dock();
            dock.setId(res.getLong("id"));
            dock.setAddress(res.getString("address"));
            dock.setArea(res.getDouble("area"));
            dock.setName(res.getString("name"));
            dock.setTotalBike(res.getLong("total_bike"));

            dock.setListBicycle(new Bicycle().listBikeByDockId(dock.getId()));
            dock.setListCoupleBike(new CoupleBike().listBikeByDockId(dock.getId()));
            dock.setListElectricBike(new ElectricBicycle().listBikeByDockId(dock.getId()));

            return dock;
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        Dock dock = new Dock();
        Dock d = dock.getDockById(1l);
        System.out.println(d);
    }

}
