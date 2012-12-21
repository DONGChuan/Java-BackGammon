package fr.ujm.tse.info4.pgammon.controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import javax.swing.JFrame;

import fr.ujm.tse.info4.pgammon.models.Joueur;
import fr.ujm.tse.info4.pgammon.models.Profils;
import fr.ujm.tse.info4.pgammon.vues.VueAjouterJoueur;
import fr.ujm.tse.info4.pgammon.vues.VueListeJoueur;

public class ControleurListeJoueur implements Controleur{

	private VueListeJoueur vueListeJoueur;
	private Controleur controleur;
	private Profils profil;
	private VueAjouterJoueur vueAjouterJoueur;
	private Boolean flag = true;
    private Integer id = 0;
    private boolean isCharger;
    private JFrame frame;
    
	public ControleurListeJoueur (boolean isCharger,Controleur controleur) 
	{
		this.controleur = controleur;
		//TODO a changer
		profil = Profils.getProfils();
		//
		
		this.isCharger = isCharger;
		
		vueListeJoueur = new VueListeJoueur();
		controleur.getControleur().getFrame().setContentPane(vueListeJoueur);
		build();
	}
	
	
	
	public void build()
	{
		listenerBouttonRetour();
		listenerBouttonAjouter();
		listenerBouttonModifier();
		listenerBouttonSupprimer();
		listenerBouttonSelection();
	}
	public void listenerBouttonSelection()
	{
		if (isCharger)
		{
			vueListeJoueur.getSelectionner().addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					if (isCharger)
					{
						if (vueListeJoueur.getPanelDescription().getJoueur() !=null){
							Joueur j=vueListeJoueur.getPanelDescription().getJoueur();
							((ControleurIntermediairePartie)controleur).retour(j);
							vueListeJoueur.setVisible(false);
							profil.sauvegarder();
						}
						else
						{
							//vueListeJoueur.afficherFenetreDemande("Accepter vous le videau ?", null).addActionListener(new ActionListener() {
						}
					}
					else
					{
						vueListeJoueur.setVisible(false);
						profil.sauvegarder();
						controleur.retour();
					}
				}			
				@Override
				public void mousePressed(MouseEvent e) {}			
				@Override
				public void mouseExited(MouseEvent e) {}			
				@Override
				public void mouseEntered(MouseEvent e) {}			
				@Override
				public void mouseClicked(MouseEvent e) {}
			});
		}
		else
			vueListeJoueur.getSelectionner().setVisible(false);
	}
	public void listenerBouttonRetour()
	{
		vueListeJoueur.getRetour().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {
				vueListeJoueur.setVisible(false);
				profil.sauvegarder();
				controleur.retour();}
		});
	}

	public void listenerBouttonAjouter()
	{
		vueListeJoueur.getAjouter().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {
				vueListeJoueur.getVueAjouterJoueur().setPath("");
				vueListeJoueur.afficheEditerProfil();
				vueAjouterJoueur = vueListeJoueur.getVueAjouterJoueur();
				
				flag = true;
				buildEditerProfil();}
		});
	}
	
	public void listenerBouttonModifier(){
		vueListeJoueur.getPanelDescription().getModifier().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {
				vueListeJoueur.afficheEditerProfil();
				vueAjouterJoueur = vueListeJoueur.getVueAjouterJoueur();
				vueAjouterJoueur.setPath(vueListeJoueur.getPanelDescription().getJoueur().getImageSource());
				flag = false;
				buildEditerProfil();}
		});
	}
	public void listenerBouttonSupprimer(){
		vueListeJoueur.getPanelDescription().getSupprimer().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {
				profil.supprimer(vueListeJoueur.getPanelDescription().getJoueur());
				vueListeJoueur.updateListe();
				vueListeJoueur.updateData();
				vueListeJoueur.getPanelDescription().setVisible(false);
			}
		});
	}
		
	public void buildEditerProfil(){
		
		if(flag){
			vueAjouterJoueur.getnomPseudo().setText("");
		}else{
			vueAjouterJoueur.getnomPseudo().setText(vueListeJoueur.getPanelDescription().getJoueur().getPseudo());
		}
		
		listenerBouttonFermerEditerProfil();
		listenerBouttonEnregistrerEditerProfil();
	}
	
	public void listenerBouttonFermerEditerProfil(){
		vueAjouterJoueur.getRetour().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {
				vueListeJoueur.masqueEditerProfil();
				}
		});
	}

	public void listenerBouttonEnregistrerEditerProfil(){
		vueAjouterJoueur.getenregistrer().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(flag){
					boolean drapeau = true;
					
					Joueur tmpJoueur = new Joueur();		 
					tmpJoueur.setPseudo(vueAjouterJoueur.getnomPseudo().getText());	
					tmpJoueur.setImageSource(vueListeJoueur.getVueAjouterJoueur().getChemin());
					Calendar date = Calendar.getInstance();
					id = 10000*date.get(Calendar.MONTH)+1000*date.get(Calendar.DATE)
							+100*date.get(Calendar.HOUR)+10*date.get(Calendar.MINUTE)+date.get(Calendar.SECOND);
					
					tmpJoueur.setId(id);
					
					for(int i=0;i<profil.getList().size();i++){
						if(tmpJoueur.getId()== profil.getList().get(i).getId()){
							drapeau = false;
						}
					}
					
					if(drapeau){
						profil.getList().add(tmpJoueur);
						profil.afficher();
					}
					vueListeJoueur.setJ(tmpJoueur);
					
				}else{
					vueListeJoueur.getPanelDescription().getJoueur().setPseudo(vueAjouterJoueur.getnomPseudo().getText());
					vueListeJoueur.getPanelDescription().getJoueur().setImageSource(vueListeJoueur.getVueAjouterJoueur().getChemin());
				
				}
				vueListeJoueur.masqueEditerProfil();
				vueListeJoueur.updateListe();
				vueListeJoueur.updateData();
			}
		});
	}



	@Override
	public Controleur getControleur() {
		return this;
	}
	
	@Override
	public JFrame getFrame() {
		return frame;
	}



	@Override
	public void retour(){}

				
	
}
