package compo_connexe;
import java.util.ArrayList;

public class ListeEqui extends ArrayList<ArrayList<Integer>> {

	/************************ EXPLOITATION DES CLASSES D'EQUIVALENCE **********************/
	private ArrayList<Integer> listeDesMin = new ArrayList<Integer>();

	public void setMinNb(Cross cross) {
		// met la valeur d'numero minimum de la classe d'equivalence
		/*
		 * 1) on recupere le numero de la classe d'equivalence 2) on recupere
		 * l'numero minimum dans cette classe 3) on remplace l'numero
		 */

		int numero = cross.getNb();
		int num = this.getNumeroListe(numero);

		// debug
		if (num == -1)
			System.out.println("debug2");

		ArrayList<Integer> listeToGetMin = this.get(num);
		int minNb = Integer.MAX_VALUE;
		for (int i : listeToGetMin)
			if (i < minNb && i != -1)
				minNb = i;
		cross.setNb(minNb);

		// on prepare la liste pour la methode suivante "getMaxDesMin"
		if (!this.isIn(minNb, listeDesMin))
			listeDesMin.add(minNb);
	}

	public int getMaxDesMin() {
		int retour = Integer.MIN_VALUE;
		for (int i : listeDesMin)
			if (i > retour)
				retour = i;
		return retour;
	}

	/************************ MISE EN PLACE DES EQUIVALENCES **********************/

	/*
	 * cette classe doit prendre en entre une liste de nombre la comparer avec
	 * les listes existentes ajouter le nombre ET/OU fusionner des listes
	 */

	public boolean add(ArrayList<Integer> aAjouter) {
		aAjouter = this.traiter(aAjouter);
		int size = aAjouter.size();
		int numListe[] = new int[size];
		for (int i = 0; i < size; i++)
			numListe[i] = this.getNumeroListe(aAjouter.get(i));

		/*
		 * y a-t-il une liste contenant un de ces nombre? sont-ils tous dans une
		 * de ces liste?
		 */
		int howManyInListe = 0;
		boolean existInListe;
		boolean allInListe;
		boolean onlyOneInListe;
		for (int numero : numListe)
			if (numero != -1)
				howManyInListe++;
		if (howManyInListe == 0) {
			existInListe = false;
			allInListe = false;
			onlyOneInListe = false;
		} else if (howManyInListe == 1) {
			existInListe = true;
			allInListe = false;
			onlyOneInListe = true;
		} else if (howManyInListe > 1 && howManyInListe < size) {
			existInListe = true;
			allInListe = false;
			onlyOneInListe = false;
		} else if (howManyInListe == size) {
			existInListe = true;
			allInListe = true;
			onlyOneInListe = false;
		} else {
			System.out.println("PROBLEME1");
			existInListe = true;
			allInListe = true;
			onlyOneInListe = true;
		}

		if (!existInListe)
			return super.add(aAjouter);
		// fusion/ajout
		else if (onlyOneInListe) {
			int numAAjouter = -1;
			// on trouve ce num
			for (int i = 0; i < size; i++)
				if (numListe[i] != -1)
					numAAjouter = i;
			// et on ajoute
			for (int i = 0; i < size; i++)
				if (numListe[i] == -1)
					this.get(numAAjouter).add(aAjouter.get(i));
			return true;
		} else if (allInListe) {
			int min = this.getMin(numListe);
			for (int i = 0; i < size; i++) {
				if (numListe[i] != min) {
					ArrayList<Integer> aFusionner = this.get(numListe[i]);
					for (int j : aFusionner) {
						this.get(min).add(j);
					}
				}
			}
			while (howManyInListe != 1) {
				int max = this.getMax(numListe);
				if (max != min && max != Integer.MIN_VALUE)
					this.remove(max);
				howManyInListe--;
			}
			return true;
		} else if (existInListe && !allInListe && !onlyOneInListe) {
			/*
			 * c'est le cas plus compliquer: il faut fusionner ce qu'il faut et
			 * rajouter le reste
			 */
			// on fusionne ce qu'il faut
			int min = this.getMin(numListe);
			for (int i = 0; i < size; i++) {
				if (numListe[i] != min && numListe[i] != -1) {
					ArrayList<Integer> aFusionner = this.get(numListe[i]);
					for (int j : aFusionner) {
						this.get(min).add(j);
					}
				}
			}
			// on ajoute le reste
			for (int i = 0; i < size; i++) {
				if (numListe[i] == -1) {
					this.get(min).add(aAjouter.get(i));
				}
			}
			// et on supprime les listes que l'on a fusionner
			while (howManyInListe != 1) {
				int max = this.getMax(numListe);
				if (max != min && max != Integer.MIN_VALUE)
					this.remove(max);
				howManyInListe--;
			}
			return true;
		} else {
			System.out.println("PROBLEME2");
			return false;
		}
	}

	public boolean add(int aAjouter) {
		if (this.isEmpty()) {
			ArrayList<Integer> newAL = new ArrayList<Integer>();
			newAL.add(aAjouter);
			return super.add(newAL);
		} else {
			int numeroListe = this.getNumeroListe(aAjouter);
			if (numeroListe == -1) {
				ArrayList<Integer> newAL = new ArrayList<Integer>();
				newAL.add(aAjouter);
				return super.add(newAL);
			} else {
				return this.get(numeroListe).add(aAjouter);
			}
		}
	}

	/********** methode auxiliaire **********/
	public int getNumeroListe(int entree) {
		int retour = -1;

		int sizeListe = this.size();
		for (int numeroListe = 0; numeroListe < sizeListe; numeroListe++) {
			int size = this.get(numeroListe).size();
			for (int j = 0; j < size; j++) {
				if (entree == this.get(numeroListe).get(j))
					retour = numeroListe;
			}
		}
		return retour;
	}

	private ArrayList<Integer> traiter(ArrayList<Integer> aAjouter) {
		ArrayList<Integer> copie = new ArrayList<Integer>();
		for (int i : aAjouter)
			if (!this.isIn(i, copie))
				copie.add(i);
		return copie;
	}

	public boolean isIn(int i, ArrayList<Integer> copie) {
		boolean retour = false;
		for (int j : copie)
			if (j == i)
				retour = true;
		return retour;
	}

	private int getMin(int[] numListe) {
		int retour = Integer.MAX_VALUE;
		for (int i : numListe)
			if (i < retour && i != -1)
				retour = i;
		return retour;
	}

	private int getMax(int[] numListe) {
		int retour = Integer.MIN_VALUE;
		for (int i : numListe)
			if (i > retour && i != -1)
				retour = i;
		for (int i = 0; i < numListe.length; i++)
			if (retour == numListe[i])
				numListe[i] = -1;
		return retour;
	}

	public String toString() {
		String str = "";
		int i = 0;
		for (ArrayList<Integer> ali : this) {
			str += "Classe " + i + " contient: ";
			for (Integer integer : ali)
				str += integer + " ,";
			i++;
			str += "\n";
		}

		return str;
	}
}