package common.exception;

public class InvalidVersionException extends CapstonException {
    public InvalidVersionException() {
        super("ERROR: Invalid Version Information!");
    }
}
