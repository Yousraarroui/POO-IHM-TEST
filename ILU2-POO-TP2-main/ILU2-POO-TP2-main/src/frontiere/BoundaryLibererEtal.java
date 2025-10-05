package frontiere;
import villagegaulois.Etal;
import controleur.ControlLibererEtal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		boolean vendeurReconnu = controlLibererEtal.isVendeur(nomVendeur);
		if (!vendeurReconnu) {
			System.out.println("Mais vous n'etes pas inscrit sur notre marche aujourd'hui !");
		}
		else {
			String[] donneesEtal = controlLibererEtal.libererEtal(nomVendeur);
			boolean etalOccupe = Boolean.valueOf(donneesEtal[0]);
			if (etalOccupe) {
				String produit = donneesEtal[2];
				int quantiteInitiale = Integer.valueOf(donneesEtal[3]);
				int quantiteVendue = Integer.valueOf(donneesEtal[4]);
				System.out.println("Vous avez vendu " + quantiteVendue + " sur " + quantiteInitiale + " " + produit + ".");
				System.out.println("Au revoir " + nomVendeur + " , passez une bonne journée.");
			}
			
		}
	}

}
