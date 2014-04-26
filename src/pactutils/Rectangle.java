package pactutils;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

public class Rectangle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1975892177586901924L;
	Point p1;
	Point p2;
	Point p3;
	Point p4;
	String nom;

	public Rectangle(Point p1, Point p2, Point p3, Point p4, String nom){
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.nom = nom;
	}
	
	public Rectangle(ArrayList<Pt> list){
		int xmax =0; int xinf=Integer.MAX_VALUE; int yinf=Integer.MAX_VALUE; 
		for (Pt pt: list){
			int x = pt.getX();
			int y = pt.getY();
			if(x<xinf)
				xinf=x;
			if (x>xmax)
				xmax=x;
			if(y<yinf)
				yinf=y;
		}
		this.p1= new Point(xinf,0);
		this.p2= new Point(xmax,0);
		this.p3= new Point(xinf,yinf);
		this.p4= new Point(xmax,yinf);
		
	}
	

	public Point getP1(){
		return p1;
	}

	public Point getP2(){
		return p2;
	}
	public Point getP3(){
		return p3;
	}
	public Point getP4(){
		return p4;
	}
	
	public String getNom(){
		return nom;
	}
	
	public void setNom(String value){
		this.nom = value;
	}

}

