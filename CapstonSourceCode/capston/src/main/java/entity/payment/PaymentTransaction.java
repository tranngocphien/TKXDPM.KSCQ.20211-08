package entity.payment;

import entity.db.CAPSTONDB;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class PaymentTransaction {
    private String errorCode;
    private CreditCard card;
    private String transactionId;
    private String transactionContent;
    private int amount;
    private String createdAt;
    private String content;


    public PaymentTransaction(String errorCode, CreditCard card, String transactionId, String transactionContent,
                              int amount, String createdAt, String content) {
        super();
        this.errorCode = errorCode;
        this.card = card;
        this.transactionId = transactionId;
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.createdAt = createdAt;
        this.content = content;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void save(PaymentTransaction paymentTransaction, Long invoiceId) {
        String sql = "INSERT INTO `lichitec_capston`.`payment_transaction` (`transaction_id`, `content`, `method`, `created_at`, `invoice_id`, `cardCode`) VALUES ('"+paymentTransaction.transactionId+"', '"+paymentTransaction.content+"' ,'credit card', '"+paymentTransaction.createdAt+"', '"+invoiceId+"', '"+paymentTransaction.card.getCardCode()+"')";
        try {
            Statement stm = CAPSTONDB.getConnection().createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
