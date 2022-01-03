package controller;

import common.exception.InternalServerErrorException;
import controller.calculateAmount.CalculateFactory;
import entity.bicycle.BorrowingBike;
import entity.bicycle.Vehicle;
import entity.dock.Dock;
import entity.dock.DockBike;
import entity.invoice.Invoice;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static utils.Configs.userId;

public class ReturnBikeController extends BaseController{

    PaymentController paymentController = new PaymentController();

    public List<Dock> getListDock()  {
        try {
            return new Dock().getAllDock();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Object returnBike(Long bikeId, Long dockId) {
        try {
            //BorrowingBike borrowingBike = new BorrowingBike().viewDetailBorrowingBikeByBikeId(bikeId);

            BorrowingBike borrowingBike = new BorrowingBike().viewDetailBorrowingBikeByUserId(userId);
            if (borrowingBike == null) return "Bạn chưa thuê xe";

            new DockBike().addBikeOnDock(bikeId, borrowingBike.getClassifyId(), dockId); // thêm xe vào bãi
            Long totalTime = System.currentTimeMillis() - borrowingBike.getBorrowedAt().getTime();
            Double totalAmount = new CalculateFactory().calculateFeeBorrowingBike(borrowingBike.getClassifyId(), System.currentTimeMillis()-borrowingBike.getBorrowedAt().getTime());// tính toán tổng tiền
            new Invoice().createInvoice(totalAmount, totalTime, bikeId, dockId, new Timestamp(System.currentTimeMillis()), borrowingBike.getUserId()); // tạo hóa đơn
            borrowingBike.removeBikeBorrowing(bikeId);//xóa xe đang mượn
            CreditCard creditCard = new CreditCard().getCreditCardByCardCode("kscq1_group8_2021");

            Double depositMoney = 0d;

            if (borrowingBike.getClassifyId() == 1) depositMoney = new Vehicle().getDeposit(borrowingBike.getClassifyId());
            else if (borrowingBike.getClassifyId() == 2) depositMoney =  new Vehicle().getDeposit(borrowingBike.getClassifyId());
            else depositMoney =  new Vehicle().getDeposit(borrowingBike.getClassifyId());

            //paymentController.refundDeposit(creditCard, (int) (depositMoney - totalAmount), "Refunds money");
            paymentController.refundDeposit((int) (depositMoney - totalAmount), "Refunds money", creditCard);

        } catch (Exception e) {
            e.printStackTrace();
            return new InternalServerErrorException();
        }

        return "Trả xe thành công";
    }
}
