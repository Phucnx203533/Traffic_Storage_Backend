package aithings.camAI.controlpanel.utils.auditQueue;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import aithings.camAI.controlpanel.entity.SAAuditEntity;
import aithings.camAI.controlpanel.repository.AuditRepository;
import aithings.camAI.controlpanel.utils.constant.BEConstant;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
public class AuditConsumer {
    Gson gson = new Gson();
    private int MAX_LENGTH = 10000;

    @Autowired
    AuditRepository auditRepository;

    @JmsListener(destination = BEConstant.QUEUE_AUDIT)
    public void receiveQueue(String text) {
        log.debug("Received message: " + text);
        log.debug("Inserting audit data into DB");
        try {
            SAAuditEntity audit = gson.fromJson(text, SAAuditEntity.class);
            if (audit.getOldValue() != null && audit.getOldValue().length() > MAX_LENGTH) {
                audit.setOldValue(audit.getOldValue().substring(0, MAX_LENGTH) + "...");
            }
            if (audit.getNewValue() != null && audit.getNewValue().length() > MAX_LENGTH) {
                audit.setNewValue(audit.getNewValue().substring(0, MAX_LENGTH) + "...");
            }
            auditRepository.insert(audit);
            log.debug("Inserting audit data successfully");
        } catch (Exception ex) {
            log.error("Error while inserting audit: " + text, ex);
        }
    }

    @PreDestroy
    public void destroy() {
        log.debug("Destroy consumer");
    }

    @PostConstruct
    public void init() {
        log.debug("init consumer");
    }
}
