import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
		
		ArrayList<Rectangle> rec = null;
		if (args.length > 0)
			rec = mainAlgo(args[0], args[1]);
		else
			rec = mainAlgo("img-patchs2.png", args[1]);
		
		serialOutput(args[2], rec);
		
		
	}
	
	static private void serialOutput(String baseName, ArrayList<Rectangle> towrite){
		ObjectOutputStream write;
		try {
			write = new ObjectOutputStream(new FileOutputStream(baseName));
			write.writeObject(towrite);
			write.flush();
			write.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static private ArrayList<Rectangle> mainAlgo(String path, String couleurSticker) {
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
		image.Save();

		if (img != null) {
			// faire la segmentation
			Pt[][] tab;
			if (couleurSticker.equals("1"))
				tab = Selection.selec(img, 0.05, 0.2, 0.14, 0.45, 0.30, 0.8);
			else if(couleurSticker.equals("2")){
				tab = Selection.selec(img, 0.2, 1.0, 0.15, 1.01, 0.01, 0.3);
			}
			else
				tab = Selection.selec(img, seuil);
			
			// faire les composantes connexes
			CompoConnexe<Pt> cc = new CompoConnexe<Pt>(tab);
			ArrayList<ArrayList<Pt>> compoCo = cc.getCompo();

			// faire la signature
			ArrayList<Signature> signatures = Signe.signe(compoCo);

			// trouver les coefficients de corr�lation
			CompareSig cs = new CompareSig(signatures);
			ArrayList<Double> coefficients = cs.compare();

			// selectionner et en d�duire le rectangle correspondant
			ArrayList<Rectangle> Rec = cs.EstSelec(coefficients, 12, compoCo, couleurSticker);
			return Rec;
		}
		return new ArrayList<Rectangle>();
	}
}
