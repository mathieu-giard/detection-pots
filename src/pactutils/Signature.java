package pactutils;

public class Signature {
	protected double signature[];

	public Signature() {
		signature = new double[360];
		for (int i = 0; i < 360; i++) {
			signature[i] = 0;
		}
	}

	/*** creation de la signature **/

	public double get(int i) {
		return signature[(i+360) % 360];
	}

	public void add(int x, int y, int xC, int yC) {
		// get theta
		int dx = x - xC, dy = y - yC;
		double rho = Math.sqrt(dx * dx + dy * dy);
		double theta = 2 * Math.atan(dy / (dx + rho));
		theta = Math.toDegrees(theta);
		int i = ((int) (theta - 0.5) + 1) % 360;

		// set rho
		i = (i+360) % 360;
		if (rho > signature[i])
			signature[i] = rho;
	}

	/**** evaluer la signature ****/
	public void normalise() {
		// get rhoMoyen
		double rhoMoyen = 0;
		for (double rho : this.signature)
			rhoMoyen += rho;
		rhoMoyen = rhoMoyen / 360;

		// on normalise
		for (int theta = 0; theta < 360; theta++)
			signature[theta] = signature[theta] / rhoMoyen;
	}

	public double compareTo(Signature sig, int k) {
		double retour = 0;
		for (int theta = 0; theta < 360; theta++)
			retour += (this.get(theta + k) - sig.get(theta))*(this.get(theta + k) - sig.get(theta)) ;
		return retour / 360;
	}

}
