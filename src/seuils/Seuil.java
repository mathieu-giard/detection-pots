package seuils;

public abstract class Seuil {
	protected double seuilTinf, seuilTsup, seuilSinf, seuilSsup, seuilLinf,
			seuilLsup;

	public Seuil(double seuilTinf, double seuilTsup, double seuilSinf,
			double seuilSsup, double seuilLinf, double seuilLsup) {
		this.seuilLinf = seuilLinf;
		this.seuilTinf = seuilTinf;
		this.seuilSinf = seuilSinf;
		this.seuilLsup = seuilLsup;
		this.seuilTsup = seuilTsup;
		this.seuilSsup = seuilSsup;
	}

	public double getSeuilTinf() {
		return seuilTinf;
	}

	public double getSeuilTsup() {
		return seuilTsup;
	}

	public double getSeuilSinf() {
		return seuilSinf;
	}

	public double getSeuilSsup() {
		return seuilSsup;
	}

	public double getSeuilLinf() {
		return seuilLinf;
	}

	public double getSeuilLsup() {
		return seuilLsup;
	}

}
