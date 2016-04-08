
public interface State {
  State insertQuarter();
  State ejectQuarter();
  State turnCrank();
  State dispense();
  String toString();
}
