package gui;

public class Phase {
	private int phase;
	public Phase(){
		this.phase = 1;
	}
	public void next_phase(){
		this.phase = this.phase %4;
	}
	public int get(){
		return phase;
	}
}
