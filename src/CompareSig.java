import java.util.ArrayList;

import pactutils.Pt;
import pactutils.Rectangle;
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
			//signature.normalise();
	double coefDiff = 0;
			for (int k = 0; k < 360; k++) {
				
				double thisDiff = signature.compareSig(temoin, k);  
				coefDiff = Math.max(coefDiff, thisDiff);
				}	
			
			retour.add(coefDiff);
			
		}

		return retour;
	}
	
	public ArrayList<Rectangle> EstSelec(ArrayList<Double> list ,int seuil, ArrayList<ArrayList<Pt>> compoCo){
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		int i =0;
		for (double d : list){
			if ( d<seuil){ // bizarrement il faut mettre inf
				Rectangle R = new Rectangle(compoCo.get(i));
				result.add(R);
			}
			i=i+1;
		}
		
		return result;
	}
	
	public int NumDuMaxDesCoef( ArrayList<Double> coefficients){
		int i = 0; int j=0;
		double d = 0;
		for ( Double coef : coefficients){
			if(coef>d){
				d=coef;
				j=i;
			}	
			i=i+1;
			
		}
		return j;
	}
	
	public int NumDuMinDesCoef( ArrayList<Double> coefficients){
		int i = 0; int j=0;
		double d = Double.MAX_VALUE;
		for ( Double coef : coefficients){
			if(coef<d){
				d=coef;
				j=i;
			}	
			i=i+1;
			
		}
		return j;
	}
	
	
}
