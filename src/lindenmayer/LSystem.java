package lindenmayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Collections;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.Point;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

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
	protected Symbol[] rulesAssociation; // Pour la comparaison avec les symbol

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

		int numRules = 0; // Nombre de règles reliées à un symbol
		Symbol goodSym;
		for (int i = 0; i < this.rulesAssociation.length; i++) {
			goodSym = this.rulesAssociation[i];
			if (sym.equals(goodSym)) {
				sym = goodSym;
			}
		}

		try {
			numRules = this.regles.get(sym).size();
		} catch (NullPointerException e) {
			ArrayList<Symbol> symReturn = new ArrayList<Symbol>();
			symReturn.add(sym);
			return symReturn.iterator();
		}

		if (numRules == 0) { // S'il n'y a toujours pas de règles associées
			ArrayList<Symbol> symReturn = new ArrayList<Symbol>();
			symReturn.add(sym);
			return symReturn.iterator();
		}

		if (numRules == 1) { // Vérifie s'il y a une seule règle
			return this.regles.get(sym).get(0);
		}

		else { // Sinon il y a plusieurs règles reliées à sym
			Random rnd = new Random();
			return this.regles.get(sym).get(rnd.nextInt(numRules)); // Retoune une règle aléatoirement

		}
	}

	// Méthode tell pour la récursion;
	public void tell(Turtle turtle, Symbol sym, int rounds) {
		if (rounds == 0) {
			tell(turtle, sym);
		} else {
			Iterator<Symbol> iter = rewrite(sym);
			while (iter.hasNext()) {
				Symbol s = iter.next();
				tell(turtle, s, rounds - 1);
			}
		}
	}

	// Méthode tell qui se lance une seule fois
	public void tell(Turtle turtle, Symbol sym) {
		String action = this.symToAction.get(sym);
		try {
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
		} catch (NullPointerException e) {
			// Ne rien faire s'il n'y a pas de symbole
		}

	}

	public static void readJSONFile(String filename, LSystem system, Turtle turtle)
			throws java.io.IOException, org.json.JSONException {
		JSONObject input = new JSONObject(new JSONTokener(new java.io.FileReader(filename))); // lecture de fichier JSON
																								// avec JSONTokener
		JSONArray alphabet = input.getJSONArray("alphabet"); // Tableau de l'alphabet
		String axiom = input.getString("axiom");
		JSONObject rules = input.getJSONObject("rules"); // Objet des règles
		JSONObject actions = input.getJSONObject("actions"); // Objet des actions
		JSONObject params = input.getJSONObject("parameters"); // Objet des paramètres

		turtle.setUnits(params.getDouble("step"), params.getDouble("angle")); // Initialisation des unités

		// Initialisation du point et de l'angle de départ de la tortue
		JSONArray start = params.getJSONArray("start");
		turtle.init((Point2D) new Point(start.getInt(0), start.getInt(1)), start.getDouble(2));

		system.setAxiom(axiom); // On place l'axiom dans le système
		JSONArray letterRules;
		for (int i = 0; i < alphabet.length(); i++) {
			String letter = alphabet.getString(i);
			Symbol sym = system.addSymbol(letter.charAt(0)); // Ajout à l'alphabet

			// RENDU-LÀ On doit faire les associations sym->action, sym->règles

			// Pour les règles
			try { // S'il y a une correspondance pour les règles
				letterRules = rules.getJSONArray(letter);

				for (int j = 0; j < letterRules.length(); j++) {
					system.addRule(sym, letterRules.getString(i)); // Ajoute la règle
				}

			} catch (org.json.JSONException e) {
				// Ne rien faire s'il n'y a pas de correspondance
			}

			// Pour les actions
			system.setAction(sym, actions.getString(letter)); // Fait l'association sym->action

		}

		system.rulesAssociation = system.regles.keySet().toArray(new Symbol[system.regles.keySet().size()]);

	}

	// Méthode apply rule
	public Iterator<Symbol> applyRules(Iterator<Symbol> seq, int n) {
		ArrayList<Symbol> list = new ArrayList<Symbol>();

		if (n == 0) { // On retourne la séquence sans modification
			return seq;
		}

		// On observe la chaine S_i
		while (seq.hasNext()) {
			try {
				Symbol nextSymbol = seq.next();
				Symbol goodSym;
				for (int i = 0; i < this.rulesAssociation.length; i++) {
					goodSym = this.rulesAssociation[i];
					if (nextSymbol.equals(goodSym)) {
						nextSymbol = goodSym;
					}
				}
				if (this.regles.containsKey(nextSymbol)) {
					// On choisi une règle a appliquer au symbole
					Iterator<Symbol> rule = rewrite(nextSymbol);
					while (rule.hasNext()) {
						// On ajoute à la liste chaque nouveau symbole dans la règle
						list.add(rule.next());
					}
				}
			} catch (NullPointerException e) {
				// while fini
				// On transforme notre liste en chaine S_i+1
				Iterator<Symbol> iter = list.iterator();
				// Condition d'arrêt, lorsque n=1, on a fait la boucle n fois.
				if (n == 1) {
					return iter;
				} else {
					return applyRules(iter, n - 1);
				}
			}
		}

		return seq; // Ne va jamais se rendre

	}

	// Méthode getBoundingBox
	public Rectangle2D getBoundingBox(Turtle turtle, Iterator<Symbol> seq, int n) {
		Rectangle finalUnion = new Rectangle(); // Rectangle final

		if (n == 0) {
			return getBoundingRecur(turtle, applyRules(seq, n));
		}

		else {
			return finalUnion.createUnion(getBoundingBox(turtle, applyRules(seq, 1), n - 1));
		}

		// On voit qu'au final applyRules sera appliqué n fois sur seq

	}

	// Méthode pour la récursion de getBoundinBox
	private Rectangle2D getBoundingRecur(Turtle turtle, Iterator<Symbol> seq) {
		ArrayList<Double> x_pos = new ArrayList<Double>(); // Pour stocker les positions en x
		ArrayList<Double> y_pos = new ArrayList<Double>(); // Pour stocker les positions en y

		x_pos.add(turtle.getPosition().getX()); // Ajout de la position initiale en x
		y_pos.add(turtle.getPosition().getY()); // Ajout de la position initiale en y

		// Boucle pour pouvoir effectuer le déplacement de la tortue sur la chaîne seq
		while (seq.hasNext()) {
			tell(turtle, seq.next());
			x_pos.add(turtle.getPosition().getX()); // Ajout de la position x
			y_pos.add(turtle.getPosition().getY()); // Ajout de la position y
		}

		int x_max = Collections.max(x_pos).intValue(); // Max de x
		int y_max = Collections.max(y_pos).intValue(); // Max de y
		int x_min = Collections.min(x_pos).intValue(); // Min de x
		int y_min = Collections.min(y_pos).intValue(); // Min de y

		int width = x_max - x_min; // Largeur du rectangle
		int height = y_max - y_min; // Hauteur du rectangle

		return (Rectangle2D) new Rectangle(width, height);
	}
}
