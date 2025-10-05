package villagegaulois;

public class EtalNonOccupeException extends Exception {
	public EtalNonOccupeException() {
	    super("L'étal ne peut pas être libéré car il n'a pas été occupé.");
	}

}
