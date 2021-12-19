package common.exception;

public class NotEnoughBalanceException extends CapstonException {
    public NotEnoughBalanceException() {
        super("ERROR: Not enough balance in card!");
    }
}
