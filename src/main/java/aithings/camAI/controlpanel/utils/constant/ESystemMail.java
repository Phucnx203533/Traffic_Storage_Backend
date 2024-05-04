package aithings.camAI.controlpanel.utils.constant;

public enum ESystemMail {
    MAIL_CREATE_USER("[AI-THINGSLAB] THÔNG BÁO TẠO MỚI TÀI KHOẢN", "sendmail/create_user.html"),
    MAIL_CHANGE_PASS("[AI-THINGSLAB] THÔNG BÁO THAY ĐỔI MẬT KHẨU", "sendmail/change_pass.html");

    private String subject;
    private String template;

    ESystemMail(String subject, String template) {
        this.subject = subject;
        this.template = template;
    }

    public String getSubject() {
        return subject;
    }

    public String getTemplate() {
        return template;
    }
}
