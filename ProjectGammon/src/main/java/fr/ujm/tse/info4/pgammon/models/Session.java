// 
//
//  @ Projet : Project Gammon
//  @ Fichier : Session.java
//  @ Date : 12/12/2012
//  @ Auteurs : DONG Chuan, BONNETTO Benjamin, FRANCON Adrien, POTHELUNE Jean-Michel
//
//

package fr.ujm.tse.info4.pgammon.models;

import java.util.HashMap;

import org.jdom2.Attribute;
import org.jdom2.Element;

public class Session
{
	private int idSession;
	private int idMaxPartie;
	private Partie partieEnCours;

	private Player joueurGagnantSession;
	private CouleurCase couleurJoueurAnciennePartie;
	private HashMap<Player, Integer> scores;
	private EtatSession etatSession;
	private ParametreJeu parametreSession;
	
	public Session() {}
	
	public Session(int idSession, ParametreJeu parametreJeu)
	{
		couleurJoueurAnciennePartie = null;
		this.idSession = idSession;
		idMaxPartie=1;
		etatSession = EtatSession.CONFIGURATION;
		parametreSession = parametreJeu;
		scores = new HashMap<Player, Integer>();
		scores.put(parametreSession.getJoueurBlanc(),0);
		scores.put(parametreSession.getJoueurNoir(),0);
		joueurGagnantSession =null;
		nouvellePartie();
	}
	
	public void finSession()
	{
		joueurGagnantSession.getStat().ajouterVictoire();
		
		if (joueurGagnantSession == parametreSession.getJoueurBlanc())
			parametreSession.getJoueurNoir().getStat().ajouterDefaite();
		else
			parametreSession.getJoueurBlanc().getStat().ajouterDefaite();
	}
	
	public void nouvellePartie()
	{
		idMaxPartie ++;
		partieEnCours = new Partie(idMaxPartie,parametreSession);
	}
	
	public void LancerPartie()
	{
		if (couleurJoueurAnciennePartie == null)
			partieEnCours.lancerPremierePartie();
		else
			partieEnCours.lancerNouvellePartie(couleurJoueurAnciennePartie);
	}
	
	public void finPartie()
	{
		couleurJoueurAnciennePartie = partieEnCours.getPremierJoueur();
		int videau = partieEnCours.getVideau().getvideau();
		CouleurCase CouleurVictorieuse = partieEnCours.getJoueurEnCour();
		partieEnCours.finPartie();
		scores.put(parametreSession.getJoueur(CouleurVictorieuse),scores.get(parametreSession.getJoueur(CouleurVictorieuse))+videau);
	}
	
	public void finSession(Player joueurGagnantSession)
	{
		this.joueurGagnantSession = joueurGagnantSession;
		finSession();
	}
	
	public Player meilleurJoueur()
	{
		if (scores.get(parametreSession.joueurBlanc) > scores.get(parametreSession.joueurNoir))
			return parametreSession.joueurBlanc;
		else if (scores.get(parametreSession.joueurBlanc) < scores.get(parametreSession.joueurNoir))
			return parametreSession.joueurNoir;
		else
			return null;
	}

	public boolean verifFinSession()
	{
		if(parametreSession.getNbrPartieGagnante() == 0) 
			return false;
		if(scores.get(parametreSession.getJoueurBlanc()) >= parametreSession.getNbrPartieGagnante())
		{
			etatSession = EtatSession.TERMINEE;
			joueurGagnantSession = parametreSession.getJoueurBlanc();
			return true;
		}
		else if(scores.get(parametreSession.getJoueurNoir()) >= parametreSession.getNbrPartieGagnante())
		{
			etatSession = EtatSession.TERMINEE;
			joueurGagnantSession = parametreSession.getJoueurBlanc();
			return true;
		}
		return false;
	}
	
