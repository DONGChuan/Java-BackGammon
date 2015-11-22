package fr.ujm.tse.info4.pgammon.vues;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.ujm.tse.info4.pgammon.gui.IconMonochromeType;
import fr.ujm.tse.info4.pgammon.gui.MonochromeIconButton;

public class PanelTermineVueDroite extends JPanel{
	/**
	 * Cette classe permet de gérer la vue droite à la fin d'une partie
	 * il y a notament la revue de partie et recommencer une partie
	 */

	private static final long serialVersionUID = 1735758716220950070L;
	//composante des images de revoir partie
	//Composant du panel droit
	private MonochromeIconButton next;
	private MonochromeIconButton undo;
	private MonochromeIconButton x_white;
	private JLabel labnext;


	/**
	* Constructeur de la classe
	*/
	public PanelTermineVueDroite(){
		build();
	}
	

	private void build() {
		setLayout(null);
		setOpaque(false);
			
			//
			//panel du coté droit
			//
			
			//setOpaque(true);
			//bouton pour revoir la partie
			next = new MonochromeIconButton(IconMonochromeType.SUIVANT,"MonochromeIconButton",MonochromeIconButton.NOIR);
			next.setSizeBig();
			next.setBounds(10, 20, next.getPreferredSize().width, next.getPreferredSize().height);
			add(next);
			
			labnext = new JLabel("<html>Next<br>Game");
			labnext.setForeground(new Color(0xCCCCCC));
			labnext.setBounds(15, 70, 80, 60);
			add(labnext);
			
			undo = new MonochromeIconButton(IconMonochromeType.PLAY,"MonochromeIconButton",MonochromeIconButton.NOIR);
			undo.setSizeBig();
			undo.setBounds(10, 185, undo.getPreferredSize().width, undo.getPreferredSize().height);
			add(undo);
			
			JLabel labundo = new JLabel("Replay!");
			labundo.setForeground(new Color(0xCCCCCC));
			labundo.setBounds(10, 240, 80, 60);
			add(labundo);
			
			x_white = new MonochromeIconButton(IconMonochromeType.X_BLANC,"MonochromeIconButton",MonochromeIconButton.NOIR);
			x_white.setSizeBig();
			x_white.setBounds(10, 350, x_white.getPreferredSize().width, x_white.getPreferredSize().height);
			add(x_white);
			
			JLabel labx_white = new JLabel("<html>Interrupt<br>Game");
			labx_white.setForeground(new Color(0xCCCCCC));
			labx_white.setBounds(10, 400, 80, 60);
			add(labx_white);
			}
	
	/**
	 * Getter du bouton suivant
	 * @return retourne la classe du bouton continuer session
	 */
	public MonochromeIconButton getNext() {
		return next;
	}

	/**
	 * Getter du bouton précédent
	 * @return retourne la classe du bouton précédent
	 */
	public MonochromeIconButton getUndo() {
		return undo;
	}

	/**
	 * Getter du bouton interrompre partie
	 * @return retourne la classe du bouton interrompre partie
	 */
	public MonochromeIconButton getX_white() {
		return x_white;
	}
	
	/**
	 * Getter du label next
	 * @return retourne la classe du label vnext
	 */
	public JLabel getLabnext() {
		return labnext;
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		//super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		Paint p;
		int h = getHeight(); 
		int w = getWidth(); 
		
		// Arriere plan
		p = new RadialGradientPaint(new Point2D.Double(getWidth() / 2.0,
                getHeight() / 2.0), 
                getHeight(),
                new float[] { 0.0f, 0.8f },
                new Color[] { new Color(0x333333), new Color(0x000000) },
                RadialGradientPaint.CycleMethod.NO_CYCLE);
		
		g2.setPaint(p);
		
		g2.fillRect(0, 0, w, h);
		g2.dispose();
	}
	
	
	
}
