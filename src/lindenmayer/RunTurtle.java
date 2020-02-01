package lindenmayer;

import java.awt.geom.Point2D;
import java.util.Stack;

/* 1.Classe bidon avec l'interface Turtle qui suit les états correctement, mais ne dessine rien en vérité.
 * Par Maxime Ton et Pierre-Olivier Tremblay
 * Matricule: 20143044 et _____________
 */
public class RunTurtle implements Turtle 
{
	//Pile que l'on va utiliser pour les instruction push et pop;
	Stack<State> pile = new Stack<State>();
	//Instance du state activement utilisé;
	State active;
	
	//Classe interne qui permet d'encapsuler l'état;
	public class State {
		//Position du nez de la tortue.
		Point2D position;
		//Angle de la tortue en degrés.
		double angle_degree;
		public State(Point2D pos, double angle) {
			this.position = pos;
			this.angle_degree = angle;
		}
	}
	
	//On implémente la méthode draw;
	public void draw() {
		double angle_rad = Math.toRadians(active.angle_degree);
		active.position.setLocation(active.position.getX() + Math.cos(angle_rad), active.position.getY() + Math.asin(angle_rad));
		//Draw line;
	}
	//On implémente la méthode move;
	public void move() {
		double angle_rad = Math.toRadians(active.angle_degree);
		active.position.setLocation(active.position.getX() + Math.cos(angle_rad), active.position.getY() + Math.asin(angle_rad));
	}
	//On implémente la méthode turnR;
    public void turnR() {
    	active.angle_degree += -45;
    }
    //On implémente la méthode turnL;
    public void turnL() {
    	active.angle_degree += 45;
    }
    //On implémente la méthode push;
    public void push() {
    	State temp = active;
    	pile.push(temp);
    }
    //On implémente la méthode pop;
    public void pop() {
    	State temp = (State)pile.pop();
    	active = temp;
    }
    //On implémente la méthode stay;
    public void stay() {
    	//Do nothing;
    }
    
    public void init(Point2D pos, double angle_deg) {
    	active = new State(pos, angle_deg);
    	active.position.setLocation(0,0);
    	active.angle_degree = 0;
    }
    
    public Point2D getPosition() {
    	return active.position;
    }

    public double getAngle() {
    	return active.angle_degree;
    }

    public void setUnits(double step, double delta) {
    	
    }
}
