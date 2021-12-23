package com.mbergershop.controller.action;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthentication extends Authenticator {
	 
    PasswordAuthentication account;
 
    public MyAuthentication(){
        String id = "yzon007";
        String pw = "jung1245";
        account = new PasswordAuthentication(id, pw);
    }
 
    public PasswordAuthentication getPasswordAuthentication(){
        return account;
    }

}
