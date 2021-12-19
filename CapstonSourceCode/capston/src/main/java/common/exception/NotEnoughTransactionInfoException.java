package common.exception;

public class NotEnoughTransactionInfoException extends CapstonException {
    public NotEnoughTransactionInfoException() {
        super("ERROR: Not Enough Transaction Information");
    }
}
