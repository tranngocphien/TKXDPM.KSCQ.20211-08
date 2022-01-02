package controller;

import entity.bicycle.*;

import java.sql.SQLException;

public class ViewBorrowingBicycleController extends BaseController{

    public BorrowingBike viewDetailBorrowingByUserId(Long userId) {
        BorrowingBike borrowingBike = new BorrowingBike().viewDetailBorrowingBikeByUserId(userId);
        Double totalMoney = calculateTotalAmount(System.currentTimeMillis()-borrowingBike.getBorrowedAt().getTime(), borrowingBike.getClassifyId());
        borrowingBike.setTotalAmount(totalMoney);
        Double deposit = new Vehicle().getDeposit(borrowingBike.getClassifyId());
        borrowingBike.setDeposit(deposit);
        return borrowingBike;
    }

    public Vehicle detailVehicle(Long bikeId) {
        try {
            Vehicle vehicle = new Vehicle().searchVehicleById(bikeId);
            return vehicle;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Double calculateTotalAmount(Long totalTime, Integer classifyId) {
        long thirtyMinutes = 1000*60*30l;
        long totalMoney = 0;
        if (totalTime <= thirtyMinutes) totalMoney = 10000;
        else totalMoney = (totalTime-thirtyMinutes)/thirtyMinutes * 3000 + 10000;
        return classifyId == 1 ? totalMoney : totalMoney*1.5;
    }


}
