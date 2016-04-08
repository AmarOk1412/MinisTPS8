

public class InitState implements State
{
  private int nbGumball = 0;

  public InitState(int gum)
  {
    this.nbGumball = gum;
  }

  public State insertQuarter()
  {
    if(nbGumball > 0)
    return new QuarterInseredState(nbGumball);
    else
    {
			System.out.println("You can't insert a quarter, the machine is sold out");
      return this;
    }
  }

  public State ejectQuarter()
  {
    System.out.println("You haven't inserted a quarter");
    return this;
  }

  public State turnCrank()
  {
    System.out.println("You turned but there's no quarter");
    return this;
  }

  public State dispense()
  {
    System.out.println("You need to pay first");
    return this;
  }

  public String toString()
  {
    return "waiting for quarter";
  }
}
