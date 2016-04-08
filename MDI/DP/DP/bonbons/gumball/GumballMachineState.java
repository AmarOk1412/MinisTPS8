

public class GumballMachineState {

	int count = 0;
	private State state;

	public GumballMachineState(int count) {
		this.count = count;
		this.state = new InitState(count);
	}

	public void insertQuarter() {
		this.state = this.state.insertQuarter();
	}

	public void ejectQuarter() {
		this.state = this.state.ejectQuarter();
	}

	public void turnCrank() {
		this.state = this.state.turnCrank();
	}

	private void dispense() {
		this.state = this.state.dispense();
	}

	public void refill(int numGumBalls) {
		this.state = new InitState(numGumBalls);
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("\nMighty Gumball, Inc.");
		result.append("\nJava-enabled Standing Gumball Model #2004\n");
		result.append("Inventory: " + count + " gumball");
		if (count != 1) {
			result.append("s");
		}
		result.append("\nMachine is ");
		result.append(this.state.toString());
		result.append("\n");
		return result.toString();
	}
}
