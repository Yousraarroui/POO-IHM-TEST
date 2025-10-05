package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import villagegaulois.Village;

class ControlTrouverEtalVendeurTest {
	private Village village;
    private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
    
    @BeforeEach
    public void initialiserSituation() {
        System.out.println("Initialisation...");
        village = new Village("le village des irreductibles ", 10, 5);
        controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
    }
	@Test
	void testControlTrouverEtalVendeur() {
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		assertNotNull(controlTrouverEtalVendeur, "Constructeur ne renvoie pas null");
	}

	@Test
	void testTrouverEtalVendeur() {
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		village.installerVendeur(village.trouverHabitant("Bohemine"), "Potion", 5);
        assertNotNull(controlTrouverEtalVendeur.trouverEtalVendeur("Bohemine"));
        assertNull(controlTrouverEtalVendeur.trouverEtalVendeur("Existe pas"));
	}

}
