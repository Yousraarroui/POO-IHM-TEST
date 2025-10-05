package frontiere;

import java.util.Scanner;

import controleur.ControlPrendreEtal;

public class BoundaryPrendreEtal {
	private ControlPrendreEtal controlPrendreEtal;
	private Scanner scan = new Scanner(System.in);

	public BoundaryPrendreEtal(ControlPrendreEtal controlChercherEtal) {
		this.controlPrendreEtal = controlChercherEtal;
	}

	public void prendreEtal(String nomVendeur) {
		boolean nomVendeurConnu = controlPrendreEtal.verifierIdentite(nomVendeur);
		
		if (!nomVendeurConnu) {
			System.out.println("Je suis désolé " + nomVendeur + " mais il faut etre un habitant de notre village pour commercer ici.");
		}
		else {
			System.out.println("Bonjour " + nomVendeur + " , je vais regarder si je peux vous trouver un etal.");
			boolean etalDisponible = controlPrendreEtal.resteEtals();
			if (!etalDisponible) {
				System.out.println("Désolée " + nomVendeur + " je n'ai plus d'etal qui ne soit pas deja occupe.");
			}
			else {
				installerVendeur(nomVendeur);
			}
		}
	}

	private void installerVendeur(String nomVendeur) {
		System.out.println("C'est parfait, il me reste etal pour vous !");
		System.out.println("Il me faudrait quelques renseignements :");
		System.out.println("Quel produit souhaitez-vous vendre ?");
		String produit = scan.nextLine();
		System.out.println("Combien souhaitez-vous en vendre ? ");
		int nbProduit = scan.nextInt();
		int numeroEtal = controlPrendreEtal.prendreEtal(nomVendeur, produit, nbProduit);
		if (numeroEtal != -1) {
			System.out.println("Le vendeur " + nomVendeur + " s'est installe a l'etal n° " + numeroEtal);
		}
	}
}
