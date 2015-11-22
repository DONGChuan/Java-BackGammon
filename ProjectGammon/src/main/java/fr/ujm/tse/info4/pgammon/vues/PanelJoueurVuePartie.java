package fr.ujm.tse.info4.pgammon.vues;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.ujm.tse.info4.pgammon.gui.ImageAvatar;
import fr.ujm.tse.info4.pgammon.gui.MonochromeCheckbox;
import fr.ujm.tse.info4.pgammon.models.CouleurCase;
import fr.ujm.tse.info4.pgammon.models.Player;
import fr.ujm.tse.info4.pgammon.models.NiveauAssistant;

public class PanelJoueurVuePartie extends JPanel {

	/**
	 * Cette classe permet la visualisation d'un joueur dans la vue partie
	 */
	private static final long serialVersionUID = -7344853213808679707L;

	private Player joueur;
	private CouleurCase couleur;


	public static final String pionblanc = "images/big_pion_blanc.png";
	public static final String pionnoir = "images/big_pion_noir.png";
	private ImageAvatar imgjoueur;
	private ImageIcon imgpion;


	private MonochromeCheckbox couppossible;
	private MonochromeCheckbox conseilcoup;
	private JLabel labNomJoueur;

	/**
	 * constructeur de la classe
	 * @param j Joueur passé en paramètre
	 * @param coul CouleurCase passé en paramètre
	 */
	public PanelJoueurVuePartie(Player j,CouleurCase coul){
		joueur=j;
		couleur=coul;

		build();
		updateData();
	}
	
	/**
	 * permet la mise à jour du panel du joueur
	 */
	public void updateData(){
		
		if(joueur.getNiveauAssistant() == NiveauAssistant.COMPLET){
			couppossible.setSelected(true);
			conseilcoup.setSelected(true);
		}
		else if(joueur.getNiveauAssistant() == NiveauAssistant.SIMPLE){
			couppossible.setSelected(true);
			conseilcoup.setSelected(false);
		}
		else{
			couppossible.setSelected(false);
			conseilcoup.setSelected(false);
		}
		if(couppossible.isSelected()){
			conseilcoup.setEnabled(true);
		}else{
			conseilcoup.setEnabled(false);
		}
		
	}

	public void build(){
		setLayout(null);


		//récupération de l'image
		try{
			if(couleur.equals(CouleurCase.BLANC)){
				imgpion = new ImageIcon(pionblanc);
			}
			else{
				imgpion = new ImageIcon(pionnoir);
			}

		}catch(Exception err){
			System.err.println(err);
		}
		
		imgjoueur = new ImageAvatar(joueur.getImageSource());
		imgjoueur.setBounds(15, 40, 50, 50);
		add(imgjoueur);

		labNomJoueur = new JLabel();
		couppossible = new MonochromeCheckbox("<html> Show <br> possible moves");
		conseilcoup = new MonochromeCheckbox("<html> Suggest <br> next move");
		
		//cette ligne permet d'afficher le conseil coup
		//mais il n'est pas encore implémenté donc on le cache dés le début
		conseilcoup.setVisible(false);


		JPanel centragePseudo = new JPanel();
		centragePseudo.setLayout(new BorderLayout());
		centragePseudo.setBounds(0,0,150,50);
		centragePseudo.setOpaque(false);
		//affichage des stats du joueur
		labNomJoueur.setForeground(new Color(0xCCCCCC));
		labNomJoueur.setText(joueur.getPseudo());
		//labNomJoueur.setPreferredSize(new Dimension(150,50));

		labNomJoueur.setHorizontalAlignment(0);
		add(centragePseudo);
		centragePseudo.add(labNomJoueur);


		//creation composant checbox
		couppossible.setForeground(new Color(0xCCCCCC));
		couppossible.setBounds(10, 120, 150, 50);
		couppossible.setOpaque(false);

		//creation composant checbox
		conseilcoup.setForeground(new Color(0xCCCCCC));
		conseilcoup.setBounds(10, 160, 150, 50);
		conseilcoup.setOpaque(false);

		//conteneurimgpion

		add(couppossible);
		add(conseilcoup);

	}
	
	/**
	 * Getter du checkbox coup possible
	 * @return retoure la checkbox
	 */
	public MonochromeCheckbox getCouppossible() {
		return couppossible;
	}

	/**
	 * Setter du checkbox coup possible
	 * @param couppossible change la valeur de la checkbox
	 */
	public void setCouppossible(MonochromeCheckbox couppossible) {
		this.couppossible = couppossible;
	}

	/**
	 * Getter du checkbox conseil coup
	 * @return retoure la checkbox
	 */
	public MonochromeCheckbox getConseilcoup() {
		return conseilcoup;
	}

	/**
	 * Setter du checkbox conseil coup
	 * @param couppossible change la valeur de la checkbox
	 */
	public void setConseilcoup(MonochromeCheckbox conseilcoup) {
		this.conseilcoup = conseilcoup;
	}

	/**
	 * Getter du label du nom du joueur
	 * @return retoure le label du nom de joueur
	 */
	public JLabel getLabNomJoueur() {
		return labNomJoueur;
	}

	public void setLabNomJoueur(JLabel affichestat) {
		this.labNomJoueur = affichestat;
	}

	/**
	 * Getter de joueur
	 * @return retoure un joueur
	 */
	public Player getJoueur() {
		return joueur;
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);


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
		g2.drawRect(2, 0, w - 5 , h - 5);

		g.drawImage(imgpion.getImage(),80,55,this);
		g2.dispose(); 
	}

}
