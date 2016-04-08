public class ReadyState implements State
{

  private int nbGumball = 0;

  public ReadyState(int gum)
  {
    this.nbGumball = gum;
  }

  public State insertQuarter()
  {
    System.out.println("Please wait, we're already giving you a gumball");
    return this;
  }

  public State ejectQuarter()
  {
    System.out.println("Sorry, you already turned the crank");
    return this;
  }

  public State turnCrank()
  {
    System.out.println("Turning twice doesn't get you another gumball!");
    return this;
  }

  public State dispense()
  {
    System.out.println("A gumball comes rolling out the slot");
    return new InitState(nbGumball-1);
  }

  public String toString()
  {
    return "delivering a gumball";
  }
}
