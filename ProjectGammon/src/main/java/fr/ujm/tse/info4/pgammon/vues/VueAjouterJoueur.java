package fr.ujm.tse.info4.pgammon.vues;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.ujm.tse.info4.pgammon.gui.ImageAvatar;
import fr.ujm.tse.info4.pgammon.gui.MonochromeButton;
import fr.ujm.tse.info4.pgammon.gui.MonochromePanel;

public class VueAjouterJoueur extends MonochromePanel{

	/**
	 * Cette classe permet de gérer l'ajout ou la modification de joueur
	 */

	private static final long serialVersionUID = 3196089768815616270L;
	
	private MonochromeButton boutonChangerAvatar;
	private MonochromeButton boutonSupprimerAvatar;
	private MonochromeButton boutonAnnuler;
	private MonochromeButton boutonEnregistrer;
	
	private JTextField nomPseudo;
	
	private ImageAvatar imgjoueur;
	
	private String chemin = "";
	
	/**
	 * Constructeur de la vue ajouter joueur
	 */
	public VueAjouterJoueur(){
		super("Edition de profil");
		build();
		
	}
	
	/**
	 * Setter du path
	 * @param p change l'adresse du chemin de l'avatar et le met a jour
	 */
	public void setPath(String p){
		chemin = p;
		updateData();
	}

	/**
	 * Mise à jour du chemin de l'avatar
	 */
	private void updateData() {
		imgjoueur.setPath(chemin);
		
	}

	private void build() {
		setLayout(null);
		
		imgjoueur = new ImageAvatar(chemin);
		imgjoueur.setBounds(25, 130, 105, 105);
		add(imgjoueur);
		
		boutonChangerAvatar = new MonochromeButton("Add Avatar");
		boutonChangerAvatar.setBounds(150, 130, 230, 40);
		add(boutonChangerAvatar);
		
		boutonSupprimerAvatar = new MonochromeButton("Delete Avatar");
		boutonSupprimerAvatar.setBounds(150, 180, 230, 40);
		add(boutonSupprimerAvatar);
		
		boutonAnnuler = new MonochromeButton("Cancel");
		boutonAnnuler.setBounds(40, 245, 150, 40);
		add(boutonAnnuler);
		
		boutonEnregistrer = new MonochromeButton("Save");
		boutonEnregistrer.setBounds(200, 245, 150, 40);
		add(boutonEnregistrer);
		
		nomPseudo = new JTextField();
		nomPseudo.setBounds(10, 60, 380, 40);
		nomPseudo.setBackground(new Color(0x61685A));
		nomPseudo.setForeground(new Color(0xCCCCCC));
		add(nomPseudo);
		
		
		JLabel pseudo = new JLabel("Pseudo");
		pseudo.setForeground(new Color(0xCCCCCC));
		pseudo.setBounds(10, 20, 100, 50);
		add(pseudo);
		
		JLabel Avatar = new JLabel("Avatar");
		Avatar.setForeground(new Color(0xCCCCCC));
		Avatar.setBounds(10, 90, 100, 50);
		add(Avatar);
		
		listenerSupprimerAvatar();
		
	}
	
	/**
	 * Getter du chemin ou path
	 * @return retourne l'adresse du chemin du joueur
	 */
	public String getChemin() {
		return chemin;
	}

	/**
	 * Setter du chemin
	 * @param chemin change l'adresse de l'avatar pour afficher la nouvelle image
	 */
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	/**
	 * met a nul l'adresse de l'avatar si on veut supprimer l'avatar
	 */
	private void listenerSupprimerAvatar()
	{
		boutonSupprimerAvatar.addMouseListener(new MouseListener() {
			
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
				setPath("");
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create(); 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 		
		g2.dispose(); 
		super.paintComponent(g);
	}
	
	/**
	 * Getter du bouton retour
	 * @return retourne la classe du bouton retour
	 */
	public MonochromeButton getRetour() {
		return boutonAnnuler;
	}
	
	/**
	 * Getter du bouton changer avatar
	 * @return retourne la classe du bouton changer avatar
	 */
	public MonochromeButton getchangerAvatar() {
		return boutonChangerAvatar;
	}
	
	/**
	 * Getter du bouton supprimer avatar
	 * @return retourne la classe du bouton supprimer avatar
	 */
	public MonochromeButton getsupprimerAvatar() {
		return boutonSupprimerAvatar;
	}
	
	/**
	 * Getter du bouton enregistrer
	 * @return retourne la classe du bouton enregistrer
	 */
	public MonochromeButton getenregistrer() {
		return boutonEnregistrer;
	}
	
	/**
	 * Getter du champ du joueur
	 * @return retourne la valeur du nom du joueur
	 */
	public JTextField getnomPseudo() {
		return nomPseudo;
	}
}
