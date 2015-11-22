package fr.ujm.tse.info4.pgammon.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import fr.ujm.tse.info4.pgammon.models.CouleurCase;
import fr.ujm.tse.info4.pgammon.models.Player;
import fr.ujm.tse.info4.pgammon.models.NiveauAssistant;

public class PanelJoueur extends MonochromePanel{
	
	/**
	 * Cette classe permet de modifier le panel de joueur dans la vue nouvelle session
	 */
	
	private static final long serialVersionUID = 7553310687895062778L;
	private Player joueur;
	private CouleurCase couleur;
	
	
	public static final String pionblanc = "images/big_pion_blanc.png";
	public static final String pionnoir = "images/big_pion_noir.png";
	private ImageIcon imgpion;
	
	
	
	
	
	
	private MonochromeCheckbox couppossible;
	private MonochromeCheckbox conseilcoup;
	private JLabel affichestat;
	private ImageAvatar imgjoueur;
	
	/**
	 * Constructeur de panel joueur
	 * @param j joueur passé en paramètre
	 * @param coul CouleurCase passé en paramètre
	 */
		public PanelJoueur(Player j,CouleurCase coul){
			super("");
			joueur=j;
			couleur=coul;
			
			build();
			updateData();
			
		}
		
		/**
		 * Setter du joueur
		 * @param j change la valeur du joueur
		 */
		public void setJoueur(Player j){
			joueur=j;
			updateData();
		}
		
		
		/**
		 * permet de faire la mise a jour du panel
		 */
		public void updateData(){
			if(joueur != null)
			{
				
				setTitle(joueur.getPseudo());
				affichestat.setText("<html> Victoires &nbsp : "
						+new Integer(joueur.getStat().getNbVictoires()).toString()
						+"<br>Défaites : "+joueur.getStat().getNbDefaites()
						);
				imgjoueur.setPath(joueur.getImageSource());
				
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

				imgjoueur.setVisible(true);
				couppossible.setVisible(true);
				
				//cette ligne permet d'afficher le conseil coup
				//mais il n'est pas encore implémenté
				//conseilcoup.setVisible(true);
				
			}else{
				setTitle("");
				affichestat.setText("");

				imgjoueur.setVisible(false);
				couppossible.setVisible(false);
				conseilcoup.setVisible(false);
			}
			
		}
		
		private void listenerboutonchangerCoupPossible()
		{
			couppossible.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					if(joueur.getNiveauAssistant() == NiveauAssistant.NON_UTILISE )
						joueur.setNiveauAssistant(NiveauAssistant.SIMPLE);
					else
						joueur.setNiveauAssistant(NiveauAssistant.NON_UTILISE);
					updateData();}
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
		
		private void listenerboutonchangerConseilcoup()
		{
			conseilcoup.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					if(joueur.getNiveauAssistant() == NiveauAssistant.COMPLET )
						joueur.setNiveauAssistant(NiveauAssistant.SIMPLE);
					else
						joueur.setNiveauAssistant(NiveauAssistant.COMPLET);
					updateData();}
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
		
		public void build(){
			
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
			
			imgjoueur = new ImageAvatar("");
			imgjoueur.setBounds(15, 40, 50, 50);
			add(imgjoueur);
			
			
			affichestat = new JLabel();
			couppossible = new MonochromeCheckbox("<html> Afficher les <br> coups possibles");
			conseilcoup = new MonochromeCheckbox("<html> conseiller le <br> prochain coup");
			
			
			
			//affichage des stats du joueur
			affichestat.setForeground(new Color(0xCCCCCC));
			
			affichestat.setBounds(130, 40, 200, 50);
			affichestat.setFont(new Font("Arial",Font.HANGING_BASELINE,12));
			
			
			
			//creation composant checbox
			conseilcoup.setForeground(new Color(0xCCCCCC));
			conseilcoup.setBounds(190, 90, 150, 50);
			conseilcoup.setOpaque(false);
			
			//creation composant checbox
			couppossible.setForeground(new Color(0xCCCCCC));
			couppossible.setBounds(10, 90, 150, 50);
			couppossible.setOpaque(false);
			
			//conteneurimgpion
			
			add(couppossible);
			add(conseilcoup);
			add(affichestat);
			
			listenerboutonchangerCoupPossible();
			listenerboutonchangerConseilcoup();
		}
		
		
		/**
		 * Getter du checkbox coup possible
		 * @return retourne la valeur du checkbox coup possible
		 */
		public MonochromeCheckbox getCouppossible() {
			return couppossible;
		}

		/**
		 * Setter du checkbox coup possible
		 * @param couppossible change la checkbox coup possible
		 */
		public void setCouppossible(MonochromeCheckbox couppossible) {
			this.couppossible = couppossible;
		}

		/**
		 * Getter du checkbox conseil coup
		 * @return retourne la valeur du checkbox conseil coup
		 */
		public MonochromeCheckbox getConseilcoup() {
			return conseilcoup;
		}

		/**
		 * Setter du checkbox conseil coup
		 * @param couppossible change la checkbox conseil coup
		 */
		public void setConseilcoup(MonochromeCheckbox conseilcoup) {
			this.conseilcoup = conseilcoup;
		}

		/**
		 * Getter de l'affichage des statistiques
		 * @return retourne les stastiques
		 */
		public JLabel getAffichestat() {
			return affichestat;
		}

		/**
		 * Setter des statistiques
		 * @param affichestat change la valeur des statistiques
		 */
		public void setAffichestat(JLabel affichestat) {
			this.affichestat = affichestat;
		}

		/**
		 * Getter de joueur
		 * @return retourne un joueur
		 */
		public Player getJoueur() {
			return joueur;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawImage(imgpion.getImage(),70,40,this);
			
			
		}
		

}
