// 
//
//  @ Projet : Project Gammon
//  @ Fichier : ControleurPartie.java
//  @ Date : 12/12/2012
//  @ Auteurs : DONG Chuan, BONNETTO Benjamin, FRANCON Adrien, POTHELUNE Jean-Michel
//
//

package fr.ujm.tse.info4.pgammon.controleur;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.jdom2.JDOMException;

import fr.ujm.tse.info4.pgammon.models.Case;
import fr.ujm.tse.info4.pgammon.models.CouleurCase;
import fr.ujm.tse.info4.pgammon.models.Deplacement;
import fr.ujm.tse.info4.pgammon.models.EtatSession;
import fr.ujm.tse.info4.pgammon.models.GestionDeSession;
import fr.ujm.tse.info4.pgammon.models.NiveauAssistant;
import fr.ujm.tse.info4.pgammon.models.Partie;
import fr.ujm.tse.info4.pgammon.models.Profils;
import fr.ujm.tse.info4.pgammon.models.Session;
import fr.ujm.tse.info4.pgammon.vues.VuePartie;


public class ControleurPartie implements Controleur
{
	private Session session;
	private VuePartie vuePartie;
	private ControleurTablier controleurTablier;
	private ControleurPartie controleurPartie;
	private JFrame frame;
	private Controleur controleur;
	private int positionRevuePartie;
	private boolean isSensAvancer;
	private Timer timerRevuePartie;
	private CouleurCase couleurVideau;
	
	
	@Deprecated
	public  ControleurPartie(Partie partie)
	{
		controleurPartie = this;
		//testInitialisation();
		vuePartie = new VuePartie(partie);
		
		build();
		controleurTablier = new ControleurTablier(partie,vuePartie,this);
	}
	
	public  ControleurPartie(Session session,Controleur controleur)
	{
		couleurVideau = CouleurCase.VIDE;
		this.controleur = controleur;
		controleurPartie = this;
		this.session = session;
		//testInitialisation();
		vuePartie = new VuePartie(session.getPartieEnCours());
		timerRevuePartie= null;
		build();
		
		controleurTablier = new ControleurTablier(session.getPartieEnCours(),vuePartie,this);
		vuePartie.getPanelEnCoursVueBas().updateScore(session.getScores().get(session.getParametreSession().getJoueurBlanc()), session.getScores().get(session.getParametreSession().getJoueurNoir()));
	}

	private void build() {
		listenerBack();
		listenerLancerDe();
		listenerGetCoupPossibleJoueur1();
		listenerGetCoupPossibleJoueur2();
		listenerButtonVideau();
		listenerPartieSuivante();
		listenerInterrompreSession();
		listenerRevoirPartie();
		listenerBoutonRevuePartie();
		listenerTimer();
		listenerInterromprePartie();
		listenerBoutonAide();
	}
	
	public void listenerTimer()
	{
		timerRevuePartie = new Timer(1000, null);
		timerRevuePartie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (positionRevuePartie < session.getPartieEnCours().nbDeplacementHistorise())
				{
					if(!isSensAvancer)
					{
						isSensAvancer = true;
					}
					else
					{
						positionRevuePartie++;
					}
				
				Deplacement dep = session.getPartieEnCours().ProchainDeplacement(positionRevuePartie);
				if ( dep != null)
					vuePartie.getPanelTermineVueBas().getReplayBarr().goTo(dep,isSensAvancer);
				}
				else
				{
					timerRevuePartie.stop();
				}
				vuePartie.updateUI();
				vuePartie.getVueTablier().updateUI();
				vuePartie.getVueTablier().updateDes();
			}
			
		});
	}
	
