package aithings.camAI.controlpanel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Base64;
import java.util.Properties;

@Configuration
public class MailSenderConfig {

    @Value(value = "${mail.host}")
    private String host;

    @Value(value = "${mail.port}")
    private String port;

    @Value(value = "${mail.protocol}")
    private String protocol;

    @Value(value = "${mail.username}")
    private String username;

    @Value(value = "${mail.password}")
    private String password;

    @Bean
    public JavaMailSender mailSenderNoReply() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(Integer.parseInt(port));
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(new String(Base64.getDecoder().decode(password)));
        javaMailSender.setJavaMailProperties(asJavaMailProperties());
        return javaMailSender;
    }

    private Properties asJavaMailProperties() {
        Properties prop = new Properties();
        prop.put("mail.transport.protocol", protocol);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.debug", "false");
        return prop;
    }
}
