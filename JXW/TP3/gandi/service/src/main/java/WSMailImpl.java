package net.mail.ws.service;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Properties;
import java.util.Vector;
import java.util.ArrayList;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.FromStringTerm;
import javax.mail.search.SearchTerm;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "net.mail.ws.service.WSMail", serviceName = "MyOwnMailAgent")
public class WSMailImpl {
  public void sendMail (String smtpHost, int smtpPort,  String from, String to, String  username, String password,  boolean isSSL){

    Session session = getSession(smtpHost, isSSL);

    try {
      //Create the mail
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
      message.setSubject("Hello");
      message.setText("Hello World");

      //Send the mail
      Transport tr;
      tr = session.getTransport();
      tr.connect(smtpHost, smtpPort, username, password);
      message.saveChanges();

      tr.sendMessage(message,message.getAllRecipients());
      tr.close();

    }
    catch (Exception e) {
      System.out.println("MessagingException");
    }
  }

  public void createRep (String smtpHost, int smtpPort,  String imapHost, int imapPort, String  username, String password, boolean isSSL, String rep){

    Session session = getSession(smtpHost, isSSL);

    try {
      Store  store = session.getStore("imaps");
      store.connect(imapHost, imapPort, username, password);

      //create the folder object and open it
      Folder defaultFolder = store.getDefaultFolder();
      Folder newFolder = defaultFolder.getFolder(rep);
      newFolder.create(Folder.HOLDS_MESSAGES);
      //close the store and folder objects
      defaultFolder.close(false);
      store.close();
    }
    catch (Exception e) {
      System.out.println("MessagingException");
    }
  }

  public String checkMail (String smtpHost, int smtpPort,  String imapHost, int imapPort,  String  username, String password,  boolean isSSL){
    String content = "";

    Session session = getSession(smtpHost, isSSL);

    try {
      Store  store = session.getStore("imaps");
      store.connect(imapHost, imapPort, username, password);

      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(Folder.READ_ONLY);

      // retrieve the messages from the folder in an array and print it
      Message[] messages = emailFolder.getMessages();
      System.out.println("messages.length---" + messages.length);

      for (int i = 0, n = messages.length; i < n; i++) {
        Message message = messages[i];
        System.out.println("---------------------------------");
        System.out.println("Email Number " + (i + 1));
        System.out.println("Subject: " + message.getSubject());
        System.out.println("From: " + message.getFrom()[0]);
        System.out.println("Text: " + message.getContent().toString());

        content += "\n----\nSubject:" + message.getSubject() + "\nFrom: " + message.getFrom()[0] + "\nText: " + message.getContent().toString();

      }

      //close the store and folder objects
      emailFolder.close(false);
      store.close();
    }
    catch (Exception e) {
      System.out.println("MessagingException");
    }
    return content;
  }

  public int searchBySubjectContains(String smtpHost, int smtpPort, String imapHost, int imapPort, String username, String password, boolean isSSL, String subject) {
    ArrayList<String> goodMessages = new ArrayList<String>();

    Session session = getSession(smtpHost, isSSL);

    try {
      Store  store = session.getStore("imaps");
      store.connect(imapHost, imapPort, username, password);

      //create the folder object and open it
      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(Folder.READ_ONLY);

      // retrieve the messages from the folder check if the subject contains the string wanted
      Message[] messages = emailFolder.getMessages();
      for (int i = 0, n = messages.length; i < n; i++) {
        Message message = messages[i];
        if(message.getSubject().contains(subject))
        goodMessages.add(message.getSubject());
      }

      //close the store and folder objects
      emailFolder.close(false);
      store.close();
    }
    catch (Exception e) {
      System.out.println("MessagingException");
    }
    return goodMessages.size();
  }

  private Session getSession(String smtpHost, boolean isSSL)
  {
    Properties props = new Properties();

    if(isSSL) {
      props.put("mail.transport.protocol", "smtps");
      props.put("mail.smtps.auth", "true");
    } else {
      props.put("mail.transport.protocol", "smtp");
      props.put("mail.smtps.auth", "false");
    }

    props.put("mail.smtp.host", smtpHost);

    Session session = Session.getDefaultInstance(props);
    session.setDebug(true);

    return session;
  }

}
