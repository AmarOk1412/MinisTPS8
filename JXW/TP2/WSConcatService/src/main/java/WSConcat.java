package net.tuto2.ws.service.concat;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://david.bromberg.fr/service/concat", name = "WSConcat")
@SOAPBinding(style = SOAPBinding.Style.RPC) //https://jax-ws.java.net/nonav/2.1.3/docs/annotations.html#2.7%20javax.jws.soap.SOAPBinding|outline
public interface WSConcat {
  @WebMethod // La méthode est considérée comme une opération du webservice
  public String concat(
  @WebParam(name = "str1")
  String str1,
  @WebParam(name = "str2")
  String str2
  );  // Donne un nom aux paramètres
}
