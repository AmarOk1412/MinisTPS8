import java.util.List;
public class Lot
{
  private List<Objet> lot;
  public Lot(List<Objet> lot)
  {
    this.lot = lot;
  }

  public double getPrix()
  {
    return lot.size()*(lot.get(0).prix)*80/100;
  }
}
