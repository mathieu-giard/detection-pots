import java.awt.Color;
import java.awt.image.BufferedImage;
import pactutils.Pt;

public class Selection {
	static public Pt[][] selec(BufferedImage img, double seuilTinf, double seuilTsup,
			double seuilSinf, double seuilSsup, double seuilLinf,
			double seuilLsup) {
		Pt retour[][] = new Pt[img.getWidth()][img.getHeight()];

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				retour[x][y] = new Pt(x, y, false);
			}
		}
		
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {// parcourir l'image
				
				Color c = new Color(img.getRGB(x, y));// prendre des valeurs RGB
														// de chaque pixel
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();

				float[] hsb = Color.RGBtoHSB(r, g, b, null);

				boolean testT = hsb[0] > seuilTinf && hsb[0] < seuilTsup;
				boolean testS = true;//hsb[1] > seuilSinf && hsb[1] < seuilSsup;
				boolean testL = true;//hsb[2] > seuilLinf && hsb[2] < seuilLsup;

				if (testT && testS && testL)
					retour[x][y].setState(true);
			}
		}

		return retour;
	}
}
