package fr.ujm.tse.info4.pgammon.vues;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import fr.ujm.tse.info4.pgammon.gui.IconMonochromeType;
import fr.ujm.tse.info4.pgammon.gui.MonochromeButton;
import fr.ujm.tse.info4.pgammon.gui.MonochromeIconButton;
import fr.ujm.tse.info4.pgammon.gui.PanelJoueur;
import fr.ujm.tse.info4.pgammon.gui.PanelParametre;
import fr.ujm.tse.info4.pgammon.models.CouleurCase;
import fr.ujm.tse.info4.pgammon.models.Player;

public class VueNouvelleSession extends JPanel{
	
	/**
	 * Cette classe reprèsente la vue pour créer une nouvelle session de jeu
	 */
	private static final long serialVersionUID = -5590865478480555417L;
	
	private MonochromeButton boutonCommencer; 
	private MonochromeIconButton boutonChangerCouleur; 
	private PanelJoueur panelJoueur1;
	private PanelJoueur panelJoueur2;
	private PanelParametre panelParamètre;
	
	private MonochromeButton boutonChangerJoueurBlanc;
	private MonochromeButton boutonChangerJoueurNoir;
	
	private Player j1;
	private Player j2;

	/**
	 * Constructeur de notre classe/vue
	 */
	public VueNouvelleSession(){
		build();
	}
	
	/**
	 * Méthode permettant de faire l'affichage de notre vue 
	 * et d'insérer tous les éléments nécessaires
	 */
	private void build() {		
		//ceci est fait pour pouvoir déplacer nos panels dans le panel
		setLayout(null);
		
		j1 = null;
		j2 = null;
		
		panelJoueur1 = new PanelJoueur(j1,CouleurCase.BLANC);
		panelJoueur2 = new PanelJoueur(j2,CouleurCase.NOIR);
		panelParamètre = new PanelParametre();
		
		boutonCommencer = new MonochromeButton("Start");
		 
		boutonChangerCouleur = new MonochromeIconButton(IconMonochromeType.SWITCH,"MonochromeIconButton","NOIR");
		boutonChangerCouleur.setSizeBig();
		boutonChangerCouleur.setBounds(10, 34, boutonChangerCouleur.getPreferredSize().width, boutonChangerCouleur.getPreferredSize().height);
		add(boutonChangerCouleur);
		 
		boutonChangerJoueurBlanc = new MonochromeButton("Change");
		boutonChangerJoueurBlanc.setBounds(250, 75, 105, 50);
		//boutonChangerJoueurBlanc.setFont(new Font("Arial", Font.PLAIN, 8));
		add(boutonChangerJoueurBlanc);
		
		boutonChangerJoueurNoir = new MonochromeButton("Change");
		boutonChangerJoueurNoir.setBounds(250, 285, 105, 50);
		add(boutonChangerJoueurNoir);
		 
		panelJoueur1.setBounds(37, 35, 332, 141);
		panelJoueur2.setBounds(37, 245, 332, 141);
		panelParamètre.setBounds(420, 35, 344, 352);
		 
		boutonCommencer.setBounds(200,420,380,58);
		boutonChangerCouleur.setBounds(175,190,55,55);
		 
		add(panelJoueur1);
		add(panelJoueur2);
		add(panelParamètre);
		add(boutonCommencer);
		add(boutonChangerCouleur);
		 
		listenerBoutonChangerCouleur();
	}
	
	/**
	 * Getter du bouton pour changer le joueur blanc 
	 * @return retourne la valeur du bouton de changement de joueur blanc
	 */
	public MonochromeButton getBoutonChangerJoueurBlanc() {
		return boutonChangerJoueurBlanc;
	}

	/**
	 * Getter du bouton pour changer le joueur noir
	 * @return retourne la valeur du bouton de changement de joueur noir
	 */
	public MonochromeButton getBoutonChangerJoueurNoir() {
		return boutonChangerJoueurNoir;
	}

	/**
	 * Méthode permettant de changer la couleur des 2 joueurs quand on clique sur le bouton de changement
	 */
	private void listenerBoutonChangerCouleur()
	{
		boutonChangerCouleur.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}		
			@Override
			public void mouseExited(MouseEvent e) {}			
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				Player jou1=j1;
				Player jou2=j2;
				setJoueur1(jou2);
				setJoueur2(jou1);
			}
		});
	}
	
	/**
	 * Setter du joueur blanc
	 * @param jBlanc Joueur blanc passé en paramètre
	 */
	public void setJoueur1(Player jBlanc){
		panelJoueur1.setJoueur(jBlanc);
		j1=jBlanc;
	}

	/**
	 * Setter du joueur noir
	 * @param jNoir Joueur noit passé en paramètre
	 */
	public void setJoueur2(Player jNoir) {
		panelJoueur2.setJoueur(jNoir);
		j2=jNoir;
	}
	
	/**
	 * Getter du bouton commencer partie
	 * @return retourne le bouton commencer
	 */
	public MonochromeButton getBoutonCommencer() {
		return boutonCommencer;
	}

	/**
	 * Getter du bouton changer couleur des joueurs
	 * @return retourne le bouton changer de couleur des joueurs
	 */
	public MonochromeIconButton getBoutonChangerCouleur() {
		return boutonChangerCouleur;
	}

	/**
	 * Getter du panel joueur noir permettant l'affichage du panel joueur blanc
	 * @return retourne l'instance du panel paramètre du joueur blanc
	 */
	public PanelJoueur getPanelJoueur1() {
		return panelJoueur1;
	}

	/**
	 * Getter du panel joueur noir permettant l'affichage du panel joueur noir
	 * @return retourne l'instance du panel paramètre du joueur noir
	 */
	public PanelJoueur getPanelJoueur2() {
		return panelJoueur2;
	}

	/**
	 * Getter du panel paramètre permettant l'affichage du panel de paramétrage de partie
	 * @return retourne l'instance du panel paramètre de partie
	 */
	public PanelParametre getPanelParamètre() {
		return panelParamètre;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create(); 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		
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
		
		// Bordure
		p = new Color(0x808080);
		g2.setStroke(new BasicStroke(5.0f) );
		g2.setPaint(p); 
		g2.drawRect(2, 0, w - 5 , h - 5 );
		
		
		g2.dispose(); 
		
	}


}
