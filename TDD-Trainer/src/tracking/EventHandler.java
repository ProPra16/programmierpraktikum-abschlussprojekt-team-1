package tracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventHandler {
	private static List<Event> events;
	
	public static void newEmptyEventList(){
		events = new ArrayList<Event>();
	}
	public static void newEventList(List<Event> e){
		events = e;
	}
	public static void addEvent(Event event){
		events.add(event);
	}
	public static List<Event> getPhaseStartEvents(){
		return events.stream().filter(e -> e instanceof PhaseStartEvent).collect(Collectors.toList());
	}
	public static List<Event> getErrorEvents(){
		return events.stream().filter(e -> e instanceof ErrorEvent).collect(Collectors.toList());
	}
}
