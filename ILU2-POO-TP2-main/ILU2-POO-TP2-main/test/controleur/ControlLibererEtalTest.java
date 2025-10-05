package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import villagegaulois.Village;

class ControlLibererEtalTest {
	private Village village;
    private ControlLibererEtal controlLibererEtal;
    private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
    
    @BeforeEach
    public void initialiserSituation() {
        System.out.println("Initialisation...");
        village = new Village("le village des irreductibles ", 10, 5);
        controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
        controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
    }
    
	@Test
	void testControlLibererEtal() {
		assertNotNull(controlLibererEtal, "Constructeur ne renvoie pas null");
	}

	@Test
	void testIsVendeur() {
		village.installerVendeur(village.trouverHabitant("Bohemine"), "Potion", 5);
        assertTrue(controlLibererEtal.isVendeur("Bohemine"));
        assertFalse(controlLibererEtal.isVendeur("Existe pas"));
	}

	@Test
	void testLibererEtal() {
		village.installerVendeur(village.trouverHabitant("Bohemine"), "Potion", 5);
		String[] donneesEtal = controlLibererEtal.libererEtal("Bohemine");
		assertNotNull(donneesEtal);
		assertEquals(5, Integer.parseInt(donneesEtal[3])); 
        assertEquals(0, Integer.parseInt(donneesEtal[4])); 
	}

}
