package fr.ujm.tse.info4.pgammon.vues;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.ujm.tse.info4.pgammon.gui.AfficheurScore;
import fr.ujm.tse.info4.pgammon.gui.IconMonochromeType;
import fr.ujm.tse.info4.pgammon.gui.MonochromeIconButton;
import fr.ujm.tse.info4.pgammon.models.CouleurCase;
import fr.ujm.tse.info4.pgammon.models.Partie;

public class PanelEnCoursVueDroite extends JPanel{
	/**
	 * Cette classe permet de visualiser le panel vue droite quand on joue une partie
	 */

	private static final long serialVersionUID = -7846360859879404327L;
	//composant des images de jouer partie
	//composant panel droit
	private MonochromeIconButton back;
	private MonochromeIconButton dices;
	private AfficheurScore videau;
	private Partie partie;
	
	
/**
 * constructeur du panel
 * @param p il lui faut une partie en paramètre
 */
public PanelEnCoursVueDroite(Partie p){
	partie = p;
	build();
}


private void build() {
	setLayout(null);
	setOpaque(false);
	
		
		//
		//panel du coté droit
		//
		
		
		//composant et label d'annuler coup
		back = new MonochromeIconButton(IconMonochromeType.RETOUR,"MonochromeIconButton","NOIR");
		back.setSizeBig();
		back.setBounds(10, 34, back.getPreferredSize().width, back.getPreferredSize().height);
		add(back);
		
		JLabel labback = new JLabel("<html>Annuler<br>coup");
		labback.setBounds(20, 79, 80, 60);
		add(labback);
		
		
		//composant avec label de lancer de dès
		dices = new MonochromeIconButton(IconMonochromeType.DES,"MonochromeIconButton","NOIR");
		dices.setSizeBig();
		dices.setBounds(10, 259, dices.getPreferredSize().width, dices.getPreferredSize().height);
		add(dices);
		
		JLabel labdices = new JLabel("<html>Lancer<br>dés");
		labdices.setForeground(new Color(0xCCCCCC));
		labdices.setBounds(20, 304, 80, 60);
		add(labdices);
		
		//creation composant et label du videau
		//attention ici j'ai pris la largeur et hauteur du dices
		videau = new AfficheurScore(partie.getVideau().getvideau(),CouleurCase.BLANC);
		videau.setBounds(10, 379, dices.getPreferredSize().width, dices.getPreferredSize().height);
		add(videau);
		
		JLabel labvideau = new JLabel("<html>Lancer<br>videau");
		labvideau.setForeground(new Color(0xCCCCCC));
		labvideau.setBounds(20, 429, 80, 60);
		add(labvideau);
		
	}

	/**
	 * Permet la mise à jour du videau
	 */
	public void updateVideau()
	{
		videau.setScore(partie.getVideau().getvideau());
	}

	/**
	 * Getter du bouton retour
	 * @return retourne la classe du bouton de retour
	 */
	public MonochromeIconButton getBack() {
		return back;
	}
	
	/**
	 * Getter du bouton dés
	 * @return retourne la classe du bouton de dés
	 */
	public MonochromeIconButton getDices() {
		return dices;
	}
	
	/**
	 * Getter du bouton videau
	 * @return retourne la classe du bouton de videau
	 */
	public AfficheurScore getVideau() {
		return videau;
	}
			
	/**
	 * Setter de partie
	 * @param partie change la partie
	 */
	public void setPartie(Partie partie) {
		this.partie = partie;
	}

}
