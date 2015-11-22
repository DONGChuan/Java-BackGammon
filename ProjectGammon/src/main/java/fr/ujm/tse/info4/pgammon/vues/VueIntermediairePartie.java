package fr.ujm.tse.info4.pgammon.vues;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JLabel;

import fr.ujm.tse.info4.pgammon.gui.MonochromeButton;
import fr.ujm.tse.info4.pgammon.gui.MonochromeVue;
import fr.ujm.tse.info4.pgammon.models.Session;

public class VueIntermediairePartie extends MonochromeVue{
	
	/**
	 * Cette classe permet de gérer les vues nouvelles session et reprendre session
	 */
	private static final long serialVersionUID = -8524922151654361657L;
	
	private VueNouvelleSession vueNouvelleSession;
	private VueChargerPartie vueChargerPartie;
	
	private Boolean fenetreAAfficher;
	
	private MonochromeButton boutonReprendre;
	private MonochromeButton boutonRetour;
	
	private MonochromeButton boutonNouvellePartie;
	
	private JLabel textNouvelleSession;
	private JLabel textReprendreSession;
	
	ArrayList<Session> listSession;
	
	
	
	//pasage de parametre de booleen pour savoir quelle fenetre afficher
	/**
	 * Méthode permettant de changer les vues en fonction d'un booléen
	 * @param fenetre booleen definissant qu'elle fenêtre choisir
	 */
	public VueIntermediairePartie(Boolean fenetre){
		
		fenetreAAfficher=fenetre;
		build();
		changerTextSession();
		
	}

	
	/**
	 * Getter de la fenêtre a afficher
	 * @return retourne un booléen
	 */
	public Boolean getFenetreAAfficher() {
		return fenetreAAfficher;
	}

	/**
	 * Setter de fenêtre a afficher
	 * @param fenetreAAfficher change le booleen en fonction de la fenetre a afficher
	 */
	public void setFenetreAAfficher(Boolean fenetreAAfficher) {
		this.fenetreAAfficher = fenetreAAfficher;
		changerTextSession();
	}

	/**
	 * Getter du bouton reprendre session
	 * @return retourne la classe du bouton reprendre session
	 */
	public MonochromeButton getBoutonReprendre() {
		return boutonReprendre;
	}

	/**
	 * Getter du bouton nouvelle session
	 * @return retourne la classe du bouton nouvelle session
	 */
	public MonochromeButton getBoutonNouvellePartie() {
		return boutonNouvellePartie;
	}

	private void build() {
		setLayout(null);
		setOpaque(false);
		
		listSession = new ArrayList<>();
		
		vueNouvelleSession = new VueNouvelleSession();
		vueNouvelleSession.setBounds(0, 80, 794, 495);
		add(vueNouvelleSession);
		
		vueChargerPartie = new VueChargerPartie(listSession,fenetreAAfficher);
		vueChargerPartie.setBounds(0, 80, 794, 495);
		add(vueChargerPartie);
		
		boutonRetour = new MonochromeButton("Return");
		boutonRetour.setBounds(550, 15, 200, 50);
		add(boutonRetour);
		
		//problème sur action de bouton donc effacement de ceux-ci
		
		boutonReprendre = new MonochromeButton("Charger");
		boutonReprendre.setBounds(300, 15, 200, 50);
		add(boutonReprendre);
		boutonReprendre.setVisible(false);
		
		boutonNouvellePartie = new MonochromeButton("Nouvelle partie");
		boutonNouvellePartie.setBounds(50, 15, 200, 50);
		add(boutonNouvellePartie);
		boutonNouvellePartie.setVisible(false);
		
		if(fenetreAAfficher == true){
			vueNouvelleSession.setVisible(true);

			vueChargerPartie.setVisible(true);
			vueChargerPartie.setVisible(false);
		}
		else{
			vueNouvelleSession.setVisible(false);
			vueChargerPartie.setVisible(true);
		}
		
		textNouvelleSession = new JLabel("New session");
		textNouvelleSession.setBounds(50, 15, 400, 50);
		textNouvelleSession.setFont(new Font("Arial",Font.BOLD,36));
		textNouvelleSession.setForeground(new Color(0xCCCCCC));
		add(textNouvelleSession);
		
		textReprendreSession = new JLabel("Reprendre session");
		textReprendreSession.setBounds(50, 15, 400, 50);
		textReprendreSession.setFont(new Font("Arial",Font.BOLD,36));
		textReprendreSession.setForeground(new Color(0xCCCCCC));
		add(textReprendreSession);

		
		listenerboutonReprendre();
		listenerboutonNouvellePartie();
		
	}
	
	private void changerTextSession(){
		if (fenetreAAfficher == true){
			textNouvelleSession.setVisible(true);
			textReprendreSession.setVisible(false);
		}
		else{
			textNouvelleSession.setVisible(false);
			textReprendreSession.setVisible(true);
		}
	}

	private void listenerboutonNouvellePartie()
	{
		boutonNouvellePartie.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(fenetreAAfficher == false){
					vueNouvelleSession.setVisible(true);
					vueChargerPartie.setVisible(false);
					fenetreAAfficher=true;
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {}		
			@Override
			public void mouseExited(MouseEvent e) {}			
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
	
	private void listenerboutonReprendre()
	{
		boutonReprendre.addMouseListener(new MouseListener() {
			
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
				if(fenetreAAfficher == true){
					vueNouvelleSession.setVisible(false);
					vueChargerPartie.setVisible(true);
					fenetreAAfficher=false;
				}
			}
		});
	}

	/**
	 * Getter de la vue nouvelle session
	 * @return retourne la classe de la vue reprendre session
	 */
	public VueNouvelleSession getVueNouvelleSession() {
		return vueNouvelleSession;
	}

	/**
	 * Getter de la vuecharger partie ou reprendre session
	 * @return retourne la classe de la vue partie ou reprendre session
	 */
	public VueChargerPartie getVueChargerPartie() {
		return vueChargerPartie;
	}

	/**
	 * Getter du bouton retour
	 * @return retourne la classe du bouton retourn
	 */
	public MonochromeButton getBoutonRetour() {
		return boutonRetour;
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
