package aithings.camAI.controlpanel.utils.constant;


public class BEConstant {
    public static final String ROLE_SUPER = "ROLE_SUPER";
    public static final String USER_PARAMETER = "username";
    public static final String PASSWORD_PARAMETER = "password";
    public static final String NOT_EXIST = "notExist";
    public static final String OK = "ok";
    public static final String NOT_OK = "error";
    public static final String INVALID_LOGIN = "invalid";
    public static final String LOCK_USER = "lock";
    public static final String FIRST_LOGIN = "fist login";
    public static final String INVALID_USER = "invalid user";
    public static final String NOT_ACCESS = "notAccess";
    public static final String BANK_NOT_ACCESS = "bankNotAccess";
    public static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";

    public static final String USER_INVALID = "Tài khoản hoặc mật khẩu không chính xác.";
    public static final String USER_BLOCKED = "Tài khoản của quý khách đã bị khóa. Vui lòng liên hệ với Admin để được hỗ trợ";
    public static final String BANK_BLOCKED = "Ngân hàng hiện đang bị khóa. Vui lòng liên hệ với Admin để được hỗ trợ";
    public static final String USER_NOT_ACCESS = "Tài khoản không được phép truy cập";
    public static final String SERVER_NOT_RESPONSE = "Đã xảy ra lỗi.";
    public static final String REGEX_GEN_PASSWORD = "[a-z][a-z][A-Z][A-Z][0-9][`!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~]";

    public static final String STATUS = "Status: ";

    public static final Integer DEFAULT_SEARCH_DAYS = 30;
    public static final int MAX_LENGTH_CONTENT_TRACE = 1000;

    public static String VNPAY = "VNPAY";
    public static Integer VNPAY_ID = 0;
    public static final String ALL_VALUE = "ALL";

    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";

    public static final String ACTIVE = "Y";
    public static final String INACTIVE = "N";

    // Queue audit
    public static final String QUEUE_AUDIT = "auditQueue";
    public static final String CONSUMER_AUDIT = "auditConsumer";
    public static final String BROKER_AUDIT = "auditBroker";

    // Queue tracing
    public static final String QUEUE_TRACING = "tracingQueue";

    /*REGEX*/
    public static final String REGEX_ALPHANUMERIC_UNDERSCORE = "^[a-zA-Z0-9_]*$";

    public static final int NUMBER_FORMAT_CODE = 4;

    public static final String EXCEPTION_SYSTEM_ERROR = "SYSTEM_ERROR";
    public static final String EXCEPTION_BUSINESS_ERROR = "BUSINESS_ERROR";
}
