package net.mail.ws.client;

public class WSMailClient {
  public static void main (String[] args) {
    MyOwnMailAgent service =new MyOwnMailAgent();
    WSMail mail = service.getWSMailImplPort();

    String mailAddress= "MAIL";
    String password = "»e=/w«.rgaé=»";
    String smtpHost = "mail.gandi.net";
    int smtpPort = 465;
    int imapPort = 993;
    boolean isSSL = true;

    mail.sendMail(smtpHost, smtpPort, mailAddress, "spam@enconn.fr", mailAddress, password, isSSL);
    mail.createRep(smtpHost, smtpPort, smtpHost, imapPort, mailAddress, password, isSSL, "demo");
    System.out.println(mail.checkMail(smtpHost, smtpPort,smtpHost, imapPort, mailAddress, password, isSSL));
    System.out.println(mail.searchBySubjectContains(smtpHost, smtpPort, smtpHost, imapPort, mailAddress, password, isSSL, "Tello"));
  }
}
