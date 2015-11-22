package fr.ujm.tse.info4.pgammon.vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.ujm.tse.info4.pgammon.gui.AfficheurScore;
import fr.ujm.tse.info4.pgammon.gui.ImageAvatar;
import fr.ujm.tse.info4.pgammon.gui.MonochromeButton;
import fr.ujm.tse.info4.pgammon.gui.MonochromeLabel;
import fr.ujm.tse.info4.pgammon.gui.MonochromePanel;
import fr.ujm.tse.info4.pgammon.models.CouleurCase;
import fr.ujm.tse.info4.pgammon.models.Session;

public class PanelParametresVueCharger extends MonochromePanel{
	/**
	 * Cette classe affiche le panel paramètre de session de la vue charger 
	 */
	
	private static final long serialVersionUID = 7111414911070584708L;
	
	private AfficheurScore scorej1;
	private AfficheurScore scorej2;
	
	private MonochromeLabel lab_parties;
	private MonochromeLabel lab_temps;
	
	private MonochromeButton boutonSupprimer;
	
	private JLabel text_parties;
	private JLabel text_temps;
	private JLabel text_videau;
	
	public static final String imgparties 	= "images/parties.png";
	public static final String imgtime 	= "images/time.png";

	private ImageIcon iconeparties;
	private ImageIcon iconetime;
	
	private Session session;
	
	private ImageAvatar imgjoueurBlanc;
	private ImageAvatar imgjoueurNoir;
	
	private JLabel nomJoueurBlanc;
	private JLabel nomJoueurNoir;
	
	public static final String pionblanc = "images/big_pion_blanc.png";
	public static final String pionnoir = "images/big_pion_noir.png";
	private ImageIcon imgpionBlanc;
	private ImageIcon imgpionNoir;
	
	/**
	 * constructeur de la classe 
	 * @param s on passe une session en paramètre
	 */
	public PanelParametresVueCharger(Session s){
		super("Parameters");
		session = s;
		
		build();
	}
	
	/**
	 * Setter de session
	 * @param s permet de changer la session en cours
	 */
	public void setSession(Session s){
		session=s;
		updateData();
	}
	
	/**
	 * Permet de mettre à jour tous les paramètres de la session
	 */
	public void updateData(){
		
		scorej1.setScore(session.getScores().get(session.getPartieEnCours().getParametreJeu().getJoueurBlanc()));
		scorej2.setScore(session.getScores().get(session.getPartieEnCours().getParametreJeu().getJoueurNoir()));
		imgjoueurBlanc.setPath(session.getPartieEnCours().getParametreJeu().getJoueurBlanc().getImageSource());
		imgjoueurNoir.setPath(session.getPartieEnCours().getParametreJeu().getJoueurNoir().getImageSource());
		nomJoueurBlanc.setText(session.getPartieEnCours().getParametreJeu().getJoueurBlanc().getPseudo());
		nomJoueurNoir.setText(session.getPartieEnCours().getParametreJeu().getJoueurNoir().getPseudo());
		
		int nbPartie = session.getParametreSession().getNbrPartieGagnante();
		
		if(nbPartie == 0){
			lab_parties.setText("\u221E");
		}else{
			lab_parties.setText(new Integer(nbPartie).toString());
		}
		
		int tempsSeconde = session.getParametreSession().getSecondesParTour()/1000;
		int j=tempsSeconde/60;		
		if(tempsSeconde == 0){
			lab_temps.setText("\u221E");
		}else if(tempsSeconde>60){
			lab_temps.setText(new Integer(tempsSeconde/60).toString()+"m "+new Integer(tempsSeconde-60*j)+"s");
		}
		else{
			lab_temps.setText(new Integer(tempsSeconde).toString()+" s");
		}
		
		if(session.getPartieEnCours().getParametreJeu().isUtiliseVideau() == true){
			text_videau.setText("Doubling cube is used");
		}
		else{
			text_videau.setText("Doubling cube is not used");
		}
		
	}
	
	public void build(){
		
		setLayout(null);
		
		//récupération de l'image
		iconeparties = new ImageIcon(imgparties);
		iconetime = new ImageIcon(imgtime);
		imgpionBlanc = new ImageIcon(pionblanc);
		imgpionNoir = new ImageIcon(pionnoir);


		lab_parties = new MonochromeLabel("");
		lab_parties.setBounds(15, 290, 120, 40);
		add(lab_parties);

		lab_temps = new MonochromeLabel("");
		lab_temps.setBounds(165, 290, 120, 40);
		add(lab_temps);


		scorej1 = new AfficheurScore(0, CouleurCase.BLANC);
		scorej1.setBounds(95, 70, 40, 40);
		add(scorej1);

		scorej2 = new AfficheurScore(0, CouleurCase.NOIR);
		scorej2.setBounds(160, 70, 40, 40);
		add(scorej2);

		imgjoueurBlanc = new ImageAvatar("");
		imgjoueurBlanc.setBounds(15, 70, 70, 70);
		add(imgjoueurBlanc);

		imgjoueurNoir = new ImageAvatar("");
		imgjoueurNoir.setBounds(210, 70, 70, 70);
		add(imgjoueurNoir);


		text_parties = new JLabel();
		text_parties.setText("Score to achieve");
		text_parties.setForeground(new Color(0xCCCCCC));
		text_parties.setBounds(15, 180, 300, 50);
		add(text_parties);

		text_temps = new JLabel();
		text_temps.setText("<html>Limitation of time <br>per turn");
		text_temps.setForeground(new Color(0xCCCCCC));
		text_temps.setBounds(165, 180, 300, 50);
		add(text_temps);

		nomJoueurBlanc = new JLabel();
		nomJoueurBlanc.setText("");
		nomJoueurBlanc.setForeground(new Color(0xCCCCCC));
		nomJoueurBlanc.setFont(new Font("Arial", Font.BOLD, 22));
		nomJoueurBlanc.setBounds(15, 28, 120, 50);
		add(nomJoueurBlanc);

		nomJoueurNoir = new JLabel();
		nomJoueurNoir.setText("");
		nomJoueurNoir.setForeground(new Color(0xCCCCCC));
		nomJoueurNoir.setFont(new Font("Arial", Font.BOLD, 22));
		nomJoueurNoir.setBounds(160, 28, 120, 50);
		add(nomJoueurNoir);

		text_videau = new JLabel();
		text_videau.setForeground(new Color(0xCCCCCC));
		text_videau.setBounds(20, 330, 300, 50);
		add(text_videau);

		boutonSupprimer = new MonochromeButton("Sup.");
		boutonSupprimer.setForeground(new Color(0xCCCCCC));
		boutonSupprimer.setBounds(165, 340, 120, 40);
		add(boutonSupprimer);
	}
	
	/**
	 * Getter du bouton supprimer
	 * @return retourne la classe du bouton supprimer
	 */
	public MonochromeButton getBoutonSupprimer() {
		return boutonSupprimer;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(iconeparties.getImage(),50,230,this);
		g.drawImage(iconetime.getImage(),200,230,this);
		g.drawImage(imgpionBlanc.getImage(),60,115,this);
		g.drawImage(imgpionNoir.getImage(),187,115,this);
	}
}
