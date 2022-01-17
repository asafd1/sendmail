package com.asaf.sendmail;

import lombok.Data;

@Data
public class Mail {
    private int port;
    private String protocol;
    private Boolean starttls;
    private Boolean auth;
    private String user;
    private String password;
    private String to;
}
