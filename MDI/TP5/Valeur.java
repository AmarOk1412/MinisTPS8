
public class Valeur extends Noeud implements Visitable {
	private int _value;
	
	public Valeur(int value) {
		_value = value;
	}
	
	public void accept(Visitor visitor) {
		
	}
	
	public int getValue() {
		return _value;
	}
}
