package entity.bicycle;

import java.sql.Timestamp;

public class BorrowingBike {
    private Long id;
    private Long userId;
    private Long bikeId;
    private Long borrowedAtDockId;
    private Timestamp totalTime;
    private Timestamp borrowedAt;

    public BorrowingBike() {

    }

    public BorrowingBike(Long id, Long userId, Long bikeId, Long borrowedAtDockId, Timestamp totalTime, Timestamp borrowedAt) {
        this.id = id;
        this.userId = userId;
        this.bikeId = bikeId;
        this.borrowedAtDockId = borrowedAtDockId;
        this.totalTime = totalTime;
        this.borrowedAt = borrowedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBikeId() {
        return bikeId;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }

    public Long getBorrowedAtDockId() {
        return borrowedAtDockId;
    }

    public void setBorrowedAtDockId(Long borrowedAtDockId) {
        this.borrowedAtDockId = borrowedAtDockId;
    }

    public Timestamp getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Timestamp totalTime) {
        this.totalTime = totalTime;
    }

    public Timestamp getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(Timestamp borrowedAt) {
        this.borrowedAt = borrowedAt;
    }
}
