
public interface Visitor {
	public void visit(Valeur v);
	public void visit(NoeudPlus v);
	public void visit(NoeudMoins m);
}
