package net.mail.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import java.util.ArrayList;
import javax.mail.*;
import javax.mail.internet.*;

@WebService(name = "WSMail", targetNamespace = "http://david.bromberg.fr/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface WSMail {

  /**
  * Send a e-mail
  * @param password
  * @param smtpHost
  * @param smtpPort
  * @param from
  * @param to
  * @param username
  * @param isSSL
  */
  @WebMethod
  public void sendMail(
  @WebParam(name = "smtpHost", partName = "smtpHost")
  String smtpHost,
  @WebParam(name = "smtpPort", partName = "smtpPort")
  int smtpPort,
  @WebParam(name = "from", partName = "from")
  String from,
  @WebParam(name = "to", partName = "to")
  String to,
  @WebParam(name = "username", partName = "username")
  String username,
  @WebParam(name = "password", partName = "password")
  String password,
  @WebParam(name = "isSSL", partName = "isSSL")
  boolean isSSL);

  /**
  * Get all mails
  * @param password
  * @param smtpHost
  * @param smtpPort
  * @param imapHost
  * @param imapPort
  * @param username
  * @param isSSL
  */
  @WebMethod
  public String checkMail(
  @WebParam(name = "smtpHost", partName = "smtpHost")
  String smtpHost,
  @WebParam(name = "smtpPort", partName = "smtpPort")
  int smtpPort,
  @WebParam(name = "imapHost", partName = "imapHost")
  String imapHost,
  @WebParam(name = "imapPort", partName = "imapPort")
  int imapPort,
  @WebParam(name = "username", partName = "username")
  String username,
  @WebParam(name = "password", partName = "password")
  String password,
  @WebParam(name = "isSSL", partName = "isSSL")
  boolean isSSL
  );

  /**
  * Create a folder
  * @param password
  * @param smtpHost
  * @param smtpPort
  * @param imapHost
  * @param imapPort
  * @param username
  * @param isSSL
  * @param rep the name of the new directory
  */
  @WebMethod
  public void createRep(
  @WebParam(name = "smtpHost")
  java.lang.String smtpHost,
  @WebParam(name = "smtpPort")
  int smtpPort,
  @WebParam(name = "imapHost")
  java.lang.String imapHost,
  @WebParam(name = "imapPort")
  int imapPort,
  @WebParam(name = "username")
  java.lang.String username,
  @WebParam(name = "password")
  java.lang.String password,
  @WebParam(name = "isSSL")
  boolean isSSL,
  @WebParam(name = "rep")
  String rep
  );


  /**
  * Search mail where the subject contains the param subject
  * @param password
  * @param smtpHost
  * @param smtpPort
  * @param imapHost
  * @param imapPort
  * @param username
  * @param isSSL
  * @param subject
  */
  @WebMethod
  public int searchBySubjectContains(
  @WebParam(name = "smtpHost")
  java.lang.String smtpHost,
  @WebParam(name = "smtpPort")
  int smtpPort,
  @WebParam(name = "imapHost")
  java.lang.String imapHost,
  @WebParam(name = "imapPort")
  int imapPort,
  @WebParam(name = "username")
  java.lang.String username,
  @WebParam(name = "password")
  java.lang.String password,
  @WebParam(name = "isSSL")
  boolean isSSL,
  @WebParam(name = "subject")
  java.lang.String subject
  );
}
