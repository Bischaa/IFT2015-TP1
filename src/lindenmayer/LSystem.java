package lindenmayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LSystem extends AbstractLSystem
{
	public List<Symbol> alphabet = new ArrayList<Symbol>();
	
	//Méthode addSymbol;
	public Symbol addSymbol(char sym) {
		Symbol temp = new Symbol();
		HashMap<Character,Symbol> charMap = new HashMap<Character,Symbol>();
		charMap.put(sym,temp);
		return temp;
	}
	
	//Méthode addRule;
	public void addRule(Symbol sym, String expansion) {
		alphabet.add(sym);
	}

	//Méthde addAction;
	public void setAction(Symbol sym, String action) {
		
	}
	
	//Méthode getAxiom;
	public Iterator<Symbol> getAxiom() {
		
	}
	
	//Méthode rewrite;
	public Iterator<Symbol> rewrite(Symbol sym) {
		
	}
	
	//Méthode tell;
	public void tell(Turtle turtle, Symbol sym) {
		
	}
}
