package fr.ujm.tse.info4.pgammon.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.ujm.tse.info4.pgammon.gui.ImageAvatar;
import fr.ujm.tse.info4.pgammon.gui.MonochromeButton;
import fr.ujm.tse.info4.pgammon.gui.MonochromeCheckbox;
import fr.ujm.tse.info4.pgammon.gui.MonochromePanel;
import fr.ujm.tse.info4.pgammon.models.Player;
import fr.ujm.tse.info4.pgammon.models.NiveauAssistant;

public class PanelVueListeJoueurDescription extends MonochromePanel{

	/**
	 * Cette classe permet l'affichage de la description d'un joueur 
	 */
	private static final long serialVersionUID = -7183137442304137995L;

	private Player joueur;


	public static final String pionblanc = "images/big_pion_blanc.png";
	public static final String pionnoir = "images/big_pion_noir.png";
	private ImageIcon imgjoueur;



	private MonochromeCheckbox coupPossible;
	private MonochromeCheckbox conseilCoup;

	private JLabel nomJoueur;
	private JLabel statisitques;

	private MonochromeButton modifier;
	private MonochromeButton supprimer;

	private ImageAvatar imagejoueur;

	/**
	 * Constructeur du panel de la desciption du joueur
	 * @param j joueur passé en paramètre
	 */
	public PanelVueListeJoueurDescription(Player j){
		super("Description");
		joueur=j;

		build();
		updateData();
	}

	
	/**
	 * Setter de joueur
	 * @param j change la valeur du joueur
	 */
	public void setJoueur(Player j){
		joueur=j;
		updateData();
	}
	
	/**
	 * Permet de mettre à jour le panel
	 */

