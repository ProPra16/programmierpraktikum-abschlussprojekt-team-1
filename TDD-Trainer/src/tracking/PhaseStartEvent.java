package tracking;

public class PhaseStartEvent extends Event{
	private int phase;
	public PhaseStartEvent(int phase1){
		super();
		this.phase = phase;
	}
	public int getPhase(){
		return phase;
	}
}
