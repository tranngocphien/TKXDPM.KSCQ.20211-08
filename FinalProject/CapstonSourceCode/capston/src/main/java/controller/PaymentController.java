package controller;

import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import convertData.ConvertExpirationDate;
import entity.invoice.Invoice;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import static utils.Configs.userId;

/**
 * This class control the flow of the payment process
 *
 * @author PhienTran
 */
public class PaymentController extends BaseController{

    private CreditCard card;
    private InterbankInterface interbank;

    public Map<String, String> payDeposit(int amount, String contents, String cardNumber, String cardHolderName,
                                        String expirationDate, String securityCode, Long bikeId, Long dockId) {
        Map<String, String> result = new Hashtable<>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
            this.card = new CreditCard(cardNumber, cardHolderName, Integer.parseInt(securityCode),
                    ConvertExpirationDate.getExpirationDate(expirationDate));

            this.interbank = new InterbankSubsystem();
            PaymentTransaction transaction = interbank.payDeposit(card, amount, contents);

            Invoice invoice = new Invoice().createInvoice((double)amount,0l, bikeId, dockId, new Timestamp(System.currentTimeMillis()), userId);
            transaction.save(transaction, invoice.getId());

            result.put("RESULT", "PAYMENT SUCCESSFUL!");
            result.put("MESSAGE", "You have succesffully paid the order!");
            card.createCard(card);
        } catch (PaymentException | UnrecognizedException ex) {
            result.put("MESSAGE", ex.getMessage());
        }
        return result;
    }

    public Map<String, String> refundDeposit(int amount, String content, CreditCard creditCard){
        Map<String, String> result = new Hashtable<>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
//            this.card = new CreditCard(cardNumber, cardHolderName, Integer.parseInt(securityCode),
//                    getExpirationDate(expirationDate));

            this.interbank = new InterbankSubsystem();
            PaymentTransaction transaction = interbank.refundDeposit(creditCard, amount, content);

            result.put("RESULT", "PAYMENT SUCCESSFUL!");
            result.put("MESSAGE", "You have succesffully paid the order!");
        } catch (PaymentException | UnrecognizedException ex) {
            result.put("MESSAGE", ex.getMessage());
        }
        return result;    }

}
