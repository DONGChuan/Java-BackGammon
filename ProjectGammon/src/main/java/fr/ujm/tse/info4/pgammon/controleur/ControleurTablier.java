package fr.ujm.tse.info4.pgammon.controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;

import fr.ujm.tse.info4.pgammon.exeption.TourNonJouableException;
import fr.ujm.tse.info4.pgammon.gui.CaseButton;
import fr.ujm.tse.info4.pgammon.models.Case;
import fr.ujm.tse.info4.pgammon.models.DeSixFaces;
import fr.ujm.tse.info4.pgammon.models.Horloge;
import fr.ujm.tse.info4.pgammon.models.HorlogeEvent;
import fr.ujm.tse.info4.pgammon.models.HorlogeEventListener;
import fr.ujm.tse.info4.pgammon.models.NiveauAssistant;
import fr.ujm.tse.info4.pgammon.models.Partie;
import fr.ujm.tse.info4.pgammon.models.Tablier;
import fr.ujm.tse.info4.pgammon.vues.VuePartie;
import fr.ujm.tse.info4.pgammon.vues.VueTablier;

public class ControleurTablier implements Controleur{
	private Tablier tablier;
	private Partie partie;
	private VueTablier vueTablier;
	private VuePartie vuePartie;
	private Horloge horloge;
	
	private ControleurPartie controleurPartie;
	private JFrame frame;



	public ControleurTablier(Partie partie,VuePartie vuePartie,ControleurPartie controleurPartie)
	{
		this.partie = partie;
		this.tablier = partie.getTablier();
		this.vuePartie = vuePartie;
		this.vueTablier = vuePartie.getVueTablier();
		this.controleurPartie = controleurPartie;
	
		build();
		vueTablier.updateDes();
		vuePartie.afficherTransition(partie.getParametreJeu().getJoueur(partie.getJoueurEnCour()).getPseudo(), "Joueur" + partie.getJoueurEnCour().toString());	
	}


	private void build() {
			
		buildTimer();
		ListenerCaseButton();
		
	}
	
	private void ListenerCaseButton()
	{
		Collection<CaseButton> lCase = vueTablier.getCasesButtons();
		for (CaseButton caseButton : lCase) {
			caseButton.addMouseListener(new MouseListener() {

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
					CaseButton caseButton = (CaseButton) e.getSource();
					if (!partie.isTourFini() && !partie.isPartieFini())
						if(vueTablier.getCandidat() == null 
								&& partie.getJoueurEnCour() == caseButton.getCase().getCouleurDame())
						{
							if (caseButton.getCase().getNbDame() != 0 
									&& (!tablier.isDameDansCaseBarre(partie.getJoueurEnCour()) 
											|| caseButton.getCase().isCaseBarre())
											&& caseButton.getCase().getCouleurDame() == partie.getJoueurEnCour())
								{
									vueTablier.setCandidat(caseButton);
									if(partie.getParametreJeu().getJoueur(partie.getJoueurEnCour()).getNiveauAssistant() == NiveauAssistant.SIMPLE
											|| partie.getParametreJeu().getJoueur(partie.getJoueurEnCour()).getNiveauAssistant() == NiveauAssistant.COMPLET)
										vueTablier.setPossibles(partie.getCoupsPossibles(caseButton.getCase()));
								}
							else if (tablier.isDameDansCaseBarre(partie.getJoueurEnCour()))
							{
								vuePartie.afficherFenetreDemande("Attention!","Sortez les dames battues avant de jouer.");
							}
						}
						else if (vueTablier.getCandidat() != null)
						{
							if (partie.jouerCoup(vueTablier.getCandidat().getCase(), caseButton.getCase()))
							{
								vueTablier.uncandidateAll();
								vueTablier.setPossibles((new ArrayList<Case>()));
								
								if(partie.isPartieFini())
								{
									controleurPartie.finPartie();
								}
								
								if (partie.siDesUtilises())
								{	
									changerTour();	
									
								}
								else if(!partie.hasCoupPossible())
								{
									changerTour();
									partie.lancerDes();
									if(!partie.hasCoupPossible())
									{
										vuePartie.afficherFenetreDemande("No possible coup","");
										changerTour();
									}
								}
										
							}
							else
							{
								vueTablier.uncandidateAll();
								vueTablier.setPossibles((new ArrayList<Case>()));
							}
							
						}

					vueTablier.updateUI();
					vueTablier.updateDes();
				}
			});
			
		}
	}
	
	
	/*public void nouvellePartie(Partie partie)
	{
		this.partie = partie;
		vueTablier.setPartie(partie);
		vueTablier.updateUI();
		vueTablier.updateDes();
	}*/
	
	public void changerTour() 
	{
		if (horloge!= null)
		{
			horloge.stop();
			horloge.setValue(0);
		}
		partie.changerTour();
		vuePartie.afficherTransition(partie.getParametreJeu().getJoueur(partie.getJoueurEnCour()).getPseudo(),"Joueur" + partie.getJoueurEnCour().toString());
	}
	
	private void buildTimer(){
		
		if (partie.getParametreJeu().getSecondesParTour() != 0)
		{
			horloge = new Horloge(partie.getParametreJeu().getSecondesParTour());
			horloge.addListener(new HorlogeEventListener() {
				@Override
				public void updateHorloge(HorlogeEvent horloge) {}
				
				@Override
				public void finHorloge(HorlogeEvent evt) {
					 try {
						  //Deplacement aleatoire du nombre de de restant
						  int nbDeNonUtiliser=0;
						  for (DeSixFaces de : partie.getDeSixFaces()) {
							if(!de.isUtilise())
								nbDeNonUtiliser++;
						  }
						  for(int i=0;i<nbDeNonUtiliser;i++)
						  {
							  partie.deplacementAleatoire();
						  }

					} catch (TourNonJouableException e) {
						changerTour();
					}
					  vueTablier.uncandidateAll();
					  vueTablier.setPossibles((new ArrayList<Case>()));
					  
					  if (partie.siDesUtilises())
						{	
							changerTour();			
						}
						else if(!partie.hasCoupPossible())
						{
							vuePartie.afficherFenetreDemande("No coup possible","");
							changerTour();
						}	
					  
					  vueTablier.updateUI();
					  vueTablier.updateDes();
					  
				  }
			});
		}
		else
		{
			horloge =null;	
		}
		vuePartie.getHorlogeBarr().setHorloge(horloge);
	}

	/**
	 *  permet de récuperer l'horloge
	 * @return
	 */
	public Horloge getHorloge() {
		return horloge;
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
	public void retour() {}
	
}
