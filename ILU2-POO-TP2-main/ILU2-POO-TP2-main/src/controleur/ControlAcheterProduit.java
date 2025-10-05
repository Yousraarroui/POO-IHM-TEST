package controleur;

import personnages.Gaulois;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public void acheterProduit(String nomAcheteur, String produit) {
		if(controlVerifierIdentite.verifierIdentite(nomAcheteur)) {
			Gaulois acheteur = village.trouverHabitant(nomAcheteur);
			Gaulois[] vendeurs = village.rechercherVendeursProduit(produit);
			
			if(vendeurs != null && vendeurs.length > 0) {
				System.out.println("Chez quel commerçant voulez-vous acheter des " + produit + "?");
			}
			
			for (int i = 0; i < vendeurs.length; i++) {
                System.out.println((i + 1) + " - " + vendeurs[i].getNom());
            }
		}
	}
}
