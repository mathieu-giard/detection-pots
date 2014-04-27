import java.util.ArrayList;

import pactutils.*;

public class Signe {
	
	static public ArrayList<Signature> signe(ArrayList<ArrayList<Pt>> compoCo) {
		ArrayList<Signature> retour = new ArrayList<Signature>();
		for (ArrayList<Pt> composante : compoCo) {
			int size = composante.size();

			// get centre

			int sommeX = 0, sommeY = 0;
			for (Pt pt : composante) {
				sommeX += pt.getX();
				sommeY += pt.getY();
			}
			int xC = sommeX / size, yC = sommeY / size;

			// set signature
			Signature signature = new Signature();
			for (Pt pt : composante)
				signature.add(pt.getX(), pt.getY(), xC, yC);

			retour.add(signature);
		}
		return retour;
	}
}
