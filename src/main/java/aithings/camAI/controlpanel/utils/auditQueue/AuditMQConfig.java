package aithings.camAI.controlpanel.utils.auditQueue;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import aithings.camAI.controlpanel.utils.constant.BEConstant;

import javax.jms.Queue;
import java.net.URI;


@Configuration
@EnableJms
public class AuditMQConfig {

    public static final String DEFAULT_BROKER_URL = "tcp://localhost:61625";

    private static final boolean IS_PERSISTENT = false;

    @Bean(name = BEConstant.BROKER_AUDIT)
    public BrokerService createBrokerService() throws Exception {
        BrokerService broker = new BrokerService();
        TransportConnector connector = new TransportConnector();
        connector.setUri(new URI(DEFAULT_BROKER_URL));
        broker.addConnector(connector);
        broker.setUseJmx(false);
        if (!IS_PERSISTENT) {
            broker.setPersistent(false);
        }
        return broker;
    }

    @Bean()
    @DependsOn(BEConstant.CONSUMER_AUDIT)
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(BEConstant.QUEUE_AUDIT);
        return template;
    }

    @Bean(name = BEConstant.QUEUE_AUDIT)
    public Queue queue() {
        return new ActiveMQQueue(BEConstant.QUEUE_AUDIT);
    }

    @Bean(name = BEConstant.QUEUE_TRACING)
    public Queue queueTracing() {
        return new ActiveMQQueue(BEConstant.QUEUE_TRACING);
    }

}
