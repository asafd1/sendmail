package com.asaf.sendmail;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SendMailController {
    @GetMapping("/sendMail")
	public String sendMailForm(Model model) {
        model.addAttribute("mail", new Mail());

		return "sendMail";
	}

    @GetMapping("/sendtest")
	public String sendTest(@RequestParam String host, @RequestParam String from, @RequestParam String to) {
		getJavaMailSender(host, 25, "smtp", true, true, "U", "P").send(simpleMessage(from, to));
        return "result";
	}

    @RequestMapping("/send")
	public String send(@ModelAttribute Mail mail, Model model) {
        model.addAttribute("mail", mail);
		getJavaMailSender(mail.getHost(), mail.getPort(), mail.getProtocol(), mail.getStarttls(), mail.getAuth(), mail.getUser(), mail.getPassword()).send(simpleMessage(mail.getFrom(), mail.getTo()));
        return "result";
	}

    private JavaMailSender getJavaMailSender(String host, int port, String protocol, boolean starttls, boolean auth, String user, String password) 
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // mailSender.setHost("smtp.sendgrid.net");
        mailSender.setHost(host);
        mailSender.setPort(port);
          
        mailSender.setUsername(user);
        mailSender.setPassword(password);
          
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", Boolean.toString(auth).toLowerCase());
        System.out.println("========================> " + Boolean.toString(starttls).toLowerCase());
        props.put("mail.smtp.starttls.enable", Boolean.toString(starttls).toLowerCase());
        props.put("mail.debug", "true");
          
        return mailSender;
    }

    private SimpleMailMessage simpleMessage(String from, String to)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        List<String> recipients = Arrays.asList(to.split(","));
        recipients.forEach(System.out::println);

        message.setTo(recipients.toArray(new String[0]));
        System.out.println("---------------------> " + message.getTo());
        message.setFrom(from);
        message.setSubject("Pingidentity test");
        message.setText("It's party time");
        return message;
    }
}
