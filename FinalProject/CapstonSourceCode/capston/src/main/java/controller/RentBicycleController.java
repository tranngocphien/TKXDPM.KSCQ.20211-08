package controller;

import entity.bicycle.BorrowingBike;
import entity.bicycle.Vehicle;
import entity.dock.DockBike;
import entity.payment.CreditCard;
import utils.Configs;

public class RentBicycleController extends BaseController{
    public void rentBike(Long bikeId, Long userId) {
        new BorrowingBike().addBorrowingBike(userId, bikeId);
        new DockBike().removeBikeOnDock(bikeId);
    }

    public void createPaymentTransactionAndAddCardInfo(CreditCard card) {
        new CreditCard().createCard(card);
    }

    public void createPaymentTransaction(){

    }

    public boolean isBorrowing(){
        return (new BorrowingBike().viewDetailBorrowingBikeByUserId(Configs.userId) != null);
    }



}
