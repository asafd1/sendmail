package com.asaf.sendmail;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SendMailController {
    @GetMapping("/sendMail")
	public String sendMailForm(Model model) {
        model.addAttribute("mail", new Mail());

		return "sendMail";
	}

    @RequestMapping("/send")
	public String send(@ModelAttribute Mail mail, Model model) {
        model.addAttribute("mail", mail);
		getJavaMailSender(mail.getPort(), mail.getProtocol(), mail.getStarttls(), mail.getAuth(), mail.getUser(), mail.getPassword()).send(simpleMessage(mail.getTo()));
        return "result";
	}

    private JavaMailSender getJavaMailSender(int port, String protocol, boolean starttls, boolean auth, String user, String password) 
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.sendgrid.net");
        mailSender.setPort(port);
          
        mailSender.setUsername(user);
        mailSender.setPassword(password);
          
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", Boolean.toString(auth).toLowerCase());
        props.put("mail.smtp.starttls.enable", Boolean.toString(starttls).toLowerCase());
        props.put("mail.debug", "true");
          
        return mailSender;
    }

    private SimpleMailMessage simpleMessage(String to)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("adavid@pingidentity.com");
        message.setSubject("Mail from sendgrid");
        message.setText("It's party time");
        return message;
    }
}
