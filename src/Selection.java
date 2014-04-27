import java.awt.Color;
import java.awt.image.BufferedImage;

import pactutils.Pt;
import seuils.Seuil;

public class Selection {

	public static int SIZEFACTOR = 1;

	static public Pt[][] selec(BufferedImage img, Seuil seuil) {
		return Selection.selec(img, seuil.getSeuilTinf(), seuil.getSeuilTsup(),
				seuil.getSeuilSinf(), seuil.getSeuilSsup(),
				seuil.getSeuilLinf(), seuil.getSeuilLsup());
	}

	static public Pt[][] selec(BufferedImage img, double seuilTinf,
			double seuilTsup, double seuilSinf, double seuilSsup,
			double seuilLinf, double seuilLsup) {
		// compteur pour les tests
		int compteur = 0;

		Pt retour[][] = new Pt[img.getWidth() / SIZEFACTOR][img.getHeight()
				/ SIZEFACTOR];

		for (int x = 0; x < img.getWidth() / SIZEFACTOR; x++) {
			for (int y = 0; y < img.getHeight() / SIZEFACTOR; y++) {// parcourir
																	// l'image
				retour[x][y] = new Pt(x, y, false);

				Color c = new Color(img.getRGB(x * SIZEFACTOR, y * SIZEFACTOR));// prendre
																				// des
																				// valeurs
																				// RGB
				// de chaque pixel
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();

				float[] hsb = Color.RGBtoHSB(r, g, b, null);

				boolean testT = hsb[0] > seuilTinf && hsb[0] < seuilTsup;
				boolean testS = hsb[1] > seuilSinf && hsb[1] < seuilSsup;
				boolean testL = hsb[2] > seuilLinf && hsb[2] < seuilLsup;

				if (testT && testS && testL) {
					retour[x][y].setState(true);
					// pour les tests
					compteur++;

					Color color = new Color(0, 0, 0);
					int rgb = color.getRGB();
					img.setRGB(x * SIZEFACTOR, y * SIZEFACTOR, rgb);
				}
			}
		}
		System.out.println(compteur);
		return retour;
	}
}
