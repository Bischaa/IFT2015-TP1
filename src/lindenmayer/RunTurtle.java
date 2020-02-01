package lindenmayer;

import java.awt.geom.Point2D;
import java.util.Stack;

/* 1.Classe bidon avec l'interface Turtle qui suit les �tats correctement, mais ne dessine rien en v�rit�.
 * Par Maxime Ton et Pierre-Olivier Tremblay
 * Matricule: 20143044 et 20049076
 */
public class RunTurtle implements Turtle {
	// Pile que l'on va utiliser pour les instruction push et pop;
	Stack<State> pile = new Stack<State>();
	// Instance du state activement utilis�;
	State active;

	// Classe interne qui permet d'encapsuler l'�tat;
	public class State {
		// Position du nez de la tortue.
		Point2D position;
		// Angle de la tortue en degr�s.
		double angle_degree;

		public State(Point2D pos, double angle) {
			this.position = pos;
			this.angle_degree = angle;
		}
	}

	// On impl�mente la m�thode draw;
	public void draw() {
		double angle_rad = Math.toRadians(active.angle_degree);
		active.position.setLocation(active.position.getX() + Math.cos(angle_rad),
				active.position.getY() + Math.asin(angle_rad));
		// Draw line;
	}

	// On impl�mente la m�thode move;
	public void move() {
		double angle_rad = Math.toRadians(active.angle_degree);
		active.position.setLocation(active.position.getX() + Math.cos(angle_rad),
				active.position.getY() + Math.asin(angle_rad));
	}

	// On impl�mente la m�thode turnR;
	public void turnR() {
		active.angle_degree += -45;
	}

	// On impl�mente la m�thode turnL;
	public void turnL() {
		active.angle_degree += 45;
	}

	// On impl�mente la m�thode push;
	public void push() {
		State temp = active;
		pile.push(temp);
	}

	// On impl�mente la m�thode pop;
	public void pop() {
		State temp = (State) pile.pop();
		active = temp;
	}

	// On impl�mente la m�thode stay;
	public void stay() {
		// Do nothing;
	}

	public void init(Point2D pos, double angle_deg) {
		active = new State(pos, angle_deg);
		active.position.setLocation(0, 0);
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
