package fr.ujm.tse.info4.pgammon.vues;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.ujm.tse.info4.pgammon.gui.AfficheurScore;
import fr.ujm.tse.info4.pgammon.gui.IconMonochromeType;
import fr.ujm.tse.info4.pgammon.gui.MonochromeIconButton;
import fr.ujm.tse.info4.pgammon.models.CouleurCase;

public class PanelEnCoursVueBas extends JPanel{
	/**
	 * Cette classe affiche le panel bas durant une partie
	 */
	
	private static final long serialVersionUID = 1587735370301921370L;
	
	//composante panel bas
		private MonochromeIconButton help;
		private MonochromeIconButton plus;
		private MonochromeIconButton x_black;
		private AfficheurScore panelScorej1;
		private AfficheurScore panelScorej2;
		
		private int scorej1;
		private int scorej2;

	/**
	 * Constructeur de la classe
	 */
	public PanelEnCoursVueBas(){
		build();
	}


	private void build() {
		setLayout(null);
		setOpaque(false);
		
		//
		//panel du bas
		//
		
		//composant et label d'aide
		help = new MonochromeIconButton(IconMonochromeType.AIDE,"MonochromeIconButton","NOIR");
		help.setSizeBig();
		help.setBounds(600, 5, help.getPreferredSize().width, help.getPreferredSize().height);
		add(help);
		
		JLabel labhelp = new JLabel("Rules");
		labhelp.setForeground(new Color(0xCCCCCC));
		labhelp.setBounds(610, 50, 80, 60);
		add(labhelp);
		
		//composant et label d'annuler nouvelle session
		plus = new MonochromeIconButton(IconMonochromeType.PLUS,"MonochromeIconButton","NOIR");
		plus.setSizeBig();
		plus.setBounds(700, 5, plus.getPreferredSize().width, plus.getPreferredSize().height);
		add(plus);
		plus.setVisible(false);
		
		JLabel labplus = new JLabel("New Session");
		labplus.setForeground(new Color(0xCCCCCC));
		labplus.setBounds(680, 50, 130, 60);
		add(labplus);
		labplus.setVisible(false);
		
		//composant et label d'annuler interrompre session
		x_black = new MonochromeIconButton(IconMonochromeType.X_NOIR,"MonochromeIconButton","NOIR");
		x_black.setSizeBig();
		x_black.setBounds(500, 5, x_black.getPreferredSize().width, x_black.getPreferredSize().height);
		add(x_black);
		
		JLabel labx_black = new JLabel("Interrupt session");
		labx_black.setForeground(new Color(0xCCCCCC));
		labx_black.setBounds(470, 50, 130, 60);
		add(labx_black);
		
		
		//composant et label du score
		panelScorej1 = new AfficheurScore(scorej1,CouleurCase.BLANC);
		panelScorej1.setBounds(270, 5, x_black.getPreferredSize().width, x_black.getPreferredSize().height);
		add(panelScorej1);
		
		panelScorej2 = new AfficheurScore(scorej1,CouleurCase.NOIR);
		panelScorej2.setBounds(350, 5, x_black.getPreferredSize().width, x_black.getPreferredSize().height);
		add(panelScorej2);
		
		JLabel labscore = new JLabel("Scores");
		labscore.setForeground(new Color(0xCCCCCC));
		labscore.setBounds(320, 50, 130, 60);
		add(labscore);
		
	}
	
	/**
	 * Permet de faire la mise a jour du scrore
	 * @param scoreJBlanc paramètre d'entier du score du joueur blanc
	 * @param scoreJNoir paramètre d'entier du score du joueur blanc
	 */
	public void updateScore(int scoreJBlanc, int scoreJNoir){
		scorej1 = scoreJBlanc;
		scorej2 = scoreJNoir;
		panelScorej1.setScore(scorej1);
		panelScorej2.setScore(scorej2);
	}


	/**
	 * Getter du bouton aide
	 * @return retourne la classe du bouton aide
	 */
	public MonochromeIconButton getHelp() {
		return help;
	}

	/**
	 * Getter du bouton nouvelle session
	 * @return retourne la classe du bouton nouvelle session
	 */
	public MonochromeIconButton getPlus() {
		return plus;
	}

	/**
	 * Getter du bouton interompre session
	 * @return retourne la classe du bouton interrompre session
	 */
	public MonochromeIconButton getX_black() {
		return x_black;
	}

	/**
	 * Getter du score du joueur blanc
	 * @return retourne le score du joueur blanc
	 */
	public int getScorej1() {
		return scorej1;
	}

	/**
	 * Getter du score du joueur noir
	 * @return retourne le score du joueur noir
	 */
	public int getScorej2() {
		return scorej2;
	}
	

}
