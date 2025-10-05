package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		//a pour objectif d'ajouter un Gaulois à la liste des habitants du village. 
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		//a pour but de rechercher un Gaulois dans le village en fonction de son nom.
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;//le Gaulois recherché n'est pas présent dans le village.
	}

	public String afficherVillageois()throws VillageSansChefException{
		// a pour but d'afficher la liste des habitants du village
		if (chef == null) {
	        throw new VillageSansChefException();
	    }
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	// a) permet d'installer un vendeur Gaulois dans un étal libre du marché.
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit){
		int indiceEtalLibre = marche.trouverEtalLibre(); //rechercher un étal libre sur le marché
		if (indiceEtalLibre != -1) {
			marche.utiliserEtal(indiceEtalLibre, vendeur, produit, nbProduit);
			return vendeur.getNom() + " vend des " + produit + " à l'étal n° " + indiceEtalLibre + ".";
		}else {
			return vendeur.getNom() + " ne peut pas trouver un etal libre pour vendre des " + produit + ".";
			}
		}
	// b)permet de rechercher les vendeurs qui proposent un produit sur le marché.
	public String rechercherVendeursProduit(String produit) {
		Etal[] etals = marche.trouverEtals(produit); //rechercher tous les étals qui contiennent le produit spécifié
		StringBuilder chaine = new StringBuilder("Les vendeurs qui proposent des " + produit + " sont :\n");
		for (int i = 0; i < etals.length; i++) {
			Etal etal = etals[i];
			chaine.append("- " + etal.getVendeur().getNom() + "\n");
		}
		return chaine.toString();
	}
	// c)permet de rechercher l'étal associé à un vendeur 
	//(Gaulois) donné en utilisant la classe interne Marche.
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	// d)gère le départ d'un vendeur du marché en libérant l'étal associé au vendeur
	public String partirVendeur(Gaulois vendeur) throws EtalNonOccupeException {
		Etal etal = marche.trouverVendeur(vendeur);//recherche l'étal associé à ce vendeur.
		if (etal != null) {
			String result = etal.libererEtal();
			return result;
		}
		else {
			return vendeur.getNom() + " n'a pas trouvé d'étal à quitter.";
		}
	}
	// e)fait appel à la méthode afficherMarche de l'objet marche, 
	//puis renvoie la chaîne de caractères résultante.
	public String afficherMarche() {
		//chaîne de caractères qui représente l'état actuel du marché, 
		//avec des informations sur chaque étal et son occupant.
		return marche.afficherMarche();
	}
	
	public class Marche{
			private Etal[] etals;
		
			public Marche(int nombreEtals) {
				etals = new Etal[nombreEtals];
				for(int i = 0; i < nombreEtals; i++) {
					etals[i] = new Etal();
				}
			}
			
			//utilisée pour attribuer un étal à un 
			//vendeur lorsqu'il souhaite vendre des produits sur le marché du village. 
			
			public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
				if (indiceEtal >= 0 && indiceEtal< etals.length) {
					etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
				}
			}
			
			public int trouverEtalLibre() {
				//conçue pour trouver le premier étal libre (non occupé) dans le marché
				for (int i = 0; i < etals.length; i++) {
					if (!etals[i].isEtalOccupe()) {
						return i;
					}
				}
				return -1; // aucun etal n'est disponible
			}
			
			public Etal[] trouverEtals(String produit) {
				// permet de rechercher tous les étals qui contiennent un certain produit donné.
			    int count = 0;//détermine combien d'étals remplissent les critères spécifiés.
			    for (int i = 0; i < etals.length; i++) {
			        if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
			            count++;
			        }
			    }
			    
			    Etal[] result = new Etal[count];//creation d'un tableau de taille count
			    int resultIndex = 0;
			    
			    for (int i = 0; i < etals.length; i++) {
			        if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
			            result[resultIndex] = etals[i];
			            resultIndex++;
			        }
			    }
			    
			    return result;// retourne un tableau contenant tous les étals où l’on vend un produit.
			}
			
			public Etal trouverVendeur(Gaulois gaulois) {
				//recherche un étal occupé par un vendeur spécifique 
			    for (int i = 0; i < etals.length; i++) {
			        if (etals[i].isEtalOccupe() && etals[i].getVendeur() == gaulois) {
			            return etals[i];
			        }
			    }
			    return null;
			}

			
			public String afficherMarche() {
				//donne une vue d'ensemble de l'état actuel du marché.
			    StringBuilder chaine = new StringBuilder();
			    int nbEtalVide = 0;
			    
			    for (int i = 0; i < etals.length; i++) {
			        if (etals[i].isEtalOccupe()) {
			            chaine.append(etals[i].afficherEtal() + "\n");
			        } else {
			            nbEtalVide++;
			        }
			    }
			    
			    if (nbEtalVide > 0) {
			        chaine.append("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
			    }
			    
			    return chaine.toString();
			}
			
			
	}

}
