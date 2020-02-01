package lindenmayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.awt.geom.Rectangle2D;

public class LSystem extends AbstractLSystem {
	public List<Symbol> alphabet = new ArrayList<Symbol>(); // Liste contenant l'alphabet
	public HashMap<Symbol, List<String>> regles = new HashMap<Symbol, List<String>>(); // Contient les règles (symbol
																						// -> [R1,R2,...])
	public HashMap<Character, Symbol> charToSym = new HashMap<Character, Symbol>(); // Lien entre Symbol et charactère

	// Méthode addSymbol;
	public Symbol addSymbol(char sym) {
		Symbol temp = new Symbol(sym);
		charToSym.put(sym, temp); // Ajoute le lien charactère -> symbole
		alphabet.add(temp); // Ajoute le symbole à l'alphabet
		return temp;
	}

	// Méthode addRule;
	public void addRule(Symbol sym, String expansion) {
		// Ajouter la règle à au tableau de référence

		// Vérifie si regles contient des relations pour sym
		if (regles.containsKey(sym)) {
			// Si oui, cela signifie qu'il y a une liste d'instanciée
			regles.get(sym).add(expansion); // Ajoute l'expansion aux règles reliées à ce symbole
		}

		else {
			// Si non, on instancie et ajoute le relation sym -> liste de règles
			List<String> newList = new ArrayList<String>();
			newList.add(expansion);
			regles.put(sym, newList);
		}
	}

	// Méthde addAction;
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
