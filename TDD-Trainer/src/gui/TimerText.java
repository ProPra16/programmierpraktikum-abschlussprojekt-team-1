package gui;

import javafx.scene.text.Text;

public class TimerText extends Text implements Runnable{
	private long startMillis = 0L;
	private int updateMillis = 100;
	private Thread t;
	private boolean running = false;
	
	public TimerText(){
		super("Time: 0:0.0");
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
	}
}