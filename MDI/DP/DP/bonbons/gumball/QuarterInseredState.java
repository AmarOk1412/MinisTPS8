
public class QuarterInseredState implements State
{
  private int nbGumball = 0;

  public QuarterInseredState(int gum)
  {
    this.nbGumball = gum;
  }

  public State insertQuarter()
  {
    System.out.println("You can't insert another quarter");
    return this;
  }

  public State ejectQuarter()
  {
    return new InitState(nbGumball);
  }

  public State turnCrank()
  {
    System.out.println("You turned...");
    return (new ReadyState(nbGumball)).dispense();
  }

  public State dispense()
  {
    System.out.println("No gumball dispensed");
    return this;
  }

  public String toString()
  {
    return "waiting for turn of crank";
  }
}
