package subsystem;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankSubsystemController;

/**
 * Class được sử dụng để kết nối với Interbank để tạo ra các giao dịch
 *
 * @author PhienTran
 */
public class InterbankSubsystem implements InterbankInterface {
    /**
     * Controller của Interbank Subsystem
     */
    private InterbankSubsystemController interbankController;

    /**
     * Khởi InterbankSubsystemController, nó thể hiện cho Interbank Subsystem
     */
    public InterbankSubsystem(){
        interbankController = new InterbankSubsystemController();

    }

    /**
     * @see InterbankInterface#payDeposit(CreditCard, int, String)
     */
    @Override
    public PaymentTransaction payDeposit(CreditCard creditCard, int amount, String contents) {
        PaymentTransaction transaction = interbankController.payDeposit(creditCard, amount, contents);
        return transaction;
    }

    /**
     * @see  InterbankInterface#refundDeposit(CreditCard, int, String)
     */

    @Override
    public PaymentTransaction refundDeposit(CreditCard creditCard, int amount, String contents) {
        PaymentTransaction transaction = interbankController.refundDeposit(creditCard, amount, contents);
        return transaction;
    }
}
