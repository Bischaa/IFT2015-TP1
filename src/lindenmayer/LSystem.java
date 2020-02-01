package lindenmayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.awt.geom.Rectangle2D;

public class LSystem extends AbstractLSystem {
	public List<Symbol> alphabet = new ArrayList<Symbol>();

	// M�thode addSymbol;
	public Symbol addSymbol(char sym) {
		Symbol temp = new Symbol();
		HashMap<Character, Symbol> charMap = new HashMap<Character, Symbol>();
		charMap.put(sym, temp);
		return temp;
	}

	// M�thode addRule;
	public void addRule(Symbol sym, String expansion) {
		alphabet.add(sym);
	}

	// M�thde addAction;
	public void setAction(Symbol sym, String action) {

	}

	// M�thode getAxiom;
	public Iterator<Symbol> getAxiom() {
		return null; // en attendant d'avoir la vraie fonction pour retirer l'erreur de retour
	}

	// Méthode setAxiom
	public void setAxiom(String str) {

	}

	// M�thode rewrite;
	public Iterator<Symbol> rewrite(Symbol sym) {
		return null; // en attendant d'avoir la vraie fonction pour retirer l'erreur de retour
	}

	// M�thode tell;
	public void tell(Turtle turtle, Symbol sym, int rounds) {

	}

	// Méthode tell
	public void tell(Turtle turtle, Symbol sym) {
	}

	// Méthode apply rule
	public Iterator<Symbol> applyRules(Iterator<Symbol> seq, int n) {
		return null; // en attendant d'avoir la vraie fonction pour retirer l'erreur de retour
	}

	// Méthode getBoundingBox
	public Rectangle2D getBoundingBox(Turtle turtle, Iterator<Symbol> seq, int n) {
		return null; // en attendant d'avoir la vraie fonction pour retirer l'erreur de retour
	}
}
