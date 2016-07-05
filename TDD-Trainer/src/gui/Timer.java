package gui;

import javafx.scene.text.Text;

/**
 * Repräsentiert den Timer, der die verbleibende Zeit (bei Verwendung von Babysteps) im Programm
 * anzeigt. Enthält den dazu notwendigen Timer-Thread, und aktualisiert sich selbstständig.
 */

public class Timer extends Text implements Runnable{
	/** Repräsentiert den Startwert des Timers (Anzahl der Millisekunden) */
	private long startMillis = 0L;
	/** Anzahl der Millisekunden zwischen den Aktualisierungen der Anzeige. */
	private int updateMillis = 1000;
	/** Der Thread, welcher den Timer repräsentiert und die Aktualisierung durchführt. */
	private Thread t;
	/** Gibt an, ob der Timer gerade läuft oder gestoppt ist. */
	private boolean running = false;
	/** Gibt (vermutlich) an, ob die Zeit abgelaufen ist.*/ //TODO: Wirklich?
	private boolean time_up;
	/** Gibt die Zeit an, die der Timer herunterzählen soll.*/
	private long duration;
	/** Speichert die Phase, die der Timer verwaltet*/ //TODO: Entsprechend ConstantsManager extrahieren.
	private Phase phase;
	
	public Timer(int sec, Phase phase){
		super("Time: 0:00");
		this.duration = ((sec+1)*1000)-1; //TODO:dieser verzoegerungsfix ist unschoen - das soll ned so bleiben!
		this.phase = phase;
		time_up = false;
	}
	public boolean time_up(){
		return time_up;
	}

	public void run(){
		startMillis = System.currentTimeMillis();
		while (running){
			update();
			try {
				Thread.sleep(updateMillis);
			} catch (InterruptedException e) {
				//DO Nothing
			}
		}
	}
	
	public void start(){
		System.out.println("Starting Timer");
		if (t == null){
			t = new Thread(this, "Timer");
			running = true;
			t.start();
		}
	}
	
	public void stop(){
		running = false;
		if (t != null){
			t.interrupt();
			t = null;
		}
	}
	
	public void reset(){
		startMillis = System.currentTimeMillis();
		time_up = false;
	}
	
	public String toString(){
		//TODO: Rundungsfehler ausmerzen!
		long diff = duration-(System.currentTimeMillis() - startMillis);
		//System.out.println(diff2);
		int seconds = (int)((diff % (1000 * 60))/1000);
		int minutes = (int)((diff % (1000 * 60 * 60))/(1000*60));
		return String.valueOf(minutes) + ":" + String.valueOf(seconds); // TODO: sec nit nullen auf zwei stellen fuellen
	}
	
	public void update(){
		this.setText("Time: " + this.toString());
		if((System.currentTimeMillis() - startMillis)>=duration){
			time_up = true;
		}
	}
}