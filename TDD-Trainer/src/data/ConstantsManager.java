package data;

public class ConstantsManager {
	private static Constants constants;
	
	public static void setConstants(Constants newConstants){
		constants = newConstants;
	}
	
	public static Constants getConstants(){
		return constants;
	}
	
	public static void newConstants(){
		constants = new Constants();
	}
}
