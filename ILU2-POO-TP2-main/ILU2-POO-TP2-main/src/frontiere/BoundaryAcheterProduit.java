package frontiere;

import java.util.Scanner;

import controleur.ControlAcheterProduit;
import villagegaulois.Village;

public class BoundaryAcheterProduit {
	private Village village;
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		StringBuilder question = new StringBuilder();
		question.append("Quel produit voulez-vous acheter ?");
		String produit = scan.nextLine();
		int nbProduitAcheter = 0;
		question.append("Chez quel commerçant voulez-vous acheter des " + produit + " ?");
		
	}
}
