package aithings.camAI.controlpanel.utils.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import aithings.camAI.controlpanel.exception.BEBusinessException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {

    private String code;
    private String message;
    private T data;
    private long timestamp;
    private ResCode resCode;

    public CommonResult(ResCode resCode) {
        this.code = resCode.getValue();
        this.message = resCode.getDes();
        this.timestamp = System.currentTimeMillis();
        this.resCode = resCode;
    }

    public CommonResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> CommonResult<T> businessException(BEBusinessException e) {
        return new CommonResult<>(e.getResCode());
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResCode.SUCCESS.getValue(), ResCode.SUCCESS.getDes(), data);
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(ResCode.SUCCESS.getValue(), ResCode.SUCCESS.getDes(), null);
    }

    public static <T> CommonResult<T> mustLogout() {
        return new CommonResult<T>(ResCode.MUST_LOGOUT.getValue(), ResCode.MUST_LOGOUT.getDes(), null);
    }

    public static <T> CommonResult<T> fail(BEBusinessException e, T data) {
        return new CommonResult<T>(e.getResCode().getValue(), e.getResCode().getDes(), data);
    }

    public static <T> CommonResult<T> fail(BEBusinessException e) {
        return new CommonResult<T>(e.getResCode().getValue(), e.getResCode().getDes(), null);
    }

    public static <T> CommonResult<T> unauthorized() {
        return new CommonResult<T>(ResCode.UNAUTHENTICATED.getValue(), ResCode.UNAUTHENTICATED.getDes(), null);
    }

    public static <T> CommonResult<T> serverError() {
        return new CommonResult<T>(ResCode.INTERNAL_ERROR.getValue(), ResCode.INTERNAL_ERROR.getDes(), null);
    }
    public static <T> CommonResult<T> serverError(ResCode resCode) {
        return new CommonResult<T>(resCode.getValue(), resCode.getDes(), null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