	public void updateData(){
		if(joueur == null)
			return;
		nomJoueur.setText(joueur.getPseudo());
		statisitques.setText("<html>" +new Integer(joueur.getStat().getPartiesJouees()).toString()+
				"<br>"+new Integer(joueur.getStat().getNbVictoires()).toString() +
				"<br>"+new Integer(joueur.getStat().getNbDefaites()).toString() +
				"<br>"+new Integer((int) (joueur.getStat().getPourcentageVictoire()*100)).toString()  
				/*+ " %<br>"+joueur.getStat().getEnnemiFavoris() */
				);

		if(joueur.getNiveauAssistant() == NiveauAssistant.COMPLET){
			coupPossible.setSelected(true);
			conseilCoup.setSelected(true);
		}
		else if(joueur.getNiveauAssistant() == NiveauAssistant.SIMPLE){
			coupPossible.setSelected(true);
			conseilCoup.setSelected(false);
		}
		else{
			coupPossible.setSelected(false);
			conseilCoup.setSelected(false);
		}
		if(coupPossible.isSelected()){
			conseilCoup.setEnabled(true);
		}else{
			conseilCoup.setEnabled(false);
		}

		imagejoueur.setPath(joueur.getImageSource());
	}

	
	private void listenerboutonchangerCoupPossible()
	{
		coupPossible.addMouseListener(new MouseListener() {

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
				if(joueur.getNiveauAssistant() == NiveauAssistant.NON_UTILISE )
					joueur.setNiveauAssistant(NiveauAssistant.SIMPLE);
				else
					joueur.setNiveauAssistant(NiveauAssistant.NON_UTILISE);
				updateData();
			}
		});
	}

	private void listenerboutonchangerConseilcoup()
	{
		conseilCoup.addMouseListener(new MouseListener() {

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
				if(joueur.getNiveauAssistant() == NiveauAssistant.COMPLET )
					joueur.setNiveauAssistant(NiveauAssistant.SIMPLE);
				else
					joueur.setNiveauAssistant(NiveauAssistant.COMPLET);
				updateData();
			}
		});
	}

	public void build(){


		imagejoueur = new ImageAvatar("");
		imagejoueur.setBounds(25, 40, 105, 105);
		add(imagejoueur);

		JLabel textStat = new JLabel();
		coupPossible = new MonochromeCheckbox("<html> Show<br> possible movements");
		conseilCoup = new MonochromeCheckbox("<html> Advise<br> next movements");
		
		//cette ligne permet d'afficher le conseil coup
		//mais il n'est pas encore implémenté donc on le cache dés le début
		conseilCoup.setVisible(false);

		JLabel labStat = new JLabel("Statistics");
		labStat.setForeground(new Color(0xCCCCCC));
		labStat.setBounds(15, 140, 200, 50);
		add(labStat);

		JLabel labConfig = new JLabel("Configuration Wizard");
		labConfig.setForeground(new Color(0xCCCCCC));
		labConfig.setBounds(15, 290, 200, 50);
		add(labConfig);

		//creation panel pour positionnement text
		JPanel posPseudo = new JPanel();

		posPseudo.setLayout(new BorderLayout());
		posPseudo.setBounds(140,40,180,50);
		posPseudo.setOpaque(false);

		//label de d'ecriture du nom de joueur 
		nomJoueur = new JLabel();
		nomJoueur.setForeground(new Color(0xCCCCCC));
		nomJoueur.setFont(new Font("Arial", Font.BOLD, 20));
		nomJoueur.setHorizontalAlignment(0);
		add(posPseudo);
		posPseudo.add(nomJoueur);

		//creation panel pour positionnement text des stats
		JPanel posStat = new JPanel();

		posStat.setLayout(new BorderLayout());
		posStat.setBounds(180, 180, 140, 100);
		posStat.setOpaque(false);

		//label de d'ecriture du nom de joueur 
		statisitques = new JLabel();
		statisitques.setText("test");
		statisitques.setForeground(new Color(0xCCCCCC));
		statisitques.setFont(new Font("Arial",Font.HANGING_BASELINE,12));
		//statisitques.setHorizontalAlignment(0);
		add(posStat);
		posStat.add(statisitques);

		//affichage des stats du joueur
		textStat.setForeground(new Color(0xCCCCCC));
		textStat.setText("<html>Games played :" +
				"<br>Victores :" +
				"<br>Defeat :" +
				"<br>Win Percentage :" 
				/*+"<br>Ennemi favori :" */);
				
		textStat.setBounds(15, 130, 140, 200);
		textStat.setFont(new Font("Arial",Font.HANGING_BASELINE,12));

		//creation composant checbox
		conseilCoup.setForeground(new Color(0xCCCCCC));
		conseilCoup.setBounds(180, 320, 200, 50);
		conseilCoup.setOpaque(false);

		//creation composant checbox
		coupPossible.setForeground(new Color(0xCCCCCC));
		coupPossible.setBounds(15, 320, 200, 50);
		coupPossible.setOpaque(false);

		//conteneurimgpion

		add(coupPossible);
		add(conseilCoup);
		add(textStat);

		modifier = new MonochromeButton("Modify");
		modifier.setBounds(15, 380, 140, 50);
		add(modifier);

		supprimer = new MonochromeButton("Delete");
		supprimer.setBounds(175, 380, 140, 50);
		add(supprimer);

		listenerboutonchangerCoupPossible();
		listenerboutonchangerConseilcoup();

	}

	/**
	 * Getter du bouton modifier
	 * @return retourne la classe du bouton modifier
	 */
	public MonochromeButton getModifier() {
		return modifier;
	}

	/**
	 * Getter du bouton supprimer
	 * @return retourne la classe du bouton supprimer
	 */
	public MonochromeButton getSupprimer() {
		return supprimer;
	}

	/**
	 * Getter de l'image du joueur
	 * @return retourne la classe de l'imageavatar
	 */
	public ImageIcon getImgjoueur() {
		return imgjoueur;
	}

	
	/**
	 * Setter de l'image du joueur
	 * @param imgjoueur change l'image avatar du joueur
	 */
	public void setImgjoueur(ImageIcon imgjoueur) {
		this.imgjoueur = imgjoueur;
	}

	/**
	 * Getter du checkbox de coup possible
	 * @return retourne la classe de la checkbox
	 */
	public MonochromeCheckbox getCoupPossible() {
		return coupPossible;
	}

	/**
	 * Setter du checkbox Coup Possible
	 * @param conseilCoup change la checkbox
	 */
	public void setCoupPossible(MonochromeCheckbox couppossible) {
		this.coupPossible = couppossible;
	}

	/**
	 * Getter du checkbox de conseil coup
	 * @return retourne la classe de la checkbox
	 */
	public MonochromeCheckbox getConseilCoup() {
		return conseilCoup;
	}

	/**
	 * Setter du checkbox conseil coup
	 * @param conseilCoup change la checkbox
	 */
	public void setConseilcoup(MonochromeCheckbox conseilCoup) {
		this.conseilCoup = conseilCoup;
	}


	/**
	 * Getter du joueur j
	 * @return retourne le joueur en cours
	 */
	public Player getJoueur() {
		return joueur;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	
	}

}
