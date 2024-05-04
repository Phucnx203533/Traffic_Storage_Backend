package aithings.camAI.controlpanel.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import aithings.camAI.controlpanel.utils.response.CommonResult;
import aithings.camAI.controlpanel.utils.response.ResCode;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Log4j2
public class BEGlobalExceptionHandler {
    /*
     * Required for the @ExceptionHandler to work and audit the exception
     * 1. Parameters:
     *      + first parameter must be the exception class
     *      + second parameter must be HttpServletRequest
     *      + third parameter must be HttpServletResponse
     * */

    @ExceptionHandler({BEBusinessException.class})
    @ResponseBody
    protected CommonResult handleBusinessException(BEBusinessException ex, HttpServletRequest request,
            HttpServletResponse response) {
        return CommonResult.fail(ex);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    protected CommonResult processValidationError(MethodArgumentNotValidException ex,
            HttpServletRequest request, HttpServletResponse response) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        return this.processFieldError(error);
    }

    @ExceptionHandler({BindException.class})
    @ResponseBody
    protected CommonResult handleBindException(BindException e, HttpServletRequest request,
            HttpServletResponse response) {
        String errorMessage = ResCode.PARAMETER_INVALID.getDes();
        if (e.getBindingResult().hasErrors()) {
            errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        }
        return CommonResult.fail(new BEBusinessException(ResCode.PARAMETER_INVALID.getValue(), errorMessage));
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseBody
    protected CommonResult handleNotFound(final RuntimeException ex, HttpServletRequest request,
            HttpServletResponse response) {
        ex.printStackTrace();
        return CommonResult.fail(new BEBusinessException(ResCode.RESOURCE_NOT_FOUND));
    }

    @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class})
    @ResponseBody
    protected CommonResult handleConflict(final RuntimeException ex, HttpServletRequest request,
            HttpServletResponse response) {
        ex.printStackTrace();
        return CommonResult.fail(new BEBusinessException(ResCode.INVALID_DATA_API));
    }

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
    public String handleInternal(final RuntimeException ex, HttpServletRequest request, HttpServletResponse response) {
        ex.printStackTrace();
        return "error/500.html";
    }


    private CommonResult processFieldError(FieldError error) {
        CommonResult message = null;
        if (error != null) {
            String msg = error.getDefaultMessage();
            message = new CommonResult(HttpStatus.BAD_REQUEST.toString(), msg);
        }

        return message;
    }

    @ExceptionHandler({AccessDeniedException.class})
    protected String handleSecurityException(AccessDeniedException ex, HttpServletRequest request,
            HttpServletResponse response) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return "error/403.html";
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    protected CommonResult handleAllException(Exception ex, HttpServletRequest request,
            HttpServletResponse response) {
        log.error(ex.getMessage());
        return CommonResult.serverError();
    }

}
