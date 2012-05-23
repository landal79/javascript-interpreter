package org.landal.simulator;

import org.landal.iodevice.IODevice;

/**
 * Questa classe rappresenta il controller dell'applicazione, in pratica
 * specializza il framework grafico, crando due bottoni che associer� alle
 * relative classi. I pulsanti corrispondono uno all'operazione di analisi
 * sintattica, uno alla interpretazione della frase.
 * 
 * @author Alex Landini
 */
public class Simulator extends IODevice {

	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore del controller dell'applicazione.
	 */
	public Simulator() {
		super();
	}

	/**
	 * Costruttore che riceve in ingresso il nome da dare alla finestra.
	 * 
	 * @param name
	 *            nome da dare alla finestra.
	 */
	public Simulator(String name) {
		super(name);
	}

	protected void configura() {		
		super.configura();
		super.addCmdButton("Analisi Sintattica", new SintaxCommand());
		super.addCmdButton("Interpreta", new InterpretCommand());
	}// configura

	/**
	 * Main dell'applicazione.
	 */
	public static void main(String args[]) {
		Simulator sim = new Simulator("JS Simulator");
		sim.init();
	}
}
