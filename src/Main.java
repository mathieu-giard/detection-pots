import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import pactutils.Image;
import pactutils.Pt;
import pactutils.Rectangle;
import pactutils.Signature;
import seuils.*;

import compo_connexe.CompoConnexe;

public class Main {
	private static Seuil seuil = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// mainAlgo("img-carre2.png");
		

		mainAlgo("img-patchs2.png");

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
		System.out.println("Début de normalisation");
		image.Normalize();
		System.out.println("Début de sauvegarde");
		image.Save();
		img = image.getImg();
		image.afficheTSL();

		
		if (img != null) {
			// faire la segmentation
			System.out.println("début de segmentation");
			Pt[][] tab;
			if (seuil == null)
				tab = Selection.selec(img, 0.85, 1, 0.05, 0.25, 0.45, 0.8);
			else
				tab = Selection.selec(img, seuil);
			// ecrire image booleenne
			PrintBooleanImage.print(tab, "imBool.png");

			// faire les composantes connexes
			System.out.println("Début de composantes connexes");
			CompoConnexe<Pt> cc = new CompoConnexe<Pt>(tab);
			ArrayList<ArrayList<Pt>> compoCo = cc.getCompo();

			// pour les test
			colorCompo(img, compoCo, path);

			
			// faire la signature
			System.out.println("Début signature");
			ArrayList<Signature> signatures = Signe.signe(compoCo);

			// trouver les coefficients de corrélation
			CompareSig cs = new CompareSig(signatures);
			ArrayList<Double> coefficients = cs.compare();

			System.out.println(coefficients);

			// selectionner et en déduire le rectangle correspondant
			ArrayList<Rectangle> Rec = cs.EstSelec(coefficients, 12, compoCo);

			// on verifie visuellement que ce soit le bon:
			image.DessinerLeSRectangleS(Rec);
			image.SaveImFinale();
		 
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
				img.setRGB(pt.getX() * Selection.SIZEFACTOR, pt.getY()
						* Selection.SIZEFACTOR, rgb);

			File outputfile = new File("CompoCoDeTest");
			try {
				ImageIO.write(img, "png", outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
