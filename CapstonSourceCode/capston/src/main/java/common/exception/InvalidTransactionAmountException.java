package common.exception;

public class InvalidTransactionAmountException extends CapstonException {
    public InvalidTransactionAmountException() {
        super("ERROR: Invalid Transaction Amount!");
    }
}
