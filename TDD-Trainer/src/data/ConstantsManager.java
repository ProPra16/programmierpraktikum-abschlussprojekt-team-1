package data;

/**
 * Der ConstantsManager verwaltet eine Constans-Objekt.
 */
public class ConstantsManager {
	private static Constants constants;
	
	public static void setConstants(Constants newConstants){
		constants = newConstants;
	}
	
	public static Constants getConstants(){
		if (constants == null) newConstants();
		return constants;
	}
	
	public static void newConstants(){
		constants = new Constants();
	}
}
