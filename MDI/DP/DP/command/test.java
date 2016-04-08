import java.util.List;
import java.util.ArrayList;

public class test {
  public static void main(String[] args) {
    List<Objet> listStylo = new ArrayList<Objet>();
    Stylo ex = new Stylo("bic", "bic", "bic", 10., "bic");
    for(int i = 0 ; i < 100; ++i)
    listStylo.add(ex);
    Lot lotStylo = new Lot(listStylo);
    System.out.println(lotStylo.getPrix());
  }
}