private void listenerBoutonAide(){
		
		vuePartie.getPanelEnCoursVueBas().getHelp().addMouseListener(new MouseListener() {
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
			   
				URI uri = URI.create("http://www.google.fr/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0CEoQFjAA&url=http%3A%2F%2Ffr.wikipedia.org%2Fwiki%2FBackgammon&ei=R0XUUM6YL4yHhQelkYHYBQ&usg=AFQjCNEOHnc7riItGN_di3jAPILrXp9twA&sig2=uesTfMvnLMwYI8reGb-vWw&bvm=bv.1355534169,d.ZG4&cad=rja");
				try {
					Desktop.getDesktop().browse(uri);
				} catch (IOException e1) {
				
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	public void listenerBoutonRevuePartie()
	{
		vuePartie.getPanelTermineVueBas().getReplayBarr().getEndBtn().addMouseListener(new MouseListener(){

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
				if(timerRevuePartie  !=null)
					timerRevuePartie.stop();
				isSensAvancer = true;
				
				for(int i = positionRevuePartie;i<session.getPartieEnCours().nbDeplacementHistorise();i++)
				{
					positionRevuePartie++;
					Deplacement dep = session.getPartieEnCours().ProchainDeplacement(positionRevuePartie);
					if ( dep != null)
						vuePartie.getPanelTermineVueBas().getReplayBarr().goTo(dep,isSensAvancer);
				}
				
				vuePartie.getPanelTermineVueBas().getReplayBarr().goEnd();
				vuePartie.updateUI();
				vuePartie.getVueTablier().updateUI();
				vuePartie.getVueTablier().updateDes();
				
			}
			
		});
		
		vuePartie.getPanelTermineVueBas().getReplayBarr().getBeginBtn().addMouseListener(new MouseListener(){

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
				if(timerRevuePartie  !=null)
					timerRevuePartie.stop();
				positionRevuePartie =0;
				isSensAvancer = true;

				vuePartie.getPanelTermineVueBas().getReplayBarr().goBegin();
				session.getPartieEnCours().getTablier().reinitialisationCase();
				vuePartie.updateUI();
				vuePartie.getVueTablier().updateUI();
				vuePartie.getVueTablier().updateDes();
				
			}
			
		});
		
		vuePartie.getPanelTermineVueBas().getReplayBarr().getNextBtn().addMouseListener(new MouseListener(){

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
				if(timerRevuePartie  !=null)
					timerRevuePartie.stop();
				if (positionRevuePartie < session.getPartieEnCours().nbDeplacementHistorise())
				{
					if(!isSensAvancer)
					{
						isSensAvancer = true;
					}
					else
					{
						positionRevuePartie++;
					}
				
				Deplacement dep = session.getPartieEnCours().ProchainDeplacement(positionRevuePartie);
				if ( dep != null)
					vuePartie.getPanelTermineVueBas().getReplayBarr().goTo(dep,isSensAvancer);
				}	
				vuePartie.updateUI();
				vuePartie.getVueTablier().updateUI();
				vuePartie.getVueTablier().updateDes();
			}
			
		});
		
		vuePartie.getPanelTermineVueBas().getReplayBarr().getPrevBtn().addMouseListener(new MouseListener(){

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
				if(timerRevuePartie  !=null)
					timerRevuePartie.stop();
				if (positionRevuePartie > 0)
				{
					if(isSensAvancer)
					{
						isSensAvancer = false;
					}
					else
					{
						positionRevuePartie--;
					}
					Deplacement dep = session.getPartieEnCours().PrecedentDeplacement(positionRevuePartie);
					
					if ( dep != null)
						vuePartie.getPanelTermineVueBas().getReplayBarr().goTo(dep,isSensAvancer);
				}
				vuePartie.updateUI();
				vuePartie.getVueTablier().updateUI();
				vuePartie.getVueTablier().updateDes();
				
			}
			
		});
				
		
	}
	public void listenerRevoirPartie()
	{
		
			vuePartie.getPaneldroitrevoir().getUndo().addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent arg0) {}
				@Override
				public void mouseEntered(MouseEvent arg0) {}
				@Override
				public void mouseExited(MouseEvent arg0) {	}
				@Override
				public void mousePressed(MouseEvent arg0) {}
				@Override
				public void mouseReleased(MouseEvent arg0) {
					if (vuePartie.getEtat() != EtatSession.REPLAY)
					{
					positionRevuePartie =0;
					isSensAvancer = true;
					vuePartie.setEtat(EtatSession.REPLAY);
					vuePartie.getPanelTermineVueBas().getReplayBarr().setTours(session.getPartieEnCours().getHistoriqueToursJoueur());
					session.getPartieEnCours().getTablier().reinitialisationCase();
					vuePartie.updateUI();
					vuePartie.getVueTablier().updateUI();
					vuePartie.getVueTablier().updateDes();
					}
					else
					{
						
						if (timerRevuePartie != null)
							timerRevuePartie.start();
						
					}
			}
		});
		
	}
	
	public void listenerBack()
	{
		vuePartie.getPaneldroitencours().getBack().addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				session.getPartieEnCours().annulerDernierCoup();
				vuePartie.updateUI();
				vuePartie.getVueTablier().updateDes();
			}
			
		});
	}
	
	public void listenerLancerDe()
	{
		
		vuePartie.getPaneldroitencours().getDices().addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(session.getPartieEnCours().isTourFini() && !session.getPartieEnCours().isPartieFini())
				{
					session.getPartieEnCours().lancerDes();
					if (controleurTablier.getHorloge()!= null)
						controleurTablier.getHorloge().restart();
					if(!session.getPartieEnCours().hasCoupPossible())
					{
						vuePartie.afficherFenetreDemande("No possible move","");
						controleurTablier.changerTour();
					}
				}
				vuePartie.updateUI();
				vuePartie.getVueTablier().updateUI();
				vuePartie.getVueTablier().updateDes();
				
			}			
		});
	}
	
	public void listenerGetCoupPossibleJoueur1()
	{
		vuePartie.getPaneljoueur1().getCouppossible().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(session.getPartieEnCours().getParametreJeu().getJoueurBlanc().getNiveauAssistant() == NiveauAssistant.NON_UTILISE )
				{
					session.getPartieEnCours().getParametreJeu().getJoueurBlanc().setNiveauAssistant(NiveauAssistant.SIMPLE);
				}
				else
				{
					session.getPartieEnCours().getParametreJeu().getJoueurBlanc().setNiveauAssistant(NiveauAssistant.NON_UTILISE);
					vuePartie.getVueTablier().setPossibles(null);
					vuePartie.getVueTablier().updateUI();
				}
					vuePartie.getPaneljoueur1().updateData();
					
			}
		});
	}
	public void listenerGetCoupPossibleJoueur2()
	{
		vuePartie.getPaneljoueur2().getCouppossible().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(session.getPartieEnCours().getParametreJeu().getJoueurNoir().getNiveauAssistant() == NiveauAssistant.NON_UTILISE )
				{
					session.getPartieEnCours().getParametreJeu().getJoueurNoir().setNiveauAssistant(NiveauAssistant.SIMPLE);
				}
				else
				{
					session.getPartieEnCours().getParametreJeu().getJoueurNoir().setNiveauAssistant(NiveauAssistant.NON_UTILISE);
					vuePartie.getVueTablier().setPossibles((new ArrayList<Case>()));
					vuePartie.getVueTablier().updateUI();
				}
				vuePartie.getPaneljoueur2().updateData();
				
			}
		});
	}
	public void listenerButtonVideau()
	{
		
			if (!session.getParametreSession().isUtiliseVideau())
			{
				vuePartie.getPaneldroitencours().getVideau().setEnabled(false);
			}
			else
			{
			vuePartie.getPaneldroitencours().getVideau().addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {}
				@Override
				public void mouseExited(MouseEvent arg0) {}
				@Override
				public void mousePressed(MouseEvent arg0) {}
				@Override
				public void mouseReleased(MouseEvent arg0) {
					if ((couleurVideau == CouleurCase.VIDE || session.getPartieEnCours().getJoueurEnCour() != couleurVideau)&& (session.getPartieEnCours().isTourFini() && !session.getPartieEnCours().isPartieFini()))
					{
						SortedSet<String> hs = new ConcurrentSkipListSet<>();
						hs.add("No");
						hs.add("Yes");
						vuePartie.afficherFenetreDemande("Do you accept the doubling cube ?", hs).addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {

								String action = e.getActionCommand();
								if (action == "Yes")
								{
									couleurVideau = session.getPartieEnCours().getJoueurEnCour() ;
									session.getPartieEnCours().doublerVideau();

								}
								else if (action == "No")
								{
									finPartie();
									//if (!session.isSessionFini())
									//controleurPartie.nouvellePartie();

								}
								vuePartie.getPaneldroitencours().updateVideau();
							}

						});
					}}
			});

		}
	}
	
	public void listenerInterromprePartie()
	{
		vuePartie.getPaneldroitrevoir().getX_white().addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				SortedSet<String> hs = new ConcurrentSkipListSet<>();
				hs.add("Finish");
				hs.add("Cancel");
				hs.add("Save");
				vuePartie.afficherFenetreDemande("What do you want to do ?", hs).addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String action = e.getActionCommand();
						if (action == "Finish")
						{
							if (session.meilleurJoueur() != null)
							{
								session.finSession(session.meilleurJoueur());
							}
							((ControleurPrincipal)controleur).finSession();
							controleur.retour();
						}
						else if (action == "Cancel")
						{	
							
						}
						else if (action == "Save")
						{
							try {
								GestionDeSession gestion = GestionDeSession.getGestionDeSession();
								gestion.sauvegarder();
								
							} catch (IOException e1) {
								
								e1.printStackTrace();
							} catch (JDOMException e1) {
								
								e1.printStackTrace();
							}
							
								Profils profil = Profils.getProfils();
								profil.sauvegarder();

							controleur.retour();
							
						}
					}
				});
			}
		});
		
	}
	
	public void listenerInterrompreSession()
	{
		vuePartie.getPanelEnCoursVueBas().getX_black().addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				SortedSet<String> hs = new ConcurrentSkipListSet<>();
				hs.add("Finish");
				hs.add("Cancel");
				hs.add("Save");
				vuePartie.afficherFenetreDemande("What do you want to do ?", hs).addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String action = e.getActionCommand();
						if (action == "Finish")
						{
							if (session.meilleurJoueur() != null)
							{
								session.finSession(session.meilleurJoueur());
								
							}
							((ControleurPrincipal)controleur).finSession();
							controleur.retour();
						}
						else if (action == "Cancel")
						{
							
							
						}
						else if (action == "Save")
						{
							try {
								GestionDeSession gestion = GestionDeSession.getGestionDeSession();
								gestion.sauvegarder();
								
							} catch (IOException e1) {
								
								e1.printStackTrace();
							} catch (JDOMException e1) {
								
								e1.printStackTrace();
							}
							
								Profils profil = Profils.getProfils();
								profil.sauvegarder();

							controleur.retour();
							
						}
					}
				});
			}
		});
		
	}
	public void listenerPartieSuivante()
	{
		vuePartie.getPaneldroitrevoir().getNext().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(session.isSessionFini())
				{
					controleur.retour();
				((ControleurPrincipal)controleur).finSession();
					Profils profil = Profils.getProfils();
					profil.sauvegarder();
				}
				else
					controleurPartie.nouvellePartie();
					
			}		
			@Override
			public void mousePressed(MouseEvent arg0) {}		
			@Override
			public void mouseExited(MouseEvent arg0) {}		
			@Override
			public void mouseEntered(MouseEvent arg0) {}		
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		});
	}
	
	public void nouvellePartie()
	{
		session.nouvellePartie();
		session.LancerPartie();
		
		vuePartie.setPartie(session.getPartieEnCours());
		vuePartie.setEtat(EtatSession.EN_COURS);
		controleurTablier = new ControleurTablier(session.getPartieEnCours(),vuePartie,this);
		
		session.LancerPartie();
		vuePartie.updateUI();
	}
	
	public void finPartie() 
	{
		
		if (controleurTablier.getHorloge()!= null)
		{
			controleurTablier.getHorloge().stop();
			controleurTablier.getHorloge().setValue(0);
		}
		
		session.finPartie();
	
		vuePartie.getPanelEnCoursVueBas().updateScore(session.getScores().get(session.getParametreSession().getJoueurBlanc()), session.getScores().get(session.getParametreSession().getJoueurNoir()));
	
		if(session.verifFinSession())
		{
			session.finSession();
			vuePartie.getPaneldroitrevoir().getLabnext().setText("<html>Finish<br>Session");
			vuePartie.afficherFenetreDemande(session.getPartieEnCours().getParametreJeu().getJoueur(session.getPartieEnCours().getJoueurEnCour()).getPseudo() , " remporte la session!");
		}
		else
		{
			vuePartie.afficherFenetreDemande(session.getPartieEnCours().getParametreJeu().getJoueur(session.getPartieEnCours().getJoueurEnCour()).getPseudo() , " remporte la partie!");	
		}
		vuePartie.setEtat(EtatSession.TERMINEE);
		
	}
	
	public Partie getPartie() {
		return session.getPartieEnCours();
	}
	
	public VuePartie getVuePartie() {
		return vuePartie;
	}

	public ControleurTablier getControleurTablier() {
		return controleurTablier;
	}
	
	@SuppressWarnings("unused")
	private void testInitialisation(){
		ArrayList<Case> lCase = new ArrayList<Case>();
		
		lCase.add(new Case(CouleurCase.NOIR, 1, 1));
		lCase.add(new Case(CouleurCase.VIDE, 0, 2));
		lCase.add(new Case(CouleurCase.VIDE, 0, 3));
		lCase.add(new Case(CouleurCase.VIDE, 0, 4));
		lCase.add(new Case(CouleurCase.VIDE, 0, 5));
		lCase.add(new Case(CouleurCase.VIDE, 0, 6));
		lCase.add(new Case(CouleurCase.VIDE, 0, 7));
		lCase.add(new Case(CouleurCase.VIDE, 0, 8));
		lCase.add(new Case(CouleurCase.VIDE, 0, 9));
		lCase.add(new Case(CouleurCase.VIDE, 0, 10));
		lCase.add(new Case(CouleurCase.VIDE, 0, 11));
		lCase.add(new Case(CouleurCase.VIDE, 0, 12));
		lCase.add(new Case(CouleurCase.VIDE, 0, 13));
		lCase.add(new Case(CouleurCase.VIDE, 0, 14));
		lCase.add(new Case(CouleurCase.VIDE, 0, 15));
		lCase.add(new Case(CouleurCase.VIDE, 0, 16));
		lCase.add(new Case(CouleurCase.VIDE, 0, 17));
		lCase.add(new Case(CouleurCase.VIDE, 0, 18));
		lCase.add(new Case(CouleurCase.VIDE, 0, 19));
		lCase.add(new Case(CouleurCase.VIDE, 0, 20));
		lCase.add(new Case(CouleurCase.VIDE, 0, 21));
		lCase.add(new Case(CouleurCase.VIDE, 0, 22));
		lCase.add(new Case(CouleurCase.VIDE, 0, 23));
		lCase.add(new Case(CouleurCase.BLANC, 1, 24));

		session.getPartieEnCours().getTablier().initialiserCase(lCase);
		
		lCase = new ArrayList<Case>();
		lCase.add(new Case(CouleurCase.BLANC, 14, 25));
		lCase.add(new Case(CouleurCase.NOIR, 14, 0));
		session.getPartieEnCours().getTablier().initialiserCaseVictoire(lCase);
		
		lCase = new ArrayList<Case>();
		lCase.add(new Case(CouleurCase.BLANC, 0, 0));
		lCase.add(new Case(CouleurCase.NOIR, 0, 25));
		session.getPartieEnCours().getTablier().initialiserCaseBarre(lCase);	
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
	public void retour() {
		
		
	}
	
}
