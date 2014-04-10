package pactutils;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	BufferedImage img;
	
	public Image( BufferedImage img){
		this.img = img; 
	}
	
	//getteur pour avoir acces à l'image modifiée
	public BufferedImage getImg(){
		return img;
	}
	
	// renormalisation de l'image
	public void Normalize(){
		int Rm = 255, RM=0, Gm=255, GM=0, Bm=255, BM=0;
		for(int i=0; i<img.getWidth();i++){
			for(int j=0; j<img.getHeight();j++){
				Color c = new Color(img.getRGB(i, j));
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				if (r<Rm)
					r=Rm;
				if (r>RM)
					r=RM;
				if (b<Bm)
					b=Bm;
				if (b>BM)
					b=BM;
				if (g<Gm)
					g=Gm;
				if (g>GM)
					g=GM;
			}
			
			for(int k=0; k<img.getWidth();k++){
				for(int l=0; l<img.getHeight();l++){
					Color c = new Color(img.getRGB(k, l));
					int r = c.getRed();
					int g = c.getGreen();
					int b = c.getBlue();
					r= 255*(r-Rm)/(RM-Rm);
					g=255*(g-Gm)/(GM-Gm);
					b=255*(b-Bm)/(BM-Bm);
					Color newColor = new Color(r,g,b);
					int rgb= newColor.getRGB();
					img.setRGB(k,l, rgb);
				}
			}	
		}
	}
	
	
	
	// TSL pour les tests
	public void TSL(int x, int y) {
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
	
	// afficheur de couleur TSL
	public void afficheTSL(){
		System.out.println("points du pots");
		this.TSL(331,471);
		this.TSL(360,452);
		this.TSL(372,510);
		System.out.println("points de la plante");
		this.TSL(397,246);
		this.TSL(358,257);
		System.out.println("points du mur");
		this.TSL(571,501);
		System.out.println("points du pots qui n'ont bizarrement pas été sélectionnés");
		this.TSL(301,466);
		this.TSL(366,501);
		this.TSL(345,521);
	}
	
	// Creer un nouveau fichier representant la photo pour voir ce qui se passe
	public void Save(){
		
		File outputfile = new File("ImageNormalisee");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
