// 
//
//  @ Projet : Project Gammon
//  @ Fichier : DeSixFaces.java
//  @ Date : 12/12/2012
//  @ Auteurs : DONG Chuan, BONNETTO Benjamin, FRANCON Adrien, POTHELUNE Jean-Michel
//
//


package fr.ujm.tse.info4.pgammon.models;

import org.jdom2.Element;


public class DeSixFaces
{
	/* VARIABLES */
	
	private int valeur;
	private boolean isUtilise;
	private CouleurCase couleurDe;
	
	/* FUNCTION */

	public DeSixFaces(CouleurCase couleurCase)
	{
		isUtilise = false;
		valeur = (int)(Math.random()*6+1);
		couleurDe = couleurCase;
	}
	
	public DeSixFaces(CouleurCase couleurCase, int valeur)
	{
		isUtilise = false;
		this.valeur = valeur;
		this.couleurDe = couleurCase;
	}
	
	public void utiliser()
	{
		isUtilise= true;
	}
	
	public void notUtiliser()
	{
		isUtilise= false;
	}
	
	
	public void sauvegarder(Element deSixFaces)
	{
		Element deSixFaceXML = new Element("deSixFace");
		deSixFaces.addContent(deSixFaceXML);
		
		Element valeurXML = new Element("valeur");
		valeurXML.setText(String.valueOf(valeur));
		deSixFaces.addContent(valeurXML);
		
		Element isUtiliseXML = new Element("isUtilise");
		if(isUtilise){
			isUtiliseXML.setText("oui");
		}else{
			isUtiliseXML.setText("non");
		}
		deSixFaces.addContent(isUtiliseXML);
		
		Element couleurDeXML = new Element("couleurDe");
		couleurDeXML.setText(String.valueOf(couleurDe));
		deSixFaces.addContent(couleurDeXML);
	
	}
	
	public void charger(Element desixfacesElement)
	{
		//TODO
		throw new UnsupportedOperationException();
	}
	
	
	
	/* GETTERS ET SETTERS */
	
	public int getValeur() {
		return valeur;
	}

	public boolean isUtilise() {
		return isUtilise;
	}

	public CouleurCase getCouleurDe() {
		return couleurDe;
	}
	
	
}
