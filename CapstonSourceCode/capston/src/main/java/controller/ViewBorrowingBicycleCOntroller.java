package controller;

import controller.calculateAmount.*;
import entity.bicycle.*;

import java.sql.SQLException;

public class ViewBorrowingBicycleController extends BaseController{

    public BorrowingBike viewDetailBorrowingByUserId(Long userId) {
        BorrowingBike borrowingBike = new BorrowingBike().viewDetailBorrowingBikeByUserId(userId);
        CalculateAmount calculateAmount = new CalculateFactory().getCalculateFeeBorrowingBike(borrowingBike.getClassifyId());
        Double totalMoney = calculateAmount.calculateTotalFeeBorrowingBike(System.currentTimeMillis()-borrowingBike.getBorrowedAt().getTime());
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

}
