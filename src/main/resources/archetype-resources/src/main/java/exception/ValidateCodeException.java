package ${package}.exception;

/**
 * @author liusy
 */
public class ValidateCodeException extends Exception {
    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException() {
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
