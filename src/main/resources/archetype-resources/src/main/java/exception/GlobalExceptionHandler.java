package ${package}.exception;

import ${package}.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static ${package}.response.ResponseType.BIZ_EXCEPTION;
import static ${package}.response.ResponseType.SYSTEM_ERROR;


/**
 * @author liusy
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public R exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return R.of(SYSTEM_ERROR.getStateCode(), SYSTEM_ERROR.getMsg());
    }

    /**
     * validation Exception
     *
     * @param exception
     * @return R
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.OK)
    public R bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.warn(fieldErrors.get(0).getDefaultMessage());
        return R.of(BIZ_EXCEPTION.getStateCode(), fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * 身份验证异常信息.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(DeniedException.class)
    @ResponseStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
    public R exception(DeniedException e) {
        log.error("身份验证异常信息 ex={}", e.getMessage(), e);
        return R.of(e.getCode(), e.getMessage());
    }

    /**
     * 业务异常信息.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    public R exception(BizException e) {
        log.error("业务异常信息 ex={}", e.getMessage(), e);
        return R.of(e.getCode(), e.getMessage());
    }
}
