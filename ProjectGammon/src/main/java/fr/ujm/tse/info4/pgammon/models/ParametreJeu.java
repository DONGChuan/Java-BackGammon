// 
//
//  @ Projet : Project Gammon
//  @ Fichier : ParametreJeu.java
//  @ Date : 12/12/2012
//  @ Auteurs : DONG Chuan, BONNETTO Benjamin, FRANCON Adrien, POTHELUNE Jean-Michel
//
//




package fr.ujm.tse.info4.pgammon.models;

import org.jdom2.Element;


public class ParametreJeu
{
	private int secondesParTour;
	private int nbrPartieGagnante;
	private boolean utiliseVideau;
	public Player joueurBlanc;
	public Player joueurNoir;
	
		
	public ParametreJeu(int secondesParTour, int nbrPartieGagnante, boolean utiliseVideau,Player joueurBlanc,Player joueurNoir )
	{
		this.secondesParTour = secondesParTour;
		this.nbrPartieGagnante = nbrPartieGagnante;
		this.utiliseVideau = utiliseVideau;
		this.joueurBlanc = joueurBlanc;
		this.joueurNoir = joueurNoir;
	}
	
	public ParametreJeu()
	{
	}
	

	public void sauvegarder(Element session)
	{
		Element parametres = new Element("parametres");
		session.addContent(parametres);
		
		Element secondesParTourXML = new Element("secondesParTour");
		secondesParTourXML.setText(String.valueOf(secondesParTour));
		parametres.addContent(secondesParTourXML);
	    
	    Element nbrPartieGagnanteXML = new Element("nbrPartieGagnante");
	    nbrPartieGagnanteXML.setText(String.valueOf(nbrPartieGagnante));
	    parametres.addContent(nbrPartieGagnanteXML);
	    
	    Element utiliseVideauXML = new Element("utiliseVideau");
	    if(utiliseVideau){
	    	 utiliseVideauXML.setText("1");
	    }else{
	    	 utiliseVideauXML.setText("0");
	    }
	    parametres.addContent(utiliseVideauXML);
	}
	
	public void charger(Element parametres)
	{
		 secondesParTour = Integer.valueOf(parametres.getChildText("secondesParTour"));
		 nbrPartieGagnante  = Integer.valueOf(parametres.getChildText("nbrPartieGagnante"));
		 switch(parametres.getChildText("utiliseVideau")){
			case "1":utiliseVideau = true;break;
			case "0":utiliseVideau = false;
		}
	}

	
	
	
	public int getSecondesParTour() {
		return secondesParTour;
	}

	public int getNbrPartieGagnante() {
		return nbrPartieGagnante;
	}

	public boolean isUtiliseVideau() {
		return utiliseVideau;
	}

	public Player getJoueurBlanc() {
		return joueurBlanc;
	}

	public Player getJoueurNoir() {
		return joueurNoir;
	}
	
	public Player getJoueur(CouleurCase couleurJoueur) {
		if (couleurJoueur == CouleurCase.BLANC)
			return joueurBlanc;
		else
			return joueurNoir;
	}
	public Player getAdversaireJoueur(CouleurCase couleurJoueur) {
		if (couleurJoueur == CouleurCase.BLANC)
			return joueurNoir;
		else
			return joueurBlanc;
	}

	public void setJoueurNoir(Player _joueurNoir){
		joueurNoir = _joueurNoir;
	}

	public void setJoueurBlanc(Player _joueurBlanc){
		joueurBlanc = _joueurBlanc;
	}

}
