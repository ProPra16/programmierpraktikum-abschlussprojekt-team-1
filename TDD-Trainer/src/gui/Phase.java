package gui;

public class Phase {
	public static final int TESTS = 0;
	public static final int CODE = 1;
	public static final int REFACTOR = 2;
	
	private int phase;
	public Phase(){
		this.phase = 1;
	}
	
	public void next_phase(){
		this.phase = this.phase %2;
	}
	
	public void set_phase(int phase){
		this.phase = phase;
	}
}
