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

public class MainAnatole {
	private static Seuil seuil = null;

	public static void main(String[] args) {
		
		if(args[1]=="jaune")
			seuil = new SeuilJaune();
		
		if (args.length > 0)
			mainAlgo(args[0]);
		else
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
		image.Normalize();
		img = image.getImg();

		if (img != null) {
			// faire la segmentation
			Pt[][] tab;
			if (seuil == null)
				tab = Selection.selec(img, 0.05, 0.2, 0.20, 0.45, 0.45, 0.8);
			else
				tab = Selection.selec(img, seuil);
			
			// faire les composantes connexes
			CompoConnexe<Pt> cc = new CompoConnexe<Pt>(tab);
			ArrayList<ArrayList<Pt>> compoCo = cc.getCompo();

			// faire la signature
			ArrayList<Signature> signatures = Signe.signe(compoCo);

			// trouver les coefficients de corrélation
			CompareSig cs = new CompareSig(signatures);
			ArrayList<Double> coefficients = cs.compare();

			// selectionner et en déduire le rectangle correspondant
			ArrayList<Rectangle> Rec = cs.EstSelec(coefficients, 12, compoCo);
		}
	}
}
