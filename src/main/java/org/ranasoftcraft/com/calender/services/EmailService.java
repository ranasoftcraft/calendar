package org.ranasoftcraft.com.calender.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.ranasoftcraft.com.calender.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.to}")
    private String to;

    @Value("${spring.mail.from}")
    private String from;

    @Value("${spring.mail.subject}")
    private String subject;

    public String sendEmail(EmailRequest emailRequest) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from); // Sender's email
            helper.setTo(to.split(",")); // Recipient
            helper.setSubject(emailRequest.subject()); // Email Subject
            helper.setText(emailRequest.message() + "\n" + "This was the sender " + emailRequest.email() + " with name '" + emailRequest.name() + "'", false);

            mailSender.send(message);


            // reply based on send
            replyEmail(emailRequest);


            return "Email sent successfully for this request " + emailRequest.email();
        } catch (MessagingException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }


    public void replyEmail(EmailRequest emailRequest) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from); // Sender's email
            helper.setTo(emailRequest.email()); // Recipient
            helper.setSubject(emailRequest.subject()); // Email Subject
            helper.setText("Dear " + emailRequest.name() +  """
                    \n
                    
                    Thanks for reaching out to ranasoftcraft. Let us access your requirement and will back to you in couple of minutes.
                    
                    Best Regards
                    Ranasoftcraft team.
                    
                    """, false);

            // Set the reply-to email to the original sender
            helper.setReplyTo(to);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.getMessage();
        }
    }
}