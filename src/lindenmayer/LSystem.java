package lindenmayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LSystem extends AbstractLSystem
{
	public List<Symbol> alphabet = new ArrayList<Symbol>();
	
	//M�thode addSymbol;
	public Symbol addSymbol(char sym) {
		Symbol temp = new Symbol();
		HashMap<Character,Symbol> charMap = new HashMap<Character,Symbol>();
		charMap.put(sym,temp);
		return temp;
	}
	
	//M�thode addRule;
	public void addRule(Symbol sym, String expansion) {
		alphabet.add(sym);
	}

	//M�thde addAction;
	public void setAction(Symbol sym, String action) {
		
	}
	
	//M�thode getAxiom;
	public Iterator<Symbol> getAxiom() {
		
	}
	
	//M�thode rewrite;
	public Iterator<Symbol> rewrite(Symbol sym) {
		
	}
	
	//M�thode tell;
	public void tell(Turtle turtle, Symbol sym) {
		
	}
}
