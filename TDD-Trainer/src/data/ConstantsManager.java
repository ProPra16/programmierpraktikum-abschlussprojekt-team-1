package data;

/**
 * Der ConstantsManager ist eine statische Schnittstelle zum aktuell
 * verwendeten {@link Constants}-Objekt, welches die aktuellen Daten
 * zur Laufzeit des Programmes bereithält.
 * 
 * @author Daniela Prigge, Lukas Rose, Rebecca Wagner
 */
public class ConstantsManager {
	/** Das von dieser Klasse verwaltete {@link Constants}-Objekt */
	private static Constants constants;
	
	/**
	 * Ein setter, um neue {@link Constants} zu verwenden.
	 * @param newConstants Die neuen {@link Constants}.
	 */
	public static void setConstants(Constants newConstants){
		constants = newConstants;
	}
	
	/**
	 * Ein Getter, um die aktuellen {@link Constants} abzufragen. Er
	 * wird verwendet, um auf die Daten zuzugreifen.
	 * @return Die aktuellen {@link Constants}.
	 */
	public static Constants getConstants(){
		if (constants == null) newConstants();
		return constants;
	}
	
	/**
	 * Mit dieser Methode lässt sich der Zustands der {@link Constants}
	 * zurücksetzen. Die aktuell verwendeten {@link Constants} werden
	 * mit neuen {@link Constans} initialisiert.
	 */
 	public static void newConstants(){
		constants = new Constants();
	}
}
