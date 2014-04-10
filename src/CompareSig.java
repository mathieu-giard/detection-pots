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
			double coefDiff2 = 0;
			for (int k = 0; k < 360; k++) {
				double thisDiff = signature.compareTo(temoin, k);
				if (thisDiff < coefDiff)
					coefDiff = thisDiff;
				double thisDiff2 = signature.compareSig(temoin, k);
				if(thisDiff2>coefDiff2){
					coefDiff2 = thisDiff2;
				}
			}
			retour.add(coefDiff);
			retour.add(coefDiff2);
		}

		return retour;
	}
}
