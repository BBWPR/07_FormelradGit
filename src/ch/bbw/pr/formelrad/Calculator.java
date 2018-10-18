package ch.bbw.pr.formelrad;

/**
 * Berechnet das Formelrad
 * @author Peter Rutschmann
 * @version 18.10.2018
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

	public void calculate() {
		/* Hier auf Grund der vorhanden Werte entscheiden
		 * welche Methode unten aufgerufen werden muss.
		 */
	}
	
	/* Hier die Methoden mit den Formlen hinzufügen
	 */
	
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
}
