package org.landal.iodevice;

import javax.swing.JPanel;

/**
 * Questa classe è astratta deve essere specializzata. serve a fare in modo che
 * le sue eredi possano essere aggiunte a un conteiner.
 * 
 * @author Alex Landini
 */
public abstract class CommandButton extends JPanel implements ICmdButton {
	
	private static final long serialVersionUID = 1L;

}
