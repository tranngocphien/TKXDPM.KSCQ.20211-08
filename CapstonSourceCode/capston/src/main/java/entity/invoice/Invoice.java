package entity.invoice;

import java.sql.Timestamp;

public class Invoice {
    private Long id;
    private Long totalAmount;
    private Timestamp totalTime;
    private Long bikeId;
    private Long dockId;
    private Long userId;
    private Timestamp createdAt;

    public Invoice() {

    }

    public Invoice(Long id, Long totalAmount, Timestamp totalTime, Long bikeId, Long dockId, Long userId, Timestamp createdAt) {
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

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Timestamp getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Timestamp totalTime) {
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
}
