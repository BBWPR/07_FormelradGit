package ch.bbw.pr.formelrad;

/**
 * Berechnet das Formelrad
 * @author Peter Rutschmann
 * @version 21.09.2018
 */
public class Calculator {
	private double leistung;
	private double spannung;
	private double strom;
	private double widerstand;
	
	public Calculator(double leistung, double spannung, double strom, double widerstand) {
		super();
		this.leistung = leistung;
		this.spannung = spannung;
		this.strom = strom;
		this.widerstand = widerstand;
	}
	
	public double getLeistung() {
		return leistung;
	}
	
	public double getSpannung() {
		return spannung;
	}

	public double getStrom() {
		return strom;
	}

	public double getWiderstand() {
		return widerstand;
	}

	@Override
	public String toString() {
		return "Calculator [leistung=" + leistung + 
				", spannung=" + spannung + 
				", strom=" + strom + 
				", widerstand="	+ widerstand + "]";
	}

	/**
	 * Calculate the missing values.
	 */
	public void calculate() {
		if(leistung != 0.0) {
			if (spannung != 0.0) {
				strom = iFromPandU(leistung, spannung);
				widerstand = rFromPandU(leistung, spannung);
			} else if(strom != 0.0) {
				spannung = uFromPandI(leistung, strom);
				widerstand = rFromPandI(leistung, strom);
			} else if(widerstand != 0.0) {
				spannung = uFromPandR(leistung, widerstand);
				strom = iFromPandR(leistung, widerstand);
			} //else do nothing
		} else if (spannung != 0.0) {
			if (strom != 0.0) {
				leistung = pFromUandI(spannung, strom);
				widerstand = rFromUandI(spannung, strom);
			}else if (widerstand != 0.0) {
				leistung = pFromUandR(spannung, widerstand);
				strom = iFromUandR(spannung, widerstand);
			} //else do nothing
		} else if (strom != 0.0) {
			if(widerstand != 0.0) {
				leistung = pFromIandR(strom, widerstand);
				spannung = uFromIandR(strom, widerstand);
			} //else do nothing
		}
	}
	
	/**
	 * Calculate Power form Tension and Current
	 * @param u
	 * @param i
	 * @return
	 */
	public double pFromUandI(double u, double i) {
		return u*i;
	}
	
	/**
	 * Calculate Power form Current and Resistence
	 * @param i
	 * @param r
	 * @return
	 */
	public double pFromIandR(double i, double r) {
		return r*i*i;
	}

	/**
	 * Calculate Power form Tension and Resistence
	 * @param u
	 * @param r
	 * @return
	 * @throws IllegalArgumentException
	 */
	public double pFromUandR(double u, double r) throws IllegalArgumentException {
		if(r==0) {
			throw new IllegalArgumentException("Resistence is 0.");
		}
		return u*u/r;
	}
	
	/**
	 * Calculate U=R*I
	 * @param i Current
	 * @param r Resistance
	 * @return  Tension
	 */
	private double uFromIandR(double i, double r) {
		return i*r;
	}

	/**
	 * Calculate U=P/I
	 * @param p Power
	 * @param i Current
	 * @return  Tension
	 */
	private double uFromPandI(double p, double i) throws IllegalArgumentException {
		if (i==0.0){
			throw new IllegalArgumentException("Argument 'Current' is 0");
		}
		return p/i;
	}
	
	/**
	 * Calculate U=SQRT(P*R)
	 * @param p Power
	 * @param r Resistance
	 * @return  Tension
	 */
	private double uFromPandR(double p, double r) throws IllegalArgumentException {
		if ((p*r)<0){
			throw new IllegalArgumentException("Argument 'Power * Resistance' is < 0");
		}
		return Math.sqrt(p*r);
	}

	/**
	 * Calculate I=P/U
	 * @param p Power
	 * @param u Tension
	 * @return  Current
	 * @throws IllegalArgumentException
	 */
	private double iFromPandU(double p, double u) throws IllegalArgumentException {
		if (u==0.0){
			throw new IllegalArgumentException("Argument 'Tension' is 0");
		}
		return p/u;
	}

	/**
	 * Calcluate I=SQRT(P/R)
	 * @param p Power
	 * @param r Resistance
	 * @return  Current
	 * @throws IllegalArgumentException
	 */
	private double iFromPandR(double p, double r) throws IllegalArgumentException {
		if (r==0.0){
			throw new IllegalArgumentException("Argument 'Resistance' is 0");
		} else if ((p/r)<0.0){
			throw new IllegalArgumentException("Argument 'Power/Resistance' is < 0");
		}
		return Math.sqrt(p/r);
	}
	
	/**
	 * Calculate I=U/R
	 * @param u Tension
	 * @param r Resistance
	 * @return  Current
	 * @throws IllegalArgumentException
	 */
	private double iFromUandR(double u, double r) throws IllegalArgumentException {
		if (r==0.0){
			throw new IllegalArgumentException("Argument 'Resistance' is 0");
		}
		return u/r;
	}

	/**
	 * Calculate R=U/I
	 * @param u Power
	 * @param i Current
	 * @return  Resistance
	 * @throws IllegalArgumentException
	 */
	private double rFromUandI(double u, double i) throws IllegalArgumentException {
		if (i==0.0){
			throw new IllegalArgumentException("Argument 'Current' is 0");
		}
		return u/i;
	}
	
	/**
	 * Calculate R=P/I^2
	 * @param p Power
	 * @param i Current
	 * @return  Resitance
	 * @throws IllegalArgumentException
	 */
	private double rFromPandI(double p, double i) throws IllegalArgumentException {
		if (i==0.0){
			throw new IllegalArgumentException("Argument 'Current' is 0");
		}
		return p/i/i;
	}
	
	/**
	 * Calculate R=U*U/P
	 * @param p Power
	 * @param u Tension
	 * @return  Resistance
	 * @throws IllegalArgumentException
	 */
	private double rFromPandU(double p, double u) throws IllegalArgumentException {
		if (p==0.0){
			throw new IllegalArgumentException("Argument 'Power' is 0");
		}
		return u*u/p;
	}
}
