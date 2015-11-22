package fr.ujm.tse.info4.pgammon.vues;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.ujm.tse.info4.pgammon.gui.MonochromeButton;
import fr.ujm.tse.info4.pgammon.gui.MonochromeVue;

public class VueMenu extends MonochromeVue {

	/**
	 * Cette classe nous permet de visualiser le menu de notre jeu
	 */
	private static final long serialVersionUID = 3060121008717453091L;
	public static final String img_menu = "images/menu_bg.png";
	
	//déclaration des variables de boutons
	private MonochromeButton boutonNouvellePartie;
	private MonochromeButton boutonReprendrePartie;
	private MonochromeButton boutonAjouter;
	private MonochromeButton boutonAide;
	private MonochromeButton boutonQuitter;
	
	private ImageIcon icon;
	
	public VueMenu(){
		build();
	}
	
	private void build() {
		
		//récupération de l'image
		try{
			icon = new ImageIcon(img_menu);
		}catch(Exception err){
			System.err.println(err);
		}
		
		//ceci est fait pour pouvoir déplacer nos panels dans le panel
		setLayout(null);
		
		//déclaration des 2 panels un contenant les boutons
		//et un autre pour pouvoir centrer le panel
		JPanel conteneurgrid = new JPanel();
		JPanel conteneurbouton = new JPanel();
		
		GridLayout gl = new GridLayout(6, 1);//permet de contenir les boutons en colonne
		
		//paramètre du conteneur de grid
		conteneurgrid.setBounds(200, 150, 400, 400);//positionnement relatif du conteneur avec ses dimensions
		conteneurgrid.setOpaque(false);
		
		
		
		//paramètre du conteneur de bouton
		gl.setVgap(10);//permet de faire un espacement de 10 entre chaque bouton
		conteneurbouton.setLayout(gl);
		conteneurbouton.setPreferredSize(new Dimension(400,350));//redimensionnement de la fenêtre
		conteneurbouton.setOpaque(false);//transparence de la fenêtre pour avoir le même fond

		
		
		boutonNouvellePartie = new MonochromeButton("New Session");
		conteneurbouton.add(boutonNouvellePartie);
		
		boutonReprendrePartie = new MonochromeButton("Load Session");
		conteneurbouton.add(boutonReprendrePartie);
		
		boutonAjouter = new MonochromeButton("Player List");
		conteneurbouton.add(boutonAjouter);
		
		boutonAide = new MonochromeButton("Rules");
		conteneurbouton.add(boutonAide);
		
		JPanel vide = new JPanel();
		vide.setOpaque(false);
		conteneurbouton.add(vide);
		
		boutonQuitter = new MonochromeButton("Quit");
		conteneurbouton.add(boutonQuitter);
		
		add(conteneurgrid);
		conteneurgrid.add(conteneurbouton);
		
	}
	
	/**
	 * Getter du bouton de nouvelle partie
	 * @return retourne la classe du bouton nouvelle partie
	 */
	public MonochromeButton getBoutonNouvellePartie() {
		return boutonNouvellePartie;
	}

	/**
	 * Getter du bouton de reprendre partie
	 * @return retourne la classe du bouton reprendre partie
	 */
	public MonochromeButton getBoutonReprendrePartie() {
		return boutonReprendrePartie;
	}

	/**
	 * Getter du bouton d'ajouter joueur
	 * @return retourne la classe du bouton d'ajouter joueur
	 */
	public MonochromeButton getBoutonAjouter() {
		return boutonAjouter;
	}

	/**
	 * Getter du bouton du bouton d'aide
	 * @return retourne la classe du bouton d'aide
	 */
	public MonochromeButton getBoutonAide() {
		return boutonAide;
	}
	
	/**
	 * Getter du bouton Quitte
	 * @return retourne la classe du bouton Quitter
	 */
	public MonochromeButton getBoutonQuitter() {
		return boutonQuitter;
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		
		g.drawImage(icon.getImage(),0,0,this);
		
	}
}
