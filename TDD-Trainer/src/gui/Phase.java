package gui;


//TODO: Ist dies nicht eigentlich eine Datentyps-Klasse?
/**
 * Repräsentiert eine Phase. Ist das hier nicht eigentlich eine Datentyps-Klasse?
 *
 */

public class Phase {
	public static final int TESTS = 0;
	public static final int CODE = 1;
	public static final int REFACTOR = 2;
	
	private int phase;
	public Phase(){
		this.phase = 0;
	}
	
	public void next_phase(){
		this.phase = (this.phase+1)%3;
	}
	
	public int get(){
		return phase;
	}
}
