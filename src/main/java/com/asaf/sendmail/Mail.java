package com.asaf.sendmail;

import lombok.Data;

@Data
public class Mail {
    private String host="smtp.sendgrid.net";
    private int port;
    private String protocol="smtps";
    private Boolean starttls;
    private Boolean auth=Boolean.TRUE;
    private String user;
    private String password;
    private String from;
    private String to;
}
