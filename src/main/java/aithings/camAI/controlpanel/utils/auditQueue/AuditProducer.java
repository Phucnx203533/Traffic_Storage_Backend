package aithings.camAI.controlpanel.utils.auditQueue;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import aithings.camAI.controlpanel.entity.SAAuditEntity;
import aithings.camAI.controlpanel.utils.constant.BEConstant;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.Queue;

@Slf4j
public class AuditProducer {
    static final Gson gson = new Gson();
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    @Qualifier(BEConstant.QUEUE_AUDIT)
    private Queue queue;

    public void send(SAAuditEntity audit) {
        try {
            String auditString = gson.toJson(audit);
            log.debug("Sending audit to queue {}", auditString);
            this.jmsMessagingTemplate.convertAndSend(this.queue, auditString);
        } catch (Exception ex) {
            log.error("Error while sending audit: " + gson.toJson(audit), ex);
        }
    }

    @PreDestroy
    public void destroy() {
        log.debug("Destroy producer");
    }

    @PostConstruct
    public void init() {
        log.debug("init producer");
    }
}
