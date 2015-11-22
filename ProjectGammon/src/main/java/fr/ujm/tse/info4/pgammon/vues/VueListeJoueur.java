package fr.ujm.tse.info4.pgammon.vues;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.ujm.tse.info4.pgammon.gui.AvatarList;
import fr.ujm.tse.info4.pgammon.gui.ImageAvatar;
import fr.ujm.tse.info4.pgammon.gui.JoueurCellRenderer;
import fr.ujm.tse.info4.pgammon.gui.MonochromeButton;
import fr.ujm.tse.info4.pgammon.gui.MonochromeListe;
import fr.ujm.tse.info4.pgammon.gui.MonochromeVue;
import fr.ujm.tse.info4.pgammon.gui.OpaqueBG;
import fr.ujm.tse.info4.pgammon.models.Player;
import fr.ujm.tse.info4.pgammon.models.Profils;

public class VueListeJoueur extends MonochromeVue{

	/**
	 * Cette classe permet de faire la visualisation de la vue liste joueur
	 * Elle comporte d'autres classe comme panel vue liste joueur description et la monochrome liste
	 */
	private static final long serialVersionUID = 9216988183357324981L;
	
	private Profils profil;
	private Player j;
	
	private PanelVueListeJoueurDescription panelDescription;
	private MonochromeListe<Player> listeJoueur;
	
	private String path = "";


	private VueAjouterJoueur vueAjouterJoueur;
	private MonochromeButton boutonAjouter;
	private MonochromeButton boutonSelectionner;
	private MonochromeButton boutonRetour;
	private OpaqueBG bg;
	
	private AvatarList listeAvatar;
	
	private ImageAvatar imgjoueur;
	
	/**
	 * Constructeur de la classe
	 */
	public VueListeJoueur(){
		
		profil = Profils.getProfils();
		build();
		
		listeJoueur.getList().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (listeJoueur.getList().getSelectedValue() !=null){
					j=listeJoueur.getList().getSelectedValue();
					//System.out.println(j);
					updateData();
						
				}
			}
		});
		
		
		
	}
	
	/**
	 * Méthode permettant d'afficher la page pour créer ou modifier son profil
	 */
	public void afficheEditerProfil(){
		vueAjouterJoueur.setVisible(true);
		bg.setVisible(true);
	}
	
	/**
	 * Méthode permettant de masquer la page pour créer ou modifier son profil
	 */
	public void masqueEditerProfil(){
		vueAjouterJoueur.setVisible(false);
		bg.setVisible(false);
	}
	
	/**
	 * Méthode permettant de faire des mises à jour quand le joueur change
	 * cela permet de mettre à la liste quand il y a des modifications
	 */
	public void updateData(){
		panelDescription.setVisible(true);
		panelDescription.setJoueur(j);
		updateListe();
	}
	
	
	private void build() {
		setLayout(null);
		setOpaque(false);
		
		j = null;
		
		//création de la fenetre d'ajouter joueur

		bg = new OpaqueBG();
		
		listeAvatar = new AvatarList();
		listeAvatar.setBounds(0, 0, 800, 600);
		add(listeAvatar);
		listeAvatar.setVisible(false);

		vueAjouterJoueur = new VueAjouterJoueur();
		vueAjouterJoueur.setBounds(200, 150, 400, 300);
		add(vueAjouterJoueur);
		add(bg);
		masqueEditerProfil();
		
		panelDescription = new PanelVueListeJoueurDescription(j);
		panelDescription.setBounds(420, 30, 330, 450);
		add(panelDescription);
		panelDescription.setVisible(false);
		
		listeJoueur = new MonochromeListe<>("Players saved",profil.getList(),new JoueurCellRenderer());
		listeJoueur.setBounds(40, 30, 330, 450);
		add(listeJoueur);
		
		boutonAjouter = new MonochromeButton("Add a new player");
		boutonAjouter.setBounds(50, 505, 300, 50);
		add(boutonAjouter);
		
		boutonSelectionner = new MonochromeButton("Select");
		boutonSelectionner.setBounds(420, 505, 150, 50);
		add(boutonSelectionner);
		
		boutonRetour = new MonochromeButton("Return");
		boutonRetour.setBounds(600, 505, 150, 50);
		add(boutonRetour);
		
		listenerchangerAvatar();
		listenerAjouterAvatar();
	}
	
	
	/**
	 * récupération de l'adresse de l'avatar sur l'appuie du bouton ajouter avatar
	 * et mise à jour dans la vue ajouter joueur
	 */
	private void listenerAjouterAvatar(){
	
		listeAvatar.getAjouter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				path = listeAvatar.getPath();
				
				vueAjouterJoueur.setPath(path);
				listeAvatar.setVisible(false);
				updateListe();
			}
		});
	
	}
	
	/**
	 * Affichage de la liste d'avatar
	 */
	private void listenerchangerAvatar()
	{
		vueAjouterJoueur.getchangerAvatar().addMouseListener(new MouseListener() {
			
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
				
				listeAvatar.setVisible(true);
			}
		});
	}
	
	/**
	 * permet de faire la mise a jour de la liste de joueur
	 */
	public void updateListe(){
		listeJoueur.updateList(new JoueurCellRenderer());
	}
	
	

	/**
	 * Getter de profil
	 * @return Récupère le profil en cours
	 */
	public Profils getProfil() {
		return profil;
	}


	/**
	 * Getter du panel vue liste joueur description
	 * @return Récupère la classe du panel de description du joueur
	 */
	public PanelVueListeJoueurDescription getPanelDescription() {
		return panelDescription;
	}


	/**
	 * Getter de l'affichage de la liste de joueur
	 * @return Récupère la classe de la liste de joueur
	 */
	public MonochromeListe<Player> getListeJoueur() {
		return listeJoueur;
	}

	/**
	 * Getter du bouton ajouter
	 * @return Récupère la classe du bouton ajouter
	 */
	public MonochromeButton getAjouter() {
		return boutonAjouter;
	}


	/**
	 * Getter du bouton selectionner
	 * @return Récupère la classe du bouton selectionner
	 */
	public MonochromeButton getSelectionner() {
		return boutonSelectionner;
	}

	/**
	 * Getter du bouton retour
	 * @return Récupère la calsse du bouton retour
	 */
	public MonochromeButton getRetour() {
		return boutonRetour;
	}
	
	/**
	 * Getter de la vue ajouter joueur
	 * @return Récupère la classe de la vue ajouter joueur
	 */
	public VueAjouterJoueur getVueAjouterJoueur() {
		return vueAjouterJoueur;
	}
	
	
	/**
	 * Getter du path
	 * @return Récupère l'adresse de l'avatar
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Setter du path
	 * @param path changer le chemin de l'adresse de l'avatar
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Getter de joueur
	 * @return Récupère le joueur en cours
	 */
	public Player getJ() {
		return j;
	}
	
	/**
	 * Setter du joueur
	 * @param j changer le joueur et met à jour la liste
	 */
	public void setJ(Player j) {
		this.j = j;
		updateData();
	}

	/**
	 * Getter de la liste d'avatar
	 * @return Récupère la classe de la liste d'avatar
	 */
	public AvatarList getListeAvatar() {
		return listeAvatar;
	}

	/**
	 * Getter de l'image du joueur 
	 * @return Récupère l'image avatar du joueur 
	 */
	public ImageAvatar getImgjoueur() {
		return imgjoueur;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create(); 

		bg.setBounds(getBounds());
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


