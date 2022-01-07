package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

/**
 * Class được dùng để giao tiếp với Interbank Subsystem để tạo ra các giao dich
 *
 * @author PhienTran
 */
public interface InterbankInterface extends BankInterface {

    /**
     * Thanh toán tiền đặt cọc và trả về thông tin giao dịch
     * @param creditCard - Thẻ sử dụng để thanh toán
     * @param amount - Số tiền thanh toán
     * @param contents - Nội dung thanh toán
     * @return PaymentTransaction Thông tin giao dịch
     * @throws PaymentException
     * @throws UnrecognizedException
     */
    public abstract PaymentTransaction payDeposit(CreditCard creditCard, int amount, String contents) throws PaymentException, UnrecognizedException;

    /**
     * Hoàn lại tiền đặt cọc và trả về thông tin thanh toán
     * @param creditCard - Thẻ được sử dụng
     * @param amount - Số tiền hoàn lại
     * @param contents - Nội dung thanh toán
     * @return PaymentTransaction Thông tin giao dịch
     * @throws PaymentException
     * @throws UnrecognizedException
     */
    public abstract PaymentTransaction refundDeposit(CreditCard creditCard, int amount, String contents) throws PaymentException, UnrecognizedException;
}
