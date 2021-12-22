package entity.bicycle;

import entity.db.CAPSTONDB;
import entity.dock.DockBike;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BorrowingBike {
    private Long id;
    private Long userId;
    private Long bikeId;
    private Long borrowedAtDockId;
    private Timestamp totalTime;
    private Timestamp borrowedAt;
    private Integer classifyId;
    private String totalTimeDisplay;
    private Double deposit;
    private Double totalAmount;

    public BorrowingBike() {

    }

    public BorrowingBike(Long id, Long userId, Long bikeId, Long borrowedAtDockId, Timestamp totalTime, Timestamp borrowedAt, Integer classifyId) {
        this.id = id;
        this.userId = userId;
        this.bikeId = bikeId;
        this.borrowedAtDockId = borrowedAtDockId;
        this.totalTime = totalTime;
        this.borrowedAt = borrowedAt;
        this.classifyId =classifyId;
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

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public void setTotalTimeDisplay(String totalTimeDisplay) {
        this.totalTimeDisplay = totalTimeDisplay;
    }

    public String getTotalTimeDisplay() {
        return totalTimeDisplay;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    //    public BorrowingBike getBorrowingBikeByBikeId(Long bikeId) {
//        try {
//            Statement stm = CAPSTONDB.getConnection().createStatement();
//            String sql = "SELECT * FROM borrowing_bikes WHERE bike_id = "+bikeId;
//            ResultSet res = stm.executeQuery(sql);
//            if (res.next()) {
//                BorrowingBike borrowingBike = new BorrowingBike();
//                borrowingBike.setId(res.getLong("id"));
//                borrowingBike.setUserId(res.getLong("user_id"));
//                borrowingBike.setBikeId(res.getLong("bike_id"));
//                borrowingBike.setTotalTime(res.getTimestamp("total_time"));
//                borrowingBike.setBorrowedAt(res.getTimestamp("borrowed_at"));
//                borrowingBike.setBorrowedAtDockId(res.getLong("borrowed_at_dock_id"));
//                borrowingBike.setClassifyId(res.getInt("classify_id"));
//                return borrowingBike;
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return null;
//    }

    public void addBorrowingBike(Long userId, Long bikeId) {
        try {

            String query = "SELECT * FROM bikes as b left join dock_bike as db on b.id = db.bike_id WHERE b.id = "+bikeId;
            Statement stm1 = CAPSTONDB.getConnection().createStatement();
            ResultSet res = stm1.executeQuery(query);
            Map<String, Object> objectMap = new HashMap<>();
            if (res.next()) {
                objectMap.put("bikeId", res.getLong("bike_id"));
                objectMap.put("dockId", res.getLong("dock_id"));
                objectMap.put("locationAtDock", res.getLong("location_at_dock"));
                objectMap.put("dockBikeId", res.getLong("db.id"));
                objectMap.put("classifyId", res.getInt("classify_id"));
            }

            Statement stm = CAPSTONDB.getConnection().createStatement();
            String sql = "INSERT INTO borrowing_bikes (`user_id`,`bike_id`,`borrowed_at`,`borrowed_at_dock_id`, `classify_id`) " +
                    "VALUES ("+userId+","+bikeId+","+new Timestamp(System.currentTimeMillis())+","+ (Long) objectMap.get("dockId")+","+ (int) objectMap.get("classifyId")+")";
            stm.executeUpdate(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeBikeBorrowing(Long bikeId) {
        String sql = "DELETE FROM borrowing_bikes where bike_id = "+bikeId;
        try {
            Statement stm2 = CAPSTONDB.getConnection().createStatement();
            stm2.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BorrowingBike viewDetailBorrowingBikeByBikeId(Long bikeId) {
        try {
            String query = "SELECT * FROM borrowing_bikes WHERE bike_id = "+bikeId;
            Statement stm1 = CAPSTONDB.getConnection().createStatement();
            ResultSet res = stm1.executeQuery(query);
            if (res.next()) {
                BorrowingBike borrowingBike = new BorrowingBike();
                borrowingBike.setId(res.getLong("id"));
                borrowingBike.setBikeId(res.getLong("bike_id"));
                borrowingBike.setBorrowedAtDockId(res.getLong("borrowed_at_dock_id"));
                borrowingBike.setUserId(res.getLong("user_id"));
                borrowingBike.setBorrowedAt(res.getTimestamp("borrowed_at"));
                Long current = System.currentTimeMillis();
                Long borrowedAtMilli = res.getTimestamp("borrowed_at").getTime();
                borrowingBike.setTotalTime(new Timestamp(current-borrowedAtMilli));
                borrowingBike.setClassifyId(res.getInt("classify_id"));

                long HH = TimeUnit.MILLISECONDS.toHours(current-borrowedAtMilli);
                long MM = TimeUnit.MILLISECONDS.toMinutes(current-borrowedAtMilli) % 60;
                long SS = TimeUnit.MILLISECONDS.toSeconds(current-borrowedAtMilli) % 60;
                String timeInHHMMSS = String.format("%02d:%02d:%02d", HH, MM, SS);

                borrowingBike.setTotalTimeDisplay(timeInHHMMSS);
                return borrowingBike;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public BorrowingBike viewDetailBorrowingBikeByUserId(Long userId) {
        try {
            String query = "SELECT * FROM borrowing_bikes WHERE user_id = "+userId;
            Statement stm1 = CAPSTONDB.getConnection().createStatement();
            ResultSet res = stm1.executeQuery(query);
            if (res.next()) {
                BorrowingBike borrowingBike = new BorrowingBike();
                borrowingBike.setId(res.getLong("id"));
                borrowingBike.setBikeId(res.getLong("bike_id"));
                borrowingBike.setBorrowedAtDockId(res.getLong("borrowed_at_dock_id"));
                borrowingBike.setUserId(res.getLong("user_id"));
                borrowingBike.setBorrowedAt(res.getTimestamp("borrowed_at"));
                Long current = System.currentTimeMillis();
                Long borrowedAtMilli = res.getTimestamp("borrowed_at").getTime();
                borrowingBike.setTotalTime(new Timestamp(current-borrowedAtMilli));
                borrowingBike.setClassifyId(res.getInt("classify_id"));

                long HH = TimeUnit.MILLISECONDS.toHours(current-borrowedAtMilli);
                long MM = TimeUnit.MILLISECONDS.toMinutes(current-borrowedAtMilli) % 60;
                long SS = TimeUnit.MILLISECONDS.toSeconds(current-borrowedAtMilli) % 60;
                String timeInHHMMSS = String.format("%02d:%02d:%02d", HH, MM, SS);

                borrowingBike.setTotalTimeDisplay(timeInHHMMSS);
                return borrowingBike;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
