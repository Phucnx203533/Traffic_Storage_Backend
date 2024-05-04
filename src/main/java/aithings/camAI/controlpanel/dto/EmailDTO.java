package aithings.camAI.controlpanel.dto;

import lombok.Data;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class EmailDTO {
    private String to;
    private String from;
    private String subject;
    private String content;
    private Map<String, Object> vars;
    private Set<String> cc;
    private Set<String> bcc;
    private String template;
    private File attachmentFile;
    private String mailSender;

    public EmailDTO() {
        this.vars = new HashMap<>();
        this.cc = new HashSet<>();
        this.bcc = new HashSet<>();
    }
}
