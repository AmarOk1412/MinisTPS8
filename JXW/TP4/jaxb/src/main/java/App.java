import net.serialisation.data.*;
import javax.xml.bind.*;
import java.math.BigInteger;
import java.math.BigDecimal;
import javax.xml.datatype.*;
import javax.xml.namespace.QName;

class App {
  public static void main(String[] args) {
    try{
      ObjectFactory factory = new ObjectFactory();

      CatalogData catalogData = factory.createCatalogData();
      Book firstBook = factory.createBook();
      firstBook.setId(new BigInteger("123456789"));
      firstBook.setAuthor("David Bromberg");
      firstBook.setTitle("Programmation Android");
      firstBook.setGenre("Programmation");
      firstBook.setPrice(new BigDecimal("0.0"));
      firstBook.setPublishDate(DatatypeFactory.newInstance().newXMLGregorianCalendar("2012-01-30"));
      firstBook.setDescription("Apprendre la programmation sur Android");
      catalogData.getBook().add(firstBook);

      Book secondBook = factory.createBook();
      secondBook.setId(new BigInteger("987654321"));
      secondBook.setAuthor("David Bromberg");
      secondBook.setTitle("Programmation Iphone");
      secondBook.setGenre("Programmation");
      secondBook.setPrice(new BigDecimal("0.0"));
      secondBook.setPublishDate(DatatypeFactory.newInstance().newXMLGregorianCalendar("2012-01-30"));
      secondBook.setDescription("Apprendre la programmation sur Iphone");
      catalogData.getBook().add(secondBook);

      JAXBContext context = JAXBContext.newInstance("net.serialisation.data");
      JAXBElement<CatalogData> element = new JAXBElement<CatalogData>(new QName("CatalogData"), CatalogData.class, catalogData);
      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
      marshaller.marshal(element,System.out);
    } catch (Exception e) {
      System.out.println(e);
    }

  }
}
