 import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pactutils.*;

public class PrintBooleanImage {
	static public void print(Pt[][] tab, String filePath) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(filePath));

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("chemin n'existe pas");
		}

		
		for (int x = 0; x < tab.length; x++) {
			for (int y = 0; y < tab[0].length; y++) {
				Color color;
				if (tab[x][y].isState())
					color = new Color(255, 255, 255);
				else
					color = new Color(0, 0, 0);

				int rgb = color.getRGB();
				img.setRGB(x * Selection.SIZEFACTOR, y * Selection.SIZEFACTOR,
						rgb);
			}

		}
		
		try {
			ImageIO.write(img, "png", new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
