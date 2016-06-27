package gui;

public class Phase {
	public static final int TESTS = 1;
	public static final int CODE = 2;
	public static final int REFACTOR = 3;
	
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
