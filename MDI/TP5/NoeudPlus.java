
public class NoeudPlus extends Noeud {
	private Noeud _gauche;
	private Noeud _droite;
	
	public NoeudPlus(Noeud gauche, Noeud droite) {
		_gauche = gauche;
		_droite = droite;		
	}

	public int getValue() {
		return _gauche.getValue() + _droite.getValue();
	}
}
