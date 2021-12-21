package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public interface InterbankInterface {
    public abstract PaymentTransaction payDeposit(CreditCard creditCard, int amount, String contents) throws PaymentException, UnrecognizedException;
    public abstract PaymentTransaction refundDeposit(CreditCard creditCard, int amount, String contents) throws PaymentException, UnrecognizedException;
}
