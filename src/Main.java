import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import pactutils.Pt;
import pactutils.Pt;
import pactutils.Signature;
import compo_connexe.CompoConnexe;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		mainAlgo("img-carre2.png");
	}

	static private void mainAlgo(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// POUR LES TEST
			System.out.println("points du pots");
			TSL(img,331,471);
			TSL(img,360,452);
			TSL(img,372,510);
			System.out.println("points de la plante");
			TSL(img,397,246);
			TSL(img,358,257);
			System.out.println("points du mur");
			TSL(img,571,501);
			System.out.println("points du pots qui n'ont bizarrement pas �t� s�lectionn�s");
			TSL(img,301,466);
			TSL(img,366,501);
			TSL(img,345,521);
			
			
		
		
		
		if (img != null) {
			Pt[][] tab = Selection.selec(img, 0.88, 1.0, 0.05, 0.4, 0.20, 0.5);

			//ecrire image booleenne
			PrintBooleanImage.print(tab, "imBool.png");
			
			CompoConnexe<Pt> cc = new CompoConnexe<Pt>(tab);
			ArrayList<ArrayList<Pt>> compoCo = cc.getCompo();

			// pour les test
			colorCompo(img, compoCo, path);

			ArrayList<Signature> signatures = Signe.signe(compoCo);

			CompareSig cs = new CompareSig(signatures);
			ArrayList<Double> coefficients = cs.compare();

			System.out.println(coefficients);
			
			
		}
	}

	// pour les test
	static private void colorCompo(BufferedImage img,
			ArrayList<ArrayList<Pt>> compoCo, String path) {
		int size = compoCo.size();

		for (ArrayList<Pt> alpt : compoCo) {
			int k = compoCo.indexOf(alpt);
			Color color = new Color(k * 20 % 255,
					125 + (-1) ^ k * 10 * k % 255, 255 - 20 * k % 255);
			int rgb = color.getRGB();
			for (Pt pt : alpt)
				img.setRGB(pt.getX() * Selection.SIZEFACTOR, pt.getY() * Selection.SIZEFACTOR, rgb);

			File outputfile = new File("CompoCoDeTest");
			try {
				ImageIO.write(img, "png", outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static public void TSL(BufferedImage img, int x, int y) {
		Color c = new Color(img.getRGB(x, y));// prendre des valeurs RGB
		// de chaque pixel
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();

		float[] hsb = Color.RGBtoHSB(r, g, b, null);
		System.out.println("La teinte est : " + hsb[0]
				+ ", et la saturation est : " + hsb[1]
				+ ", et la luminance est : " + hsb[2]);
	}

}
