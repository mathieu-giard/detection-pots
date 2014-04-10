import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import pactutils.Image;
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
		
		// On retravaille l'image avant le traitement
		Image image = new Image(img);
		System.out.println("D�but de normalisation");
		image.Normalize();
		System.out.println("D�but de sauvegarde");
		image.Save();
			
			
		
		/*
		
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
			// */
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


}
