package lindenmayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.awt.geom.Rectangle2D;

public class LSystem extends AbstractLSystem {
	protected ArrayList<Symbol> alphabet = new ArrayList<Symbol>(); // Liste contenant l'alphabet
	protected HashMap<Symbol, ArrayList<Iterator<Symbol>>> regles = new HashMap<Symbol, ArrayList<Iterator<Symbol>>>(); // Contient
																														// les
																														// règles
																														// (symbol
																														// ->
																														// [R1,R2,...])
	protected HashMap<Character, Symbol> charToSym = new HashMap<Character, Symbol>(); // Lien entre Symbol et
																						// charactère
	protected HashMap<Symbol, String> symToAction = new HashMap<Symbol, String>(); // Lien entre un symbol et une action
	private ArrayList<Symbol> axiom = new ArrayList<Symbol>(); // Chaîne de départ

	// Méthode de conversion de string à iterator
	public Iterator<Symbol> stringToIterator(String str) {
		ArrayList<Symbol> symTab = new ArrayList<Symbol>();

		for (int i = 0; i < str.length(); i++) {
			symTab.add(charToSym.get(str.charAt(i))); // Ajoute le symbol
		}

		return symTab.iterator();
	}

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
		if (this.regles.containsKey(sym)) {
			// Si oui, cela signifie qu'il y a une liste d'instanciée
			this.regles.get(sym).add(stringToIterator(expansion)); // Ajoute l'expansion aux règles reliées à ce symbole
		}

		else {
			// Si non, on instancie et ajoute le relation sym -> liste de règles
			ArrayList<Iterator<Symbol>> newList = new ArrayList<Iterator<Symbol>>();
			newList.add(stringToIterator(expansion));
			this.regles.put(sym, newList);
		}
	}

	// Méthode setAction;
	public void setAction(Symbol sym, String action) {
		this.symToAction.put(sym, action); // On ajoute la relation au Map

	}

	// Méthode getAxiom;
	public Iterator<Symbol> getAxiom() {
		return this.axiom.iterator(); // Retourne l'axiom
	}

	// Méthode setAxiom
	public void setAxiom(String str) {
		ArrayList<Symbol> newList = new ArrayList<Symbol>();
		char inStr;
		for (int i = 0; i < str.length(); i++) {
			inStr = str.charAt(i);

			if (Character.isLetter(inStr))
				newList.add(new Symbol(inStr)); // Ajoute seulement si c'est une lettre
		}

		this.axiom = newList;
	}

	// Méthode rewrite;
	public Iterator<Symbol> rewrite(Symbol sym) {

		int numRules = this.regles.get(sym).size();

		if (!this.regles.containsKey(sym)) {
			return null; // Retourne null si aucune règle reliée à ce symbole
		}

		else if (numRules == 0) { // Vérifie si aucune règle reliée
			return null;
		}

		else if (numRules == 1) { // Vérifie s'il y a une seule règle
			return this.regles.get(sym).get(0);
		}

		else { // Sinon il y a plusieurs règles reliées à sym
			Random rnd = new Random();
			return this.regles.get(sym).get(rnd.nextInt(numRules)); // Retoune une règle aléatoirement

		}
	}

	// Méthode tell pour la récursion;
	public void tell(Turtle turtle, Symbol sym, int rounds) {

	}

	// Méthode tell qui se lance une seule fois
	public void tell(Turtle turtle, Symbol sym) {
		// draw, move, turnL, turnR, push, pop, stay

		String action = symToAction.get(sym);

		switch (action) {
		case "draw":
			turtle.draw();
			break;

		case "move":
			turtle.move();
			break;

		case "turnL":
			turtle.turnL();
			break;

		case "turnR":
			turtle.turnR();
			break;

		case "push":
			turtle.push();
			break;

		case "pop":
			turtle.pop();
			break;

		default: // stay action
			turtle.stay();
			break;
		}

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
