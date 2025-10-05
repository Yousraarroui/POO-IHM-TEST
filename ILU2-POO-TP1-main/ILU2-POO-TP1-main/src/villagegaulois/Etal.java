package villagegaulois;

import personnages.Gaulois;
import villagegaulois.EtalNonOccupeException;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		//utilisée pour occuper un étal avec les informations d'un vendeur 
		//et des produits qu'il souhaite vendre
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	
	public String libererEtal() throws EtalNonOccupeException {
		//responsable de libérer l'étal, de calculer et d'afficher le nombre de produits vendus par le vendeur, 
		//ainsi que de définir l'état de l'étal comme libre (non occupé).
		if (!etalOccupe) {
	        // L'étal n'a pas été occupé, déclencher l'exception
	        throw new EtalNonOccupeException();
	    }
		etalOccupe = false;
		StringBuilder chaine = new StringBuilder(
				"Le vendeur " + vendeur.getNom() + " quitte son étal, ");
		int produitVendu = quantiteDebutMarche - quantite;
		if (produitVendu > 0) {
			chaine.append(
					"il a vendu " + produitVendu + " parmi " + produit + ".\n");
		} else {
			chaine.append("il n'a malheureusement rien vendu.\n");
		}
		return chaine.toString();
	}

	public String afficherEtal() {
		//utilisée pour afficher des informations sur l'étal, en particulier sur son contenu
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
		//permet à un Gaulois d'acheter un produit de l'étal
		 if (acheteur == null) {
		        throw new NullPointerException("L'acheteur ne peut pas être null.");
		    }
		 if (quantiteAcheter < 1) {
		        throw new IllegalArgumentException("La quantité doit être supérieure ou égale à 1.");
		    }
		 if (!etalOccupe) {
		        throw new IllegalStateException("L'étal n'est pas occupé, vous ne pouvez pas acheter.");
		    }
		StringBuilder chaine = new StringBuilder();
		chaine.append(acheteur.getNom() + " veut acheter " + quantiteAcheter
				+ " " + produit + " à " + vendeur.getNom());
		if (quantite == 0) {
			chaine.append(", malheureusement il n'y en a plus !");
			quantiteAcheter = 0;
		}
		if (quantiteAcheter > quantite) {
			chaine.append(", comme il n'y en a plus que " + quantite + ", "
					+ acheteur.getNom() + " vide l'étal de "
				+ vendeur.getNom() + ".\n");
			quantiteAcheter = quantite;
			quantite = 0;
		}
		if (quantite != 0) {
			quantite -= quantiteAcheter;
			chaine.append(". " + acheteur.getNom()
				+ ", est ravi de tout trouver sur l'étal de "
				+ vendeur.getNom() + "\n");
		}
		return chaine.toString();
	}

	public boolean contientProduit(String produit) {
		return this.produit.equals(produit);
	}
	

}
