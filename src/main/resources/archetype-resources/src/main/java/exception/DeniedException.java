package ${package}.exception;

import lombok.NoArgsConstructor;

/**
 * @author liusy
 */
@NoArgsConstructor
public class DeniedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DeniedException(String message) {
        super(message);
    }

    public DeniedException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public DeniedException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public DeniedException(Throwable cause) {
        super(cause);
    }

    public DeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
