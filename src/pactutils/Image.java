package pactutils;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
		int Rm = 254, RM=1, Gm=254, GM=1, Bm=254, BM=1;
		for(int i=0; i<img.getWidth();i++){
			for(int j=0; j<img.getHeight();j++){
				Color c = new Color(img.getRGB(i, j));
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				if (r<Rm)
					Rm=r;
				if (r>RM)
					RM=r;
				if (b<Bm)
					Bm=b;
				if (b>BM)
					BM=b;
				if (g<Gm)
					Gm=g;
				if (g>GM)
					GM=g;
			}
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
		this.TSL(1635,1355);
		this.TSL(1739,1391);
		this.TSL(449,1493);
		System.out.println("points de la plante");
		this.TSL(989,1171);
		this.TSL(929,927);
		System.out.println("points du mur");
		this.TSL(703,1409);
		/*System.out.println("points du pots qui n'ont bizarrement pas été sélectionnés");
		this.TSL(301,466);
		this.TSL(366,501);
		this.TSL(345,521);*/
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
	
	public void SaveImFinale(){
		
		File outputfile = new File("ImageFinale");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public void DessinerLeRectangle(Rectangle Rec){
		int xmin = (int) Rec.getP3().getX();
		int xmax = (int) Rec.getP4().getX();
		int yinf = (int) Rec.getP3().getY();
		Color color= new Color(255,0,0);
		int rgb = color.getRGB();
		int delta = xmax - xmin;
		xmin = (int) xmin - delta / 2;
		int xgch = Math.max(xmin, 0);
		xmax = (int) xmax + delta / 2;
		int xim = img.getWidth() - 1;
		int xdt = Math.min(xmax, xim);
		for (int i = xgch; i< xdt;i++){
			img.setRGB(i, yinf, rgb);
		}
		for (int j = 0; j<yinf;j++){
			img.setRGB(xgch, j, rgb);
			img.setRGB(xdt, j, rgb);
		}
	}
	
	public void DessinerLeSRectangleS(ArrayList<Rectangle> list){
		for (Rectangle rec : list){
			this.DessinerLeRectangle(rec);
		}
	}

}
