package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import villagegaulois.Village;

class ControlAfficherMarcheTest {
	private Village village;
    private ControlAfficherMarche controlAfficherMarche;
    
    @BeforeEach
    public void initialiserSituation() {
        System.out.println("Initialisation...");
        village = new Village("le village des irreductibles ", 10, 5);
        controlAfficherMarche = new ControlAfficherMarche(village);
    }
	@Test
	void testControlAfficherMarche() {
		assertNotNull(controlAfficherMarche, "Constructeur ne renvoie pas null");
	}

	@Test
	void testDonnerInfosMarche() {
		village.installerVendeur(village.trouverHabitant("Bohemine"), "Potion", 5);
        String[] infosMarche = controlAfficherMarche.donnerInfosMarche();
        assertNotNull(infosMarche);
        assertEquals(3, infosMarche.length); 
        assertEquals("Bohemine", infosMarche[0]); 
        assertEquals("5", infosMarche[1]); 
        assertEquals("Potion", infosMarche[2]);

	}
}
