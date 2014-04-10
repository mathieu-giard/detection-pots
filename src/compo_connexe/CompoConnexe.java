package compo_connexe;

import java.util.ArrayList;

public class CompoConnexe<T extends Cross> {

	ArrayList<ArrayList<Cross>> retour;
	Cross[][] tab;

	public CompoConnexe(Cross[][] tab) {
		this.tab = tab;
	}

	public CompoConnexe(ArrayList<Cross> list, int maxX, int maxY) {
		Cross tab[][] = new Cross[maxX][maxY];
		for (Cross cross : list) {
			tab[cross.getX()][cross.getY()] = cross;
		}
		
	}

	public ArrayList<ArrayList<T>> getCompo() {
		int maxY = tab[0].length;
		int maxX = tab.length;

		ListeEqui listeEqui = new ListeEqui();

		int maxNb = 0;
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				Cross cross = tab[x][y];
				if (cross.isState()) {
					cross.setNb(maxNb);

					ArrayList<Cross> voisins = cross.getVoisin(tab);
					if (!voisins.isEmpty()) {
						cross.setNb(voisins.get(0).getNb());
						ArrayList<Integer> aAjouter = new ArrayList<Integer>();
						for (Cross voisin : voisins)
							aAjouter.add(voisin.getNb());
						listeEqui.add(aAjouter);
					} else {
						cross.setNb(maxNb);
						ArrayList<Integer> aAjouter = new ArrayList<Integer>();
						aAjouter.add(maxNb);
						listeEqui.add(aAjouter);
						maxNb++;
					}
				}
			}
		}

		ArrayList<ArrayList<T>> retourGeant = new ArrayList<ArrayList<T>>();
		int sizeRetour = listeEqui.size();
		for (int i = 0; i < sizeRetour; i++)
			retourGeant.add(new ArrayList<T>());

		// ENLEVER LE COMPO DE TROP PETITE TAILLE

		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				Cross cross = tab[x][y];
				if (cross.getNb() != -1) {
					int nbListe = listeEqui.getNumeroListe(cross.getNb());
					retourGeant.get(nbListe).add((T) cross);
				}
			}
		}

		ArrayList<ArrayList<T>> retourNain = new ArrayList<ArrayList<T>>();
		for (ArrayList<T> alt : retourGeant) {
			if (alt.size() > 1000)
				retourNain.add(alt);
		}

		return retourNain;
	}
}
