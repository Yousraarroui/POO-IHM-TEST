package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import personnages.Gaulois;
import villagegaulois.Village;
class ControlPrendreEtalTest {
	private ControlVerifierIdentite controlVerifierIdentite;
	private Village village;
	@Test
	void testControlPrendreEtal() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertNotNull(controlPrendreEtal , "Constructeur ne renvoie pas null");
	}

	@Test
	void testResteEtals() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertTrue(controlPrendreEtal.resteEtals());
		village.installerVendeur(village.trouverHabitant("Bohemine"), "Potion", 5);
        assertFalse(controlPrendreEtal.resteEtals());
	}

	@Test
	void testPrendreEtal() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		int numeroEtal = controlPrendreEtal.prendreEtal("Bohemine", "Potion", 5);
	    assertTrue(numeroEtal >= 0 && numeroEtal < village.donnerNbEtal());
	}

	@Test
	void testVerifierIdentite() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertTrue(controlPrendreEtal.verifierIdentite("Bohemine"));
		assertFalse(controlPrendreEtal.verifierIdentite("Existe pas"));
	}

}
