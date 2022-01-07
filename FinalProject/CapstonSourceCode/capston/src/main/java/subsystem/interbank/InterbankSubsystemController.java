package subsystem.interbank;

import common.exception.*;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import utils.Configs;
import utils.MyMap;
import utils.Utils;

import java.util.Map;

/**
 * Class điều khiển luồng hoạt động cảu InterbankSubsystem.
 *
 * @author PhienTran
 */
public class InterbankSubsystemController {
    private static final String PUBLIC_KEY = "AQzdE8O/fR8=";
    private static final String SECRET_KEY = "BUXj/7/gHHI=";
    /**
     * command trong trường hợp thanh toán giao dịch
     */
    private static final String PAY_COMMAND = "pay";
    /**
     * command trong trường hợp hoàn tiền
     */
    private static final String REFUND_COMMAND = "refund";
    /**
     * version API 1.0.1: Capston
     */
    private static final String VERSION = "1.0.1";

    private static final String APP_CODE = "r2+3aNfQ4A==";

    private static InterbankBoundary interbankBoundary = new InterbankBoundary();

    /**
     * Thực hiện thanh toán tiền đặt cọc và trả về thông tin giao dịch
     * @param card - Thông tin thẻ sử dụng
     * @param amount - Số tiền thanh toán
     * @param contents - Nội dung giao dịch
     * @return PaymentTransaction: Thông tin giao dịch thanh toán
     */

    public PaymentTransaction payDeposit(CreditCard card, int amount, String contents){
        Map<String, Object> transaction = new MyMap();
        try {
            transaction.putAll(MyMap.toMyMap(card));
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new InvalidCardException();
        }
        transaction.put("command", PAY_COMMAND);
        transaction.put("transactionContent", contents);
        transaction.put("amount", amount);
        transaction.put("createdAt", Utils.getToday());

        Map<String, Object> requestMap = new MyMap();
        requestMap.put("version", VERSION);
        requestMap.put("transaction", transaction);

        String data = generateData(requestMap);
        String hasCode = Utils.md5(data);
        requestMap.put("hashCode", hasCode);
        requestMap.put("appCode", APP_CODE);

        String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
        System.out.println("Response: " + responseText);
        MyMap response = null;
        try {
            response = MyMap.toMyMap(responseText, 0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new UnrecognizedException();
        }

        return makePaymentTransaction(response);
    }

    /**
     * Thực hiện hoàn lại tiền đặt cọc và trả về thông tin giao dịch
     * @param card - Thông tin thẻ được hoàn lại tiền
     * @param amount - Số tiền hoàn lại
     * @param content - Nội dung giao dịch
     * @return PaymentTransaction Thông tin giao dịch
     */

    public PaymentTransaction refundDeposit(CreditCard card, int amount, String content){
        Map<String, Object> transaction = new MyMap();
        try {
            transaction.putAll(MyMap.toMyMap(card));
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new InvalidCardException();
        }
        transaction.put("command", REFUND_COMMAND);
        transaction.put("transactionContent", content);
        transaction.put("amount", amount);
        transaction.put("createdAt", Utils.getToday());

        Map<String, Object> requestMap = new MyMap();
        requestMap.put("version", VERSION);
        requestMap.put("transaction", transaction);

        String data = generateData(requestMap);
        String hasCode = Utils.md5(data);
        requestMap.put("hashCode", hasCode);
        requestMap.put("appCode", APP_CODE);

        String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
        System.out.println("Response: " + responseText);
        card.deleteCard(card.getCardCode());

        MyMap response = null;
        try {
            response = MyMap.toMyMap(responseText, 0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new UnrecognizedException();
        }

        return makePaymentTransaction(response);    }

    /**
     * Chuyển đổi từ MyMap sang dạng Json
     * @param data
     * @return
     */
    private String generateData(Map<String, Object> data) {
        return ((MyMap) data).toJSON();
    }

    /**
     * Thực hiện chuyển response khi gọi API trong bước thanh toán và hoàn tiền về PaymentTransaction
     * @param response
     * @return
     */

    private PaymentTransaction makePaymentTransaction(MyMap response) {
        if (response == null)
            return null;
        MyMap transcation = (MyMap) response.get("transaction");
        CreditCard card = new CreditCard((String) transcation.get("cardCode"), (String) transcation.get("owner"),
                Integer.parseInt((String) transcation.get("cvvCode")), (String) transcation.get("dateExpired"));
        PaymentTransaction trans = new PaymentTransaction((String) response.get("errorCode"), card,
                (String) transcation.get("transactionId"), (String) transcation.get("transactionContent"),
                Integer.parseInt((String) transcation.get("amount")), (String) transcation.get("createdAt"), (String) transcation.get("transactionContent"));

        switch (trans.getErrorCode()) {
            case "00":
                break;
            case "01":
                throw new InvalidCardException();
            case "02":
                throw new NotEnoughBalanceException();
            case "03":
                throw new InternalServerErrorException();
            case "04":
                throw new SuspiciousTransactionException();
            case "05":
                throw new NotEnoughTransactionInfoException();
            case "06":
                throw new InvalidVersionException();
            case "07":
                throw new InvalidTransactionAmountException();
            default:
                throw new UnrecognizedException();
        }

        return trans;
    }


    public static void main(String[] args) {
        Map<String, Object> transaction = new MyMap();
        CreditCard card = new CreditCard("kscq1_group8_2021", "Group 8", 853, "1125");
        try {
            transaction.putAll(MyMap.toMyMap(card));
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new InvalidCardException();
        }
        transaction.put("command", PAY_COMMAND);
        transaction.put("transactionContent", "ahihihihi");
        transaction.put("amount", 1000);
        transaction.put("createdAt", Utils.getToday());

        Map<String, Object> requestMap = new MyMap();
        requestMap.put("version", VERSION);
        requestMap.put("transaction", transaction);

        String data = ((MyMap) requestMap).toJSON();
        String hasCode = Utils.md5(data);
        System.out.println(hasCode);
        System.out.println("=======================================");
        System.out.println(data);
    }

}
