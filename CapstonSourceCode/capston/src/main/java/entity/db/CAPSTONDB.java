package entity.db;

import entity.bicycle.Bicycle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CAPSTONDB {
    private static Connection connect;
    private static String DB_URL = "jdbc:mysql://103.200.22.212:3306/lichitec_capston";
    private static String USER_NAME = "lichitec_capston";
    private static String PASSWORD = "tkxdpm2021";


    public static Connection getConnection() {
        if (connect != null) return connect;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connect;
    }

    public static void createBike() throws SQLException {
        int count = 1;
        int dockId = 1;
        for (int i = 1; i <= 600; i++) {
            count++;
            if (count == 15) {
                dockId = dockId + 1;
                count = 1;
                if (dockId > 15) dockId = 1;
            }
            String bikeCode = "BIKECO"+String.format("%04d",i);
            String sql;
            String sql1;
            if (i < 200) {
                String bikeName = "Xe đạp thường "+String.format("%04d",i);
                sql = "INSERT INTO `lichitec_capston`.`bike` (`id`, `classify_id`, `bike_code`, `name`) VALUES ("+i+", 1, '" +bikeCode+"', '"+bikeName+"')";
                sql1 = "INSERT INTO `lichitec_capston`.`dock_bike` (`id`, `dock_id`, `bike_id`, `location_at_dock`, `classify_id`) VALUES ("+i+", "+dockId+", "+i+", "+count+", 1)";
            }
            else if (i < 400) {
                String bikeName = "Xe đạp đôi "+String.format("%04d",i);
                sql = "INSERT INTO `lichitec_capston`.`bike` (`id`, `classify_id`, `bike_code`, `name`) VALUES ("+i+", 2, '" +bikeCode+"', '"+bikeName+"')";
                sql1 = "INSERT INTO `lichitec_capston`.`dock_bike` (`id`, `dock_id`, `bike_id`, `location_at_dock`, `classify_id`) VALUES ("+i+", "+dockId+", "+i+", "+count+", 2)";

            }
            else  {
                String bikeName = "Xe đạp điện "+String.format("%04d",i);
                sql = "INSERT INTO `lichitec_capston`.`bike` (`id`, `classify_id`, `bike_code`, `name`) VALUES ("+i+", 3, '" +bikeCode+"', '"+bikeName+"')";
                sql1 = "INSERT INTO `lichitec_capston`.`dock_bike` (`id`, `dock_id`, `bike_id`, `location_at_dock`, `classify_id`) VALUES ("+i+", "+dockId+", "+i+", "+count+", 3)";

            }
            Statement stm = getConnection().createStatement();
            stm.executeUpdate(sql);
            stm.executeUpdate(sql1);
        }

    }

    public static void addEmpty() throws SQLException {
        for (int i = 1; i <= 15; i++) {
            String q1 = "SELECT * FROM lichitec_capston.dock_bike where dock_id = "+i+" and classify_id = 1 order by location_at_dock desc limit 1";
            String q2 = "SELECT * FROM lichitec_capston.dock_bike where dock_id = "+i+" and classify_id = 2 order by location_at_dock desc limit 1";
            String q3 = "SELECT * FROM lichitec_capston.dock_bike where dock_id = "+i+" and classify_id = 3 order by location_at_dock desc limit 1";
            Statement stm1= CAPSTONDB.getConnection().createStatement();
            ResultSet res1 = stm1.executeQuery(q1);
            Statement stm2= CAPSTONDB.getConnection().createStatement();
            ResultSet res2 = stm2.executeQuery(q2);
            Statement stm3= CAPSTONDB.getConnection().createStatement();
            ResultSet res3 = stm3.executeQuery(q3);
            long last1 = 0;
            if (res1.next()) last1 = res1.getLong("location_at_dock");
            long last2 = 0;
            if (res2.next()) last2 = res2.getLong("location_at_dock");
            long last3 = 0;
            if (res3.next()) last3 = res3.getLong("location_at_dock");

            for (long j = last1+1; j <= 20; j++) {
                String update = "INSERT INTO `lichitec_capston`.`dock_bike` (`dock_id`, `bike_id`, `location_at_dock`,`classify_id`) VALUES ("+i+", "+0+", "+j+",1)";
                Statement stm= CAPSTONDB.getConnection().createStatement();
                stm.executeUpdate(update);
            }

            for (long j = last2+1; j <= 20; j++) {
                String update = "INSERT INTO `lichitec_capston`.`dock_bike` (`dock_id`, `bike_id`, `location_at_dock`, `classify_id`) VALUES ("+i+", "+0+", "+j+",2)";
                Statement stm= CAPSTONDB.getConnection().createStatement();
                stm.executeUpdate(update);
            }
            for (long j = last3+1; j <= 20; j++) {
                String update = "INSERT INTO `lichitec_capston`.`dock_bike` (`dock_id`, `bike_id`, `location_at_dock`, `classify_id`) VALUES ("+i+", "+0+", "+j+",3)";
                Statement stm= CAPSTONDB.getConnection().createStatement();
                stm.executeUpdate(update);
            }

        }
    }

    public static void main(String[] args) throws SQLException {

        //createBike();
        addEmpty();
        String bikeCode = "BIKECO"+String.format("%04d",1);
        System.out.println(String.format("%04d",1));
    }
}
