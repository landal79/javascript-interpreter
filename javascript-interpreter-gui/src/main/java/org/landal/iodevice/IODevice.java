package org.landal.iodevice;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 * Questa classe implementa l'interfaccia IIODevice. Fornisce le principali
 * funzionalit� per gestire un dispositivo di I/O. IODevice pu� essere usata per
 * creare sia una applicazione stand-alone sia per creare un applet. Rappresenta
 * un Framework per applicazioni con interfaccia grafica, esso pu� essere usato
 * specializzandolo, oppure lo si pu� usare come classe a se stante in quanto
 * fornisce tutte le funzionalit� fondamentali per l'I/O.
 * 
 * @author Alex Landini
 */
public class IODevice extends JApplet implements IIODevice {

	private static final long serialVersionUID = 1L;

	/**
	 * nome dell'applicazione
	 */
	protected String logo = null;

	/**
	 * Dispositivo di input.
	 */
	protected InputDev in;

	/**
	 * Dispositivo di output.
	 */
	protected OutputDev out;

	/**
	 * Panello dei pulsanti
	 */
	protected CommandButton cmdBut;

	/**
	 * Applet context.
	 */
	protected java.applet.AppletContext apCtx;

	/**
	 * JFrame.
	 */
	protected JFrame frame;

	/**
	 * Costruttore di IODevice.
	 */
	public IODevice() {

	}// IODevice

	/**
	 * Costruttore di IODevice.
	 * 
	 * @param s
	 *            rappresenta il nome da dare alla finestra
	 */
	public IODevice(String nomeAppl) {

		logo = nomeAppl;

	}// IODevice

	/**
	 * Init (operazione di inizializzazione dell'ambiente quando vogliamo creare
	 * un applet) configura l'interfaccia.
	 */
	public void init() {

		configura();
		
		// se è un'applicazione stand-alone devo creare il frame
		setFrame();

		validate();

		if (frame != null) {
			frame.setVisible(true);
		}

	}// init

	/**
	 * Questo metodo serve a configurare il dispositivo di I/O. Le classe che
	 * ereditano possono specializzarlo al fine di ottenere un interfaccia
	 * grafica diversa.
	 */
	protected void configura() {
		// ottengo il pannello su cui disegnare l'interfaccia grafica
		Container panel = getContentPane();

		// imposto il Layout
		panel.setLayout(new BorderLayout());

		// creo i dispositivi
		out = new OutDev();// output

		in = new InpDev();// input

		cmdBut = new CmdButton();// pulsanti

		panel.add("North", in);

		panel.add("Center", cmdBut);

		panel.add("South", out);

	}// configura

	/**
	 * Creazione di un frame per l'applicazione stand-alone. Il metodo controlla
	 * se l'applicazione � gi� un applet. In caso contrario crea un frame di
	 * classe FrameClosing
	 */
	protected void setFrame() {

		try {
			apCtx = getAppletContext();
		} catch (NullPointerException e) {

			frame = new FrameClosing(logo);
			frame.getContentPane().add(this);
			frame.pack();
			frame.validate();

		}// catch

	}// setFrame

	/**
	 * Questo metodo serve per leggere quanto scritto sul dispositivo di input.
	 * 
	 * @return rappresenta la stringa letta sul dispositivo di input.
	 */
	public String read() {

		return in.read();

	}// read

	/**
	 * Questo metodo permette di inserire un messaggio indicativo per richiedere
	 * dati.
	 * 
	 * @param msg
	 *            rappresenta il messaggio da inserire
	 */
	public void emit(String msg) {

		in.emit(msg);

	}// emit

	/**
	 * Questo metodo serve per visualizzare una stringa sul dispositivo di
	 * output.
	 * 
	 * @param msg
	 *            rappresenta il messaggio da visualizzare.
	 */
	public void print(String msg) {

		out.print(msg);

	}// print

	/**
	 * Questo metodo serve per visualizzare una stringa sul dispositivo di
	 * output, andando a capo dopo averla visualizzata.
	 * 
	 * @param msg
	 *            rappresenta il messaggio da visualizzare.
	 */
	public void println(String msg) {

		out.println(msg);

	}// println

	/**
	 * Questo metodo serve per associare al bottone di nome <i>buttonName</i> al
	 * comando <i>cmd</i>.
	 * 
	 * @param buttonName
	 *            nome del bottone a cui associare il comando.
	 * @param cmd
	 *            comando da associare al bottone.
	 */
	public void addCmdButton(String buttonName, ICommand cmd) {

		cmdBut.addCmdButton(buttonName, cmd, in, out);

	}// addCmdButton

}// IODevice

