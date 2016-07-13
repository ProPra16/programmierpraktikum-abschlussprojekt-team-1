package tracking;

import java.time.LocalDateTime;

public class Event {
	protected LocalDateTime time;
	public Event(){
		time = LocalDateTime.now();
	}

}
