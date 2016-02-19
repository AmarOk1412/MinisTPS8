package net.tuto2.ws.client;

import javax.xml.ws.WebServiceRef;

public class App {
  @WebServiceRef(wsdlLocation="http://localhost:8080/services/concat?wsdl")
  static MyConcatAgent service = new MyConcatAgent();

  public static void main(String[] args) {
    try {
      App client = new App();
      client.doTest(args);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void doTest(String[] args) {
    try {
      System.out.println("Retrieving the port from  the following service: " + service);
      WSConcat port = service.getWSConcatImplPort();
      String str1;
      if (args.length > 0) {
        str1 = args[0];
      } else {
        str1 = "a";
      }
      String str2;
      if (args.length > 1) {
        str2 = args[1];
      } else {
        str2 = "b";
      }

      String response = port.concat(str1,str2);
      System.out.println(response);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
