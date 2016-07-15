package tracking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PhaseStartEvent extends Event{
	private int phase;
	private int duration;
	public PhaseStartEvent(int phase){
		super();
		this.phase = phase;
	}
	public int getPhase(){
		return phase;
	}
	public int getDuration(){
		return duration;
	}
	public void calculateDuration() {
		duration =  (int)super.time.until(LocalDateTime.now(), ChronoUnit.SECONDS);
	}
}
