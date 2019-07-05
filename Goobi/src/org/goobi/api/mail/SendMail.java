package org.goobi.api.mail;

import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.goobi.beans.Step;
import org.goobi.beans.User;

import de.sub.goobi.config.ConfigurationHelper;
import de.sub.goobi.helper.StorageProvider;
import de.sub.goobi.helper.VariableReplacer;
import de.sub.goobi.helper.enums.StepStatus;
import lombok.Data;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Log4j
public class SendMail {

    private static SendMail instance = null;

    @Getter
    private MailConfiguration config;

    /**
     * private constructor to prevent instantiation from outside
     */

    private SendMail() {
        config = new MailConfiguration();

    }

    public static synchronized SendMail getInstance() {
        if (instance == null) {
            instance = new SendMail();
        }
        return instance;
    }

    @Data
    public class MailConfiguration {

        private boolean enableMail;

        private String smtpServer;
        private String smtpUser;
        private String smtpPassword;
        private boolean smtpUseStartTls;
        private boolean smtpUseSsl;
        private String smtpSenderAddress;
        private String messageStepOpenSubject;
        private String messageStepOpenBody;

        public MailConfiguration() {
            String configurationFile = ConfigurationHelper.getInstance().getConfigurationFolder() + "goobi_mail.xml";
            if (StorageProvider.getInstance().isFileExists(Paths.get(configurationFile))) {

                XMLConfiguration config;
                try {
                    config = new XMLConfiguration(configurationFile);
                } catch (ConfigurationException e) {
                    log.error(e);
                    config = new XMLConfiguration();
                }
                config.setExpressionEngine(new XPathExpressionEngine());
                config.setReloadingStrategy(new FileChangedReloadingStrategy());

                enableMail = config.getBoolean("/configuration/@enabled", false);

                smtpServer = config.getString("/configuration/smtpServer", null);
                smtpUser = config.getString("/configuration/smtpUser", null);
                smtpPassword = config.getString("/configuration/smtpPassword", null);
                smtpUseStartTls = config.getBoolean("/configuration/smtpUseStartTls", false);
                smtpUseSsl = config.getBoolean("/configuration/smtpUseSsl", false);
                smtpSenderAddress = config.getString("/configuration/smtpSenderAddress", null);

                messageStepOpenSubject = config.getString("/messageStepOpen/subject");
                messageStepOpenBody = config.getString("/messageStepOpen/body");
            }
        }

    }

    public void postMail(List<User> recipients, String messageType, Step step) throws MessagingException, UnsupportedEncodingException {

        if (!config.isEnableMail()) {
            return;
        }

        // Set the host smtp address
        Properties props = new Properties();
        if (config.isSmtpUseStartTls()) {
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.port", "25");
            props.setProperty("mail.smtp.host", config.getSmtpServer());
            props.setProperty("mail.smtp.ssl.trust", "*");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.starttls.required", "true");
        } else if (config.isSmtpUseSsl()) {
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.host", config.getSmtpServer());
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.ssl.enable", "true");
            props.setProperty("mail.smtp.ssl.trust", "*");

        } else {
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.port", "25");
            props.setProperty("mail.smtp.host", config.getSmtpServer());
        }
        // metadata are not allowed, other variables can be used
        VariableReplacer rep = new VariableReplacer(null, null, step.getProzess(), step);

        for (User user : recipients) {
            Session session = Session.getDefaultInstance(props, null);
            Message msg = new MimeMessage(session);

            InternetAddress addressFrom = new InternetAddress(config.getSmtpSenderAddress());
            msg.setFrom(addressFrom);
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            String messageSubject = "";
            String messageBody = "";
            if (StepStatus.OPEN.getTitle().equals(messageType)) {
                messageSubject = config.getMessageStepOpenSubject();
                messageBody = config.getMessageStepOpenBody();
            } else {
                // allow other types of mails
                return;
            }

            msg.setSubject(rep.replace(messageSubject));
            MimeBodyPart messagePart = new MimeBodyPart();
            messagePart.setText(rep.replace(messageBody), "utf-8");
            messagePart.setHeader("Content-Type", "text/plain; charset=\"utf-8\"");
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messagePart);

            msg.setContent(multipart);
            msg.setSentDate(new Date());

            Transport transport = session.getTransport();
            transport.connect(config.getSmtpUser(), config.getSmtpPassword());
            transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
            transport.close();
        }
    }
}
