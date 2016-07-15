package tracking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PhaseStartEvent extends Event{
	private int phase;
	private long duration;
	public PhaseStartEvent(int phase){
		super();
		this.phase = phase;
	}
	public int getPhase(){
		return phase;
	}
	public long getDuration(){
		return duration;
	}
	public void calculateDuration() {
		duration =  super.time.until(LocalDateTime.now(), ChronoUnit.SECONDS);
	}
}
