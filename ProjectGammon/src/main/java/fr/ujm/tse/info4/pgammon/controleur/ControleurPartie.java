// 
//
//  @ Projet : Project Gammon
//  @ Fichier : ControleurPartie.java
//  @ Date : 12/12/2012
//  @ Auteurs : DONG Chuan, BONNETTO Benjamin, FRANCON Adrien, POTHELUNE Jean-Michel
//
//

package fr.ujm.tse.info4.pgammon.controleur;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

import fr.ujm.tse.info4.pgammon.models.Case;
import fr.ujm.tse.info4.pgammon.models.CouleurCase;
import fr.ujm.tse.info4.pgammon.models.EtatSession;
import fr.ujm.tse.info4.pgammon.models.NiveauAssistant;
import fr.ujm.tse.info4.pgammon.models.Partie;
import fr.ujm.tse.info4.pgammon.models.Session;
import fr.ujm.tse.info4.pgammon.vues.VuePartie;


public class ControleurPartie
{
	private Session session;
	private VuePartie vuePartie;
	private ControleurTablier controleurTablier;
	private ControleurPartie controleurPartie;
	
	//TODO Ce constructeur seras detruit
	public  ControleurPartie(Partie partie)
	{
		
		
		controleurPartie = this;
		//testInitialisation();
		vuePartie = new VuePartie(partie);
		build();
		controleurTablier = new ControleurTablier(partie,vuePartie);
	}
	
	public  ControleurPartie(Session session)
	{
		this.session = session;
	
		//testInitialisation();
		vuePartie = new VuePartie(session.getPartieEnCours());
		build();
		controleurTablier = new ControleurTablier(session.getPartieEnCours(),vuePartie);
	}

	private void build() {
		listenerBack();
		listenerLancerDe();
		listenerGetCoupPossibleJoueur1();
		listenerGetCoupPossibleJoueur2();
		listenerButtonVideau();
		listenerPartieSuivante();
		
		vuePartie.getPaneldroitencours().addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				if (session != null)
					if (session.getPartieEnCours().isPartieFini())
						session.finPartie();
			}
		});
	}
	
	public void listenerBack()
	{
		vuePartie.getPaneldroitencours().getBack().addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				session.getPartieEnCours().annulerDernierCoup();
				vuePartie.updateUI();
				vuePartie.getVueTablier().updateDes();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
	}
	
	public void listenerLancerDe()
	{
		vuePartie.getPaneldroitencours().getDices().addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(session.getPartieEnCours().isTourFini() && !session.getPartieEnCours().isPartieFini())
				{
					session.getPartieEnCours().lancerDes();
					if(!session.getPartieEnCours().hasCoupPossible())
					{
						//TODO affichage plus de coup possible
						controleurTablier.changerTour();
					}
				}
				vuePartie.updateUI();
				vuePartie.getVueTablier().updateUI();
				vuePartie.getVueTablier().updateDes();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}			
		});
	}
	
	public void listenerGetCoupPossibleJoueur1()
	{
		vuePartie.getPaneljoueur1().getCouppossible().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(session.getPartieEnCours().getParametreJeu().getJoueurBlanc().getNiveauAssistant() == NiveauAssistant.NON_UTILISE )
					session.getPartieEnCours().getParametreJeu().getJoueurBlanc().setNiveauAssistant(NiveauAssistant.SIMPLE);
				else
					session.getPartieEnCours().getParametreJeu().getJoueurBlanc().setNiveauAssistant(NiveauAssistant.NON_UTILISE);
				vuePartie.getPaneljoueur1().updateData();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
	}
	public void listenerGetCoupPossibleJoueur2()
	{
		vuePartie.getPaneljoueur2().getCouppossible().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(session.getPartieEnCours().getParametreJeu().getJoueurNoir().getNiveauAssistant() == NiveauAssistant.NON_UTILISE )
					session.getPartieEnCours().getParametreJeu().getJoueurNoir().setNiveauAssistant(NiveauAssistant.SIMPLE);
				else
					session.getPartieEnCours().getParametreJeu().getJoueurNoir().setNiveauAssistant(NiveauAssistant.NON_UTILISE);
				vuePartie.getPaneljoueur2().updateData();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
	}
	public void listenerButtonVideau()
	{
		vuePartie.getPaneldroitencours().getVideau().addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				SortedSet<String> hs = new ConcurrentSkipListSet<>();
				hs.add("Non");
				hs.add("Oui");
				vuePartie.afficherFenetreDemande("Accepter vous le videau ?", hs);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});

	}
	
	public void listenerPartieSuivante()
	{
		vuePartie.getPaneldroitrevoir().getNext().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {}		
			@Override
			public void mousePressed(MouseEvent arg0) {}		
			@Override
			public void mouseExited(MouseEvent arg0) {}		
			@Override
			public void mouseEntered(MouseEvent arg0) {}		
			@Override
			public void mouseClicked(MouseEvent arg0) {
				session.nouvellePartie();
				controleurTablier.nouvellePartie(session.getPartieEnCours());
				vuePartie.setPartie(session.getPartieEnCours());
				vuePartie.setEtat(EtatSession.EN_COURS);
				session.LancerPartie();
				vuePartie.updateUI();		
			}
		});
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
	
	private void testInitialisation(){
		ArrayList<Case> lCase = new ArrayList<Case>();
		
		lCase.add(new Case(CouleurCase.NOIR, 3, 1));
		lCase.add(new Case(CouleurCase.NOIR, 3, 2));
		lCase.add(new Case(CouleurCase.NOIR, 3, 3));
		lCase.add(new Case(CouleurCase.NOIR, 2, 4));
		lCase.add(new Case(CouleurCase.NOIR, 2, 5));
		lCase.add(new Case(CouleurCase.NOIR, 2, 6));
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
		lCase.add(new Case(CouleurCase.BLANC, 2, 20));
		lCase.add(new Case(CouleurCase.BLANC, 2, 21));
		lCase.add(new Case(CouleurCase.BLANC, 3, 22));
		lCase.add(new Case(CouleurCase.BLANC, 3, 23));
		lCase.add(new Case(CouleurCase.BLANC, 3, 24));

		session.getPartieEnCours().getTablier().initialiserCase(lCase);
		
		lCase = new ArrayList<Case>();
		lCase.add(new Case(CouleurCase.BLANC, 0, 25));
		lCase.add(new Case(CouleurCase.NOIR, 0, 0));
		session.getPartieEnCours().getTablier().initialiserCaseVictoire(lCase);
		
		lCase = new ArrayList<Case>();
		lCase.add(new Case(CouleurCase.BLANC, 2, 0));
		lCase.add(new Case(CouleurCase.NOIR, 0, 25));
		session.getPartieEnCours().getTablier().initialiserCaseBarre(lCase);	
	}
	
}
