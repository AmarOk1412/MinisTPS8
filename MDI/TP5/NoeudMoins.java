
public class NoeudMoins extends Noeud {
	private Noeud _gauche;
	private Noeud _droite;
	
	public NoeudMoins(Noeud gauche, Noeud droite) {
		_gauche = gauche;
		_droite = droite;		
	}

	public int getValue() {
		return _gauche.getValue() - _droite.getValue();
	}
}
