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

	public void calculate() {
		/* Hier auf Grund der vorhanden Werte entscheiden
		 * welche Methode unten aufgerufen werden muss.
		 */
	}
	
	
	/* Hier die Methoden mit den Formlen hinzufügen
	 */
	
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
	 * Calculate U=P/I)
	 * @param p Power
	 * @param i Current
	 * @return  Tension
	 */
	private double uFromPandI(double p, double i) throws IllegalArgumentException {
		if (i==0.0)
		{
			throw new IllegalArgumentException("Argument 'Current' is 0");
		}
		return p/i;
	}
	
}
