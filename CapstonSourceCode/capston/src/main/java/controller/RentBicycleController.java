package controller;

import entity.bicycle.BorrowingBike;
import entity.dock.DockBike;

public class RentBicycleController extends BaseController{
    public void rentBike(Long bikeId, Long userId) {
        new BorrowingBike().addBorrowingBike(userId, bikeId);
        new DockBike().removeBikeOnDock(bikeId);
    }
}
