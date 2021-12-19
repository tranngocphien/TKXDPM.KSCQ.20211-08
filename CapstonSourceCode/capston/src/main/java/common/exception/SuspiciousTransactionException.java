package common.exception;

public class SuspiciousTransactionException extends CapstonException {
    public SuspiciousTransactionException() {
        super("ERROR: Suspicious Transaction Report!");
    }
}