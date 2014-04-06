import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import compo_connexe.CompoConnexe;
import pactutils.*;

public class MainAlgo {
	private BufferedImage img;

	public MainAlgo(String path) {
		try {
			img = ImageIO.read(new File(path));

		} catch (IOException e) {
			e.printStackTrace();
		}

		Pt[][] tab = Selection.selec(img, 0D, 1D, 0D, 1D, 0D, 1D);

		CompoConnexe<Pt> cc = new CompoConnexe<Pt>(tab);
		ArrayList<ArrayList<Pt>> compoCo = cc.getCompo();

		ArrayList<Signature> signatures = Signe.signe(compoCo);

		CompareSig cs = new CompareSig(signatures);
		ArrayList<Double> coefficients = cs.compare();

		System.out.println(coefficients);
	}
}
