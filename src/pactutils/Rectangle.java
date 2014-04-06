package pactutils;
import java.awt.Point;
import java.io.Serializable;

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
