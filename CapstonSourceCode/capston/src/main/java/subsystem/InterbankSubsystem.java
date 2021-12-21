package subsystem;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankSubsystemController;

public class InterbankSubsystem implements InterbankInterface {
    private InterbankSubsystemController interbankController;

    public InterbankSubsystem(){
        interbankController = new InterbankSubsystemController();

    }

    @Override
    public PaymentTransaction payDeposit(CreditCard creditCard, int amount, String contents) {
        PaymentTransaction transaction = interbankController.payDeposit(creditCard, amount, contents);
        return transaction;
    }

    @Override
    public PaymentTransaction refundDeposit(CreditCard creditCard, int amount, String contents) {
        PaymentTransaction transaction = interbankController.refundDeposit(creditCard, amount, contents);
        return transaction;
    }
}