	/**
	 * Sauvegarder tous les infos sous cette racine 
	 * @param racine c'est la racine "Sessions" dans le fichier XML
	 */
	public void sauvegarder(Element racine)
	{
		Element session = new Element("session");
	    racine.addContent(session);
	    
	    Attribute idsession = new Attribute("id",String.valueOf(idSession));
	    session.setAttribute(idsession);
	    
		    Element etatSessionXML = new Element("etatSession");
		    etatSessionXML.setText(String.valueOf(etatSession));
		    session.addContent(etatSessionXML);
		    
		    Element idMaxPartieXML = new Element("idMaxPartie");
		    idMaxPartieXML.setText(String.valueOf(idMaxPartie));
		    session.addContent(idMaxPartieXML);
		    
		    Element couleurJoueurAnciennePartieXML = new Element("couleurJoueurAnciennePartie");
		    couleurJoueurAnciennePartieXML.setText(String.valueOf(couleurJoueurAnciennePartie));
		    session.addContent(couleurJoueurAnciennePartieXML);

		    Element joueursXML = new Element("joueurs");
		    session.addContent(joueursXML);
		    
			    Element joueurNoirXML = new Element("joueurNoir");
			    joueursXML.addContent(joueurNoirXML);
			    
			    Attribute idNoir = new Attribute("id",String.valueOf(parametreSession.getJoueurNoir().getId()));
			    joueurNoirXML.setAttribute(idNoir);
			    
				    Element scoreNoirXML = new Element("score");
				    scoreNoirXML.setText(String.valueOf(scores.get(parametreSession.getJoueurNoir())));
				    joueurNoirXML.addContent(scoreNoirXML);	
				    
				Element joueurBlancXML = new Element("joueurBlanc");
				joueursXML.addContent(joueurBlancXML);
				    
				Attribute idBlanc = new Attribute("id",String.valueOf(parametreSession.getJoueurBlanc().getId()));
				joueurBlancXML.setAttribute(idBlanc);
				    
					Element scoreBlancXML = new Element("score");
					scoreBlancXML.setText(String.valueOf(scores.get(parametreSession.getJoueurBlanc())));
					joueurBlancXML.addContent(scoreBlancXML);
		    
		    parametreSession.sauvegarder(session);
		    
		    partieEnCours.sauvegarder(session);
	}
	
	/**
	 * Charger tous les infos sous cette racine 
	 * @param racine c'est la racine "Sessions" dans le fichier XML
	 */
	public boolean charger(Element racine)
	{
		idSession = Integer.valueOf(racine.getChild("session").getAttributeValue("id"));
		switch(racine.getChild("session").getChildText("etatSession")){
			case "CONFIGURATION":etatSession = EtatSession.CONFIGURATION;break;
			case "EN_COURS":etatSession =  EtatSession.EN_COURS;break;
			case "TERMINEE":etatSession =  EtatSession.TERMINEE;
		}
		idMaxPartie = Integer.valueOf(racine.getChild("session").getChildText("idMaxPartie"));
		switch(racine.getChild("session").getChildText("couleurJoueurAnciennePartie")){
			case "BLANC":couleurJoueurAnciennePartie = CouleurCase.BLANC;break;
			case "NOIR":couleurJoueurAnciennePartie =  CouleurCase.NOIR;break;
			case "VIDE":couleurJoueurAnciennePartie =  CouleurCase.VIDE;
		}
			
		int tmpID = Integer.valueOf(racine.getChild("session").getChild("joueurs").getChild("joueurNoir").getAttributeValue("id"));
			
		scores = new HashMap<Player,Integer>();
			
		Profils profil = Profils.getProfils();
		Player JoueurNoir = profil.getJoueur(tmpID);
		if (JoueurNoir == null)
			return false;
		
		scores.put(JoueurNoir,Integer.valueOf(racine.getChild("session").getChild("joueurs").getChild("joueurNoir").getChildText("score")));
			
		tmpID = Integer.valueOf(racine.getChild("session").getChild("joueurs").getChild("joueurBlanc").getAttributeValue("id"));
		Player JoueurBlanc = profil.getJoueur(tmpID);
		if (JoueurBlanc == null)
			return false;
		scores.put(JoueurBlanc,Integer.valueOf(racine.getChild("session").getChild("joueurs").getChild("joueurBlanc").getChildText("score")));
			
		parametreSession = new ParametreJeu();
			
		parametreSession.charger(racine.getChild("session").getChild("parametres"));
		parametreSession.setJoueurBlanc(JoueurBlanc);
		parametreSession.setJoueurNoir(JoueurNoir);
			
		partieEnCours = new Partie(parametreSession);
		partieEnCours.charger(racine.getChild("session").getChild("partie"));
		return true;
	}

	public int getIdSession() {
		return idSession;
	}
	
	public Partie getPartieEnCours() {
		return partieEnCours;
	}

	public Player getJoueurGagnantSession() {
		return joueurGagnantSession;
	}
	
	public HashMap<Player, Integer> getScores() {
		return scores;
	}

	public boolean isSessionFini()
	{	
		if (joueurGagnantSession == null)
			return false;
		else
			return true;	
	}

	public ParametreJeu getParametreSession() {
		return parametreSession;
	}
}

