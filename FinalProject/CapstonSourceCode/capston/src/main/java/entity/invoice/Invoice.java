package entity.invoice;

import entity.db.CAPSTONDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Invoice {
    private Long id;
    private Double totalAmount;
    private Long totalTime;
    private Long bikeId;
    private Long dockId;
    private Long userId;
    private Timestamp createdAt;

    public Invoice() {

    }

    public Invoice(Long id, Double totalAmount, Long totalTime, Long bikeId, Long dockId, Long userId, Timestamp createdAt) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.totalTime = totalTime;
        this.bikeId = bikeId;
        this.dockId = dockId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Long getBikeId() {
        return bikeId;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }

    public Long getDockId() {
        return dockId;
    }

    public void setDockId(Long dockId) {
        this.dockId = dockId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Invoice createInvoice(Double totalAmount, Long totalTime, Long bikeId, Long dockId, Timestamp createdAt, Long userId) {
        String sql = "INSERT INTO `invoice` (`total_amount`, `total_time`, `bike_id`, `dock_id`, `created_at`, `user_id`) " +
                "VALUES ("+totalAmount+", "+totalTime+", "+bikeId+", "+dockId+", '"+createdAt+"', "+userId+")";
        try {
            Statement stm = CAPSTONDB.getConnection().createStatement();
            stm.executeUpdate(sql);

            String query = "SELECT * FROM invoice where user_id = "+userId+" order by created_at desc limit 1";
            Statement stm1 = CAPSTONDB.getConnection().createStatement();
            ResultSet rs = stm1.executeQuery(query);
            if (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getLong("id"));
                invoice.setCreatedAt(rs.getTimestamp("created_at"));
                invoice.setBikeId(rs.getLong("bike_id"));
                invoice.setDockId(rs.getLong("dock_id"));
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                invoice.setUserId(userId);
                invoice.setTotalTime(rs.getLong("total_time"));
                return invoice;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
