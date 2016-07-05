package data;

public class Constants {
	private Project project;
	private boolean showBabystepsAlert;
	private boolean loadExercise = false;
	
	public Constants(){
		project = new Project();
		showBabystepsAlert = false;
	}
	
	public void setProject(Project newProject){
		project = newProject;
	}
	
	public Project getProject(){
		if (project == null) project = new Project();
		return project;
	}
	
	public void setShowBabystepsAlert(boolean showBabystepsAlert){
		this.showBabystepsAlert = showBabystepsAlert;
	}
	
	public boolean getShowBabystepsAlert(){
		return this.showBabystepsAlert;
	}
	
	public void setLoadExercise(boolean loadExercise){
		this.loadExercise = loadExercise;
	}
	
	public boolean getLoadExercise(){
		return loadExercise;
	}
}
