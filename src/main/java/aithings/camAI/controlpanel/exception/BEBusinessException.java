package aithings.camAI.controlpanel.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import aithings.camAI.controlpanel.utils.response.ResCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BEBusinessException extends Exception {
    private String requestId;
    private String code;
    private String message;
    private String detailMessage;
    private ResCode resCode;

    public BEBusinessException(String requestId,ResCode resCode) {
        super(resCode.toString());
        this.resCode = resCode;
        this.code = resCode.getValue();
        this.message = resCode.getDes();
        this.requestId = requestId;
    }

    public BEBusinessException(ResCode resCode) {
        super(resCode.toString());
        this.resCode = resCode;
        this.code = resCode.getValue();
        this.message = resCode.getDes();
    }
    public BEBusinessException(ResCode resCode,Exception e) {
        super(e.getMessage());
        this.detailMessage = e.getMessage();
        this.resCode = resCode;
        this.code = resCode.getValue();
        this.message = resCode.getDes();
    }

    public BEBusinessException(ResCode resCode, String description) {
        super(resCode.toString());
        if (resCode.getOrigin() == null) {
            resCode.setOrigin(resCode.getDes());
        }
        resCode.setDes(String.format(resCode.getOrigin(), description));
        this.resCode = resCode;
        this.code = resCode.getValue();
        this.message = resCode.getDes();
    }

    public BEBusinessException(String code, String message) {
        super(code);
        this.code = code;
        this.message = message;
    }

    public BEBusinessException(String message) {
        super(message);
        this.message = message;
    }
}
