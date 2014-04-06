import java.util.ArrayList;

import pactutils.Signature;
import pactutils.SignatureCarre;

public class CompareSig {
	private ArrayList<Signature> signatures;

	public CompareSig(ArrayList<Signature> signatures) {
		this.signatures = signatures;
	}

	public ArrayList<Double> compare() {
		ArrayList<Double> retour = new ArrayList<Double>();

		SignatureCarre temoin = new SignatureCarre();

		for (Signature signature : signatures) {
			signature.normalise();
			double coefDiff = Double.MAX_VALUE;
			for (int k = 0; k < 360; k++) {
				double thisDiff = signature.compareTo(temoin, k);
				if (thisDiff < coefDiff)
					coefDiff = thisDiff;
			}
			retour.add(coefDiff);
		}

		return retour;
	}
}
