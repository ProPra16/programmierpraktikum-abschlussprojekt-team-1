package gui;

import javafx.scene.text.Text;

public class Timer extends Text implements Runnable{//TODO:Thread schliessen!!!
	private long startMillis = 0L;
	private int updateMillis = 1000;
	private Thread t;
	private boolean running = false;
	private boolean time_up;
	private long duration;
	private Phase phase;
	
	public Timer(int sec, Phase phase){
		super("Time: 0:00");
		this.duration = sec*1000;
		this.phase = phase;
		time_up = false;
	}
	public boolean time_up(){
		return time_up;
	}
	public void time_up_act(){
		phase.next_phase();
		reset();
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
		long diff = System.currentTimeMillis() - startMillis;
		int seconds = (int)((diff % (1000 * 60))/1000);
		int minutes = (int)((diff % (1000 * 60 * 60))/(1000*60));
		return String.valueOf(minutes) + ":" + String.valueOf(seconds); // TODO: sec nit nullen auf zwei stellen fuellen
	}
	
	public void update(){
		this.setText("Time: " + this.toString());
		if((System.currentTimeMillis() - startMillis)>=duration){
			time_up = true;
			time_up_act();
		}
	}
}