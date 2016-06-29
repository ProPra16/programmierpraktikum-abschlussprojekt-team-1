package data;

public class Constants {
	private Exercise exercise;
	private boolean showBabystepsAlert;
	
	public Constants(){
		exercise = new Exercise();
		showBabystepsAlert = false;
	}
	
	public void setExercise(Exercise newExercise){
		exercise = newExercise;
	}
	
	public Exercise getExercise(){
		if (exercise == null) exercise = new Exercise();
		return exercise;
	}
	
	public void setShowBabystepsAlert(boolean showBabystepsAlert){
		this.showBabystepsAlert = showBabystepsAlert;
	}
	
	public boolean getShowBabystepsAlert(){
		return this.showBabystepsAlert;
	}
}
