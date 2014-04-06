package pactutils;

import java.util.ArrayList;

import compo_connexe.*;

public class Pt implements Cross {

	private int x, y;
	private int etiquette;
	private boolean state;

	public Pt(int x, int y, boolean state) {
		this.x = x;
		this.y = y;
		this.state = state;
		this.etiquette = -1;
	}
	

	/*************** PT = CROSS ********/

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	// A FAIRE
	public ArrayList<Cross> getVoisin(Cross[][] tab) {
		ArrayList<Cross> retour = new ArrayList<Cross>();
		if (x == 0 && y == 0)
			;
		else if (x == 0) {
			if (tab[x][y - 1].getNb() >= 0)
				retour.add(tab[x][y - 1]);
		} else if (y == 0) {
			if (tab[x - 1][y].getNb() >= 0)
				retour.add(tab[x - 1][y]);
		} else {
			if (tab[x][y - 1].getNb() >= 0)
				retour.add(tab[x][y - 1]);
			if (tab[x - 1][y].getNb() >= 0)
				retour.add(tab[x - 1][y]);
		}

		return retour;
	}

	public int getNb() {
		return this.etiquette;
	}

	public void setNb(int nb) {
		this.etiquette = nb;
	}
	
	
	/***** setter et getter ********/

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
